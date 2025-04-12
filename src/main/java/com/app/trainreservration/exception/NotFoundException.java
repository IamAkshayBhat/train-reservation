package com.app.trainreservration.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundException extends RuntimeException{
	
	private HttpStatus errorCode;
	private String errorMessage;

}
