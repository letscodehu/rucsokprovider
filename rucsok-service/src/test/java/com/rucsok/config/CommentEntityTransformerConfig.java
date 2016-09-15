package com.rucsok.config;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;

import com.rucsok.comment.DateService;
import com.rucsok.user.transform.UserTransformer;

public class CommentEntityTransformerConfig {

	@Mock
	private DateService mockDateService;
	
	@Mock
	private UserTransformer mockUserTransformer;
	
	public CommentEntityTransformerConfig() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Bean
	public DateService dateService() {
		return mockDateService;
	}

	@Bean
	public UserTransformer userTransformer() {
		return mockUserTransformer;
	}
	
}
