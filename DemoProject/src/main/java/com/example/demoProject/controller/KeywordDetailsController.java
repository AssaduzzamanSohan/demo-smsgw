package com.example.demoProject.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoProject.model.KeywordDetails;
import com.example.demoProject.service.KeywordDetailsService;

@RestController
@RequestMapping(value = "/api/keyword-details", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class KeywordDetailsController {

	private final KeywordDetailsService keywordDetailsService;

	public KeywordDetailsController(KeywordDetailsService keywordDetailsService) {
		this.keywordDetailsService = keywordDetailsService;
	}

	@GetMapping("/findAll")
	public List<KeywordDetails> findAll() {
		return keywordDetailsService.findAll();
	}

	@GetMapping("/findById/{id}")
	public KeywordDetails findById(@PathVariable int id) {
		return keywordDetailsService.findById(id);
	}

	@GetMapping("/refreshKeywordMap")
	public void refreshKeywordMap() {
		keywordDetailsService.refreshKeywordMap();
	}
}
