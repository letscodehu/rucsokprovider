package com.rucsok.login.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.rucsok.login.service.LoginService;

@Controller
public class LoginController {

	public static final String REQUEST_MAPPING = "/login";
	
	@Autowired
	private LoginService loginService;

	@ResponseBody
	@RequestMapping(path = REQUEST_MAPPING, method = RequestMethod.POST)
	public JsonNode login(@RequestParam String username, @RequestParam String password) {
		return loginService.accessToken(username, password);
	}
}
