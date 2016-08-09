package com.rucsok.test.config;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rucsok.rucsok.service.helper.UrlFetchHelper;

//@Configuration removed to prevent component scan
public class RucsokCrawlServiceConfig {

	@Mock
	UrlFetchHelper urlFetchHelper;

	public RucsokCrawlServiceConfig() {
		MockitoAnnotations.initMocks(this);
	}

	@Bean
	public UrlFetchHelper getUrlFetchHelper() {
		return urlFetchHelper;
	}
}
