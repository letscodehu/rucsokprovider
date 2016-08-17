package com.rucsok.test.login;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rucsok.RucsokAuthServerApp;
import com.rucsok.rucsok.repository.dao.RucsokDao;
import com.rucsok.test.config.RepositoryConfig;
import com.rucsok.test.config.TestConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class, RucsokAuthServerApp.class })
@WebIntegrationTest
public class LoginIntegrationTest {

	@Autowired
	private RucsokDao rucsokDao;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private ObjectMapper mapper;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		mapper = new ObjectMapper();
	}
	
	@Test
	public void restLogin(){
		
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
		String authorization = "Basic "
				+ new String(Base64Utils.encode("clientapp:123456".getBytes()));
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("Authorization", authorization);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("username", "rucsok");
		map.add("password", "123");
		map.add("grant_type", "password");
		map.add("scope", "read");
		map.add("client_id", "clientapp");
		map.add("client_secret", "123456");

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		String postForObject = restTemplate.postForObject("http://localhost:9999/oauth/token", entity,	String.class);
		
		System.out.println(postForObject);
	}
	
	@Test
	public void mockedRestLogin(){
		
		String accessToken =  "{\"access_token\":\"afd810a0-0ef5-464a-9356-080637692184\",\"token_type\":\"bearer\",\"refresh_token\":\"d18fbb5a-9861-46b7-b7d0-b9d9db5d0373\",\"expires_in\":38366,\"scope\":\"read\"}";

		RestTemplate restTemplate = new RestTemplate();
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
		
		   mockServer.expect(requestTo("http://localhost:9999/oauth/token"))
           .andExpect(method(HttpMethod.POST))
           .andRespond(withSuccess(accessToken, MediaType.TEXT_PLAIN));

		
		restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
		String authorization = "Basic "
				+ new String(Base64Utils.encode("clientapp:123456".getBytes()));
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("Authorization", authorization);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("username", "rucsok");
		map.add("password", "123");
		map.add("grant_type", "password");
		map.add("scope", "read");
		map.add("client_id", "clientapp");
		map.add("client_secret", "123456");

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//
		String postForObject = restTemplate.postForObject("http://localhost:9999/oauth/token", entity,
				String.class);
		
		System.out.println(postForObject);
		Assert.assertEquals(accessToken, postForObject);
	}
	
	@Test
	public void getAccessToken() throws Exception {
		String authorization = "Basic "
				+ new String(Base64Utils.encode("clientapp:123456".getBytes()));
		String contentType = MediaType.APPLICATION_JSON + ";charset=UTF-8";
		
		 mockMvc.perform(
						post("/oauth/token")
								.header("Authorization", authorization)
								.contentType(
										MediaType.APPLICATION_FORM_URLENCODED)
								.param("username", "rucsok")
								.param("password", "123")
								.param("grant_type", "password")
								.param("scope", "read write")
								.param("client_id", "clientapp")
								.param("client_secret", "123456"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.access_token", is(notNullValue())))
				.andExpect(jsonPath("$.token_type", is(equalTo("bearer"))))
				.andExpect(jsonPath("$.refresh_token", is(notNullValue())))
				.andExpect(jsonPath("$.expires_in", is(greaterThan(4000))))
				.andExpect(jsonPath("$.scope", is(equalTo("read write"))));				

	}
	
	@Test
	public void login() throws Exception {
		
//		 RestTemplate restTemplate = new RestTemplate();
//				 MockRestServiceServer server = MockRestServiceServer.bindTo(restTemplate).build();
		
		String contentType = MediaType.APPLICATION_JSON + ";charset=UTF-8";
		
		 mockMvc.perform(
						post("/login")
								.contentType(
										MediaType.APPLICATION_FORM_URLENCODED)
								.param("username", "rucsok")
								.param("password", "123"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType));

	}

	@Test
	@Ignore
	public void postLoginShouldReturnAcceptWhenUsingCorrectCredentials() throws Exception {

		// Given

		// When

		mockMvc.perform(post("/login")
					.with(csrf())
					.param("sec-user", "rucsok")
					.param("sec-password", "123"))		
					.andExpect(status().isOk());

		// Then
	}

	@Test
	@Ignore
	public void postLoginShouldReturnUnauthorizedWhenUsingWrongCredentials() throws Exception {

		// Given

		// When

		mockMvc.perform(post("/login").with(csrf()).param("sec-user", "asd").param("sec-password", "qwe"))
				.andExpect(status().isUnauthorized());

		// Then
	}
}
