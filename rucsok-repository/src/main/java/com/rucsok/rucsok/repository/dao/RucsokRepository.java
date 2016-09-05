package com.rucsok.rucsok.repository.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rucsok.rucsok.repository.domain.RucsokEntity;

@Repository
public interface RucsokRepository extends CrudRepository<RucsokEntity, Long> {

	@Query("SELECT r FROM Rucsok r")
	public List<RucsokEntity> getAllRucsok();

	public RucsokEntity findByLink(String link);
	
	@Query("SELECT r FROM Rucsok r WHERE id BETWEEN (:id -1) AND (:id + 1)")
	public List<RucsokEntity> findById(@Param("id") long id);
	
	@Query("from Rucsok ORDER BY CreatedAt DESC")
	public List<RucsokEntity> getAllRucsokByCreatedAt(Pageable pageable);
	
}
