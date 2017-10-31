package com.abc.portal.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.abc.portal.exception.DataNotFoundException;
import com.abc.portal.exception.NotAuthorizedException;
import com.abc.portal.exception.ResourceNotFoundException;
import com.abc.portal.exception.response.ExceptionResponse;

@RestController
@ControllerAdvice
public class ExceptionHandlingResource {

	@Autowired
	ExceptionResponse exceptionResponse;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		exceptionResponse.setErrorCode("Not Found");
		exceptionResponse.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NotAuthorizedException.class)
	public ResponseEntity<ExceptionResponse> handleNotAuthorizedException(NotAuthorizedException ex) {
		exceptionResponse.setErrorCode("UNAUTHORIZED");
		exceptionResponse.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleDataNotFoundException(DataNotFoundException ex) {
		exceptionResponse.setErrorCode("Not Found");
		exceptionResponse.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

}