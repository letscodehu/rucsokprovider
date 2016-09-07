package com.rucsok.user.transform;

import org.springframework.stereotype.Component;

import com.rucsok.user.domain.User;
import com.rucsok.user.repository.domain.UserEntity;

@Component
public class UserTransformer {

	public User transformEntityToUser(UserEntity userEntity) {
		User result = new User();
		result.setEmail(userEntity.getEmail());
		result.setUsername(userEntity.getName());
		return result;
	}
	
	public UserEntity transformUserToEntity(User userDTO) {
		UserEntity result = new UserEntity();
		result.setEmail(userDTO.getEmail());
		result.setName(userDTO.getUsername());
		return result;
	}
	
}
