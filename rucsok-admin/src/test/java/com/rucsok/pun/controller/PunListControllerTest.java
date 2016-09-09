package com.rucsok.pun.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.testng.Assert;

import com.rucsok.pun.controller.PunListController;
import com.rucsok.pun.service.PunListService;
import com.rucsok.pun.service.domain.Pun;


@RunWith(MockitoJUnitRunner.class)
public class PunListControllerTest {

	@Mock
	private Page<Pun> mockPage;
	
	@Mock
	private PunListService service;

	@InjectMocks
	private PunListController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void indexShouldReturnIndexViewName() {
		// GIVEN

		// WHEN
		String viewName = controller.index();

		// THEN
		Assert.assertEquals(viewName, PunListController.PUN_INDEX);

	}

	@Test
	public void punsShouldReturnPageOfPuns() {
		// GIVEN
		Mockito.when(service.listAll(null)).thenReturn(mockPage);
		// WHEN
		Page<Pun> puns = controller.puns(null);
		// THEN
		Assert.assertEquals(puns,  mockPage);
	}

}
