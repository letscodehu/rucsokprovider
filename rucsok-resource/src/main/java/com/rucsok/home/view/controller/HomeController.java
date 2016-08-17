package com.rucsok.home.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class HomeController {
	
	@Autowired
	private OAuth2RestOperations restTemplate;

	@RequestMapping(name = "home", path = "/")
	public String index() {
		return "index";
	}

	@ResponseBody
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public JsonNode login(@RequestParam String username, @RequestParam String password) {
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("Authorization", getAuthorizationHeader());

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("username", username);
		map.add("password",  password);
		map.add("grant_type", "password");
		map.add("scope", "read");
		map.add("client_id", "clientapp");
		map.add("client_secret", "123456");

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		return restTemplate.postForObject("http://localhost:9999/oauth/token", entity, JsonNode.class);
	}

	private String getAuthorizationHeader() {
		return "Basic "	+ new String(Base64Utils.encode("clientapp:123456".getBytes()));
	}

}
