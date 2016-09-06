package com.rucsok.test.user.service;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.test.config.UserRegistrationServiceConfig;
import com.rucsok.user.domain.User;
import com.rucsok.user.domain.UserRegistration;
import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.user.service.UserCheckerService;
import com.rucsok.user.service.UserRegistrationService;
import com.rucsok.user.service.exception.NoUserGivenException;
import com.rucsok.user.service.exception.UserAlreadyPresentException;
import com.rucsok.user.transform.UserRegistrationTransformer;
import com.rucsok.user.transform.UserTransformer;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UserRegistrationServiceConfig.class, UserRegistrationService.class, UserTransformer.class, UserRegistrationTransformer.class })
public class UserRegistrationServiceTest {

	private final String testEmail = "test@email.com";

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Autowired
	private UserRepository mockUserRepository;
	
	@Autowired
	private UserRegistrationService registrationService;
	
	@Autowired
	private UserCheckerService mockCheckerService;
	
	private final String testUsername = "testuser";
	
	private final String testPassword = "testpass";
	
	@Test
	public void itThrowsExceptionWhenNullGiven() {
		// GIVEN
		final UserRegistration userRegistration = null;
		expectedEx.expect(NoUserGivenException.class);
		expectedEx.expectMessage(NoUserGivenException.MESSAGE);
		// WHEN
		
		registrationService.registerUser(userRegistration);
		
		// THEN in expected
		
	}
	
	@Test
	public void itThrowsExceptionWhenUserIsPresent() {
		// GIVEN
		Mockito.when(mockCheckerService.isUserExists(testUsername)).thenReturn(true);
		expectedEx.expect(UserAlreadyPresentException.class);
		expectedEx.expectMessage(UserAlreadyPresentException.MESSAGE);
		final UserRegistration user = new UserRegistration(testEmail, testUsername, testPassword);

		// WHEN

		registrationService.registerUser(user);
		
		// THEN in expected
	}

	@Test
	public void itReturnsTheUserAfterSuccessfulRegistration() {
		// GIVEN
		final UserEntity mockEntity = new UserEntity(testUsername, testEmail);
		final UserRegistration user = new UserRegistration(testEmail, testUsername, testPassword);
		
		Mockito.when(mockCheckerService.isUserExists(testUsername)).thenReturn(false);
		ArgumentCaptor<UserEntity> entityCaptor = ArgumentCaptor.forClass(UserEntity.class);
		Mockito.when(mockUserRepository.save(Mockito.any(UserEntity.class))).thenReturn(mockEntity);
		
		// WHEN
		User registeredUser = registrationService.registerUser(user);
		
		// THEN
		Assert.assertEquals(testUsername, registeredUser.getUsername());
		Assert.assertEquals(testEmail, registeredUser.getEmail());
		Mockito.verify(mockUserRepository).save(entityCaptor.capture());
		Assert.assertEquals(testEmail, entityCaptor.getValue().getEmail());
		Assert.assertEquals(testUsername, entityCaptor.getValue().getUsername());
		Assert.assertEquals(testPassword, entityCaptor.getValue().getPassword());
	}
	

}
