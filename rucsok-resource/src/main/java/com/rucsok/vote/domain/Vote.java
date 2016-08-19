package com.rucsok.vote.domain;

public class Vote {

	private Long userId;
	private Long rucsokId;
	private VoteType voteType;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
