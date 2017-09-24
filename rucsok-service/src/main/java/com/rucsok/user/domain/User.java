package com.rucsok.user.domain;

public class User {

	private long id;
	private String email;
	private String username;

	public User() {
		super();
	}
	
	public User(String email, String username) {
		super();
		this.email = email;
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
