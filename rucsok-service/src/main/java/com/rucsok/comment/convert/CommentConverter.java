package com.rucsok.comment.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.rucsok.comment.DateService;
import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.repository.domain.CommentEntity;
import com.rucsok.user.transform.UserTransformer;

@Component
public class CommentConverter implements Converter<CommentEntity, Comment> {

	@Autowired
	private DateService dateService;
	
	@Autowired
	private UserTransformer userTransformer;

	@Override
	public Comment convert(CommentEntity source) {
		Comment result = new Comment();
		result.setText(source.getText());
		result.setCreatedAt(dateService.getLocaldateTimeFromDate(source.getCreatedAt()));
		result.setUser(userTransformer.transformEntityToUser(source.getUser()));
		return result;
	}

}
