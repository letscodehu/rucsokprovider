package com.rucsok.user.repository.domain;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class User {

	@Id
	private long id;
	private String name;
	private String password;
	private int failedlogin;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getFailedlogin() {
		return failedlogin;
	}
	public void setFailedlogin(int failedlogin) {
		this.failedlogin = failedlogin;
	}
}
