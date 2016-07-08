package com.rucsok.repository;

import org.springframework.data.repository.CrudRepository;

import com.rucsok.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByName(String name);
	
}
