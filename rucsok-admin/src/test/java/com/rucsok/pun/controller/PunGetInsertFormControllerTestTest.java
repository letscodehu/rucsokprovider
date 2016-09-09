package com.rucsok.pun.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.Assert;

@RunWith(MockitoJUnitRunner.class)
public class PunGetInsertFormControllerTestTest {

	@InjectMocks
	private PunGetInsertFormController controller;
	private String expectedViewName = PunGetInsertFormController.VIEW_NAME;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void indexShouldReturnViewName() {
		// GIVEN
		
		// WHEN
		String viewName = controller.index();
		// THEN		
		Assert.assertEquals(viewName, expectedViewName );
	}

}
