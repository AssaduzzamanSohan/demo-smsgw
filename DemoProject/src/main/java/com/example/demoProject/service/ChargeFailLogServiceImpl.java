package com.example.demoProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demoProject.exception.GenericNotFoundException;
import com.example.demoProject.model.ChargeFailLog;
import com.example.demoProject.model.Inbox;
import com.example.demoProject.model.KeywordDetails;
import com.example.demoProject.repository.ChargeFailLogRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChargeFailLogServiceImpl implements ChargeFailLogService {

	private final ChargeFailLogRepository chargeFailLogRepository;
	private final ChargeConfService chargeConfService;

	public ChargeFailLogServiceImpl(ChargeFailLogRepository chargeFailLogRepository,
			ChargeConfService chargeConfService) {
		this.chargeFailLogRepository = chargeFailLogRepository;
		this.chargeConfService = chargeConfService;
	}

	@Override
	public List<ChargeFailLog> findAll() {
		return chargeFailLogRepository.findAll();
	}

	@Override
	public ChargeFailLog findById(Long id) {
		return chargeFailLogRepository.findById(id)
				.orElseThrow(() -> new GenericNotFoundException(String.valueOf(id), "ChargeFailLog not found with id"));
	}

	@Override
	public ChargeFailLog formEntiry(Inbox it, KeywordDetails keywordDetails, String[] unlockCodeParts,
			String chargeResult, String[] chargeResultParts) {

		ChargeFailLog chargeFailLog = new ChargeFailLog();

		chargeFailLog.setSmsId(it.getId());
		chargeFailLog.setMsisdn(it.getMsisdn());
		chargeFailLog.setKeywordId(keywordDetails.getId());
		chargeFailLog.setAmount(Integer.parseInt(unlockCodeParts[1]));
		chargeFailLog.setCharge_id(getChargeId(unlockCodeParts[0]));
		chargeFailLog.setFailCode(Integer.parseInt(chargeResultParts[1]));
		chargeFailLog.setTidRef(chargeResultParts[0]);
		chargeFailLog.setResponse(chargeResult);

		return chargeFailLog;
	}

	@Override
	public ChargeFailLog save(ChargeFailLog chargeFailLog) {
		return chargeFailLogRepository.save(chargeFailLog);
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
