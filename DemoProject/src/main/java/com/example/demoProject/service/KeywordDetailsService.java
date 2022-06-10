package com.example.demoProject.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.example.demoProject.model.KeywordDetails;

public interface KeywordDetailsService {

	List<KeywordDetails> findAll();

	KeywordDetails findById(int id);

	ConcurrentHashMap<String, KeywordDetails> getKeywordMap();

	void refreshKeywordMap();

}
