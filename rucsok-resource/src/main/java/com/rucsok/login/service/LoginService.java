package com.rucsok.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.rucsok.login.service.config.LoginServiceConfig.LoginFormMapFactory;

@Service
public class LoginService {

	private static final String USERNAME_PARAM = "username";
	private static final String PASSWORD_PARAM = "password";

	@Autowired
	private RestTemplate accessTokenRestTemplate;

	@Autowired
	private LoginFormMapFactory loginFormMapFactory;

	@Autowired
	private HttpHeaders loginHeader;

	@Value("${oauth2.token.uri}")
	private String oauth2TokenUri;

	public JsonNode accessToken(String username, String password) {
		LinkedMultiValueMap<String, String> loginFormMap = loginFormMapFactory.create();
		loginFormMap.add(USERNAME_PARAM, username);
		loginFormMap.add(PASSWORD_PARAM, password);
		return accessTokenRestTemplate.postForObject(oauth2TokenUri,
				new HttpEntity<MultiValueMap<String, String>>(loginFormMap, loginHeader), JsonNode.class);
	}
}
