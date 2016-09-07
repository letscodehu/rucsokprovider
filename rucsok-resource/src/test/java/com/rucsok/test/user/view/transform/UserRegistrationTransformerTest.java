package com.rucsok.test.user.view.transform;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.Assert;

import com.rucsok.test.config.UserRegistrationConfig;
import com.rucsok.user.domain.User;
import com.rucsok.user.domain.UserRegistration;
import com.rucsok.user.service.exception.NoUserGivenException;
import com.rucsok.user.view.model.UserProfileView;
import com.rucsok.user.view.model.UserRegistrationError;
import com.rucsok.user.view.model.UserRegistrationRequest;
import com.rucsok.user.view.model.UserRegistrationResponse;
import com.rucsok.user.view.transform.UserRegistrationTransformer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { UserRegistrationTransformer.class, UserRegistrationConfig.class})
public class UserRegistrationTransformerTest {

	@Autowired
	private PasswordEncoder mockEncoder;
	
	@Autowired
	private UserRegistrationTransformer transformer;
	
	private final String testEmail = "test@email.com";
	private final String testUsername = "testuser";
	private final String testPassword = "testpass";
	private final String testPasswordConfirmation = "testpass";
	private final String encodedPassword = "encodedPass";
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	
	@Test
	public void itShouldTransformFromUserRegistrationRequestToUserRegistration() {
		// GIVEN
		UserRegistrationRequest request = new UserRegistrationRequest(
				testEmail, testUsername, testPassword, testPasswordConfirmation);
		Mockito.when(mockEncoder.encode(testPassword)).thenReturn(encodedPassword);
		// WHEN
		UserRegistration user = transformer.transformToRegistration(request);
		// THEN
		
		Assert.assertEquals(user.getEmail(), testEmail);
		Assert.assertEquals(user.getPassword(), encodedPassword);
		Assert.assertEquals(user.getUsername(), testUsername);
	}
	
	@Test
	public void shouldThrowExceptionOnNullRequest() {
		// GIVEN
		expectedEx.expect(IllegalArgumentException.class);
		UserRegistrationRequest request = null;
		// WHEN
		transformer.transformToRegistration(request);
		
		// THEN
	}
	
	@Test
	public void shouldReturnResponseWithUserInformation() {
		// GIVEN
		final User user = new User(testEmail, testUsername);
		// WHEN

		UserRegistrationResponse response = transformer.transformToResponse(user); 
		
		// THEN
		UserProfileView userView = response.getUser();
		Assert.assertEquals(userView.getEmail(), testEmail);
		Assert.assertEquals(userView.getUsername(), testUsername);
	}
	
	
	@Test
	public void shouldThrowExceptionOnNullUserInformation() {
		// GIVEN
		expectedEx.expect(IllegalArgumentException.class);
		User user = null;
		// WHEN
		transformer.transformToResponse(user);
		// THEN
	}
	
	@Test
	public void shouldConvertToResponseWhenExceptionGiven() {
		// GIVEN
		NoUserGivenException exception = new NoUserGivenException();
		// WHEN
		UserRegistrationResponse response = transformer.transformToResponse(exception);
		UserRegistrationError error = response.getError();
		// THEN
		Assert.assertEquals(error.getMessage(), exception.getMessage());
	}

}
