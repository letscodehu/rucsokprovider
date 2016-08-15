package com.rucsok.rucsok.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.domain.SingleRucsok;
import com.rucsok.rucsok.repository.dao.RucsokDao;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.service.exception.AlreadyExistsRucsokException;
import com.rucsok.rucsok.service.exception.IllegalRucsokArgumentException;
import com.rucsok.rucsok.service.transform.RucsokServiceTransform;
import com.rucsok.user.service.UserService;

@Service
public class RucsokService {

	@Autowired
	private RucsokDao rucsokRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private RucsokServiceTransform rucsokServiceTransform;

	public List<Rucsok> findAll() {
		return rucsokServiceTransform.transformToRucsok(rucsokRepo.getAllRucsok());
	}

	public void deleteById(long id) {
		rucsokRepo.delete(rucsokRepo.findOne(id));
	}

	public Optional<RucsokEntity> findByLink(String url) {
		return Optional.ofNullable(rucsokRepo.findByLink(url));
	}

	public SingleRucsok findRucsokById(int id) {
		return rucsokServiceTransform.transformToSingleRucsok(rucsokRepo.findById(id));
	}

	public void saveRucsok(Rucsok rucsok, String username) {
		checkCreateRucsokPreconditions(rucsok, username);
		createNewRucsok(rucsok, username);
	}

	private void setUser(RucsokEntity rucsokEntity, String username) {
		rucsokEntity.setUser(userService.findUserByName(username));
	}

	private void createNewRucsok(Rucsok rucsok, String username) {
		RucsokEntity rucsokEntity = rucsokServiceTransform.transformToRucsokEntity((rucsok));
		setUser(rucsokEntity, username);
		rucsokRepo.save(rucsokEntity);
	}

	private void checkCreateRucsokPreconditions(Rucsok rucsok, String username) {
		checkUser(username);
		checkArguments(rucsok);
		checkIfUrlAlreadyAdded(rucsok);
	}

	private void checkIfUrlAlreadyAdded(Rucsok rucsok) {
		if (findByLink(rucsok.getLink()).isPresent()) {
			throw new AlreadyExistsRucsokException(rucsok);
		}
	}

	private void checkUser(String username) {
		if (!userService.isUserExists(username)) {
			throw new IllegalRucsokArgumentException("Rucsok cannot be create without user.");
		}
	}

	private void checkArguments(Rucsok rucsok) {
		if (null == rucsok.getLink() || null == rucsok.getImageUrl()) {
			throw new IllegalRucsokArgumentException();
		}
	}

}
