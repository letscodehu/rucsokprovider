package com.rucsok.user.service.exception;

@SuppressWarnings("serial")
public class EmailAlreadyTakenException extends IllegalArgumentException {
	
	public static final String MESSAGE = "Email already taken!";

	public EmailAlreadyTakenException(String email) {
		super("Email " + email + " already taken!");
	}
}
