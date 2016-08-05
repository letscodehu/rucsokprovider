package com.rucsok.rucsok.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalRucsokArgumentException extends RuntimeException {

	private static final long serialVersionUID = -1071032990353435069L;

	public IllegalRucsokArgumentException() {
		super();
	}

	public IllegalRucsokArgumentException(String message) {
		super(message);
	}

}
