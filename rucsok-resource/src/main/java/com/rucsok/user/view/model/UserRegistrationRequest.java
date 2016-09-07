package com.rucsok.user.view.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.rucsok.user.view.helper.ValidEmail;

public class UserRegistrationRequest {

	@NotNull
	@NotEmpty
	@ValidEmail
	private String email;
	
	@NotNull
	@NotEmpty
	private String username;
	
	@NotNull
	@NotEmpty
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
