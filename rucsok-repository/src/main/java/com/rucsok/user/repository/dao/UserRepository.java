package com.rucsok.user.repository.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rucsok.user.repository.domain.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByName(String name);
	
	UserEntity findByEmail(String email);
	
}
