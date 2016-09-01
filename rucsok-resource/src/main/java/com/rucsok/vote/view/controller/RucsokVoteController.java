package com.rucsok.vote.view.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.vote.service.VoteService;
import com.rucsok.vote.view.model.RucsokVoteRequest;
import com.rucsok.vote.view.transform.VoteRequestTransformer;

@RestController
public class RucsokVoteController {

	public static final String REQUEST_MAPPING = "/vote";

	@Autowired
	private VoteRequestTransformer transformer;

	@Autowired
	private VoteService voteService;

	@RequestMapping(value = REQUEST_MAPPING, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void voteForRucsok(@RequestBody RucsokVoteRequest request, Principal principal) {
		voteService.createVote(transformer.transformFromRucsokVoteRequest(request, principal.getName()));
	}

}
