package com.rucsok.login.service.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:application.properties")
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

	@Value("${oauth2.token.uri}")
	private String tokenUri;

	@Bean
	public String oauth2TokenUri() {
		return tokenUri;
	}

	@Bean
	public HttpHeaders loginHeaderFactory() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set(AUTH_HEADER, getAuthorizationHeader());
		return headers;
	}

	@Bean
	public LoginFormMapFactory loginFormMapFactory() {
		return new LoginFormMapFactory();
	}

	@Bean
	public String getAuthorizationHeader() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(AUTH_TYPE);
		stringBuilder.append(" ");
		stringBuilder.append(getHeaderTokenAsBase64());
		return stringBuilder.toString();
	}

	@Bean
	public String getHeaderTokenAsBase64() {
		StringBuilder stringBuilder = new StringBuilder();
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
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public class LoginFormMapFactory {

		public LinkedMultiValueMap<String, String> create() {
			LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add(GRANT_TYPE, grantType);
			scopes.forEach(s -> map.add(SCOPE, s));
			map.add(CLIENT_ID, clientId);
			map.add(CLIENT_SECRET, clientSecret);
			return map;
		}

	}
}