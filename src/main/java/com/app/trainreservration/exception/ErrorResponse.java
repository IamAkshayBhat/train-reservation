package com.app.trainreservration.exception;

import lombok.Builder;

@Builder
public class ErrorResponse {

	private String errorCode;
	private String message;
	
}
