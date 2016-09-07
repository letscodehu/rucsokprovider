package com.rucsok.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.user.domain.User;
import com.rucsok.user.domain.UserRegistration;
import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.user.service.exception.NoUserGivenException;
import com.rucsok.user.service.exception.UserAlreadyPresentException;
import com.rucsok.user.transform.UserRegistrationServiceTransformer;
import com.rucsok.user.transform.UserTransformer;

@Service
public class UserRegistrationService {
	
	private UserRepository userRepository;
	private UserRegistrationServiceTransformer userRegistrationTransformer;
	private UserTransformer userTransformer;
	private UserCheckerService userCheckerService;
	
	@Autowired
	public UserRegistrationService(UserRepository userRepository,
			UserRegistrationServiceTransformer userRegistrationTransformer, UserTransformer userTransformer,
			UserCheckerService userCheckerService) {
		super();
		this.userRepository = userRepository;
		this.userRegistrationTransformer = userRegistrationTransformer;
		this.userTransformer = userTransformer;
		this.userCheckerService = userCheckerService;
	}


	
	public User registerUser(final UserRegistration user) {
		if (user == null) {
			throw new NoUserGivenException();	
		}
		if (userCheckerService.isUserExists(user.getUsername())) {
			throw new UserAlreadyPresentException();	
		}
		UserEntity userEntity = userRepository.save(userRegistrationTransformer.transformFromRegistration(user)); 
		return userTransformer.transformEntityToUser(userEntity);
	}

}
