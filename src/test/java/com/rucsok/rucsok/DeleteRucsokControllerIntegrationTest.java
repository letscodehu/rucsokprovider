package com.rucsok.rucsok;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rucsok.request.RucsokInsertRequest;
import com.rucsok.rucsok.repository.dao.RucsokDao;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.view.controller.DeleteRucsokController;
import com.rucsok.rucsok.view.controller.PostRucsokController;
import com.rucsok.rucsok.view.model.RucsokPost;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
@WebIntegrationTest
public class DeleteRucsokControllerIntegrationTest {

	private static final String DELETE_ID = "1";

	@Autowired
	private DeleteRucsokController deleteRucsokController;

	@Autowired
	private RucsokDao rucsokDao;

	private MockMvc mockMvc;

	private ObjectMapper mapper;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(deleteRucsokController).build();
		mapper = new ObjectMapper();
	}
	
	@Test
	public void deleteShouldReturnBadGatewayWhenEmptyStringId() throws Exception {

		// Given
		
		String nullParam = "";

		// When
		
		mockMvc.perform(delete(DeleteRucsokController.REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON)
				.param("id", nullParam)).andExpect(status().isBadRequest());
		
		// Then

	}
	
	@Test
	public void deleteShouldReturnBadGatewayWhenNullIdProvided() throws Exception {

		// Given
		
		String nullParam = null;

		// When
		
		mockMvc.perform(delete(DeleteRucsokController.REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON)
				.param("id", nullParam)).andExpect(status().isBadRequest());
		
		// Then

	}

	@Test
	public void deleteShouldRemoveTheEntityFromRepository() throws Exception {

		// Given

		// When
		
		mockMvc.perform(delete(DeleteRucsokController.REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON)
				.param("id", DELETE_ID)).andExpect(status().isAccepted());
		
		RucsokEntity entity = rucsokDao.findOne(Long.valueOf(DELETE_ID));
		
		// Then

		Assert.assertNull(entity);
		
	}




}
