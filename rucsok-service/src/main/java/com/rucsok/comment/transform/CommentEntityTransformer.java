package com.rucsok.comment.transform;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.stereotype.Component;

import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.repository.domain.CommentEntity;

@Component
public class CommentEntityTransformer {

	public CommentEntity transformToEntity(Comment comment) {
		CommentEntity result = new CommentEntity();
		result.setCreatedAt(new Date(Instant.now().toEpochMilli()));
		result.setText(comment.getText());
		return result;
	}

	public Comment transformToComment(CommentEntity comment) {
		Comment result = new Comment();
		result.setText(comment.getText());
		result.setCreatedAt(new Timestamp(comment.getCreatedAt().getTime())
				.toLocalDateTime());
		return result;
	}

}
