package com.rucsok.vote.domain;

import com.rucsok.rucsok.repository.domain.VoteTypeEntity;

public class Vote {

	private String username;
	private Long rucsokId;
	private VoteTypeEntity voteType;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getRucsokId() {
		return rucsokId;
	}

	public void setRucsokId(Long rucsokId) {
		this.rucsokId = rucsokId;
	}

	public VoteTypeEntity getVoteType() {
		return voteType;
	}

	public void setVoteType(VoteTypeEntity voteType) {
		this.voteType = voteType;
	}

}
