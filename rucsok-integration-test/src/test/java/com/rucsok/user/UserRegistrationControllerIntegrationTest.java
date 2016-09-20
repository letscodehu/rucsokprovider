package com.rucsok.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;
import com.rucsok.user.domain.User;
import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.user.service.exception.UserAlreadyPresentException;
import com.rucsok.user.view.controller.UserRegistrationController;
import com.rucsok.user.view.model.UserRegistrationRequest;
import com.rucsok.user.view.model.UserRegistrationResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@SpringBootTest
public class UserRegistrationControllerIntegrationTest {

	private static final String REQUEST_MAPPING = UserRegistrationController.REQUEST_MAPPING;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
	
	private String email = "test@email.com";
	private String username = "testuser";
	private String password = "password";

	private ObjectMapper mapper;

	private User user;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		mapper = new ObjectMapper();
		user = new User(email, username);
	}
	
	@Test
	public void postShouldReturnTheUser_When_CalledWithCorrectParameters() throws Exception {
		// Given
		UserRegistrationRequest request = new UserRegistrationRequest(email, username, password, password);
		// When
		MvcResult result = mockMvc.perform(post(REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsBytes(request)))
				.andExpect(status().isCreated())
				.andReturn();
		// Then
		String contentAsString = result.getResponse().getContentAsString();
		UserRegistrationResponse response = mapper.readValue(contentAsString, UserRegistrationResponse.class);
		
		Assert.assertEquals(username, response.getUser().getUsername());
		Assert.assertEquals(email, response.getUser().getEmail());
	}
	
	@Test
	public void postShouldReturnBadRequest_When_CalledWithNull() throws Exception {
		// Given
		// When
		ResultActions result = mockMvc.perform(post(REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsBytes(null)));
		// Then
		result.andExpect(status().isBadRequest());
	}
	
	@Test
	@Ignore
	public void postShouldReturnError_When_CalledWithNullEmail() throws Exception {
		// Given
		UserRegistrationRequest request = new UserRegistrationRequest(null, "wolverine00112", password, password);
		// When
		MvcResult result = mockMvc.perform(post(REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsBytes(request)))
				.andExpect(status().isCreated())
				.andReturn();
		// Then
		mapper.setSerializationInclusion(Include.NON_NULL);
		String contentAsString = result.getResponse().getContentAsString();
		UserRegistrationResponse response = mapper.readValue(contentAsString, UserRegistrationResponse.class);
		
		Assert.assertEquals(null, response.getUser());
		Assert.assertEquals("email may not be null", response.getError().getMessage());
	}
	
	@Test
	@Ignore
	public void postShouldReturnError_When_CalledWithEmptyEmail() throws Exception {
		// Given
		UserRegistrationRequest request = new UserRegistrationRequest("", "wolverine2", password, password);
		// When
		MvcResult result = mockMvc.perform(post(REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsBytes(request)))
				.andExpect(status().isCreated())
				.andReturn();
		// Then
		mapper.setSerializationInclusion(Include.NON_NULL);
		String contentAsString = result.getResponse().getContentAsString();
		UserRegistrationResponse response = mapper.readValue(contentAsString, UserRegistrationResponse.class);
		
		Assert.assertEquals(null, response.getUser());
		Assert.assertEquals("email may not be empty", response.getError().getMessage());
	}
	
	@Test
	@Ignore
	public void postShouldReturnError_When_CalledWithNullUsername() throws Exception {
		// Given
		UserRegistrationRequest request = new UserRegistrationRequest("asd@test.com", null, password, password);
		// When
		MvcResult result = mockMvc.perform(post(REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsBytes(request)))
				.andExpect(status().isCreated())
				.andReturn();
		// Then
		mapper.setSerializationInclusion(Include.NON_NULL);
		String contentAsString = result.getResponse().getContentAsString();
		UserRegistrationResponse response = mapper.readValue(contentAsString, UserRegistrationResponse.class);
		
		Assert.assertEquals(null, response.getUser());
		String message = response.getError().getMessage();
		Assert.assertEquals("username may not be null", message);
	}
	
	@Test
	@Ignore
	public void postShouldReturnError_When_CalledWithEmptyUsername() throws Exception {
		// Given
		UserRegistrationRequest request = new UserRegistrationRequest("asd1@test.com", "", password, password);
		// When
		MvcResult result = mockMvc.perform(post(REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsBytes(request)))
				.andExpect(status().isCreated())
				.andReturn();
		// Then
		mapper.setSerializationInclusion(Include.NON_NULL);
		String contentAsString = result.getResponse().getContentAsString();
		UserRegistrationResponse response = mapper.readValue(contentAsString, UserRegistrationResponse.class);
		
		Assert.assertEquals(null, response.getUser());
		Assert.assertEquals("username may not be empty", response.getError().getMessage());
	}
	
	
	@Test
	public void postShouldReturnError_When_CalledWithExistingUserName() throws Exception {
		// Given
		UserRegistrationRequest request = new UserRegistrationRequest("asdasd@email.hu", "rantotthus", password, password);
		// When
		mockMvc.perform(post(REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsBytes(request)))
				.andExpect(status().isCreated());
		// perform again with the same credentials
		MvcResult result = mockMvc.perform(post(REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsBytes(request)))
				.andExpect(status().isCreated())
				.andReturn();
		// Then
		mapper.setSerializationInclusion(Include.NON_NULL);
		String contentAsString = result.getResponse().getContentAsString();
		UserRegistrationResponse response = mapper.readValue(contentAsString, UserRegistrationResponse.class);
		
		Assert.assertEquals(null, response.getUser());
		Assert.assertEquals(UserAlreadyPresentException.MESSAGE, response.getError().getMessage());
	}
	
	@Test
	public void postShouldReturnError_When_CalledWithNotMatchingPassword() throws Exception {
		// Given
		UserRegistrationRequest request = new UserRegistrationRequest("asd3@test.com", "wolverine3", password, password + "notmatching");
		// When
		MvcResult result = mockMvc.perform(post(REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsBytes(request)))
				.andExpect(status().isCreated())
				.andReturn();
		// Then
		mapper.setSerializationInclusion(Include.NON_NULL);
		String contentAsString = result.getResponse().getContentAsString();
		UserRegistrationResponse response = mapper.readValue(contentAsString, UserRegistrationResponse.class);
		
		Assert.assertEquals(null, response.getUser());
		Assert.assertEquals("Passwords don't match", response.getError().getMessage());
	}
	
	@Test
	public void postShouldReturnError_When_CalledWithExistingEmail() throws Exception {
		// Given
		UserRegistrationRequest request = new UserRegistrationRequest("csavart@stex.hu", "crappy_ice_frut", password, password);
		// When
		mockMvc.perform(post(REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsBytes(request)))
				.andExpect(status().isCreated());
		
		// perform again with the same email
		
		request = new UserRegistrationRequest("csavart@stex.hu", "happy mail menu", password, password);
		
		MvcResult result = mockMvc.perform(post(REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsBytes(request)))
				.andExpect(status().isCreated())
				.andReturn();
		// Then
		mapper.setSerializationInclusion(Include.NON_NULL);
		String contentAsString = result.getResponse().getContentAsString();
		UserRegistrationResponse response = mapper.readValue(contentAsString, UserRegistrationResponse.class);
		
		Assert.assertEquals(null, response.getUser());
		Assert.assertEquals("Email csavart@stex.hu already taken!", response.getError().getMessage());
	}
}
