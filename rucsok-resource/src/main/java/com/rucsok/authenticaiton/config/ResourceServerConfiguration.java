package com.rucsok.authenticaiton.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

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
	

}
