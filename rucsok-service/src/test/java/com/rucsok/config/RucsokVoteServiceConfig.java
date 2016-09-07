package com.rucsok.config;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;

import com.rucsok.rucsok.repository.dao.RucsokRepository;
import com.rucsok.rucsok.repository.dao.VoteRepository;
import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.vote.service.VoteService;
import com.rucsok.vote.transform.VoteTransformer;

public class RucsokVoteServiceConfig {

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private VoteService voteService;
	
	@Mock
	private VoteTransformer voteTransformer;
	
	@Mock
	private VoteRepository voteRepository;
	
	
	@Mock
	private RucsokRepository rucsokRepository;
	
	@Bean
	public RucsokRepository rucsokRepository() {
		return rucsokRepository;
	}	
	
	
	
	public RucsokVoteServiceConfig() {
		MockitoAnnotations.initMocks(this);
	}



	@Bean
	public VoteRepository voteRepository() {
		return voteRepository;
	}	
	
	@Bean
	public UserRepository userRepository() {
		return userRepository;
	}	
		
	@Bean
	public VoteService voteService() {
		return voteService;
	}
	
	@Bean
	public VoteTransformer voteTransformer() {
		return voteTransformer;
	}
	
}
