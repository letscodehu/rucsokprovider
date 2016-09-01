package com.rucsok.vote.view.transform;

import org.springframework.stereotype.Component;

import com.rucsok.rucsok.repository.domain.VoteTypeEntity;
import com.rucsok.vote.domain.Vote;
import com.rucsok.vote.view.model.RucsokVoteRequest;

@Component
public class VoteRequestTransformer {

	public Vote transformFromRucsokVoteRequest(RucsokVoteRequest request, String username) {
		Vote vote = new Vote();
		vote.setRucsokId(request.getRucsokid());
		vote.setUsername(username);
		vote.setVoteType(VoteTypeEntity.valueOf(request.getVoteType().toUpperCase()));
		return vote;
	}
}
