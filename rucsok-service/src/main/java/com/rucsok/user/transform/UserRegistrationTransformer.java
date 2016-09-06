package com.rucsok.user.transform;

import com.rucsok.user.domain.UserRegistration;
import com.rucsok.user.repository.domain.UserEntity;

public class UserRegistrationTransformer {

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
