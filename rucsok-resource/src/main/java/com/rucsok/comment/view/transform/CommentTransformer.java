package com.rucsok.comment.view.transform;

import org.springframework.stereotype.Component;

import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.view.model.CommentInsertRequest;
import com.rucsok.comment.view.model.CommentView;

@Component
public class CommentTransformer {

	public Comment transformToComment(CommentInsertRequest request) {
		Comment result = new Comment();
		result.setText(request.getText());
//		Comment parent = new Comment();
//		parent.set
//		result.setParent();
		return null;
	}

	public CommentView transformToView(Comment saveRucsok) {
		// TODO Auto-generated method stub
		return null;
	}

}
