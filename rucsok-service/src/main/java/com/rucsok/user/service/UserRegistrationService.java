package com.rucsok.user.service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.user.domain.User;
import com.rucsok.user.domain.UserRegistration;
import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.user.service.exception.EmailAlreadyTakenException;
import com.rucsok.user.service.exception.NoUserGivenException;
import com.rucsok.user.service.exception.UserAlreadyPresentException;
import com.rucsok.user.transform.UserRegistrationServiceTransformer;
import com.rucsok.user.transform.UserTransformer;

@Service
public class UserRegistrationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationService.class);

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

	@Transactional(value = TxType.REQUIRES_NEW)
	public User registerUser(final UserRegistration user) {
		if (user == null) {
			throw new NoUserGivenException();
		}
		LOGGER.error(user.getUsername());
		LOGGER.error(user.getEmail());
		if (userCheckerService.isUserExists(user.getUsername())) {
			throw new UserAlreadyPresentException();
		}
		if (userCheckerService.isEmailExists(user.getEmail())) {
			throw new EmailAlreadyTakenException(user.getEmail());
		}
		UserEntity userEntity = userRepository.save(userRegistrationTransformer.transformFromRegistration(user));
		return userTransformer.transformEntityToUser(userEntity);
	}

}
