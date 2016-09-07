package com.rucsok.test.config;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindingResult;

import com.rucsok.user.service.UserRegistrationService;

public class UserRegistrationConfig {

	@Mock
	private UserRegistrationService userRegistrationService;

	@Mock
	private BindingResult mockValidationResult;
	
	
	public UserRegistrationConfig() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Bean
	public BindingResult mockValidationResult() {
		return mockValidationResult;
	}
	
	
	@Bean
	public UserRegistrationService userRegistrationService() {
		return userRegistrationService;
	}
	
	
	
}
