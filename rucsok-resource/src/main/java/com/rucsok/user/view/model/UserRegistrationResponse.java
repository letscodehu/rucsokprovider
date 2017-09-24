package com.rucsok.user.view.model;

public class UserRegistrationResponse {

	private UserProfileView user;
		
	private UserRegistrationError error;

	public UserRegistrationResponse(UserProfileView user, UserRegistrationError error) {
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
