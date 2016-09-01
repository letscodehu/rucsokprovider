package com.rucsok.rucsok.repository.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VotePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private long userId;
	@Column(name = "rucsok_id")
	private long rucsokId;

	public VotePK() {
	}

	public VotePK(long userId, long rucsokId) {
		this.userId = userId;
		this.rucsokId = rucsokId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRucsokId() {
		return rucsokId;
	}

	public void setRucsokId(long rucsokId) {
		this.rucsokId = rucsokId;
	}

}
