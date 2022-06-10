package com.example.demoProject.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoProject.model.ChargeConf;
import com.example.demoProject.service.ChargeConfService;

@RestController
@RequestMapping(value = "/api/charge-conf", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class ChargeConfController {

	private final ChargeConfService chargeConfService;

	public ChargeConfController(ChargeConfService chargeConfService) {
		this.chargeConfService = chargeConfService;
	}

	@GetMapping("/findAll")
	public List<ChargeConf> findAll() {
		return chargeConfService.findAll();
	}

	@GetMapping("/findById/{id}")
	public ChargeConf findById(@PathVariable int id) {
		return chargeConfService.findById(id);
	}

	@GetMapping("/refreshChargeCodeMap")
	public void refreshChargeCodeMap() {
		chargeConfService.refreshChargeCodeMap();
	}
}
