package com.rucsok.user.service.exception;

@SuppressWarnings("serial")
public class UserAlreadyPresentException extends IllegalArgumentException {

	public static final String MESSAGE = "The username already present!";
	
	public UserAlreadyPresentException() {
		super(MESSAGE);
	}

	
	
}
