package com.rucsok.rucsok.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.domain.SingleRucsok;
import com.rucsok.rucsok.repository.dao.RucsokRepository;
import com.rucsok.rucsok.repository.dao.VoteRepository;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.service.exception.AlreadyExistsRucsokException;
import com.rucsok.rucsok.service.exception.IllegalRucsokArgumentException;
import com.rucsok.rucsok.transform.RucsokServiceTransform;
import com.rucsok.user.service.UserCheckerService;

@Service
public class RucsokService {
	
	@Value("${rucsok.page.size}")
	public static final int PAGINATION_SIZE = 3;

	@Autowired
	private RucsokRepository rucsokRepository;

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private UserCheckerService userService;

	@Autowired
	private RucsokServiceTransform rucsokServiceTransform;

	public List<Rucsok> findAll() {
		return rucsokServiceTransform.transformToRucsok(rucsokRepository.getAllRucsok());
	}
	
	public List<Rucsok> findFresh(int page) {
		return rucsokServiceTransform.transformToRucsok(rucsokRepository.getAllRucsokByCreatedAt(new PageRequest(page, PAGINATION_SIZE)));
	}

	public void deleteById(long id) {
		rucsokRepository.delete(rucsokRepository.findOne(id));
	}

	public Optional<RucsokEntity> findByLink(String url) {
		return Optional.ofNullable(rucsokRepository.findByLink(url));
	}

	public SingleRucsok findRucsokById(int id) {
		SingleRucsok rucsok = getTransformedRucsok(id);
		setVoteNumber(rucsok.getCurrent());
		return rucsok;
	}

	private SingleRucsok getTransformedRucsok(int id) {
		return rucsokServiceTransform.transformToSingleRucsok(rucsokRepository.findById(id));
	}

	private void setVoteNumber(Rucsok rucsok) {
		Long voteNumber = voteRepository.countByRucsokId(rucsok.getId());
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
		return rucsokRepository.save(transformToRucsokEntity(rucsok, username));
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
