package com.example.demoProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demoProject.exception.GenericNotFoundException;
import com.example.demoProject.model.Outbox;
import com.example.demoProject.repository.OutboxRepository;

@Service
public class OutboxServiceImpl implements OutboxService {

	private final OutboxRepository outboxRepository;

	public OutboxServiceImpl(OutboxRepository outboxRepository) {
		this.outboxRepository = outboxRepository;
	}

	@Override
	public List<Outbox> findAll() {
		return outboxRepository.findAll();
	}

	@Override
	public Outbox findById(Long id) {
		return outboxRepository.findById(id)
				.orElseThrow(() -> new GenericNotFoundException(String.valueOf(id), "Outbox not found with id"));
	}

}
