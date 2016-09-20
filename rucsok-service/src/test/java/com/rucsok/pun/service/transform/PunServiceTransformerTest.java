package com.rucsok.pun.service.transform;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.rucsok.pun.service.domain.Pun;
import com.rucsok.config.RucsokPunServiceConfig;
import com.rucsok.pun.repository.domain.PunEntity;
import com.rucsok.pun.service.transform.PunServiceTransformer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RucsokPunServiceConfig.class, PunServiceTransformer.class })
public class PunServiceTransformerTest {

	@Autowired
	private PunServiceTransformer punTransformer;

	@Test
	public void transformToPunShouldTransformsEntityToDTO() {
		
		PunEntity entity = Mockito.mock(PunEntity.class);
		final String testText = "test text";
		final Long testId = 1L;
		Mockito.when(entity.getId()).thenReturn(testId);
		Mockito.when(entity.getText()).thenReturn(testText);
		
		Pun pun = punTransformer.transformToPun(entity);
		
		assertEquals(testText, pun.getText());
		assertEquals(testId, pun.getId());
		
	}
	
	@Test
	public void transformToEntityShouldTransformDTOToEntity() {
		final String testText = "test text";
		final Long testId = 1L;
		
		final Pun pun = new Pun(testId, testText);
		
		PunEntity entity = punTransformer.transformToEntity(pun);
		
		assertEquals(testText, entity.getText());
		
	}
	
	
}
