package com.rucsok.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rucsok.user.domain.User;
import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.user.service.exception.UserNotFoundException;
import com.rucsok.user.transform.UserTransformer;

@Component
public class UserProfileService {

	@Autowired
	private UserRepository userDao;

	@Autowired
	private UserTransformer userTranformer;

	public User getUserByName(String username) {
		Optional<UserEntity> findUserByName = findUserByName(username);
		if(!findUserByName.isPresent()){
			throw new UserNotFoundException("User with name " + username + " not found.");
		}
		return userTranformer.transformToUser(findUserByName.get());
	}

	private Optional<UserEntity> findUserByName(String username) {
		return Optional.ofNullable(userDao.findByName(username));
	}

}
