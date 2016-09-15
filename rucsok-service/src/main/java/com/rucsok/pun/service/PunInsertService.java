package com.rucsok.pun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.pun.repository.PunRepository;
import com.rucsok.pun.service.domain.Pun;
import com.rucsok.pun.service.transform.PunServiceTransformer;

@Service
public class PunInsertService {

	private PunRepository repository;

	private PunServiceTransformer transformer;


	@Autowired
	public PunInsertService(PunRepository repository, PunServiceTransformer transformer) {
		super();
		this.repository = repository;
		this.transformer = transformer;
	}



	public Pun createPun(Pun punToBeCreated) {
		if (punToBeCreated == null ) {
			throw new IllegalArgumentException();
		}
		return transformer.convert(repository.save(transformer.transformDTOToEntity(punToBeCreated)));
	}

}
