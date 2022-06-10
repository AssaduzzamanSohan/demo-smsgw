package com.example.demoProject.service;

import java.util.List;

import org.springframework.scheduling.annotation.Async;

import com.example.demoProject.model.AppConf;

public interface AppConfService {

	List<AppConf> findAll();

	AppConf findById(int id);

	@Async
	void startDemoWork();

}
