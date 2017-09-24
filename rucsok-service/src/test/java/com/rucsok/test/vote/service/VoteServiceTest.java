package com.rucsok.test.vote.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.config.RucsokVoteServiceConfig;
import com.rucsok.rucsok.repository.dao.VoteRepository;
import com.rucsok.rucsok.repository.domain.VoteEntity;
import com.rucsok.rucsok.repository.domain.VoteTypeEntity;
import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.vote.domain.UserVoteType;
import com.rucsok.vote.service.VoteService;
import com.rucsok.vote.transform.VoteTransformer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { VoteService.class, RucsokVoteServiceConfig.class })
public class VoteServiceTest {

	@Autowired
	private VoteService voteService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VoteTransformer voteTransformer;
	
	@Autowired
	private VoteRepository voteRepository;
	
	private final String username = "testuser";
	private final Long rucsokId = 5L;
	private final Long userId = 5L;
	private final UserEntity userEntity = new UserEntity();
	private final VoteEntity queryResult = new VoteEntity();
	
	@Before
	public void setUp() {
		userEntity.setId(userId);
	}
	
	@Test
	public void itReturns_NOT_VOTED_whenNoEntityIsPresent() {
		// GIVEN in setup
		
		Mockito.when(userRepository.findByName(username)).thenReturn(userEntity);		
		Mockito.when(voteRepository.findByUserIdAndRucsokId(userId, rucsokId)).thenReturn(null);
		Mockito.when(voteTransformer.transformVoteTypeToUserVoteType(null)).thenReturn(UserVoteType.NOT_VOTED);
		// WHEN
		UserVoteType result = voteService.getVoteStatusForSingleRucsok(username, rucsokId);
		
		// THEN
		Assert.assertEquals(UserVoteType.NOT_VOTED, result);
	}
	
	@Test
	public void itReturns_UP_whenEntityIsPresentWithVoteTypeUP() {
		// GIVEN in setup

		queryResult.setVoteType(VoteTypeEntity.UP);
		
		Mockito.when(voteTransformer.transformVoteTypeToUserVoteType(queryResult)).thenReturn(UserVoteType.UP);
		
		Mockito.when(userRepository.findByName(username)).thenReturn(userEntity);		
		Mockito.when(voteRepository.findByUserIdAndRucsokId(userId, rucsokId)).thenReturn(queryResult);
		
		// WHEN
		UserVoteType result = voteService.getVoteStatusForSingleRucsok(username, rucsokId);
		
		// THEN
		Assert.assertEquals(UserVoteType.UP, result);
	}
	
	@Test
	public void itReturns_DOWN_whenEntityIsPresentWithVoteTypeDOWN() {
		// GIVEN in setup
		
		queryResult.setVoteType(VoteTypeEntity.DOWN);
		
		Mockito.when(voteTransformer.transformVoteTypeToUserVoteType(queryResult)).thenReturn(UserVoteType.DOWN);
		
		Mockito.when(userRepository.findByName(username)).thenReturn(userEntity);		
		Mockito.when(voteRepository.findByUserIdAndRucsokId(userId, rucsokId)).thenReturn(queryResult);
		
		// WHEN
		UserVoteType result = voteService.getVoteStatusForSingleRucsok(username, rucsokId);
		
		// THEN
		Assert.assertEquals(UserVoteType.DOWN, result);
	}
	

}
