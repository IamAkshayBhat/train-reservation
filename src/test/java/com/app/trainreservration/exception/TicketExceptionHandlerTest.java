package com.app.trainreservration.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class TicketExceptionHandlerTest {
	
	@InjectMocks
	private TicketExceptionHandler ticketExceptionHandler;

	@Test
	void givenNotFoundException_whenHandlingNotFoundException_itReturnsResponseEntityOfErrorResponse() {
		NotFoundException notFoundException = new NotFoundException(HttpStatus.NOT_FOUND, "not found");
		var errorResponse = ticketExceptionHandler.handleNotFoundException(notFoundException);
		assertEquals(HttpStatus.NOT_FOUND.value(), errorResponse.getBody().getErrorCode());
	}
	
	@Test
	void givenGenericException_whenHandlingException_itReturnsResponseEntityOfErrorResponse() {
		Exception exception = new Exception("This is generic exception");
		var errorResponse = ticketExceptionHandler.handleGenericException(exception);
		assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getBody().getErrorCode());
	}

}
