package com.example.demoProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoProject.model.KeywordDetails;

public interface KeywordDetailsRepository extends JpaRepository<KeywordDetails, Long> {

	Optional<KeywordDetails> findById(int id);

}
