package com.rucsok.config;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;

import com.rucsok.pun.repository.PunRepository;
import com.rucsok.pun.repository.domain.PunEntity;
import com.rucsok.pun.service.domain.Pun;
import com.rucsok.pun.service.transform.PunServiceTransformer;

public class PunListServiceConfig {

	@Mock
	private PunServiceTransformer transformer;

	@Mock
	private Page<PunEntity> punEntityPage;

	@Mock
	private PunRepository punRepository;
	
	@Mock
	private Page<Pun> punPage;
	
	
	public PunListServiceConfig() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Bean
	public Page<PunEntity> punPage() {
		return punEntityPage;
	}
	
	@Bean
	public Page<Pun> mockPunPage() {
		return punPage;
	}
	
	@Bean
	public PunRepository punRepository() {
		return punRepository;
	}

	
	@Bean
	public PunServiceTransformer transformer() {
		return transformer;
	}
	
}

