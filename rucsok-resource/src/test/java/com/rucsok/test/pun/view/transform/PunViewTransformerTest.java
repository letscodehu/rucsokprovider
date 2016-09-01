package com.rucsok.test.pun.view.transform;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.pun.service.domain.Pun;
import com.rucsok.pun.view.model.RandomPunResponse;
import com.rucsok.pun.view.transform.PunViewTransformer;

import static org.junit.Assert.*;

import org.junit.Test;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PunViewTransformer.class)
public class PunViewTransformerTest {

	@Autowired
	private PunViewTransformer punViewTransformer;
	
	@Test
	public void itTransformsFromPunToRandomPunResponse() {
		final String testText = "testText";
		final Long testId = 5L;
		
		final Pun pun = new Pun(testId, testText);
		
		RandomPunResponse response = punViewTransformer.transfromToRandomPunResponse(pun);
		
		assertEquals(testId, response.getId());
		assertEquals(testText, response.getText());
		
	}
	
}
