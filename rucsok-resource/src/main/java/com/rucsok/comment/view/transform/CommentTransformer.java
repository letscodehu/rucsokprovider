package com.rucsok.comment.view.transform;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.transform.DateTimeTransformer;
import com.rucsok.comment.transform.OutputTextTransformer;
import com.rucsok.comment.view.model.CommentInsertRequest;
import com.rucsok.comment.view.model.CommentView;

/**
 * 
 * @deprecated
 */
@Component
public class CommentTransformer {

	private DateTimeTransformer dateTimeTransformer;
	private OutputTextTransformer outputTextTransformer;

	@Autowired
	public CommentTransformer(DateTimeTransformer dateTimeTransformer, OutputTextTransformer outputTextTransformer){
		this.dateTimeTransformer = dateTimeTransformer;
		this.outputTextTransformer = outputTextTransformer;
	}

	public CommentView transformToView(Comment comment) {
		CommentView result = new CommentView();
		result.setText(outputTextTransformer.escape(comment.getText()));
		result.setDate(dateTimeTransformer.format(comment.getCreatedAt()));
		result.setUsername(comment.getUser().getUsername());
		return result;
	}
	

	public List<CommentView> transformToCommentView(List<Comment> comments) {
		return comments.
				stream()
				.map(this::transformToView)
				.collect(Collectors.toList());
	}

}
