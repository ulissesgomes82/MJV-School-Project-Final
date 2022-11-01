package com.api.school.service.exceptions;

public class DataIntegrationViolationException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DataIntegrationViolationException(String message) {
		super(message);
	}

	
}
