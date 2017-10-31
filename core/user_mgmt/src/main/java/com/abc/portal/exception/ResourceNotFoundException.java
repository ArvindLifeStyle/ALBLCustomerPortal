package com.abc.portal.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2626220667677149446L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
