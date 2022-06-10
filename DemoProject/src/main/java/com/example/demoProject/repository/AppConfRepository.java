package com.example.demoProject.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoProject.model.AppConf;

public interface AppConfRepository extends JpaRepository<AppConf, Long> {

	Optional<AppConf> findById(int id);

	Page<AppConf> findAll(Pageable pageable);

}
