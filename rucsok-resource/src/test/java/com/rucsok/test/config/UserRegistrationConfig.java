package com.rucsok.test.config;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;

import com.rucsok.user.service.UserRegistrationService;

public class UserRegistrationConfig {

	@Mock
	private UserRegistrationService userRegistrationService;

	public UserRegistrationConfig() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Bean
	public UserRegistrationService userRegistrationService() {
		return userRegistrationService;
	}
	
	
	
}
