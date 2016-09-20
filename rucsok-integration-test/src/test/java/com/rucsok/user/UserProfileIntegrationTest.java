package com.rucsok.user;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.rucsok.TokenHelper;
import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;
import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.user.repository.domain.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@SpringBootTest
public class UserProfileIntegrationTest {

	private static final String REQUEST_MAPPING = "/profile/";

	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private UserEntity user;

	private String accessToken;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		user = userDao.findByName("rucsok");
		accessToken = TokenHelper.getAccessToken("rucsok", "123", mockMvc);
	}

	@Test
	public void getShouldReturnOk() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING)
				.header("Authorization", "Bearer " + accessToken))
				.andExpect(status().isOk());
	}

	@Test
	public void getShouldReturnJson() throws Exception {
		// Given

		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING)
					.header("Authorization", "Bearer " + accessToken))
					.andExpect(content()
					.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	@WithUserDetails("rucsok")
	public void getShouldContainsCorrectData() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING)
				.header("Authorization", "Bearer " + accessToken))
				.andExpect(jsonPath("$.username", is(user.getName())));
	}

	@Test
	public void getShouldReturnUnauthorized_When_UserNotLoggedIn() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING))
				.andExpect(status().isUnauthorized());
	}

}
