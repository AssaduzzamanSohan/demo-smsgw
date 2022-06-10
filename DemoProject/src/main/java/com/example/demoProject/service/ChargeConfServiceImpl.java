package com.example.demoProject.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demoProject.exception.GenericNotFoundException;
import com.example.demoProject.model.ChargeConf;
import com.example.demoProject.repository.ChargeConfRepository;

@Service
public class ChargeConfServiceImpl implements ChargeConfService {

	private final ChargeConfRepository chargeConfRepository;

	private ConcurrentHashMap<String, ChargeConf> chargeCodeMap = new ConcurrentHashMap<>();

	public ChargeConfServiceImpl(ChargeConfRepository chargeConfRepository) {
		this.chargeConfRepository = chargeConfRepository;
	}

	@PostConstruct
	public void initialize() throws Exception {
		initChargeCodeMap();
	}

	@Override
	public List<ChargeConf> findAll() {
		return chargeConfRepository.findAll();
	}

	@Override
	public ChargeConf findById(int id) {
		return chargeConfRepository.findById(id)
				.orElseThrow(() -> new GenericNotFoundException(String.valueOf(id), "ChargeConf not found with id"));
	}

	@Override
	public ConcurrentHashMap<String, ChargeConf> getChargeCodeMap() {
		return chargeCodeMap;
	}

	/**
	 * By this method we will keep up-to date, keywordMap or Redis DB where we are
	 * keeping this details for fast access
	 */
	@Override
	public void refreshChargeCodeMap() {
		initChargeCodeMap();
	}

	/**
	 * Keeping in java will take memory, doing this to improve performance, We can
	 * keep data also in Redis DB to improve performance.
	 */
	private void initChargeCodeMap() {
		List<ChargeConf> chargeConfList = chargeConfRepository.findAll();
		chargeCodeMap = chargeConfList.parallelStream().collect(Collectors.toMap(ChargeConf::getChargeCode,
				Function.identity(), (existing, replacement) -> existing, ConcurrentHashMap::new));
	}

}
