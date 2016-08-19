package com.rucsok.rucsok.repository.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rucsok.rucsok.repository.domain.VoteEntity;
import com.rucsok.rucsok.repository.domain.VotePK;

@Repository
public interface VoteDao extends CrudRepository<VoteEntity, VotePK> {

}
