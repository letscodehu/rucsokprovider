package com.rucsok.pun.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.rucsok.pun.repository.PunRepository;
import com.rucsok.pun.repository.domain.PunEntity;
import com.rucsok.pun.service.domain.Pun;
import com.rucsok.pun.service.transform.PunServiceTransformer;

@RunWith(MockitoJUnitRunner.class)
public class PunUpdateServiceTest {

	@InjectMocks
	private PunUpdateService service;

	@Mock
	private PunRepository repository;

	@Mock
	private PunServiceTransformer transformer;

	private final long punId = 1L;

	private String punText = "test";

	@Test
	public void getPunShouldInvokeRepository() {
		// GIVEN

		// WHEN
		service.getPun(punId);

		// THEN
		Mockito.verify(repository).findOne(punId);
	}

	@Test
	public void getPunShouldPassEntityToTransformer() {
		// GIVEN
		final PunEntity mockEntity = new PunEntity();
		Mockito.when(repository.findOne(punId)).thenReturn(mockEntity);
		// WHEN

		service.getPun(punId);

		// THEN

		Mockito.verify(transformer).convert(mockEntity);
	}

	@Test
	public void getPunShouldReturnAfterTransforming() {
		// GIVEN
		final Pun mockPun = new Pun(1L, punText );
		Mockito.when(repository.findOne(punId)).thenReturn(null);
		Mockito.when(transformer.convert(null)).thenReturn(mockPun);
		// WHEN

		Pun result = service.getPun(punId);

		// THEN
		assertEquals(mockPun, result);

	}

	@Test
	public void updatePunShouldInvokeRepository() {
		// GIVEN
		final Pun mockPun = new Pun(1L, punText);

		// WHEN
		service.updatePun(mockPun);

		// THEN
		Mockito.verify(repository).setTextById(mockPun.getText(), mockPun.getId());
	}


}
