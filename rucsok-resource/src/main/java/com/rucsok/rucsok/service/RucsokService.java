package com.rucsok.rucsok.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.domain.SingleRucsok;
import com.rucsok.rucsok.repository.dao.RucsokDao;
import com.rucsok.rucsok.repository.dao.VoteDao;
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
	private VoteDao voteDao;

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
		SingleRucsok rucsok = getTransformetRucsok(id);
		setVoteNumber(rucsok.getCurrent());
		return rucsok;
	}

	private SingleRucsok getTransformetRucsok(int id) {
		return rucsokServiceTransform.transformToSingleRucsok(rucsokRepo.findById(id));
	}

	private void setVoteNumber(Rucsok rucsok) {
		Long voteNumber = voteDao.countByRucsokId(rucsok.getId());
		rucsok.setVote(voteNumber.intValue());
	}

	public Rucsok saveRucsok(Rucsok rucsok, String username) {
		checkCreateRucsokPreconditions(rucsok, username);
		return rucsokServiceTransform.transformToRucsok(createNewRucsok(rucsok, username));
	}

	private void setUser(RucsokEntity rucsokEntity, String username) {
		rucsokEntity.setUser(userService.findUserByName(username));
	}

	private RucsokEntity createNewRucsok(Rucsok rucsok, String username) {
		return rucsokRepo.save(transformToRucsokEntity(rucsok, username));
	}

	private RucsokEntity transformToRucsokEntity(Rucsok rucsok, String username) {
		rucsok.setCreatedAt(LocalDateTime.now());
		RucsokEntity rucsokEntity = rucsokServiceTransform.transformToRucsokEntity(rucsok);
		setUser(rucsokEntity, username);
		return rucsokEntity;
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
