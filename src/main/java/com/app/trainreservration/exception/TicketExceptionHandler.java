package com.app.trainreservration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TicketExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException notFoundException) {
		return ResponseEntity.ok(ErrorResponse.builder()
				.errorCode(notFoundException.getErrorCode())
				.message(notFoundException.getErrorMessage()).build());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(Exception exception) {
		return ResponseEntity.ok(ErrorResponse.builder()
				.errorCode(HttpStatus.BAD_REQUEST.name())
				.message(exception.getMessage()).build());
	}
}
