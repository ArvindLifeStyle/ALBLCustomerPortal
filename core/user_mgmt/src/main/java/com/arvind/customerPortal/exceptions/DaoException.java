package com.arvind.customerPortal.exceptions;

public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 4749078520993906861L;

	public DaoException(String message) {
		super(message);
	}
}