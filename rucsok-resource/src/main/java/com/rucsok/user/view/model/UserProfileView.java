package com.rucsok.user.view.model;

public class UserProfileView {

	private String username;
	private String email;
	
	public UserProfileView(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}
	
	public UserProfileView() {
		super();
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
