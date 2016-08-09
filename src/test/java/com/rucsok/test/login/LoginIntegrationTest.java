package com.rucsok.test.login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rucsok.authenticaiton.config.WebSecurityConfig;
import com.rucsok.rucsok.repository.dao.RucsokDao;
import com.rucsok.rucsok.view.controller.PostRucsokController;
import com.rucsok.test.config.RepositoryConfig;
import com.rucsok.test.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class, TestConfig.class})
@WebIntegrationTest
public class LoginIntegrationTest {

	@Autowired
	private RucsokDao rucsokDao;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private FilterChainProxy filterChainProxy;

	private MockMvc mockMvc;

	private ObjectMapper mapper;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders
					.webAppContextSetup(context)
					.dispatchOptions(true)
					.addFilters(filterChainProxy)
					.build();
		mapper = new ObjectMapper();
	}

	@Test
	public void postLoginShouldReturnAccept() throws Exception {

		// Given

		// When

		// Then

//		mockMvc.perform(post(PostRucsokController.REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON)
//				.content(mapper.writeValueAsString(null))).andExpect(status().isOk());

	}
}
