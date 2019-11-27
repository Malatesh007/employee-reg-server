package com.example.register.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class EmployeeRegisterException extends RuntimeException {

	private static final Logger log = LoggerFactory.getLogger(EmployeeRegisterException.class);
	private final String message;

	public EmployeeRegisterException(String message) {
		this.message = message;
		log.info(message, this);
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
