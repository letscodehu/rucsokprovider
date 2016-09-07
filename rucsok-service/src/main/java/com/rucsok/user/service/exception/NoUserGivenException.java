package com.rucsok.user.service.exception;

@SuppressWarnings("serial")
public class NoUserGivenException extends IllegalArgumentException {

	public static final String MESSAGE = "No user given!";
	
	public NoUserGivenException() {
		super(MESSAGE);
	}

	
	
}
