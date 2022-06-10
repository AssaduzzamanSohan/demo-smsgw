package com.example.demoProject.exception;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private Object reason;
	private String errorCode;
	private Instant timeStamp;

}