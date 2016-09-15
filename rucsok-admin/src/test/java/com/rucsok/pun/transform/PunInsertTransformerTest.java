package com.rucsok.pun.transform;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.rucsok.pun.model.PunInsertForm;
import com.rucsok.pun.service.domain.Pun;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class PunInsertTransformerTest {

	@InjectMocks
	private PunInsertTransformer transformer;
	
	private final String punText = "puntext";
	@Test
	public void convertShouldInsertTheTextToThePun() {
		// GIVEN
		PunInsertForm form = new PunInsertForm(punText);
		// WHEN
		Pun pun = transformer.convert(form);
		// THEN
		
		Assert.assertEquals(punText, pun.getText());
	}
	
}
