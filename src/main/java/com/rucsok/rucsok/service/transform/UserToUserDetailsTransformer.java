package com.rucsok.rucsok.service.transform;

import com.rucsok.user.repository.*;
import org.springframework.stereotype.Component;
import com.rucsok.user.repository.domain.UserEntity;

@Component
public class UserToUserDetailsTransformer {

	public UserDetails transform(UserEntity user) {
		return new UserDetails(user.getName(), user.getPassword());
	}
	
	
}
