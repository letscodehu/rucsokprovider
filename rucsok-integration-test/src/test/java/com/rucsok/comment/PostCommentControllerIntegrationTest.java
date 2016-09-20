package com.rucsok.comment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.rucsok.comment.repository.dao.CommentRepository;
import com.rucsok.comment.repository.domain.CommentEntity;
import com.rucsok.comment.view.controller.PostCommentController;
import com.rucsok.comment.view.model.CommentInsertRequest;
import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@SpringBootTest
@TestPropertySource("classpath:/application.properties")
public class PostCommentControllerIntegrationTest {

	private static final String TEST_TEXT = "test";

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private String accessToken;

	private ObjectMapper mapper;

	private CommentEntity postedComment;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		mapper = new ObjectMapper();
		accessToken = TokenHelper.getAccessToken("rucsok", "123", mockMvc);
	}

	@After
	public void tearDown() {
		if (null != postedComment) {
			commentRepo.delete(postedComment);
		}
	}

	@Test
	@Transactional
	public void postShouldSaveComment_When_UserLoggedIn() throws Exception {

		// Given

		CommentInsertRequest request = new CommentInsertRequest();
		request.setRucsokId(1);
		request.setText(TEST_TEXT);

		// When

		mockMvc.perform(post(PostCommentController.REQUEST_MAPPING).header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(request)))
				.andExpect(status().isCreated());

		// Then

		Page<CommentEntity> commentPage = commentRepo.findByRucsokIdAndParentNullOrderByCreatedAt(1,
				new PageRequest(0, 10));
		postedComment = commentPage.getContent().get(commentPage.getContent().size() - 1);

		Assert.assertNotNull("Entity should'nt be null", postedComment);
		Assert.assertEquals("Text should match", TEST_TEXT, postedComment.getText());
		Assert.assertEquals("Id should match", 1, postedComment.getRucsok().getId());
	}

	@Test
	public void postShouldSaveCommentToParent_When_UserLoggedIn() throws Exception {

		// Given

		CommentInsertRequest request = new CommentInsertRequest();
		int rucsokId = 1;
		int parentId = 9;
		request.setRucsokId(rucsokId);
		request.setParentId(parentId);
		request.setText(TEST_TEXT);

		// When

		mockMvc.perform(post(PostCommentController.REQUEST_MAPPING).header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(request)))
				.andExpect(status().isCreated());

		// Then

		Page<CommentEntity> commentPage = commentRepo.findByParentIdOrderByCreatedAt(parentId, new PageRequest(0, 10));
		postedComment= commentPage.getContent().get(commentPage.getContent().size() - 1);

		Assert.assertNotNull("Entity should'nt be null", postedComment);
		Assert.assertEquals("Text should match", TEST_TEXT, postedComment.getText());
		Assert.assertEquals("Id should match", rucsokId, postedComment.getRucsok().getId());
	}

	@Test
	public void postShouldReturnBadRequest_When_TextNotProvided() throws Exception {

		// Given

		CommentInsertRequest request = new CommentInsertRequest();
		int rucsokId = 1;
		int parentId = 8;
		request.setRucsokId(rucsokId);
		request.setParentId(parentId);

		// When

		// Then

		mockMvc.perform(post(PostCommentController.REQUEST_MAPPING).header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(request)))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void postShouldReturnBadRequest_When_RucsokIdNotProvided() throws Exception {

		// Given

		CommentInsertRequest request = new CommentInsertRequest();
		int parentId = 8;
		request.setParentId(parentId);
		request.setText(TEST_TEXT);

		// When

		// Then

		mockMvc.perform(post(PostCommentController.REQUEST_MAPPING).header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(request)))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void postShouldReturnBadRequest_When_RucsokIsNotExists() throws Exception {

		// Given

		CommentInsertRequest request = new CommentInsertRequest();
		int parentId = 8;
		request.setRucsokId(1000);
		request.setParentId(parentId);
		request.setText(TEST_TEXT);

		// When

		// Then

		mockMvc.perform(post(PostCommentController.REQUEST_MAPPING).header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(request)))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void postShouldReturnBadRequest_When_ParentIsNotExists() throws Exception {

		// Given

		CommentInsertRequest request = new CommentInsertRequest();
		int parentId = 8000;
		int rucsokId = 1;
		request.setRucsokId(rucsokId);
		request.setParentId(parentId);
		request.setText(TEST_TEXT);

		// When

		// Then

		mockMvc.perform(post(PostCommentController.REQUEST_MAPPING).header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(request)))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void postShouldReturnUnauthorized_When_UserNotLoggedIn() throws Exception {

		// Given

		CommentInsertRequest request = new CommentInsertRequest();

		// When

		// Then

		mockMvc.perform(post(PostCommentController.REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(request))).andExpect(status().isUnauthorized());

	}
}
