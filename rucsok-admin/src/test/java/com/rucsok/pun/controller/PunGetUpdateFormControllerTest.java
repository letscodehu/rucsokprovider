package com.rucsok.pun.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.rucsok.pun.service.PunUpdateService;
import com.rucsok.pun.service.domain.Pun;
import com.rucsok.pun.transform.PunUpdateTransformer;


@RunWith(MockitoJUnitRunner.class)
public class PunGetUpdateFormControllerTest {

	@InjectMocks
	private PunGetUpdateFormController controller;
	
	@Mock
	private PunUpdateTransformer transformer;
	
	@Mock
	private PunUpdateService service;
	
	@Test
	public void indexShouldReturnViewName() {
		// GIVEN
		
		// WHEN
		String logicalViewName = controller.index();
		
		// THEN
		
		assertEquals(PunGetUpdateFormController.VIEW_NAME, logicalViewName);
	}
	
	@Test
	public void getPunShouldCallServiceForPun() {
		// GIVEN
		final long punId = 1L;
		// WHEN
		controller.getPun(1L);
		
		// THEN
		Mockito.verify(service).getPun(1L);
	}
	
	@Test
	public void getPunShouldPassServiceReturnToTransformer() {
		// GIVEN
		final long punId = 1L;
		final String punText = "test";
		final Pun mockPun = new Pun(punId, punText);
		Mockito.when(service.getPun(punId)).thenReturn(mockPun);
		// WHEN

		controller.getPun(punId);
		
		// THEN
		Mockito.verify(transformer).convert(mockPun);
		
	}

}
