package com.example.demoProject.service;

import java.util.List;

import com.example.demoProject.model.Outbox;

public interface OutboxService {

	List<Outbox> findAll();

	Outbox findById(Long id);

}
