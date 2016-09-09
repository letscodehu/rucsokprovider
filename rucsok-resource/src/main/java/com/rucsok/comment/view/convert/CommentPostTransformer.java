package com.rucsok.comment.view.convert;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.view.model.CommentInsertRequest;
import com.rucsok.user.domain.User;

@Component
public class CommentPostTransformer {

	@Autowired
	private CommentPostConverter commentPostConverter;

	public Comment transform(CommentInsertRequest request, Principal principal) {
		Comment result = commentPostConverter.convert(request);
		result.setUser(createUserFromName(principal));
		return result;		
	}

	private User createUserFromName(Principal principal) {
		User user = new User();
		user.setUsername(principal.getName());
		return user;
	}
	
}