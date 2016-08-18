package com.rucsok.user.view.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.user.service.UserProfileService;
import com.rucsok.user.view.model.UserProfileView;
import com.rucsok.user.view.transform.UserProfileTransform;

@RestController
public class UserProfileController {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(UserProfileController.class);

	public static final String REQUEST_MAPPING = "/profile";

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private UserProfileTransform userProfileTransformer;

	@RequestMapping(name = "profile", path = REQUEST_MAPPING)
	public UserProfileView userProfileView(Principal principal) {
		if (null == principal) {
			throw new UnauthenticatedException();
		}
		return userProfileTransformer.transformToUserProfileView(userProfileService.getUserByName(principal.getName()));
	}

	@SuppressWarnings("serial")
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	protected class UnauthenticatedException extends RuntimeException {

		public UnauthenticatedException() {
			super();
		}

		public UnauthenticatedException(String message) {
			super(message);
		}

	}

}
