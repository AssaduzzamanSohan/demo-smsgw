package com.example.demoProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoProject.model.ChargeFailLog;

public interface ChargeFailLogRepository extends JpaRepository<ChargeFailLog, Long> {

}
