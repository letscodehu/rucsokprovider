package com.rucsok.test.login.service;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rucsok.login.service.LoginService;
import com.rucsok.login.service.config.LoginServiceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LoginService.class, LoginServiceConfig.class })
public class LoginServiceIntegrationTest {

	private static final String USERNAME = "rucsok";
	private static final String PASSWORD = "123";

	@Autowired
	private String oauth2TokenUri;

	@Autowired
	private LoginService underTest;

	@Autowired
	private RestTemplate accessTokenRestTemplate;

	private MockRestServiceServer mockServer;
	private ObjectMapper mapper;

	@Before
	public void setUp() throws IOException {
		mockServer = MockRestServiceServer.createServer(accessTokenRestTemplate);
		mapper = new ObjectMapper();
	}

	@Test
	public void tokenAccessUriShouldReturnAccessToken() throws JsonProcessingException, IOException {
		
		// Given		
		String accessToken = "{\"access_token\":\"afd810a0-0ef5-464a-9356-080637692184\",\"token_type\":\"bearer\",\"refresh_token\":\"d18fbb5a-9861-46b7-b7d0-b9d9db5d0373\",\"expires_in\":38366,\"scope\":\"read\"}";
		
		// When
		
		mockServer.expect(requestTo(oauth2TokenUri))
			.andExpect(method(HttpMethod.POST))
			.andRespond(withSuccess(accessToken, MediaType.APPLICATION_JSON_UTF8));
		
		JsonNode responseJson = underTest.accessToken(USERNAME, PASSWORD);		
		JsonNode accessTokenAsJsonNode = mapper.readTree(accessToken);

		// Then
		
		Assert.assertEquals(accessTokenAsJsonNode, responseJson);
	}

}
