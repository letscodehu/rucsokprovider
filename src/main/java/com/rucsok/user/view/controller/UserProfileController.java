package com.rucsok.user.view.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.user.service.UserProfileService;
import com.rucsok.user.view.model.UserProfileView;
import com.rucsok.user.view.transform.UserProfileTransform;

@RestController
public class UserProfileController {
	
	public static final String REQUEST_MAPPING = "/profile";
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private UserProfileTransform userProfileTransformer;

	@RequestMapping(name = "profile", path = REQUEST_MAPPING)
	public UserProfileView userProfileView(Principal principal) {
		return userProfileTransformer.transformToUserProfileView(userProfileService.getUserByName(principal.getName()));
	}

}
