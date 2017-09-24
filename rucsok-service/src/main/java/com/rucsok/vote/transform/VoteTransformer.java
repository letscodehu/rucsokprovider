package com.rucsok.vote.transform;

import org.springframework.stereotype.Component;

import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.repository.domain.VoteEntity;
import com.rucsok.rucsok.repository.domain.VotePK;
import com.rucsok.rucsok.repository.domain.VoteTypeEntity;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.vote.domain.UserVoteType;
import com.rucsok.vote.domain.Vote;

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

	public UserVoteType transformVoteTypeToUserVoteType(VoteEntity voteEntity) {
		if (voteEntity == null) {
			return UserVoteType.NOT_VOTED;
		} else if (voteEntity.getVoteType().equals(VoteTypeEntity.DOWN)) {
			return UserVoteType.DOWN;
		} else {
			return UserVoteType.UP;	
		}
		
	}

}
