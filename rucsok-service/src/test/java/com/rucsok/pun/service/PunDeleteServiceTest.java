package com.rucsok.pun.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.rucsok.pun.repository.PunRepository;

@RunWith(MockitoJUnitRunner.class)
public class PunDeleteServiceTest {


	@InjectMocks
	private PunDeleteService service;

	@Mock
	private PunRepository repository;
	
	@Test
	public void deleteShouldCallRepository() {
		// GIVEN
		final long punID = 1L;
		
		// WHEN
		service.delete(punID);
		
		// THEN
		Mockito.verify(repository).delete(punID);
	}

}
