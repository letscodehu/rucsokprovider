package com.rucsok.rucsok.service.transform;

import com.rucsok.user.repository.*;
import org.springframework.stereotype.Component;
import com.rucsok.user.repository.domain.User;

@Component
public class UserToUserDetailsTransformer {

	public UserDetails transform(User user) {
		return new UserDetails(user.getName(), user.getPassword());
	}
	
	
}
