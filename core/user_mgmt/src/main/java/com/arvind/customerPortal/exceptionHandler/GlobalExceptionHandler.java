package com.arvind.customerPortal.exceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.arvind.customerPortal.exceptions.*;
import com.arvind.customerPortal.model.ErrorResult;



@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	public GlobalExceptionHandler() {

	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	ErrorResult handleException(UserNotFoundException exception) {
		ErrorResult error=new ErrorResult();
		error.setCode((HttpStatus.UNAUTHORIZED).toString());
		error.setDescription(exception.getErrorMessage());
		error.setMessage("Failure");
		return error;
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	ErrorResult handleException(InsufficientPriviledgesException exception) {
		ErrorResult error=new ErrorResult();
		error.setCode((HttpStatus.UNAUTHORIZED).toString());
		error.setDescription(exception.getErrorMessage());
		error.setMessage("Failure");
		return error;
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	ErrorResult handleException(InsufficientAuthoritiesException exception) {
		ErrorResult error=new ErrorResult();
		error.setCode((HttpStatus.UNAUTHORIZED).toString());
		error.setDescription(exception.getErrorMessage());
		error.setMessage("Failure");
		return error;
	}

}
