package com.example.demoProject.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
public class AppController {

	@GetMapping("/test/api")
	public String testApi() {
		log.info("Api call landed");
		return "API is working.";
	}

}
