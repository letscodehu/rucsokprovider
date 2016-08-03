package com.rucsok.rucsok.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.domain.SingleRucsok;
import com.rucsok.rucsok.repository.dao.RucsokDao;
import com.rucsok.rucsok.service.transform.RucsokServiceTransform;

@Service
public class RucsokService {

	@Autowired
	private RucsokDao repo;

	@Autowired
	private RucsokServiceTransform rucsokServiceTransform;

	public List<Rucsok> findAll() {
		return rucsokServiceTransform.transformToRucsok(repo.getAllRucsok());
	}

	public SingleRucsok findRucsokById(int id) {
		return rucsokServiceTransform.transformToSingleRucsok(repo.findById(id));
	}

}
