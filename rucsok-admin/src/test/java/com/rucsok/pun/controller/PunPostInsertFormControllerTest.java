package com.rucsok.pun.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import com.rucsok.pun.model.PunInsertForm;
import com.rucsok.pun.service.PunInsertService;
import com.rucsok.pun.service.domain.Pun;
import com.rucsok.pun.transform.PunInsertTransformer;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class PunPostInsertFormControllerTest {

	@InjectMocks
	private PunPostInsertFormController controller;
	
	@Mock
	private PunInsertTransformer transformer;
	
	@Mock
	private PunInsertService service;
	
	@Mock
	private BindingResult result;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}	
	
	@Test
	public void indexShouldRedirectToPunListOnSuccess() {
		// GIVEN
		Mockito.when(result.hasErrors()).thenReturn(false);
		// WHEN

		String logicalViewName = controller.index(null, result, null);
		
		// THEN
	
		Assert.assertEquals(PunPostInsertFormController.REDIRECTVIEW, logicalViewName);
	}
	
	@Test
	public void indexShouldReturnAddPunViewOnValidationErrors() {
		// GIVEN
		Mockito.when(result.hasErrors()).thenReturn(true);

		// WHEN
		String logicalViewName = controller.index(null, result, null);
		// THEN
		
		Assert.assertEquals(PunGetInsertFormController.VIEW_NAME, logicalViewName);
	}
	
	@Test
	public void indexShouldCallTransformerWhenNoValidationErrorsPresent() {
		// GIVEN
		Mockito.when(result.hasErrors()).thenReturn(false);
		final PunInsertForm form = new PunInsertForm();
		// WHEN
		controller.index(form, result, null);
		// THEN
		Mockito.verify(transformer).convert(form);
	}
	

	@Test
	public void indexShouldPassPunToServiceAfterTransforming() {
		// GIVEN
		Mockito.when(result.hasErrors()).thenReturn(false);
		final Pun mockPun = new Pun(null, null);
		Mockito.when(transformer.convert(Mockito.any())).thenReturn(mockPun);
		
		// WHEN
		controller.index(null, result, null);
		
		// THEN
		Mockito.verify(service).createPun(mockPun);
	}
	
	@Test
	public void indexShouldStoreMessageInFlashAttributesOnSuccessfulAddition() {
		// GIVEN
		Mockito.when(result.hasErrors()).thenReturn(false);
		final Pun mockPun = new Pun(null, null);
		
		// WHEN

		// THEN
	}
	
}
