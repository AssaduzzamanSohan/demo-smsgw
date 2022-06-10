package com.example.demoProject.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoProject.model.ChargeFailLog;
import com.example.demoProject.service.ChargeFailLogService;

@RestController
@RequestMapping(value = "/api/charge-fail-log", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class ChargeFailLogController {

	private final ChargeFailLogService chargeFailLogService;

	public ChargeFailLogController(ChargeFailLogService chargeFailLogService) {
		this.chargeFailLogService = chargeFailLogService;
	}

	@GetMapping("/findAll")
	public List<ChargeFailLog> findAll() {
		return chargeFailLogService.findAll();
	}

	@GetMapping("/findById/{id}")
	public ChargeFailLog findById(@PathVariable Long id) {
		return chargeFailLogService.findById(id);
	}
}
