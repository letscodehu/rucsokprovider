package com.rucsok.vote.service.transform;

import org.springframework.stereotype.Component;

import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.repository.domain.VoteEntity;
import com.rucsok.rucsok.repository.domain.VotePK;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.vote.domain.Vote;
import com.rucsok.vote.domain.VoteType;
import com.rucsok.vote.view.model.RucsokVoteRequest;

@Component
public class VoteTransformer {

	public VoteEntity transformToVoteEntity(Vote vote, RucsokEntity rucsokEntity, UserEntity userEntity) {
		VoteEntity result = new VoteEntity();
		result.setRucsok(rucsokEntity);
		result.setUser(userEntity);
		result.setKey(tranformToVotePK(rucsokEntity, userEntity));
		result.setVoteType(vote.getVoteType());
		return result;
	}

	public VotePK tranformToVotePK(RucsokEntity rucsokEntity, UserEntity userEntity) {
		VotePK result = new VotePK();
		result.setRucsokId(rucsokEntity.getId());
		result.setUserId(userEntity.getId());
		return result;
	}
	
	public Vote transformFromRucsokVoteRequest(RucsokVoteRequest request, String username) {
		Vote vote = new Vote();
		vote.setRucsokId(request.getRucsokid());
		vote.setUsername(username);
		vote.setVoteType(VoteType.valueOf(request.getVoteType().toUpperCase()));
		return vote;
	}

}
