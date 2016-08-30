package com.rucsok.user.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserHasNoRightException extends RuntimeException {

	public UserHasNoRightException() {
		super();
	}

	public UserHasNoRightException(String message) {
		super(message);
	}

}
