package com.rucsok.pun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.pun.repository.PunRepository;
import com.rucsok.pun.repository.domain.PunEntity;
import com.rucsok.pun.service.domain.Pun;
import com.rucsok.pun.service.domain.PunServiceRandom;
import com.rucsok.pun.service.transform.PunServiceTransformer;

@Service
public class RandomPunService {

	@Autowired
	private PunRepository punRepository;

	@Autowired
	private PunServiceTransformer punTransformer;

	@Autowired
	private PunServiceRandom random;

	public Pun serveRandom() {
		return punTransformer.convert(punRepository.findOne(getRandomValueBetweenSize()));
	}

	private long getRandomValueBetweenSize() {
		return random.nextLong(punRepository.count() - 1) + 1;
	}

	public void save(PunEntity pun) {
		punRepository.save(pun);
	}

}
