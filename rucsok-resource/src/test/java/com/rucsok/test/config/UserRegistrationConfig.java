package com.rucsok.test.config;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;

import com.rucsok.user.service.UserRegistrationService;

public class UserRegistrationConfig {

	@Mock
	private UserRegistrationService userRegistrationService;

	@Mock
	private PasswordEncoder passwordEncoder;
	
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
	public PasswordEncoder passwordEncoder() {
		return passwordEncoder;
	}
	
	
	@Bean
	public UserRegistrationService userRegistrationService() {
		return userRegistrationService;
	}
	
	
	
}
