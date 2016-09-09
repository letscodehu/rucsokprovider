package com.rucsok.comment.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.rucsok.comment.DateService;
import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.repository.domain.CommentEntity;

@Component
public class CommentEntityConverter implements Converter<Comment, CommentEntity> {

	@Autowired
	private DateService dateService;
	
	@Override
	public CommentEntity convert(Comment source) {
		CommentEntity result = new CommentEntity();
		result.setCreatedAt(dateService.getCurrentTimestamp());
		result.setText(source.getText());
		return result;
	}

}
