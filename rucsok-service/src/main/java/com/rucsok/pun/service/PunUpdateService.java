package com.rucsok.pun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.pun.repository.PunRepository;
import com.rucsok.pun.service.domain.Pun;
import com.rucsok.pun.service.transform.PunServiceTransformer;

@Service
public class PunUpdateService {

	@Autowired
	public PunUpdateService(PunRepository punRepository, PunServiceTransformer transformer) {
		super();
		this.transformer = transformer;
		this.punRepository = punRepository;
	}
	
	private PunServiceTransformer transformer;

	private PunRepository punRepository;
	
	
	public Pun getPun(long id) {
		return transformer.convert(punRepository.findOne(id));
	}


	public void updatePun(Pun mockPun) {
		// TODO Auto-generated method stub
		
	}

	
	
}
