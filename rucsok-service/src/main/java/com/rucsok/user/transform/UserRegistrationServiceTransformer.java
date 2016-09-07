package com.rucsok.user.transform;

import org.springframework.stereotype.Component;

import com.rucsok.user.domain.UserRegistration;
import com.rucsok.user.repository.domain.UserEntity;

@Component
public class UserRegistrationServiceTransformer {

	public UserEntity transformFromRegistration(UserRegistration registration) {
		if (registration == null) {
			throw new IllegalArgumentException();
		}		
		return new UserEntity(registration.getUsername(), registration.getEmail(), registration.getPassword());
	}

	public UserRegistration transformFromEntity(UserEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException();
		}		
		return new UserRegistration(entity.getEmail(), entity.getName(), entity.getPassword());
	}

}
