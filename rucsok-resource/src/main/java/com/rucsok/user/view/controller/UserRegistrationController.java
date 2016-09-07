package com.rucsok.user.view.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.user.domain.User;
import com.rucsok.user.service.UserRegistrationService;
import com.rucsok.user.service.exception.NoUserGivenException;
import com.rucsok.user.view.model.UserRegistrationRequest;
import com.rucsok.user.view.model.UserRegistrationResponse;
import com.rucsok.user.view.transform.UserRegistrationTransformer;
import org.springframework.http.HttpStatus;
import io.swagger.annotations.Api;

@RestController
@Api
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
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public UserRegistrationResponse register(@RequestBody @Valid UserRegistrationRequest request, BindingResult result) {
		User user;
		if (request == null) {
			return userTransformer.transformToResponse(new NoUserGivenException());
		}
		if (result.hasErrors()) {
			return userTransformer.transformToResponseError(result.getAllErrors());
		}		
		try {
			user = userRegistrationService.registerUser(userTransformer.transformToRegistration(request));	
		} catch (RuntimeException ex) {
			return userTransformer.transformToResponse(ex);	
		}
		
		return userTransformer.transformToResponse(user);
	}
	
}
