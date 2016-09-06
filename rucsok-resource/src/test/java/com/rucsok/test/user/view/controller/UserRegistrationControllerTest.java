package com.rucsok.test.user.view.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.Assert;

import com.rucsok.test.config.UserRegistrationConfig;
import com.rucsok.user.domain.User;
import com.rucsok.user.domain.UserRegistration;
import com.rucsok.user.service.UserRegistrationService;
import com.rucsok.user.service.exception.NoUserGivenException;
import com.rucsok.user.service.exception.UserAlreadyPresentException;
import com.rucsok.user.view.controller.UserRegistrationController;
import com.rucsok.user.view.model.UserProfileView;
import com.rucsok.user.view.model.UserRegistrationError;
import com.rucsok.user.view.model.UserRegistrationRequest;
import com.rucsok.user.view.model.UserRegistrationResponse;
import com.rucsok.user.view.transform.UserRegistrationTransformer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { UserRegistrationConfig.class, UserRegistrationController.class, UserRegistrationTransformer.class})
public class UserRegistrationControllerTest {

	@Autowired
	private UserRegistrationService mockService;

	@Autowired
	private UserRegistrationController controller;

	private String email = "test@email.com";
	private String username = "testuser";
	private String password = "password";
	private String distinctPassword = "distinctPassword";
	
	@Test
	public void itShouldReturnErrorWithNullParameter() {
		// GIVEN
		
		final UserRegistrationRequest request = null;
		// WHEN

		UserRegistrationResponse response = controller.register(request);
		UserRegistrationError error = response.getError();
		// THEN

		Assert.assertEquals(response.getUser(), null);
		Assert.assertEquals(error.getMessage(), NoUserGivenException.MESSAGE);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void itShouldReturnErrorWhenUserExists() {
		// GIVEN

		final UserRegistrationRequest request = new UserRegistrationRequest(email, username, password, password);
		Mockito.when(mockService.registerUser(Mockito.any(UserRegistration.class))).thenThrow(new UserAlreadyPresentException());
		// WHEN

		UserRegistrationResponse response = controller.register(request);
		UserRegistrationError error = response.getError();
		// THEN

		Assert.assertEquals(response.getUser(), null);
		Assert.assertEquals(error.getMessage(), UserAlreadyPresentException.MESSAGE);
	}
	
	@Test
	public void itShouldReturnTheUserInTheResponse() {
		// GIVEN
		final UserRegistrationRequest request = new UserRegistrationRequest(email, username, password, password);
		final User user = new User(email, username);
		Mockito.when(mockService.registerUser(Mockito.any(UserRegistration.class))).thenReturn(user);
		// WHEN
		UserRegistrationResponse response = controller.register(request);
		UserProfileView userView = response.getUser();
		// THEN
		
		Assert.assertEquals(userView.getUsername(), username);
		Assert.assertEquals(userView.getEmail(), email);
	}
	
}
