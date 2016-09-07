package com.rucsok.rucsok.repository.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rucsok.rucsok.repository.domain.VoteEntity;
import com.rucsok.rucsok.repository.domain.VotePK;

@Repository
public interface VoteRepository extends CrudRepository<VoteEntity, VotePK> {
	
	Long countByRucsokId(Long rucsokId);
	VoteEntity findByUserIdAndRucsokId(Long userId, Long rucsokId);
	
}
