package com.rucsok.pun.transform;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.rucsok.pun.model.PunUpdateForm;
import com.rucsok.pun.service.domain.Pun;

@RunWith(MockitoJUnitRunner.class)
public class PunUpdateTransformerTest {

	@InjectMocks
	private PunUpdateTransformer transformer;
	private Long punId = 1L;
	private String punText = "i_hate_tests";
	
	@Test
	public void convertShouldReturnPunUpdateForm() {
		// GIVEN
		final Pun pun = new Pun(punId, punText);
		// WHEN
		PunUpdateForm form = transformer.convert(pun);
		// THEN
		assertEquals((Long) pun.getId(), (Long) form.getId());
		assertEquals(pun.getText(), form.getText());
	}
	
	@Test
	public void convertToPunShouldReturnPun() {
		// GIVEN
		final PunUpdateForm form = new PunUpdateForm(punId, punText);
		// WHEN
		Pun pun = transformer.convertToPun(form);
		// THEN
		assertEquals((Long) form.getId(), (Long) pun.getId());
		assertEquals(form.getText(), pun.getText());
	}
}
