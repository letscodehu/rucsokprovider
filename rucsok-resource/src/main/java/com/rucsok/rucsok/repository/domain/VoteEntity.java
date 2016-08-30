package com.rucsok.rucsok.repository.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.vote.domain.VoteType;

@Entity(name = "Vote")
public class VoteEntity {

	@EmbeddedId
	private VotePK key;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "rucsok_id", referencedColumnName = "id", insertable = false, updatable = false)
	private RucsokEntity rucsok;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
	private UserEntity user;

	@Enumerated(EnumType.STRING)
	private VoteType vote;

	public VotePK getKey() {
		return key;
	}

	public void setKey(VotePK key) {
		this.key = key;
	}

	public RucsokEntity getRucsok() {
		return rucsok;
	}

	public void setRucsok(RucsokEntity rucsok) {
		this.rucsok = rucsok;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public VoteType getVoteType() {
		return vote;
	}

	public void setVoteType(VoteType vote) {
		this.vote = vote;
	}

}
