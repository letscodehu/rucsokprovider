package com.rucsok.user.view.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegistrationResponse {
	
	private UserProfileView user;
	
	private UserRegistrationError error;

	public UserRegistrationResponse(@JsonProperty("user") UserProfileView user,
			@JsonProperty("error") UserRegistrationError error) {
		super();
		this.user = user;
		this.error = error;
	}

	public UserProfileView getUser() {
		return user;
	}

	public UserRegistrationError getError() {
		return error;
	}

}
