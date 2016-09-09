package com.rucsok.comment.repository.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rucsok.comment.repository.domain.CommentEntity;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<CommentEntity, Long> {

	Page<CommentEntity> findByRucsokIdAndParentNullOrderByCreatedAt(long rucsokId, Pageable pageable);
	Page<CommentEntity> findByParentIdOrderByCreatedAt(long parentId, Pageable pageable);
	
}
