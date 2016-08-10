package com.rucsok.test.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rucsok.test.config.RepositoryConfig;
import com.rucsok.test.config.TestConfig;
import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.user.view.controller.UserProfileController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@WebIntegrationTest
public class UserProfileIntegrationTest {

	@Autowired
	private UserRepository userDao;

	@Autowired
	private UserProfileController profileController;

	private MockMvc mockMvc;

	private UserEntity user;

	private String requestMapping;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
		user = userDao.findOne(Long.valueOf(1));
		requestMapping = "/profile/" + user.getName();
	}

	@Test
	public void statusShouldOk() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(requestMapping)).andExpect(status().isOk());
	}

	@Test
	public void contentShouldBeJson() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(requestMapping)).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void contentShouldContainsCorrectData() throws Exception {
		 // Given
		 // When
		 // Then
		 mockMvc.perform(get(requestMapping))
		 		.andExpect(jsonPath("$.username", is(user.getName())));
	}
	
	@Test
	public void contentShouldReturn() throws Exception {
		 // Given
		 // When
		 // Then
		 mockMvc.perform(get("/profile/asdqwe"))
		 		.andExpect(status().isBadRequest());
	}

}
