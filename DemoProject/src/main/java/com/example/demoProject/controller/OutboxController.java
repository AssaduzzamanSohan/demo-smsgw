package com.example.demoProject.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoProject.model.Outbox;
import com.example.demoProject.service.OutboxService;

@RestController
@RequestMapping(value = "/api/outbox", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class OutboxController {

	private final OutboxService outboxService;

	public OutboxController(OutboxService outboxService) {
		this.outboxService = outboxService;
	}

	@GetMapping("/findAll")
	public List<Outbox> findAll() {
		return outboxService.findAll();
	}

	@GetMapping("/findById/{id}")
	public Outbox findById(@PathVariable Long id) {
		return outboxService.findById(id);
	}
}
