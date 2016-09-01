package com.rucsok.config;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;

import com.rucsok.rucsok.service.helper.UrlFetchHelper;
import com.rucsok.user.service.UserService;

//@Configuration removed to prevent component scan
public class RucsokCrawlServiceConfig {

	@Mock
	UrlFetchHelper urlFetchHelper;
	
	@Mock
	UserService userService;

	public RucsokCrawlServiceConfig() {
		MockitoAnnotations.initMocks(this);
	}

	@Bean
	public UrlFetchHelper getUrlFetchHelper() {
		return urlFetchHelper;
	}
	
	@Bean
	public UserService getUserService() {
		return userService;
	}
}
