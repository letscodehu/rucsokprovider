package com.rucsok.login.view.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.databind.JsonNode;
import com.rucsok.login.service.LoginService;

@Controller
public class LoginController {

	public static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	public static final String REQUEST_MAPPING = "/login";
	
	@Autowired
	private LoginService loginService;

	@CrossOrigin(origins = "*")
	@ResponseBody
	@RequestMapping(path = REQUEST_MAPPING, method = RequestMethod.POST)
	public JsonNode login(@RequestParam String username, @RequestParam String password) {
		try{
		return loginService.accessToken(username, password);
		}catch(HttpClientErrorException e){
			LOGGER.debug(e.getMessage());
			throw new LoginException();
		}
	}
	
	@SuppressWarnings("serial")
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected class LoginException extends RuntimeException{		
	}
}
