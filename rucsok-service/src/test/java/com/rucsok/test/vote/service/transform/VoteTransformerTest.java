package com.rucsok.test.vote.service.transform;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.repository.domain.VoteEntity;
import com.rucsok.rucsok.repository.domain.VoteTypeEntity;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.vote.domain.Vote;
import com.rucsok.vote.transform.VoteTransformer;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { VoteTransformer.class })
public class VoteTransformerTest {

	@Autowired
	private VoteTransformer transformer;
	
	
	private RucsokEntity rucsok;
	private UserEntity user;
	
	@Before
	public void setUp() {
		rucsok = Mockito.mock(RucsokEntity.class);
		user = Mockito.mock(UserEntity.class);
	}

	@Test
	public void itTransformsToVoteEntityFromVoteRucsokEntityAndUserEntity() {
		//GIVEN in setup
		
		final Vote vote = new Vote();
		vote.setVoteType(VoteTypeEntity.UP);
		
			
		//WHEN
		final VoteEntity voteEntity = transformer.transformToVoteEntity(vote, rucsok, user);
		
		//THEN
		
		Assert.assertEquals(voteEntity.getRucsok(), rucsok);
		Assert.assertEquals(voteEntity.getUser(), user);
		Assert.assertEquals(voteEntity.getVoteType(), VoteTypeEntity.UP);
		Assert.assertEquals(voteEntity.getKey().getRucsokId(), rucsok.getId());
		Assert.assertEquals(voteEntity.getKey().getUserId(), user.getId());
		
	}
	
	
}
