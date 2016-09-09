package com.rucsok.comment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rucsok.TokenHelper;
import com.rucsok.comment.view.controller.PostCommentController;
import com.rucsok.comment.view.model.CommentInsertRequest;
import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@SpringBootTest
@TestPropertySource("classpath:/application.properties")
public class PostCommentControllerIntegrationTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private String accessToken;
	
	private ObjectMapper mapper;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		mapper = new ObjectMapper();
		accessToken = TokenHelper.getAccessToken("rucsok", "123", mockMvc);
	}

	@Test
	public void itShouldReturnUnauthorized_When_UserNotLoggedIn() throws Exception {

		// Given
		CommentInsertRequest c = new CommentInsertRequest();
		// When

		// Then

		mockMvc.perform(post(PostCommentController.REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(c)))
				.andExpect(status().isUnauthorized());

	}
}
