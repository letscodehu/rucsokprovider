package com.rucsok.rucsok.config;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;

import com.rucsok.rucsok.repository.dao.ListedRucsokRepository;
import com.rucsok.rucsok.transform.DateTransformer;
import com.rucsok.rucsok.transform.ListedRucsokTransformer;

public class ListedRucsokServiceConfig {

	@Mock
	ListedRucsokRepository rucsokRepository;

	@Mock
	ListedRucsokTransformer listedRucsokTransformer;

	@Mock
	DateTransformer dateTransformer;

	public ListedRucsokServiceConfig() {
		MockitoAnnotations.initMocks(this);
	}

	@Bean
	public ListedRucsokRepository mockListedRucsokRepository() {
		return rucsokRepository;
	}

	@Bean
	public ListedRucsokTransformer mockListedRucsokTransformer() {
		return listedRucsokTransformer;
	}

	@Bean
	public DateTransformer mockDateTransformer() {
		return dateTransformer;
	}
}
