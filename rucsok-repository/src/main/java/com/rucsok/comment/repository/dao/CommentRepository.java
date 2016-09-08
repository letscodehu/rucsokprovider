package com.rucsok.comment.repository.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rucsok.comment.repository.domain.CommentEntity;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {

}
