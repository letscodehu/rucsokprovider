package com.rucsok.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.user.repository.domain.UserEntity;

@Service
public class UserCheckerService {

	private UserRepository userRepository;

	@Autowired
	public void setUserRepo(UserRepository userRepo) {
		this.userRepository = userRepo;
	}

	public UserEntity findUserByName(String username) {
		return userRepository.findByName(username);
	}
	
	public boolean isUserExists(String username) {
		return null != findUserByName(username);
	}
}
