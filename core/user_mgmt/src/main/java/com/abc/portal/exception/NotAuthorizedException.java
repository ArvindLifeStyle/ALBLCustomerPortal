package com.abc.portal.exception;

public class NotAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1129331641177661703L;

	public NotAuthorizedException(String message) {
		super(message);
	}

}
