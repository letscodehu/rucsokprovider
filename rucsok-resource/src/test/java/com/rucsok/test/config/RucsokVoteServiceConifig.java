package com.rucsok.test.config;

import org.mockito.Mock;
import org.springframework.context.annotation.Bean;

import com.rucsok.vote.service.VoteService;
import com.rucsok.vote.transform.VoteTransformer;
import com.rucsok.vote.view.transform.VoteRequestTransformer;

public class RucsokVoteServiceConifig {

	@Mock
	private VoteService voteService;
	
	@Mock
	private VoteTransformer voteTransformer;
	
	@Mock
	private VoteRequestTransformer voteRequestTransformer;
	
		
	@Bean
	public VoteService voteService() {
		return voteService;
	}
	
	@Bean
	public VoteTransformer voteTransformer() {
		return voteTransformer;
	}
			
	@Bean
	public VoteRequestTransformer voteRequestTransformer() {
		return voteRequestTransformer;
	}
}
