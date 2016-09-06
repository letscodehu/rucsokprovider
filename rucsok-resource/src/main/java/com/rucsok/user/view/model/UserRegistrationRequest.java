package com.rucsok.user.view.model;

public class UserRegistrationRequest {

	private String email;
	private String username;
	private String password;
	private String passwordConfirmation;
	public String getEmail() {
		return email;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	
	public UserRegistrationRequest() {
		super();
	}
	
	public UserRegistrationRequest(String email, String username, String password, String passwordConfirmation) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
	}
	
}
