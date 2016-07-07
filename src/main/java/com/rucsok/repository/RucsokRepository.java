package com.rucsok.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rucsok.entity.Rucsok;

@Repository
public interface RucsokRepository extends CrudRepository<Rucsok, Long> {

	@Query("SELECT r FROM Rucsok r")
	public List<Rucsok> getAllRucsok();

	public Rucsok findByLink(String link);
	
}
