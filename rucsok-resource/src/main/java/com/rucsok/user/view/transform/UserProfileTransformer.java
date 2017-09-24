package com.rucsok.user.view.transform;

import org.springframework.stereotype.Component;

import com.rucsok.user.domain.User;
import com.rucsok.user.view.model.UserProfileView;

@Component
public class UserProfileTransformer {

	public UserProfileView transformToUserProfileView(User user) {
		UserProfileView result = new UserProfileView();
		result.setUsername(user.getUsername());
		result.setEmail(user.getEmail());
		return result;
	}

}
