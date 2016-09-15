package com.rucsok.config;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;

import com.rucsok.comment.convert.CommentConverter;
import com.rucsok.comment.convert.CommentEntityConverter;
import com.rucsok.comment.repository.dao.CommentRepository;
import com.rucsok.rucsok.repository.dao.RucsokRepository;
import com.rucsok.user.service.UserCheckerService;
import com.rucsok.user.transform.UserTransformer;

public class CommentServiceConfig {

	@Mock
	private CommentRepository mockCommentRepository;
	
	@Mock
	private UserTransformer mockUserTransformer;
	
	@Mock
	private RucsokRepository mockRucsokRepository;
	
	@Mock
	private UserCheckerService mockUserCheckerService;
	
	@Mock
	private CommentEntityConverter mockCommentEntityConverter;
	
	@Mock
	private CommentConverter mockCommentConverter;
	
	public CommentServiceConfig() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Bean
	public CommentRepository commentRepository() {
		return mockCommentRepository;
	}

	@Bean
	public RucsokRepository rucsokRepository() {
		return mockRucsokRepository;
	}
	
	@Bean
	public UserCheckerService userCheckerService() {
		return mockUserCheckerService;
	}
	
	@Bean
	public CommentEntityConverter commentEntityConverter() {
		return mockCommentEntityConverter;
	}
	
	@Bean
	public CommentConverter commentConverter() {
		return mockCommentConverter;
	}
	
	@Bean
	public UserTransformer userTransformer() {
		return mockUserTransformer;
	}

}
