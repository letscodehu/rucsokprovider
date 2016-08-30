package com.rucsok.vote.domain;

public class Vote {

	private String username;
	private Long rucsokId;
	private VoteType voteType;

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

	public VoteType getVoteType() {
		return voteType;
	}

	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}

}
