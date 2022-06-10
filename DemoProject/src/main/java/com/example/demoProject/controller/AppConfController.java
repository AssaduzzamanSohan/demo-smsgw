package com.example.demoProject.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoProject.model.AppConf;
import com.example.demoProject.service.AppConfService;

@RestController
@RequestMapping(value = "/api/app-conf", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class AppConfController {

	private final AppConfService appConfService;

	public AppConfController(AppConfService appConfService) {
		this.appConfService = appConfService;
	}

	@GetMapping("/findAll")
	public List<AppConf> findAll() {
		return appConfService.findAll();
	}

	@GetMapping("/findById/{id}")
	public AppConf findById(@PathVariable int id) {
		return appConfService.findById(id);
	}

	@GetMapping("/startDemoWork")
	public void startDemoWork() {
		appConfService.startDemoWork();
	}
}
