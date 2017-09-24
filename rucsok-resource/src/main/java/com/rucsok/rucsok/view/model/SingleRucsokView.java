package com.rucsok.rucsok.view.model;

import com.rucsok.vote.domain.UserVoteType;

public class SingleRucsokView {

	private RucsokView current;
	private long previousId;
	private long nextId;

	public RucsokView getCurrent() {
		return current;
	}

	public void setCurrent(RucsokView current) {
		this.current = current;
	}

	public long getPreviousId() {
		return previousId;
	}

	public void setPreviousId(long previousId) {
		this.previousId = previousId;
	}

	public long getNextId() {
		return nextId;
	}

	public void setNextId(long nextId) {
		this.nextId = nextId;
	}

	public void setCurrentVoteType(UserVoteType userVoteType) {
		current.setAlreadyVoted(userVoteType.toString());
	}

}
