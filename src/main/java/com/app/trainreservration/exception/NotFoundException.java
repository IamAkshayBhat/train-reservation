package com.app.trainreservration.exception;

import lombok.Getter;
import lombok.experimental.StandardException;

@StandardException
@Getter
public class NotFoundException extends RuntimeException{
	
	private String errorCode;
	private String errorMessage;
	
	public NotFoundException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

}
