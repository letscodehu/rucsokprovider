package com.rucsok.vote;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;
import com.rucsok.rucsok.repository.dao.VoteDao;
import com.rucsok.rucsok.repository.domain.VoteEntity;
import com.rucsok.rucsok.repository.domain.VotePK;
import com.rucsok.rucsok.repository.domain.VoteTypeEntity;
import com.rucsok.vote.domain.Vote;
import com.rucsok.vote.service.VoteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@SpringBootTest
public class VoteServiceIntegrationTest {

	@Autowired
	private VoteDao voteRepository;

	@Autowired
	private VoteService underTest;

	@Mock
	private Vote vote;
	
	@Before
	public void setUp(){
		vote = Mockito.mock(Vote.class);
	}

	@Test
	@Transactional
	public void createOrUpdateVoteShouldCreateANewVote() {
		
		// Given
		
		String username = "rucsok";
		Long rucsokId = Long.valueOf(4);
		Long userId = Long.valueOf(1);
		VoteTypeEntity voteType = VoteTypeEntity.UP;
		long count = voteRepository.count();
		
		// When
		
		when(vote.getRucsokId()).thenReturn(rucsokId);
		when(vote.getUsername()).thenReturn(username);
		when(vote.getVoteType()).thenReturn(voteType);
		
		underTest.createVote(vote);
		
		VoteEntity persistedVote =  voteRepository.findOne(new VotePK(userId, rucsokId));
		
		// Then
		
		Assert.assertEquals("Vote should be presisted.", count + 1, voteRepository.count());
		Assert.assertEquals("Votetype should be the same.", voteType, persistedVote.getVoteType());
		Assert.assertEquals("Rucsok Id should be the same.", rucsokId.longValue(), persistedVote.getRucsok().getId());
		Assert.assertEquals("UserId Id should be the same.", userId.longValue(), persistedVote.getUser().getId());
		
		verify(vote).getRucsokId();
		verify(vote).getUsername();
		verify(vote).getVoteType();
	}
}
