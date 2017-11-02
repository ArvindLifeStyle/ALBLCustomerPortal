package com.arvind.customerPortal.exceptions;

public class InsufficientPriviledgesException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorMessage="Insufficient Priviledges to perform the action";
	private String description="Insufficient Priviledges";

	public String getDescription() {
		return description;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	public InsufficientPriviledgesException (String errorMessage,String description) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.description = description;
	}

	public InsufficientPriviledgesException() {
		super();
	}

}
