package com.rucsok.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.user.repository.domain.UserEntity;

@Service
public class UserService {

	private UserRepository userRepo;

	@Autowired
	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public UserEntity findUserByName(String username) {
		return userRepo.findByName(username);
	}
	
	public boolean isUserExists(String username) {
		return null != findUserByName(username);
	}
}
