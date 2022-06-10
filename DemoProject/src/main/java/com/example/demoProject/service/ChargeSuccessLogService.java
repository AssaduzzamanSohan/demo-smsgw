package com.example.demoProject.service;

import java.util.List;

import com.example.demoProject.model.ChargeSuccessLog;
import com.example.demoProject.model.Inbox;
import com.example.demoProject.model.KeywordDetails;

public interface ChargeSuccessLogService {

	List<ChargeSuccessLog> findAll();

	ChargeSuccessLog findById(Long id);

	ChargeSuccessLog formEntiry(Inbox it, KeywordDetails keywordDetails, String[] unlockCodeParts, String chargeResult,
			String[] chargeResultParts);

	ChargeSuccessLog save(ChargeSuccessLog chargeSuccessLog);

}
