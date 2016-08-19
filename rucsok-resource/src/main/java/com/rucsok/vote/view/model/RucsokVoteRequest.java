package com.rucsok.vote.view.model;

import com.rucsok.vote.domain.VoteType;

public class RucsokVoteRequest {

	private long rucsokid;
	private VoteType type;
	
	public VoteType getType() {
		return type;
	}


	public long getRucsokid() {
		return rucsokid;
	}
	
	public RucsokVoteRequest() {}

	public RucsokVoteRequest(long rucsokid, VoteType type) {
		super();
		this.rucsokid = rucsokid;
		this.type = type;
	}
	
}
