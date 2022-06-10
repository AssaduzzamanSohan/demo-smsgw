package com.example.demoProject.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoProject.model.ChargeConf;

public interface ChargeConfRepository extends JpaRepository<ChargeConf, Long> {

	Optional<ChargeConf> findById(int id);

	Page<ChargeConf> findAll(Pageable pageable);

}
