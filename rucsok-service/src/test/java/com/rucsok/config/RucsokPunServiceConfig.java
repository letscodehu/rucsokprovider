package com.rucsok.config;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;

import com.rucsok.pun.repository.PunRepository;
import com.rucsok.pun.service.domain.PunServiceRandom;

public class RucsokPunServiceConfig {

	@Mock
	private PunRepository repository;
	
	@Mock
	private PunServiceRandom randomMock;
	
	public RucsokPunServiceConfig() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Bean
	public PunServiceRandom random() {
		return randomMock;
	}
	
	@Bean
	public PunRepository punRepository() {
		return repository;
	}

	
	
}
