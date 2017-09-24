package com.rucsok.comment.view.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.authenticaiton.UnauthorizedException;
import com.rucsok.comment.CommentService;
import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.view.convert.CommentPostTransformer;
import com.rucsok.comment.view.convert.CommentViewConverter;
import com.rucsok.comment.view.model.CommentInsertRequest;
import com.rucsok.comment.view.model.CommentView;

@RestController
public class PostCommentController {

	public static final String REQUEST_MAPPING = "/comment";

	@Autowired
	private CommentService commentService;

	@Autowired
	private CommentPostTransformer commentPostTransformer;
	
	@Autowired
	private CommentViewConverter commentViewConverter;

	@RequestMapping(name = "postcomment", path = REQUEST_MAPPING, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public CommentView putRucsok(@RequestBody CommentInsertRequest request, Principal principal) {	
		checkIfUserLoggedIn(principal);
		return commentViewConverter.convert(commentService.saveRucsok(commentPostTransformer.transform(request, principal)));
	}

	private void checkIfUserLoggedIn(Principal principal) {
		if(null == principal){
			throw new UnauthorizedException("Tried to create a comment without login.");
		}
	}
}
