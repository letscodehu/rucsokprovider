package com.rucsok.vote.service;

import java.security.Principal;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.rucsok.repository.dao.RucsokDao;
import com.rucsok.rucsok.repository.dao.VoteDao;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.service.exception.IllegalRucsokArgumentException;
import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.user.service.UserProfileService;
import com.rucsok.user.service.exception.UserHasNoRightException;
import com.rucsok.vote.domain.Vote;
import com.rucsok.vote.service.transform.VoteTransformer;

@Service
public class VoteService {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(VoteService.class);

	@Autowired
	private VoteTransformer voteTransformer;

	@Autowired
	private VoteDao voteRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private RucsokDao rucsokRepository;

	public void createVote(Vote vote, Principal principal) {
		Optional<RucsokEntity> rucsok = getRucsokById(vote);
		Optional<UserEntity> user = getUserById(vote);
		checkIfUserExist(user);
		checkIfRucsokExist(rucsok);
		checkIfUserCanMakeAVote(user.get(), principal);
		saveVote(vote, rucsok.get(), user.get());
	}

	private void checkIfUserCanMakeAVote(UserEntity userEntity, Principal principal) {
		if (!userEntity.getName().equals(principal.getName())) {
			LOGGER.error("User: " + principal.getName() + " tried to create vote with: " + userEntity.getName());
			throw new UserHasNoRightException();
		}
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

	private Optional<UserEntity> getUserById(Vote vote) {
		return Optional.ofNullable(userRepository.findOne(vote.getUserId()));
	}

	private Optional<RucsokEntity> getRucsokById(Vote vote) {
		return Optional.ofNullable(rucsokRepository.findOne(vote.getRucsokId()));
	}
}
