package com.example.demoProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoProject.model.Inbox;

public interface InboxRepository extends JpaRepository<Inbox, Long> {

	Page<Inbox> findByStatus(Pageable pageable, char status);

}
