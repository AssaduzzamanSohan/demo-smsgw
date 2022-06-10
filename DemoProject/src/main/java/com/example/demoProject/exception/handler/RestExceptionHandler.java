package com.example.demoProject.exception.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demoProject.controller.AppConfController;
import com.example.demoProject.exception.ExceptionResponseDto;
import com.example.demoProject.exception.GenericNotFoundException;

@ControllerAdvice(basePackageClasses = AppConfController.class)
public class RestExceptionHandler {

	@ExceptionHandler({ GenericNotFoundException.class })
	public ResponseEntity<ExceptionResponseDto> handleEntityNotFoundException(GenericNotFoundException e,
			WebRequest request) {
		ExceptionResponseDto response = new ExceptionResponseDto();
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setErrorCode("4004");
		response.setReason(e.getInternalCode() + " " + e.getEntityId());
		response.setTimeStamp(Instant.now());

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

}
