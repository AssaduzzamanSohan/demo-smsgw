package com.example.demoProject.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.example.demoProject.model.ChargeConf;

public interface ChargeConfService {

	List<ChargeConf> findAll();

	ChargeConf findById(int id);

	ConcurrentHashMap<String, ChargeConf> getChargeCodeMap();

	void refreshChargeCodeMap();

}
