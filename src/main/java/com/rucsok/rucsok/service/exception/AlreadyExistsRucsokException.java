package com.rucsok.rucsok.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rucsok.rucsok.domain.Rucsok;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class AlreadyExistsRucsokException extends RuntimeException {

	
	private static final long serialVersionUID = 7360787821756679340L;
	public static final String ALREADY_EXISTS = "already exists!";

	public AlreadyExistsRucsokException(Rucsok rucsok) {
		super(rucsok.getLink() + " " + ALREADY_EXISTS);
	}

	public AlreadyExistsRucsokException(String message) {
		super(message);
	}

}
