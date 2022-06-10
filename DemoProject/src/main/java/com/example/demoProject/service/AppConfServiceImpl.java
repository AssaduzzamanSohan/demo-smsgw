package com.example.demoProject.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.demoProject.exception.GenericNotFoundException;
import com.example.demoProject.model.AppConf;
import com.example.demoProject.model.ChargeFailLog;
import com.example.demoProject.model.ChargeSuccessLog;
import com.example.demoProject.model.Inbox;
import com.example.demoProject.model.KeywordDetails;
import com.example.demoProject.repository.AppConfRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppConfServiceImpl implements AppConfService {

	private final AppConfRepository appConfRepository;
	private final KeywordDetailsService keywordDetailsService;
	private final InboxService inboxService;
	private final ChargeSuccessLogService chargeSuccessLogService;
	private final ChargeFailLogService chargeFailLogService;
	private final RestTemplate restTemplate;

	public AppConfServiceImpl(AppConfRepository appConfRepository, InboxService inboxService,
			KeywordDetailsService keywordDetailsService, RestTemplate restTemplate, ChargeConfService chargeConfService,
			ChargeSuccessLogService chargeSuccessLogService, ChargeFailLogService chargeFailLogService) {
		this.appConfRepository = appConfRepository;
		this.inboxService = inboxService;
		this.keywordDetailsService = keywordDetailsService;
		this.restTemplate = restTemplate;
		this.chargeSuccessLogService = chargeSuccessLogService;
		this.chargeFailLogService = chargeFailLogService;
	}

	@Override
	public List<AppConf> findAll() {
		return appConfRepository.findAll();
	}

	@Override
	public AppConf findById(int id) {
		return appConfRepository.findById(id)
				.orElseThrow(() -> new GenericNotFoundException(String.valueOf(id), "AppConf not found with id"));
	}

	public void startDemoWork() {

		while (true) {

			// No identification added of data which will be used to do the task, so taking
			// the first data from the table
			Page<AppConf> pagedResult = appConfRepository.findAll(PageRequest.of(0, 1, Sort.by("id").ascending()));

			List<AppConf> appConfList = pagedResult.getContent();

			if (appConfList == null || appConfList.size() == 0) {
				log.info("No data found in app_conf table, exiting from processing loop");
				break;
			}

			if (appConfList.get(0).getStatus() == 1) {
				processInbox(appConfList.get(0).getNumberOfRow());
			} else {
				log.info("status found[{}], we will process data only if status is 1, exiting from processing loop",
						appConfList.get(0).getStatus());
				break;
			}
		}
	}

	private void processInbox(int dataPerPage) {
		log.info("Started processing Inbox at {}", LocalDateTime.now());

		List<Inbox> inboxList = inboxService.findByStatus(dataPerPage, 'N');

		inboxList.parallelStream().forEach(it -> {
			try {

				log.info("Processing inbox data of id -> smsText [{} -> {}]", it.getId(), it.getSmsText());

				String keyword = it.getSmsText().split(" ")[0];

				if (!keywordDetailsService.getKeywordMap().containsKey(keyword)) {

					updateInbox(it, 'F');

				} else {

					KeywordDetails keywordDetails = keywordDetailsService.getKeywordMap().get(keyword);

					String unlockUrl = buildUnlockCodeUrl(keywordDetails, it);
					String unlockCode = sendGetRequest(unlockUrl);

					if (unlockCode != null && !unlockCode.isEmpty()) {

						// at 0 -> charge code, at 1 -> amount
						String[] unlockCodeParts = unlockCode.split("::");

						String chargeUrl = buildChargeUrl(keywordDetails, it, unlockCodeParts);
						String chargeResult = sendGetRequest(chargeUrl);

						if (chargeResult != null && !chargeResult.isEmpty()) {

							// at 0 -> transaction id, at 1 -> success/fail code, at 2 -> description
							String[] chargeResultParts = chargeResult.split("::");

							// success code = 100
							if (chargeResultParts[1].matches("100")) {

								ChargeSuccessLog chargeSuccessLog = chargeSuccessLogService.formEntiry(it,
										keywordDetails, unlockCodeParts, chargeResult, chargeResultParts);

								chargeSuccessLogService.save(chargeSuccessLog);

								updateInbox(it, 'S');
							} else {

								ChargeFailLog changeFailLog = chargeFailLogService.formEntiry(it, keywordDetails,
										unlockCodeParts, chargeResult, chargeResultParts);

								chargeFailLogService.save(changeFailLog);

								updateInbox(it, 'F');
							}

						} else {
							updateInbox(it, 'F');
						}
					} else {
						updateInbox(it, 'F');
					}
				}
			} catch (Exception e) {
				log.error("Error processing inbox [{}] {}", it.toString(), e.getMessage());
			}
		});

		log.info("Finished processing Inbox at {}", LocalDateTime.now());
	}

	private void updateInbox(Inbox inbox, char status) {
		inbox.setStatus(status);
		inboxService.save(inbox);
	}

	private String buildUnlockCodeUrl(KeywordDetails keywordDetails, Inbox it) {
		String unlockUrl = keywordDetails.getUnlockUrl();
		unlockUrl = unlockUrl.replace("<sms_text>", it.getSmsText());

		return unlockUrl;
	}

	private String buildChargeUrl(KeywordDetails keywordDetails, Inbox it, String[] unlockCodeParts) {
		String chargeUrl = keywordDetails.getChargeUrl();
		chargeUrl = chargeUrl.replace("<charge_code>", unlockCodeParts[0]).replace("<msisdn>", it.getMsisdn());

		return chargeUrl;
	}

	private String sendGetRequest(String url) {
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			log.info("Http error {}", e.getMessage(), e);
		} catch (Exception e) {
			log.info("Unknown error {}", e.getMessage(), e);
		}

		return null;
	}

}
