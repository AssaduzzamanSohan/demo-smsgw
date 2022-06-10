package com.example.demoProject.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoProject.model.ChargeSuccessLog;
import com.example.demoProject.service.ChargeSuccessLogService;

@RestController
@RequestMapping(value = "/api/charge-success-log", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class ChargeSuccessLogController {

	private final ChargeSuccessLogService chargeSuccessLogService;

	public ChargeSuccessLogController(ChargeSuccessLogService chargeSuccessLogService) {
		this.chargeSuccessLogService = chargeSuccessLogService;
	}

	@GetMapping("/findAll")
	public List<ChargeSuccessLog> findAll() {
		return chargeSuccessLogService.findAll();
	}

	@GetMapping("/findById/{id}")
	public ChargeSuccessLog findById(@PathVariable Long id) {
		return chargeSuccessLogService.findById(id);
	}
}
