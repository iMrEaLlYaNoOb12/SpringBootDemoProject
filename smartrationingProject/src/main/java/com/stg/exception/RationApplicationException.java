package com.stg.exception;

public class RationApplicationException extends Exception {

	private String errorMessege;

	public RationApplicationException(String errorMessege) {
		super();
		this.errorMessege = errorMessege;
	}

	@Override
	public String getMessage() {

		return this.errorMessege;
	}

}
