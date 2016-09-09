package com.rucsok.pun.repository.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.rucsok.user.repository.domain.UserEntity;

@Entity(name = "Pun")
public class PunEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String text;
	
	public PunEntity() {
		
	}
	
	public PunEntity(long id, String text, UserEntity user) {
		super();
		this.id = id;
		this.text = text;
		this.user = user;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public UserEntity getUser() {
		return user;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	
}
