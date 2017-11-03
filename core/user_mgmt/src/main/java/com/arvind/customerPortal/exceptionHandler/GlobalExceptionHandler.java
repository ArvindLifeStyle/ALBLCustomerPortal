package com.arvind.customerPortal.exceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arvind.customerPortal.exceptions.DataNotFoundException;
import com.arvind.customerPortal.exceptions.InsufficientAuthoritiesException;
import com.arvind.customerPortal.exceptions.InsufficientPriviledgesException;
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.model.ErrorResult;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	public GlobalExceptionHandler() {

	}

	@Autowired
	ErrorResult errorResult;

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	ErrorResult handleException(UserNotFoundException exception) {
		errorResult.setCode((HttpStatus.UNAUTHORIZED).toString());
		errorResult.setDescription(exception.getErrorMessage());
		errorResult.setMessage("Failure");
		return errorResult;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	ErrorResult handleException(InsufficientPriviledgesException exception) {
		errorResult.setCode((HttpStatus.UNAUTHORIZED).toString());
		errorResult.setDescription(exception.getErrorMessage());
		errorResult.setMessage("Failure");
		return errorResult;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	ErrorResult handleException(InsufficientAuthoritiesException exception) {
		errorResult.setCode((HttpStatus.UNAUTHORIZED).toString());
		errorResult.setDescription(exception.getErrorMessage());
		errorResult.setMessage("Failure");
		return errorResult;
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorResult> handleDataNotFoundException(DataNotFoundException ex) {
		errorResult.setCode("Not Found");
		errorResult.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResult>(errorResult, HttpStatus.NOT_FOUND);
	}

}
