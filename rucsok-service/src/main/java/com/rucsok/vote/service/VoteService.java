package com.rucsok.vote.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.rucsok.repository.dao.RucsokRepository;
import com.rucsok.rucsok.repository.dao.VoteRepository;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.repository.domain.VoteEntity;
import com.rucsok.rucsok.service.exception.IllegalRucsokArgumentException;
import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.vote.domain.UserVoteType;
import com.rucsok.vote.domain.Vote;
import com.rucsok.vote.transform.VoteTransformer;

@Service
public class VoteService {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(VoteService.class);

	@Autowired
	private VoteTransformer voteTransformer;

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RucsokRepository rucsokRepository;

	public void createVote(Vote vote) {
		Optional<RucsokEntity> rucsok = getRucsokById(vote);
		Optional<UserEntity> user = getUserByName(vote);
		checkIfUserExist(user);
		checkIfRucsokExist(rucsok);
		saveVote(vote, rucsok.get(), user.get());
	}

	private void saveVote(Vote vote, RucsokEntity rucsok, UserEntity user) {
		voteRepository.save(voteTransformer.transformToVoteEntity(vote, rucsok, user));
	}
	

	private void checkIfUserExist(Optional<UserEntity> user) {
		if (!user.isPresent()) {
			throw new IllegalRucsokArgumentException("User not exists!");
		}
	}

	private void checkIfRucsokExist(Optional<RucsokEntity> rucsok) {
		if (!rucsok.isPresent()) {
			throw new IllegalRucsokArgumentException("Rucsok not exists!");
		}
	}

	private Optional<UserEntity> getUserByName(Vote vote) {
		return Optional.ofNullable(userRepository.findByName(vote.getUsername()));
	}
	
	private Optional<RucsokEntity> getRucsokById(Vote vote) {
		return Optional.ofNullable(rucsokRepository.findOne(vote.getRucsokId()));
	}

	public UserVoteType getVoteStatusForSingleRucsok(String username, Long rucsokId) {
		if (username == null) {
			return UserVoteType.NOT_LOGGED_IN;
		}
		VoteEntity entity = voteRepository
				.findByUserIdAndRucsokId(getUserIdByUsername(username), rucsokId);
		
		return voteTransformer.transformVoteTypeToUserVoteType(entity);
	}

	private long getUserIdByUsername(String username) {
		return userRepository.findByName(username).getId();
	}
}
