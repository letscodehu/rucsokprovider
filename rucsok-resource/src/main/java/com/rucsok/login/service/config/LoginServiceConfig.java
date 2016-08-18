package com.rucsok.login.service.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LoginServiceConfig {
	
	private static final String AUTH_HEADER_DELIMITER = ":";
	private static final String SCOPE = "scope";
	private static final String CLIENT_SECRET = "client_secret";
	private static final String CLIENT_ID = "client_id";
	private static final String GRANT_TYPE = "grant_type";
	private static final String AUTH_HEADER = "Authorization";
	private static final String AUTH_TYPE = "Basic";

	@Value("${oauth2.grant.type}")
	private String grantType;

	@Value("#{'${oauth2.scope}'.split(',')}")
	private List<String> scopes;

	@Value("${oauth2.client.id}")
	private String clientId;

	@Value("${oauth2.client.secret}")
	private String clientSecret;

	@Bean
	public HttpHeaders loginHeaderFactory() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set(AUTH_HEADER, getAuthorizationHeader());
		return headers;
	}

	@Bean
	@Scope("prototype")
	public LinkedMultiValueMap<String, String> loginFormMap() {
		LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add(GRANT_TYPE, grantType);
		scopes.forEach(s -> map.add(SCOPE, s));
		map.add(CLIENT_ID, clientId);
		map.add(CLIENT_SECRET, clientSecret);
		return map;
	}

	@Bean
	public String getAuthorizationHeader() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(AUTH_TYPE);
		stringBuilder.append(" ");
		stringBuilder.append(clientId);
		stringBuilder.append(AUTH_HEADER_DELIMITER);
		stringBuilder.append(clientSecret);
		return new String(Base64Utils.encode(stringBuilder.toString().getBytes()));
	}

	@Bean
	public RestTemplate accessTokenRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
		return restTemplate;
	}
}
