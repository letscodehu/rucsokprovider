package com.rucsok.pun.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import com.rucsok.pun.model.PunUpdateForm;
import com.rucsok.pun.service.PunUpdateService;
import com.rucsok.pun.service.domain.Pun;
import com.rucsok.pun.transform.PunUpdateTransformer;

@RunWith(MockitoJUnitRunner.class)
public class PunPostUpdateFormControllerTest {

	@InjectMocks
	private PunPostUpdateFormController controller;
	
	@Mock	
	private PunUpdateService service;
	
	@Mock
	private PunUpdateTransformer transformer;
	
	@Mock
	private BindingResult result;
	
	@Test
	public void indexShouldInvokeTransformerWhenNoValidationErrorsArePresent() {
		// GIVEN
		final PunUpdateForm mockPun = new PunUpdateForm(1L, "text");
		Mockito.when(result.hasErrors()).thenReturn(false);
		// WHEN
		controller.index(mockPun, result);
		// THEN
		Mockito.verify(transformer).convertToPun(mockPun);
	}
	
	@Test
	public void indexShouldNotInvokeTransformerOnValidationErrors() {
		// GIVEN
		final PunUpdateForm mockPun = new PunUpdateForm(1L, "text");
		Mockito.when(result.hasErrors()).thenReturn(true);
		// WHEN
		controller.index(mockPun, result);
		// THEN
		Mockito.verify(transformer, Mockito.never()).convertToPun(mockPun);
	}
	
	@Test
	public void indexShouldPassTransformedObjectToService() {
		// GIVEN
		final PunUpdateForm mockPunUpdate = new PunUpdateForm(1L, "text");
		final Pun mockPun = new Pun(null, null);
		Mockito.when(result.hasErrors()).thenReturn(false);
		Mockito.when(transformer.convertToPun(mockPunUpdate)).thenReturn(mockPun);
		// WHEN
		controller.index(mockPunUpdate, result);
		// THEN
		Mockito.verify(service).updatePun(mockPun);
	}

	public void indexShouldRedirectOnSuccess() {
		
	}
	
}
