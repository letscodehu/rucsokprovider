package com.rucsok.test.config;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;

import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.user.service.UserCheckerService;
import com.rucsok.user.transform.UserTransformer;

public class UserRegistrationServiceConfig {

	@Mock
	private UserCheckerService userCheckerService;

	@Mock
	private UserRepository userRepository;
	
	@Bean
	public UserRepository userRepository() {
		return userRepository;
	}
	
	public UserRegistrationServiceConfig() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Bean	
	public UserCheckerService userService() {
		return userCheckerService;
	}
	
	
}
