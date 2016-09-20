package com.rucsok.user.view.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.user.domain.User;
import com.rucsok.user.service.UserRegistrationService;
import com.rucsok.user.service.exception.NoUserGivenException;
import com.rucsok.user.view.model.UserRegistrationRequest;
import com.rucsok.user.view.model.UserRegistrationResponse;
import com.rucsok.user.view.transform.UserRegistrationTransformer;

import io.swagger.annotations.Api;

@RestController
@Api
public class UserRegistrationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationController.class);

	public static final String REQUEST_MAPPING = "/register";

	private UserRegistrationTransformer userTransformer;

	private UserRegistrationService userRegistrationService;

	@Autowired
	public UserRegistrationController(UserRegistrationTransformer userTransformer,
			UserRegistrationService userRegistrationService) {
		super();
		this.userTransformer = userTransformer;
		this.userRegistrationService = userRegistrationService;
	}

	@RequestMapping(path = REQUEST_MAPPING, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public UserRegistrationResponse register(@RequestBody @Valid UserRegistrationRequest request,
			BindingResult result) {
		User user = null;

		if (request == null) { // impossibru, a valid tag megfogja
			return userTransformer.transformToResponse(new NoUserGivenException());
		}
		if (result.hasErrors()) {
			return userTransformer.transformToResponseError(result.getAllErrors());
		}
		try {
			user = userRegistrationService.registerUser(userTransformer.transformToRegistration(request));
		} catch (RuntimeException ex) {
			LOGGER.error(request.getUsername());
			LOGGER.error(request.getEmail());
			LOGGER.error("-----");
			LOGGER.error(ex.getMessage());
			return userTransformer.transformToResponse(ex);
		}

		return userTransformer.transformToResponse(user);
	}

}
