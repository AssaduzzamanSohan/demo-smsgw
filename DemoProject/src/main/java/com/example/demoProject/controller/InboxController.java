package com.example.demoProject.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoProject.model.Inbox;
import com.example.demoProject.service.InboxService;

@RestController
@RequestMapping(value = "/api/inbox", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class InboxController {

	private final InboxService inboxService;

	public InboxController(InboxService inboxService) {
		this.inboxService = inboxService;
	}

	@GetMapping("/findAll")
	public List<Inbox> findAll() {
		return inboxService.findAll();
	}

	@GetMapping("/findById/{id}")
	public Inbox findById(@PathVariable Long id) {
		return inboxService.findById(id);
	}
}
