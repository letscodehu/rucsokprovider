package com.rucsok.test.pun.service.transform;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.rucsok.pun.service.domain.Pun;
import com.rucsok.pun.repository.domain.PunEntity;
import com.rucsok.pun.service.transform.PunServiceTransformer;
import com.rucsok.test.config.RucsokPunServiceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RucsokPunServiceConfig.class, PunServiceTransformer.class })
public class PunServiceTransformerTest {

	@Autowired
	private PunServiceTransformer punTransformer;

	@Test
	public void itTransformsEntityToDTO() {
		
		PunEntity entity = Mockito.mock(PunEntity.class);
		final String testText = "test text";
		final Long testId = 1L;
		Mockito.when(entity.getId()).thenReturn(testId);
		Mockito.when(entity.getText()).thenReturn(testText);
		
		Pun pun = punTransformer.transformEntityToDTO(entity);
		
		assertEquals(testText, pun.getText());
		assertEquals(testId, pun.getId());
		
	}
	
	@Test
	public void it_Transforms_DTO_To_Entity() {
		final String testText = "test text";
		final Long testId = 1L;
		
		final Pun pun = new Pun(testId, testText);
		
		PunEntity entity = punTransformer.transformDTOToEntity(pun);
		
		assertEquals(testText, entity.getText());
		
	}
	
	
}
