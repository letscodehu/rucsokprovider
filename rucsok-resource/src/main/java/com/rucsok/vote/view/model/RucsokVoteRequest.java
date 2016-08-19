package com.rucsok.vote.view.model;

import com.rucsok.vote.domain.VoteType;

public class RucsokVoteRequest {

	private long userid;
	private long rucsokid;
	private VoteType type;
	
	public VoteType getType() {
		return type;
	}

	public long getUserid() {
		return userid;
	}	

	public long getRucsokid() {
		return rucsokid;
	}


	public RucsokVoteRequest(long userid, long rucsokid, VoteType type) {
		super();
		this.userid = userid;
		this.rucsokid = rucsokid;
		this.type = type;
	}
	
}
