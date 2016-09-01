package com.rucsok.vote;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rucsok.TokenHelper;
import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;
import com.rucsok.rucsok.repository.dao.VoteDao;
import com.rucsok.rucsok.repository.domain.VoteEntity;
import com.rucsok.rucsok.repository.domain.VotePK;
import com.rucsok.vote.view.controller.RucsokVoteController;
import com.rucsok.vote.view.model.RucsokVoteRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class, TestConfig.class})
@SpringBootTest
public class VoteControllerIntegrationTest {
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
	
	@Autowired
	private VoteDao voteDao;
	
	private ObjectMapper mapper;

	private String accessToken;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		mapper = new ObjectMapper();
		accessToken = TokenHelper.getAccessToken("rucsok", "123", mockMvc);
	}
	
	@Test
	public void voteWithoutLoginShouldReturnUnauthorizedWhenNotLoggedIn() throws Exception {

		// Given in setup
		
		// When
	
		mockMvc.perform(post(RucsokVoteController.REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(null)))
		
		// Then		
				.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void voteShouldReturnBadRequestWhenRequestIsNull() throws Exception {

		// Given in setup
		
		// When
		
		mockMvc.perform(post(RucsokVoteController.REQUEST_MAPPING)
				.header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(null)))
		
		// Then
		
				.andExpect(status().isBadRequest());
	}
	
	@Test
	@Transactional
	public void voteShouldCreateNewEntity() throws Exception {

		// Given
		
		Long rucsokId = Long.valueOf(4);
		String voteType = "UP";
		int userId = 1;
		RucsokVoteRequest request = new RucsokVoteRequest(rucsokId, voteType);
		
		// When
		
		mockMvc.perform(post(RucsokVoteController.REQUEST_MAPPING)
				.header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)))
				.andExpect(status().isCreated());
		
		VoteEntity result = voteDao.findOne(new VotePK(userId, rucsokId));
		
		// Then

		Assert.assertEquals("Votetype should match", voteType, result.getVoteType().toString());
		Assert.assertEquals("RucsokId should match", rucsokId.longValue(), result.getRucsok().getId());
		Assert.assertEquals("Votetype should match", userId, result.getUser().getId());
	}
}
