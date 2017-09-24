package com.rucsok.config;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;

import com.rucsok.comment.transform.DateTimeTransformer;
import com.rucsok.comment.transform.OutputTextTransformer;

public class CommentTransformerConfig {
	
	@Mock
	private DateTimeTransformer formatter;
	
	@Mock
	private OutputTextTransformer outputTextTransformer;
	
	public CommentTransformerConfig() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Bean
	public DateTimeTransformer dateTimeTransformer() {
		return formatter;
	}
	
	@Bean
	public OutputTextTransformer outputTextTransformer() {
		return outputTextTransformer;
	}
}
