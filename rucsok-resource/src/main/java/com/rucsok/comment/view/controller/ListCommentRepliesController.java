package com.rucsok.comment.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.comment.CommentService;
import com.rucsok.comment.view.convert.CommentViewConverter;
import com.rucsok.comment.view.model.CommentView;

@RestController
public class ListCommentRepliesController {
	
	public static final String REQUEST_MAPPING = "/comment/{commentId}/replies/{page}";

	@Value("${comment.replies.page.size}")
	private int pageSize;

	@Autowired
	private CommentService commentService;

	@Autowired
	private CommentViewConverter commentViewConverter;

	@RequestMapping(name = "commentreplieslist", path = REQUEST_MAPPING, method = RequestMethod.GET)
	public Page<CommentView> getComments(@PathVariable int commentId, @PathVariable int page) {
		return commentService.findCommentsByParentId(commentId, new PageRequest(page, pageSize))
				.map(commentViewConverter);
	}
}
