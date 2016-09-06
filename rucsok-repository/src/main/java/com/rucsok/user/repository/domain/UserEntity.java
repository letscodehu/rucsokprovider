package com.rucsok.user.repository.domain;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.repository.domain.VoteEntity;

@Entity(name = "User")
public class UserEntity implements UserDetails {

	@Id
	private long id;
	private String name;
	private String email;
	
	public UserEntity() {
		super();
	}
	
	public UserEntity(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	
	public UserEntity(String name, String email, String password) {
		this(name, email);
		this.password = password;
	}

	private String password;
	private int failedlogin;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<RucsokEntity> rucsoks;
	
	@OneToMany(mappedBy = "vote", fetch = FetchType.LAZY)
	private Set<VoteEntity> votes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getFailedlogin() {
		return failedlogin;
	}

	public void setFailedlogin(int failedlogin) {
		this.failedlogin = failedlogin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
