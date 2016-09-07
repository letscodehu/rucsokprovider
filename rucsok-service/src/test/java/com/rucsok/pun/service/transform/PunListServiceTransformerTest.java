package com.rucsok.pun.service.transform;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.pun.repository.domain.PunEntity;
import com.rucsok.pun.service.domain.Pun;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PunListServiceTransformer.class, PunServiceTransformer.class})
public class PunListServiceTransformerTest {

	@Autowired
	private PunListServiceTransformer transformer;
	
	private Long testPunId = 1L;
	private String testPunText = "testpun";
	private Long testPunId2 = 2L;
	private String testPunText2 = "testpun2";
	List<PunEntity> entities; 
	
	@Before
	public void setUp() {
		entities = new ArrayList<PunEntity>();
		entities.add(new PunEntity(testPunId, testPunText, null));
		entities.add(new PunEntity(testPunId2, testPunText2, null));
	}
	
	@Test
	public void itReturnsSameSizeList() {
		// GIVEN in setup
		
		// WHEN
		List<Pun> puns = transformer.transfromFromEntityList(entities);
		// THEN
		Assert.assertEquals(entities.size(), puns.size());
	}
	
	@Test
	public void shouldContainTheSameElementsAfterTransform() {
		// GIVEN in setup
		// WHEN
		List<Pun> puns = transformer.transfromFromEntityList(entities);
		Pun first = puns.get(0);
		Pun second = puns.get(1);

		// THEN

		Assert.assertEquals(testPunText, first.getText());
		Assert.assertEquals(testPunId, first.getId());
		
		Assert.assertEquals(testPunText2, second.getText());
		Assert.assertEquals(testPunId2, second.getId());
	}

}
