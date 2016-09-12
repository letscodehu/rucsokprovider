package com.rucsok.comment.view.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.view.model.CommentInsertRequest;
import com.rucsok.rucsok.domain.Rucsok;

@Component
public class CommentPostConverter implements Converter<CommentInsertRequest, Comment> {

	@Override
	public Comment convert(CommentInsertRequest source) {
		Comment result = new Comment();
		result.setText(source.getText());
		result.setRucsok(createRucsok(source));
		result.setParent(createParent(source));
		return result;
	}

	private Comment createParent(CommentInsertRequest source) {
		Comment parent = null;
		if(0 != source.getParentId() ){
			parent = new Comment();
			parent.setId(source.getParentId());
		}
		return parent;
	}

	private Rucsok createRucsok(CommentInsertRequest source) {
		Rucsok rucsok = new Rucsok();
		rucsok.setId(source.getRucsokId());
		return rucsok;
	}

}
