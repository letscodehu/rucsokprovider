package com.rucsok.user.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.annotations.GwtIncompatible;
import com.rucsok.user.domain.User;
import com.rucsok.user.service.UserRegistrationService;
import com.rucsok.user.service.exception.NoUserGivenException;
import com.rucsok.user.view.model.UserRegistrationRequest;
import com.rucsok.user.view.model.UserRegistrationResponse;
import com.rucsok.user.view.transform.UserRegistrationTransformer;

@RestController
public class UserRegistrationController {

	private UserRegistrationTransformer userTransformer;
	
	private UserRegistrationService userRegistrationService;
	
	@Autowired
	public UserRegistrationController(UserRegistrationTransformer userTransformer,
			UserRegistrationService userRegistrationService) {
		super();
		this.userTransformer = userTransformer;
		this.userRegistrationService = userRegistrationService;
	}
	
	public UserRegistrationResponse register(UserRegistrationRequest request) {
		User user;
		if (request == null) {
			return userTransformer.transformToResponse(new NoUserGivenException());
		}
		try {
			user = userRegistrationService.registerUser(userTransformer.transformToRegistration(request));	
		} catch (RuntimeException ex) {
			return userTransformer.transformToResponse(ex);	
		}
		
		return userTransformer.transformToResponse(user);
	}
	
}
