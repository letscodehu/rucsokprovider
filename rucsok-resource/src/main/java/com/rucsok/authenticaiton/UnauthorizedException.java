package com.rucsok.authenticaiton;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends UnauthorizedUserException {

	public UnauthorizedException(String msg) {
		super(msg);
	}

}
