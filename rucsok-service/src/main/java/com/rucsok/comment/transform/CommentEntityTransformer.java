package com.rucsok.comment.transform;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rucsok.comment.DateService;
import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.repository.domain.CommentEntity;

/**
 * 
 * @deprecated
 */
@Component
public class CommentEntityTransformer {
	
	@Autowired
	private DateService dateService;

	public CommentEntity transformToEntity(Comment comment) {
		CommentEntity result = new CommentEntity();
		result.setCreatedAt(dateService.getCurrentTimestamp());
		result.setText(comment.getText());
		return result;
	}

	public Comment transformToComment(CommentEntity comment) {
		Comment result = new Comment();
		result.setText(comment.getText());
		result.setCreatedAt(dateService.getLocaldateTimeFromDate(comment.getCreatedAt()));
		return result;
	}

	public List<Comment> transformToCommentList(List<CommentEntity> comments){
		return comments
				.stream()
				.map(this::transformToComment)
				.collect(Collectors.toList());
	}
}
