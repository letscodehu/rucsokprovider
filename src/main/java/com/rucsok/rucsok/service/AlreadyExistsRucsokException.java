package com.rucsok.rucsok.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rucsok.rucsok.domain.Rucsok;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class AlreadyExistsRucsokException extends RuntimeException {

	public static final String ALREADY_EXISTS = "already exists!";

	public AlreadyExistsRucsokException(Rucsok rucsok) {
		super(rucsok.getLink() + " " + ALREADY_EXISTS);
	}

	public AlreadyExistsRucsokException(String message) {
		super(message);
	}

}
