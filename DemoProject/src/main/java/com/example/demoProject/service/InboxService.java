package com.example.demoProject.service;

import java.util.List;

import com.example.demoProject.model.Inbox;

public interface InboxService {

	List<Inbox> findAll();

	Inbox findById(Long id);

	List<Inbox> findByStatus(int dataPerPage, char c);

	Inbox save(Inbox inbox);
}
