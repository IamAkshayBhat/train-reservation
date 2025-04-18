package com.app.trainreservration.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class TicketExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException notFoundException) {
		log.error("No Element Found for the requested item");
		var errorResponse = ErrorResponse.builder()
				.errorCode(notFoundException.getErrorCode().value())
				.message(notFoundException.getErrorMessage()).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		var errorMessages = ex.getBindingResult().getFieldErrors().stream()
	            .map(error -> error.getField() + ": " + error.getDefaultMessage())
	            .collect(Collectors.joining("; "));
        var errorResponse = ErrorResponse.builder().errorCode(HttpStatus.BAD_REQUEST.value()).message(errorMessages).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
	    String errorMessages = ex.getConstraintViolations().stream()
	            .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
	            .collect(Collectors.joining("; "));

	    var errorResponse = ErrorResponse.builder()
	            .errorCode(HttpStatus.BAD_REQUEST.value())
	            .message(errorMessages)
	            .build();

	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException exception) {
		log.error("Something is not right, please try again...!");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST) 
				.body(ErrorResponse.builder()
						.errorCode(HttpStatus.BAD_REQUEST.value())
						.message(exception.getMessage()).build());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception exception) {
		log.error("Something is not right, please try again...!");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST) 
				.body(ErrorResponse.builder()
						.errorCode(HttpStatus.BAD_REQUEST.value())
						.message(exception.getMessage()).build());
	}
}
