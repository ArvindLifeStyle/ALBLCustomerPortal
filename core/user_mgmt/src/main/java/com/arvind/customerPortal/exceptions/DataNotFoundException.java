package com.arvind.customerPortal.exceptions;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4749078520993906861L;

	public DataNotFoundException(String message) {
		super(message);
	}

}
