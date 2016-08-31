package com.rucsok.test.config;

import org.mockito.Mock;
import org.springframework.context.annotation.Bean;

import com.rucsok.vote.service.VoteService;
import com.rucsok.vote.service.transform.VoteTransformer;

public class RucsokVoteServiceConifig {

	@Mock
	private VoteService voteService;
	
	@Mock
	private VoteTransformer voteTransformer;
	
		
	@Bean
	public VoteService voteService() {
		return voteService;
	}
	
	@Bean
	public VoteTransformer voteTransformer() {
		return voteTransformer;
	}
			
}
