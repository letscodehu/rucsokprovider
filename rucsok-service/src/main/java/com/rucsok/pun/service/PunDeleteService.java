package com.rucsok.pun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.pun.repository.PunRepository;

@Service
public class PunDeleteService {

	private PunRepository repository;
	
	@Autowired
	public PunDeleteService(PunRepository repository) {
		super();
		this.repository = repository;
	}

	public void delete(long punID) {
		repository.delete(punID);
	}

}
