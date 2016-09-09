package com.rucsok.comment.config;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;

import com.rucsok.comment.transform.DateTimeTransformer;
import com.rucsok.comment.transform.OutputTextTransformer;

public class CommentViewConverterConfig {

	@Mock
	private DateTimeTransformer formatter;

	@Mock
	private OutputTextTransformer outputTextTransformer;

	public CommentViewConverterConfig() {
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
