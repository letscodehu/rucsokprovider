package com.rucsok.login;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;
import com.rucsok.login.service.config.LoginServiceConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class, LoginServiceConfig.class })
@SpringBootTest
public class LoginIntegrationTest {

	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private String oauth2TokenUri;
	
	@Autowired
	private RestTemplate accessTokenRestTemplate;

	private MockRestServiceServer mockServer;
	
	private MockMvc mockMvc;

	private ObjectMapper mapper;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		mockServer = MockRestServiceServer.createServer(accessTokenRestTemplate);
		mapper = new ObjectMapper();
	}
	
	@Test
	public void loginShouldReturnCorrectAcceptToken() throws Exception {
		
		// Given
				
		MockAccessTokenResponse accessTokenResponse = new MockAccessTokenResponse();
		String accessToken = mapper.writeValueAsString(accessTokenResponse);
		
		// When
		
		mockServer.expect(requestTo(oauth2TokenUri))
				.andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess(accessToken, MediaType.APPLICATION_JSON_UTF8));

		// Then
		
		mockMvc.perform(
						post("/login")
								.contentType(
										MediaType.APPLICATION_FORM_URLENCODED)
								.param(USERNAME, "rucsok")
								.param(PASSWORD, "123"))
						.andExpect(status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
						.andExpect(jsonPath("$.access_token", is(accessTokenResponse.access_token)))
						.andExpect(jsonPath("$.token_type", is(equalTo(accessTokenResponse.token_type))))
						.andExpect(jsonPath("$.refresh_token", is(accessTokenResponse.refresh_token)))
						.andExpect(jsonPath("$.expires_in", is(accessTokenResponse.expires_in)))
						.andExpect(jsonPath("$.scope", is(equalTo(accessTokenResponse.scope))));	

	}

	@Test
	public void postLoginShouldReturnOkWhenUsingCorrectCredentials() throws Exception {
		
		// Given	
		
		String accessToken = mapper.writeValueAsString(new MockAccessTokenResponse());
		// When
		
		mockServer.expect(requestTo(oauth2TokenUri))
			.andExpect(method(HttpMethod.POST))
			.andRespond(withSuccess(accessToken, MediaType.APPLICATION_JSON_UTF8));

		// Then
		
		mockMvc.perform(
					post("/login")
					.contentType(
							MediaType.APPLICATION_FORM_URLENCODED)
					.param(USERNAME, "rucsok")
					.param(PASSWORD, "123"))		
					.andExpect(status().isOk());
	}

	@Test
	public void postLoginShouldReturnBadRequestWhenUsingWrongCredentials() throws Exception {

		// Given		
		
		// When
		
		mockServer.expect(requestTo(oauth2TokenUri))
			.andExpect(method(HttpMethod.POST))
			.andRespond(withBadRequest());

		// Then
		
		mockMvc.perform(
					post("/login")
					.contentType(
							MediaType.APPLICATION_FORM_URLENCODED)
					.param(USERNAME, "rucsok")
					.param(PASSWORD, "asdasdqwe"))		
					.andExpect(status().isBadRequest());
	}
	
	protected class MockAccessTokenResponse{
		public String access_token =  "afd810a0-0ef5-464a-9356-080637692184";
		public String token_type =  "bearer";
		public String refresh_token =  "d18fbb5a-9861-46b7-b7d0-b9d9db5d0373";
		public int expires_in =  383663;
		public String scope = "read write";				
	}
}
