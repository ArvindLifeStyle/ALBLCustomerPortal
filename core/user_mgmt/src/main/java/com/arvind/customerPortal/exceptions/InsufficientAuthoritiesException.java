package com.arvind.customerPortal.exceptions;

public class InsufficientAuthoritiesException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorMessage="Insufficient authorities to perform the action";
	private String description="Insufficient authorities";

	public String getDescription() {
		return description;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	public InsufficientAuthoritiesException (String errorMessage,String description) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.description = description;
	}

	public InsufficientAuthoritiesException() {
		super();
	}

}