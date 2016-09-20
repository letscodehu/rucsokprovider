package com.rucsok.user.view.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegistrationError {

	private String message;

	public String getMessage() {
		return message;
	}

	@JsonCreator
	public UserRegistrationError(@JsonProperty("message") String message) {
		super();
		this.message = message;
	}
	
}
