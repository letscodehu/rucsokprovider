package com.rucsok.vote;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;
import com.rucsok.rucsok.repository.dao.RucsokRepository;
import com.rucsok.rucsok.repository.dao.VoteRepository;
import com.rucsok.rucsok.repository.domain.VoteEntity;
import com.rucsok.rucsok.repository.domain.VotePK;
import com.rucsok.rucsok.repository.domain.VoteTypeEntity;
import com.rucsok.user.repository.dao.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@SpringBootTest
public class VoteDaoIntegrationTest {

	private static final int TEST_DATA_SIZE = 3;

	@Autowired
	private VoteRepository underTest;

	@Autowired
	private RucsokRepository rucsokRepository;

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
		VoteTypeEntity vote = VoteTypeEntity.UP;
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
