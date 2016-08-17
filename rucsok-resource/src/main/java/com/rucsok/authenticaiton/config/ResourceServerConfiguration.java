package com.rucsok.authenticaiton.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableOAuth2Client
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	
	public static final String RESOURCE_ID = "restservice";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {		

		http.authorizeRequests()				
				.antMatchers(HttpMethod.GET, "/", "/login", "/profile", "/check-rucsok", "/rucsok/**", "/**/*.js","/app/**/*.html", "/**/*.js.map", "/**/*.css", "/images/**")
					.permitAll()
				.antMatchers("/users").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/rucsok/**")
					.authenticated()
				.antMatchers(HttpMethod.DELETE, "/rucsok/**")
					.authenticated();

	}
	
	@Bean 
	public RestTemplate accessTokenRestTemplate(){
		return null;
	}
	
	@Bean
	public OAuth2ClientContext oauth2ClientContext() {
		return new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest());
	}

	@Bean
	public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
		return new OAuth2RestTemplate(resource(), oauth2ClientContext);
	}

	private OAuth2ProtectedResourceDetails resource() {
		ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
//		ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
		resource.setGrantType("password");
		resource.setClientId("clientapp");
		resource.setClientSecret("123456");
		resource.setAccessTokenUri("http://localhost:9999/oauth/token");
//		resource.setUserAuthorizationUri("http://localhost:9999/oauth/authorize");
		resource.setScope(Arrays.asList("read"));
		resource.setUsername("rucsok");
		resource.setPassword("123");
		return resource;
	}
}
