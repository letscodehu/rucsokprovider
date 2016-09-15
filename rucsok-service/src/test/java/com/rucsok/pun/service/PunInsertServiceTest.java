package com.rucsok.pun.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.xml.TransformerUtils;

import com.rucsok.pun.repository.PunRepository;
import com.rucsok.pun.repository.domain.PunEntity;
import com.rucsok.pun.service.domain.Pun;
import com.rucsok.pun.service.transform.PunServiceTransformer;


@RunWith(MockitoJUnitRunner.class)
public class PunInsertServiceTest {

	@InjectMocks
	private PunInsertService service;
	
	@Mock
	private PunRepository repository;
	
	@Mock
	private PunServiceTransformer transformer;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(transformer.transformDTOToEntity(Mockito.any())).thenReturn(new PunEntity());
	}
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	private String mockText = "mockText";
	
	
	@Test
	public void createPunShouldThrowExceptionOnNull() {
		// GIVEN
		
		final Pun mockPun = null;
		expectedEx.expect(IllegalArgumentException.class);
		
		// WHEN
		
		service.createPun(mockPun);
		
		// THEN exception thrown
		
	}
	
	@Test
	public void createPunShouldInvokeTransformer() {
		
		// GIVEN
		final Pun mockPun = new Pun(null, mockText);
		
		// WHEN
		service.createPun(mockPun);
		
		// THEN
		Mockito.verify(transformer).transformDTOToEntity(mockPun);
	}

	@Test
	public void createPunShouldPassEntityToRepository() {
		// GIVEN
		final Pun mockPun = new Pun(null, mockText);
		final PunEntity mockEntity = new PunEntity();
		Mockito.when(transformer.transformDTOToEntity(Mockito.any())).thenReturn(mockEntity);

		// WHEN
		service.createPun(mockPun);

		// THEN
		Mockito.verify(repository).save(mockEntity);
	}
	
	@Test
	public void createPunShouldTransformTheEntityBack() {
		// GIVEN
		final Pun mockPun = new Pun(null, mockText);
		final PunEntity mockEntity = new PunEntity();
		Mockito.when(transformer.transformDTOToEntity(Mockito.any())).thenReturn(mockEntity);
		Mockito.when(repository.save(mockEntity)).thenReturn(mockEntity);

		// WHEN
		
		service.createPun(mockPun);
		
		// THEN
		
		Mockito.verify(transformer).convert(mockEntity);
	}
	

	@Test
	public void createPunShouldReturnTheCreatedPun() {
		// GIVEN
		final Pun mockPun = new Pun(null, mockText);
		final Pun punToBeReturned = new Pun(1L, mockText);
		Mockito.when(transformer.convert(Mockito.any())).thenReturn(punToBeReturned);
		// WHEN
		final Pun returned = service.createPun(mockPun);
		// THEN
		Assert.assertEquals(punToBeReturned, returned);
	}
	
}
