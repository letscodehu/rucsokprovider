package com.rucsok.rucsok.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rucsok.rucsok.domain.Rucsok;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalRucsokArgumentException extends RuntimeException {

	public IllegalRucsokArgumentException() {
		super();
	}

	public IllegalRucsokArgumentException(String message) {
		super(message);
	}

}
