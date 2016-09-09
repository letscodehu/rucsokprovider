package com.rucsok.comment.config;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;

import com.rucsok.comment.view.convert.CommentPostConverter;

public class CommentPostTransformerTestConfig {
	
	@Mock
	private CommentPostConverter mockCommentPostConverter;
	
	
	public CommentPostTransformerTestConfig() {
		MockitoAnnotations.initMocks(this);
	}

	@Bean
	public CommentPostConverter commentPostConverter() {
		return mockCommentPostConverter;
	}
}
