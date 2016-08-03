package com.rucsok.rucsok.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.domain.SingleRucsok;
import com.rucsok.rucsok.repository.dao.RucsokDao;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
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

	public void saveRucsok(Rucsok rucsok) {
		checkArguments(rucsok);
		checkIfUrlAlreadyAdded(rucsok);
		repo.save(rucsokServiceTransform.transformToRucsokEntity((rucsok)));
	}

	private void checkIfUrlAlreadyAdded(Rucsok rucsok) {
		if (findByLink(rucsok.getLink()).isPresent()) {
			throw new AlreadyExistsRucsokException(rucsok);
		}
	}

	private void checkArguments(Rucsok rucsok) {
		if (null == rucsok.getLink() || null == rucsok.getImage()) {
			throw new IllegalRucsokArgumentException();
		}
	}

	public Optional<RucsokEntity> findByLink(String url) {
		return Optional.ofNullable(repo.findByLink(url));
	}

}
