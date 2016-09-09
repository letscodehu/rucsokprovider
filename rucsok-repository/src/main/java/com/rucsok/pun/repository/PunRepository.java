package com.rucsok.pun.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rucsok.pun.repository.domain.PunEntity;

@Repository
public interface PunRepository extends PagingAndSortingRepository<PunEntity, Long> {

}
