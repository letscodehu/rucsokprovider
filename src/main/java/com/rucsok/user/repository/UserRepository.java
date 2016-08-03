package com.rucsok.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.rucsok.user.repository.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByName(String name);
	
}
