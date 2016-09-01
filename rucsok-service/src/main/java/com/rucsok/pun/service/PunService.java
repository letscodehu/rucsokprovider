package com.rucsok.pun.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.pun.service.domain.Pun;
import com.rucsok.pun.service.domain.PunServiceRandom;
import com.rucsok.pun.repository.PunRepository;
import com.rucsok.pun.repository.domain.PunEntity;
import com.rucsok.pun.service.transform.PunServiceTransformer;

@Service
public class PunService {

	@Autowired
	private PunRepository punRepository;

	@Autowired
	private PunServiceTransformer punTransformer;

	@Autowired
	private PunServiceRandom random;

	public Pun serveRandom() {
		return punTransformer.transformEntityToDTO(punRepository.findOne(random.nextLong(punRepository.count())));
	}

	public void save(PunEntity pun) {
		punRepository.save(pun);
	}

}
