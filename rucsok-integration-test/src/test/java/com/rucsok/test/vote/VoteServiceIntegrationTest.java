package com.rucsok.test.vote;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.security.Principal;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.neo4j.cypher.internal.compiler.v2_1.planner.logical.steps.verifyBestPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.rucsok.repository.dao.VoteDao;
import com.rucsok.rucsok.repository.domain.VoteEntity;
import com.rucsok.rucsok.repository.domain.VotePK;
import com.rucsok.test.config.RepositoryConfig;
import com.rucsok.test.config.TestConfig;
import com.rucsok.vote.domain.Vote;
import com.rucsok.vote.domain.VoteType;
import com.rucsok.vote.service.VoteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@IntegrationTest
public class VoteServiceIntegrationTest {

	@Autowired
	private VoteDao voteRepository;

	@Autowired
	private VoteService underTest;

	@Mock
	private Principal principal;

	@Mock
	private Vote vote;
	
	@Before
	public void setUp(){
		principal = Mockito.mock(Principal.class);
		vote = Mockito.mock(Vote.class);
	}

	@Test
	@Transactional
	public void createOrUpdateVoteShouldCreateANewVote() {
		
		// Given
		
		String username = "rucsok";
		Long rucsokId = Long.valueOf(4);
		Long userId = Long.valueOf(1);
		VoteType voteType = VoteType.UP;
		long count = voteRepository.count();
		
		// When
		
		when(principal.getName()).thenReturn(username);
		when(vote.getRucsokId()).thenReturn(rucsokId);
		when(vote.getUserId()).thenReturn(userId);
		when(vote.getVoteType()).thenReturn(voteType);
		underTest.createVote(vote, principal);
		
		VoteEntity persistedVote =  voteRepository.findOne(new VotePK(userId, rucsokId));
		
		// Then
		
		Assert.assertEquals("Vote should be presisted.", count + 1, voteRepository.count());
		Assert.assertEquals("Votetype should be the same.", voteType, persistedVote.getVoteType());
		
		verify(principal).getName();
		verify(vote).getRucsokId();
		verify(vote).getUserId();
		verify(vote).getVoteType();
	}
}
