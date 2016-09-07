package com.rucsok.config;

import org.mockito.Mock;
import org.springframework.context.annotation.Bean;

import com.rucsok.rucsok.repository.dao.VoteRepository;
import com.rucsok.vote.transform.VoteTransformer;

public class VoteServiceConfig {


	@Mock
	private VoteTransformer voteTransformer;
	
	@Mock
	private VoteRepository voteRepository;
	
	@Bean
	public VoteRepository voteRepository() {
		return voteRepository;
	}		
	
	@Bean
	public VoteTransformer voteTransformer() {
		return voteTransformer;
	}
	
}
