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
public class ListCommentController {
	
	public static final String REQUEST_MAPPING = "/rucsok/{rucsokId}/comment/{page}";
	
	@Value("${comment.page.size}")
	private int pageSize;

	@Autowired
	private CommentService commentService;

	@Autowired
	private CommentViewConverter commentViewConverter;

	@RequestMapping(name = "commentlist", path = REQUEST_MAPPING, method = RequestMethod.GET)
	public Page<CommentView> getFreshRucsok(@PathVariable int rucsokId, @PathVariable int page) {		
		return commentService.findCommentsByRucsokId(rucsokId, new PageRequest(page, pageSize)).map(commentViewConverter);
	}
}
