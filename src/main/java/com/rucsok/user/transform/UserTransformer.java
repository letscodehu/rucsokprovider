package com.rucsok.user.transform;

import org.springframework.stereotype.Component;

import com.rucsok.user.domain.User;
import com.rucsok.user.repository.domain.UserEntity;

@Component
public class UserTransformer {

	public User transformToUser(UserEntity userEntity) {
		User result = new User();
		result.setEmail(userEntity.getEmail());
		result.setUsername(userEntity.getName());
		return result;
	}
}
