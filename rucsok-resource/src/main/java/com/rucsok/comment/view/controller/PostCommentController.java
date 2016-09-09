package com.rucsok.comment.view.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
		Comment transform = commentPostTransformer.transform(request, principal);
		Comment saveRucsok = commentService.saveRucsok(transform);
		CommentView convert = commentViewConverter.convert(saveRucsok);
		return convert;
	}
}
