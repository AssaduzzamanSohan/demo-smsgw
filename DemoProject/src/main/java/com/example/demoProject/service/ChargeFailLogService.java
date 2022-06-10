package com.example.demoProject.service;

import java.util.List;

import com.example.demoProject.model.ChargeFailLog;
import com.example.demoProject.model.Inbox;
import com.example.demoProject.model.KeywordDetails;

public interface ChargeFailLogService {

	List<ChargeFailLog> findAll();

	ChargeFailLog findById(Long id);

	ChargeFailLog formEntiry(Inbox it, KeywordDetails keywordDetails, String[] unlockCodeParts, String chargeResult,
			String[] chargeResultParts);

	ChargeFailLog save(ChargeFailLog chargeFailLog);

}
