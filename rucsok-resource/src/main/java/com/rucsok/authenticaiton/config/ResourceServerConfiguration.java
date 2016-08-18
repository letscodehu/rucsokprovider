package com.rucsok.authenticaiton.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.http.converter.FormOAuth2ExceptionHttpMessageConverter;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableOAuth2Client
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	public static final String RESOURCE_ID = "restservice";

	@Value("${oauth2.token.uri}")
	private String tokenUri;

	@Bean
	public String oauth2TokenUri() {
		return tokenUri;
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/", "/login", "/profile", "/check-rucsok", "/rucsok/**", "/**/*.js",
						"/app/**/*.html", "/**/*.js.map", "/**/*.css", "/images/**")
				.permitAll().antMatchers("/users").hasRole("ADMIN").antMatchers(HttpMethod.POST, "/rucsok/**")
				.authenticated().antMatchers(HttpMethod.DELETE, "/rucsok/**").authenticated();

	}

	@Bean
	public AccessTokenConverter accessTokenConverter() {
		return new DefaultAccessTokenConverter();
	}
	

	@Bean
	public RestTemplate checkTokenRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new FormOAuth2ExceptionHttpMessageConverter());
		return restTemplate;
	}

	@Bean
	public RemoteTokenServices remoteTokenServices(final @Value("${oauth2.token.check}") String checkTokenUrl,
			final @Value("${oauth2.client.id}") String clientId,
			final @Value("${oauth2.client.secret}") String clientSecret, RestTemplate checkTokenRestTemplate) {
		RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
		remoteTokenServices.setRestTemplate(checkTokenRestTemplate);
		remoteTokenServices.setCheckTokenEndpointUrl(checkTokenUrl);
		remoteTokenServices.setClientId(clientId);
		remoteTokenServices.setClientSecret(clientSecret);
		remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
		return remoteTokenServices;
	}

}
