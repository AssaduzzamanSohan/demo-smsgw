package com.example.demoProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demoProject.exception.GenericNotFoundException;
import com.example.demoProject.model.ChargeConf;
import com.example.demoProject.model.ChargeSuccessLog;
import com.example.demoProject.model.Inbox;
import com.example.demoProject.model.KeywordDetails;
import com.example.demoProject.repository.ChargeSuccessLogRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChargeSuccessLogServiceImpl implements ChargeSuccessLogService {

	private final ChargeSuccessLogRepository chargeSuccessLogRepository;
	private final ChargeConfService chargeConfService;

	public ChargeSuccessLogServiceImpl(ChargeSuccessLogRepository chargeSuccessLogRepository,
			ChargeConfService chargeConfService) {
		this.chargeSuccessLogRepository = chargeSuccessLogRepository;
		this.chargeConfService = chargeConfService;
	}

	@Override
	public List<ChargeSuccessLog> findAll() {
		return chargeSuccessLogRepository.findAll();
	}

	@Override
	public ChargeSuccessLog findById(Long id) {
		return chargeSuccessLogRepository.findById(id).orElseThrow(
				() -> new GenericNotFoundException(String.valueOf(id), "ChargeSuccessLog not found with id"));
	}

	@Override
	public ChargeSuccessLog save(ChargeSuccessLog chargeSuccessLog) {
		return chargeSuccessLogRepository.save(chargeSuccessLog);
	}

	@Override
	public ChargeSuccessLog formEntiry(Inbox it, KeywordDetails keywordDetails, String[] unlockCodeParts,
			String chargeResult, String[] chargeResultParts) {

		ChargeSuccessLog chargeSuccessLog = new ChargeSuccessLog();

		chargeSuccessLog.setSmsId(it.getId());
		chargeSuccessLog.setMsisdn(it.getMsisdn());
		chargeSuccessLog.setKeywordId(keywordDetails.getId());
		chargeSuccessLog.setAmount(Integer.parseInt(unlockCodeParts[1]));
		chargeSuccessLog
				.setAmountWithVat(calculateAmountWithVat(unlockCodeParts[0], Integer.parseInt(unlockCodeParts[1])));
		chargeSuccessLog.setValidity(getValidity(unlockCodeParts[0]));
		chargeSuccessLog.setCharge_id(getChargeId(unlockCodeParts[0]));
		chargeSuccessLog.setTidRef(chargeResultParts[0]);
		chargeSuccessLog.setResponse(chargeResult);

		return chargeSuccessLog;
	}

	private float calculateAmountWithVat(String chargeCode, int amount) {
		if (!chargeConfService.getChargeCodeMap().containsKey(chargeCode)) {
			log.info("Charge code details not found for {}", chargeCode);
			// TODO: if not found then setting -1, this is a business call should take from
			// higher
			// management
			return -1;
		}

		ChargeConf chargeConf = chargeConfService.getChargeCodeMap().get(chargeCode);

		return (chargeConf.getPriceWithVat() * amount) / chargeConf.getPrice();
	}

	private int getValidity(String chargeCode) {
		if (!chargeConfService.getChargeCodeMap().containsKey(chargeCode)) {
			log.info("Charge code details not found for {}", chargeCode);
			// if not found then setting -1, this is a business call should take from higher
			// management
			return -1;
		}

		return chargeConfService.getChargeCodeMap().get(chargeCode).getValidity();
	}

	private int getChargeId(String chargeCode) {
		if (!chargeConfService.getChargeCodeMap().containsKey(chargeCode)) {
			log.info("Charge code details not found for {}", chargeCode);
			// if not found then setting -1, this is a business call should take from higher
			// management
			return -1;
		}

		return chargeConfService.getChargeCodeMap().get(chargeCode).getId();
	}

}
