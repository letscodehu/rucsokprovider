package com.rucsok.test.rucsok.service.config;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rucsok.rucsok.service.helper.UrlFetchHelper;

@Configuration
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
