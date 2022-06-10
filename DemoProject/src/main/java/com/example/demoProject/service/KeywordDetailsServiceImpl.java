package com.example.demoProject.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demoProject.exception.GenericNotFoundException;
import com.example.demoProject.model.KeywordDetails;
import com.example.demoProject.repository.KeywordDetailsRepository;

@Service
public class KeywordDetailsServiceImpl implements KeywordDetailsService {

	private final KeywordDetailsRepository keywordDetailsRepository;

	private ConcurrentHashMap<String, KeywordDetails> keywordMap = new ConcurrentHashMap<>();

	public KeywordDetailsServiceImpl(KeywordDetailsRepository keywordDetailsRepository) {
		this.keywordDetailsRepository = keywordDetailsRepository;
	}

	@PostConstruct
	public void initialize() throws Exception {
		initKeyWordMap();
	}

	@Override
	public List<KeywordDetails> findAll() {
		return keywordDetailsRepository.findAll();
	}

	@Override
	public KeywordDetails findById(int id) {
		return keywordDetailsRepository.findById(id).orElseThrow(
				() -> new GenericNotFoundException(String.valueOf(id), "KeywordDetails not found with id"));
	}

	@Override
	public ConcurrentHashMap<String, KeywordDetails> getKeywordMap() {
		return keywordMap;
	}

	/**
	 * By this method we will keep up-to date, keywordMap or Redis DB where we are
	 * keeping this details for fast access
	 */
	@Override
	public void refreshKeywordMap() {
		initKeyWordMap();
	}

	/**
	 * Keeping in java will take memory, doing this to improve performance, We can
	 * keep data also in Redis DB to improve performance.
	 */
	private void initKeyWordMap() {
		List<KeywordDetails> keywordList = keywordDetailsRepository.findAll();
		keywordMap = keywordList.parallelStream().collect(Collectors.toMap(KeywordDetails::getKeyword,
				Function.identity(), (existing, replacement) -> existing, ConcurrentHashMap::new));
	}

}
