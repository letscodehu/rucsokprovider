package com.rucsok.comment.view.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.transform.DateTimeTransformer;
import com.rucsok.comment.transform.OutputTextTransformer;
import com.rucsok.comment.view.model.CommentView;

@Component
public class CommentViewConverter implements Converter<Comment, CommentView> {
	
	private DateTimeTransformer dateTimeTransformer;
	private OutputTextTransformer outputTextTransformer;

	@Autowired
	public CommentViewConverter(DateTimeTransformer dateTimeTransformer, OutputTextTransformer outputTextTransformer){
		this.dateTimeTransformer = dateTimeTransformer;
		this.outputTextTransformer = outputTextTransformer;
	}

	@Override
	public CommentView convert(Comment source) {
		CommentView result = new CommentView();
		result.setText(outputTextTransformer.escape(source.getText()));
		result.setDate(dateTimeTransformer.format(source.getCreatedAt()));
		result.setUsername(source.getUser().getUsername());
		return result;
	}

}
