package com.rucsok.pun.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.config.PunListServiceConfig;
import com.rucsok.pun.repository.PunRepository;
import com.rucsok.pun.repository.domain.PunEntity;
import com.rucsok.pun.service.domain.Pun;
import com.rucsok.pun.service.transform.PunServiceTransformer;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PunListService.class, PunListServiceConfig.class})
public class PunListServiceTest {

	@Autowired
	private PunListService listService;
	
	@Autowired
	private PunRepository repository;

	private Pageable pageRequest = null;
	
	@Autowired
	private Page<PunEntity> mockEntityPage;

	@Autowired
	private Page<Pun> mockPunPage;
	
	
	@Test
	public void itShouldReturn() {
		// GIVEN
		Mockito.when(repository.findAll(pageRequest)).thenReturn(mockEntityPage);
		Mockito.when(mockEntityPage.map(Mockito.any(PunServiceTransformer.class))).thenReturn(mockPunPage);

		// WHEN
		Page<Pun> punPage = listService.listAll(pageRequest);
		
		// THEN
		Assert.assertEquals(mockPunPage, punPage);
		
	}

}
