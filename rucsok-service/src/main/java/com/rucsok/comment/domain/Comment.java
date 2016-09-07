package com.rucsok.comment.domain;

import java.time.LocalDateTime;

import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.user.domain.User;

public class Comment {

	private long id;
	private String text;
	private User user;
	private Comment parent;
	private Rucsok rucsok;
	private LocalDateTime createdAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Rucsok getRucsok() {
		return rucsok;
	}

	public void setRucsok(Rucsok rucsok) {
		this.rucsok = rucsok;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comment getParent() {
		return parent;
	}

	public void setParent(Comment parent) {
		this.parent = parent;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
