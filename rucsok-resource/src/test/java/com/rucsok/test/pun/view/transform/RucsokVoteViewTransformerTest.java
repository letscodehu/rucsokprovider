package com.rucsok.test.pun.view.transform;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.rucsok.repository.domain.VoteTypeEntity;
import com.rucsok.vote.domain.Vote;
import com.rucsok.vote.view.model.RucsokVoteRequest;
import com.rucsok.vote.view.transform.VoteRequestTransformer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = VoteRequestTransformer.class)
public class RucsokVoteViewTransformerTest {

	@Autowired
	private VoteRequestTransformer requestTransformer;
	

	@Test
	public void itTransformsFromRucsokVoteRequestToVote() {
		
		// GIVEN
		
		final RucsokVoteRequest request = new RucsokVoteRequest();
		final String voteType = "down";
		final Long rucsokId = 1L;
		request.setRucsokid(rucsokId);
		request.setVoteType(voteType);
		String username = "testuser";	
		
		
		// WHEN
		
		Vote vote = requestTransformer.transformFromRucsokVoteRequest(request, username);
		
		// THEN
		
		Assert.assertEquals(username, vote.getUsername());
		Assert.assertEquals(rucsokId, vote.getRucsokId());
		Assert.assertEquals(VoteTypeEntity.DOWN, vote.getVoteType());
	}

}
