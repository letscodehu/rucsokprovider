package com.rucsok.user.domain;

public class UserRegistration extends User {

	private String password;

	public UserRegistration(String email, String username, String password) {
		super(email, username);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
}
