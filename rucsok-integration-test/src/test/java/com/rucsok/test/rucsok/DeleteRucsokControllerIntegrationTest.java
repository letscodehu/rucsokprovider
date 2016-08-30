package com.rucsok.test.rucsok;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rucsok.rucsok.repository.dao.RucsokDao;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.view.controller.DeleteRucsokController;
import com.rucsok.test.TokenHelper;
import com.rucsok.test.config.RepositoryConfig;
import com.rucsok.test.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class, TestConfig.class})
@WebIntegrationTest
public class DeleteRucsokControllerIntegrationTest {

	private static final String DELETE_ID = "4";

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private RucsokDao rucsokDao;

	private MockMvc mockMvc;

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
	public void deleteShouldReturnUnauthorizedWhenUserNotLoggedIn() throws Exception {

		// Given
		
		// When
		
		mockMvc.perform(delete(DeleteRucsokController.REQUEST_MAPPING)		
				 .contentType(MediaType.APPLICATION_JSON))
		 		 .andExpect(status().isUnauthorized());
		
		// Then

	}
	
	
	@Test
	@WithUserDetails("rucsok")
	public void deleteShouldReturnBadGatewayWhenEmptyStringId() throws Exception {

		// Given
		
		String nullParam = "";

		// When
		
		mockMvc.perform(delete(DeleteRucsokController.REQUEST_MAPPING)		
				 .header("Authorization", "Bearer " + accessToken)
				 .contentType(MediaType.APPLICATION_JSON)
				 .param("id", nullParam))
		 		 .andExpect(status().isBadRequest());
		
		// Then

	}
	
	@Test
	@WithUserDetails("rucsok")
	public void deleteShouldReturnBadGatewayWhenNullIdProvided() throws Exception {

		// Given
		
		String nullParam = null;

		// When
		
		mockMvc.perform(delete(DeleteRucsokController.REQUEST_MAPPING)		
				.header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON)
				.param("id", nullParam))
		        .andExpect(status()
		         .isBadRequest());
		
		// Then

	}

	@Test
	@WithUserDetails("rucsok")
	public void deleteShouldRemoveTheEntityFromRepository() throws Exception {

		// Given

		// When
		
		mockMvc.perform(delete(DeleteRucsokController.REQUEST_MAPPING)
				.header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON)
				.param("id", DELETE_ID))
		        .andExpect(status()
		        .isAccepted());
		
		RucsokEntity entity = rucsokDao.findOne(Long.valueOf(DELETE_ID));
		
		// Then

		Assert.assertNull(entity);
		
	}




}
