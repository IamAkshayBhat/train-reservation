package com.app.trainreservration.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {

	private int errorCode;
	private String message;
	
}
