package com.example.demoProject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoProject.exception.GenericNotFoundException;
import com.example.demoProject.model.Inbox;
import com.example.demoProject.repository.InboxRepository;

@Service
public class InboxServiceImpl implements InboxService {

	private final InboxRepository inboxRepository;

	public InboxServiceImpl(InboxRepository inboxRepository) {
		this.inboxRepository = inboxRepository;
	}

	@Override
	public List<Inbox> findAll() {
		return inboxRepository.findAll();
	}

	@Override
	public Inbox findById(Long id) {
		return inboxRepository.findById(id)
				.orElseThrow(() -> new GenericNotFoundException(String.valueOf(id), "Inbox not found with id"));
	}

	@Override
	public List<Inbox> findByStatus(int dataPerPage, char c) {
		Pageable pageable = PageRequest.of(0, dataPerPage, Sort.by("id").ascending());

		Page<Inbox> pagedResult = inboxRepository.findByStatus(pageable, c);

		return pagedResult.getContent();
	}

	@Override
	public Inbox save(Inbox inbox) {
		return inboxRepository.save(inbox);
	}

}
