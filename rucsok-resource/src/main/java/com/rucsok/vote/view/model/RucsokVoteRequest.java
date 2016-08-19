package com.rucsok.vote.view.model;

public class RucsokVoteRequest {

	private long rucsokid;
	private String voteType;

	public String getType() {
		return voteType;
	}

	public long getRucsokid() {
		return rucsokid;
	}

	public String getVoteType() {
		return voteType;
	}

	public void setVoteType(String voteType) {
		this.voteType = voteType;
	}

	public void setRucsokid(long rucsokid) {
		this.rucsokid = rucsokid;
	}

	public RucsokVoteRequest() {
	}

	public RucsokVoteRequest(long rucsokid, String type) {
		super();
		this.rucsokid = rucsokid;
		this.voteType = type;
	}

}
