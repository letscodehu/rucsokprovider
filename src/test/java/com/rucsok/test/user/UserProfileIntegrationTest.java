package com.rucsok.test.user;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;

import com.rucsok.test.config.RepositoryConfig;
import com.rucsok.test.config.TestConfig;
import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.user.view.controller.UserProfileController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@WebIntegrationTest
public class UserProfileIntegrationTest {

	private static final String REQUEST_MAPPING = "/profile/";

	@Autowired
	private UserRepository userDao;

	@Autowired
	private UserProfileController profileController;
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private UserEntity user;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		user = userDao.findByName("rucsok");
	}

	@Test
	@WithUserDetails("rucsok")
	public void statusShouldOk() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING)).andExpect(status().isOk());
	}

	@Test
	@WithUserDetails("rucsok")
	public void contentShouldBeJson() throws Exception {
		// Given

		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING))
					.andExpect(content()
					.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	@WithUserDetails("rucsok")
	public void contentShouldContainsCorrectData() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING)).andExpect(jsonPath("$.username", is(user.getName())));
	}

	@Test
	public void contentShouldReturnBadRequestWhenUserNotLoggedIn() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING)).andExpect(status().isFound());
	}

}
