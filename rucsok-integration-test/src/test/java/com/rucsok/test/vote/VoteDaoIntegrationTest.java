package com.rucsok.test.vote;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.rucsok.repository.dao.RucsokDao;
import com.rucsok.rucsok.repository.dao.VoteDao;
import com.rucsok.rucsok.repository.domain.VoteEntity;
import com.rucsok.rucsok.repository.domain.VotePK;
import com.rucsok.test.config.RepositoryConfig;
import com.rucsok.test.config.TestConfig;
import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.vote.domain.VoteType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@IntegrationTest
public class VoteDaoIntegrationTest {

	private static final int TEST_DATA_SIZE = 3;

	@Autowired
	private VoteDao underTest;

	@Autowired
	private RucsokDao rucsokRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	@Transactional
	public void countShouldReturnCorrectNumberOfEntites() {
		// Given

		// When
		long count = underTest.count();
		// Then
		Assert.assertEquals(TEST_DATA_SIZE, count);
	}

	@Test
	@Transactional
	public void saveShouldIncreaseTheCountValue() {
		// Given
		Long rucsokId = Long.valueOf(4);
		Long userId = Long.valueOf(1);
		VoteType vote = VoteType.UP;
		VoteEntity voteEntity = new VoteEntity();
		VotePK votePK = new VotePK();
		votePK.setRucsokId(rucsokId);
		votePK.setUserId(userId);
		voteEntity.setKey(votePK);
		voteEntity.setRucsok(rucsokRepository.findOne(rucsokId));
		voteEntity.setUser(userRepository.findOne(userId));
		voteEntity.setVoteType(vote);
		// When
		underTest.save(voteEntity);
		long count = underTest.count();
		// Then
		Assert.assertEquals(TEST_DATA_SIZE + 1, count);
	}

}