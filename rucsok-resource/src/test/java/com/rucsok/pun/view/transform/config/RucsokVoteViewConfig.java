package com.rucsok.pun.view.transform.config;

import org.mockito.Mock;
import org.springframework.context.annotation.Bean;

import com.rucsok.vote.view.transform.VoteRequestTransformer;

public class RucsokVoteViewConfig {


	@Mock
	private VoteRequestTransformer voteRequestTransformer;


	@Bean
	public VoteRequestTransformer voteRequestTransformer() {
		return voteRequestTransformer;
	}

}
