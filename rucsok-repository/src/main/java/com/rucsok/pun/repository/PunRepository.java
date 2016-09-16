package com.rucsok.pun.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rucsok.pun.repository.domain.PunEntity;

@Repository
public interface PunRepository extends PagingAndSortingRepository<PunEntity, Long> {

	@Modifying
	@Query("update Pun p set p.text = ?1 where p.id = ?2")
	void setTextById(String text, Long id);

}
