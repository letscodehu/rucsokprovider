package com.rucsok.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.rucsok.user.repository.domain.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByName(String name);
	
}
