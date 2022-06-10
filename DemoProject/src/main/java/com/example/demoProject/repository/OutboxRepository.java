package com.example.demoProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoProject.model.Outbox;

public interface OutboxRepository extends JpaRepository<Outbox, Long> {

}
