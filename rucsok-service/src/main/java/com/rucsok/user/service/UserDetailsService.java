package com.rucsok.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rucsok.user.domain.UserDetails;
import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.user.transform.UserToUserDetailsTransformer;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	UserRepository repository;	
	
	@Autowired
	UserToUserDetailsTransformer transformer;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails details;
		try {
			details = transformer.transform(repository.findByName(username));	
		} catch (Exception e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
		return details;
	}
}