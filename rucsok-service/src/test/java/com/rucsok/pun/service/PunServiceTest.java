package com.rucsok.pun.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.config.RucsokPunServiceConfig;
import com.rucsok.pun.repository.PunRepository;
import com.rucsok.pun.repository.domain.PunEntity;
import com.rucsok.pun.service.PunService;
import com.rucsok.pun.service.domain.PunServiceRandom;
import com.rucsok.pun.service.transform.PunServiceTransformer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RucsokPunServiceConfig.class, PunService.class, PunServiceTransformer.class})
public class PunServiceTest {
	
	@Autowired
	private PunRepository punRepository;
	
	@Autowired
	private PunService punService;
	
	@Autowired
	private PunServiceRandom random;
	
	@Test
	public void serveRandomShouldReturnRandomPun() {
		// GIVEN
		final Long max = 500L;
		final Long id = 5L;
		
		Mockito.when(punRepository.count()).thenReturn(max);
		Mockito.when(random.nextLong(max - 1)).thenReturn(id);
		Mockito.when(punRepository.findOne(id + 1)).thenReturn(new PunEntity());
		
		// WHEN
		
		punService.serveRandom();
		
		// THEN
		
		Mockito.verify(random).nextLong(max - 1);
		Mockito.verify(punRepository).findOne(id + 1);
	}
	
	@Test
	public void saveShouldCreateANewPun() {
		
		// GIVEN
		
		PunEntity pun = new PunEntity();
		
		
		// WHEN
		
		punService.save(pun);
		
		//THEN
		
		Mockito.verify(punRepository).save(pun);
	}
	

}
