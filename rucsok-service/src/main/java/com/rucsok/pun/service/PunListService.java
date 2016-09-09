package com.rucsok.pun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rucsok.pun.repository.PunRepository;
import com.rucsok.pun.service.domain.Pun;
import com.rucsok.pun.service.transform.PunServiceTransformer;

@Service
public class PunListService {

	@Autowired
	private PunRepository punRepository;
	
	@Autowired
	private PunServiceTransformer punServicetransformer;
	
	@Autowired
	public PunListService(PunRepository punRepository, PunServiceTransformer transformer) {
		super();
		this.punRepository = punRepository;
		this.punServicetransformer = transformer;
	}
	
	public Page<Pun> listAll(Pageable pageRequest) {
		return punRepository.findAll(pageRequest).map(punServicetransformer);
	}
	
	
}
