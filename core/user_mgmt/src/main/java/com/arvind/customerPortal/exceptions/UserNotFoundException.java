package com.arvind.customerPortal.exceptions;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorMessage="Invalid username or password";
	private String description="User does not exist";

	public String getDescription() {
		return description;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	public UserNotFoundException (String errorMessage,String description) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.description = description;
	}

	public UserNotFoundException() {
		super();
	}

}
