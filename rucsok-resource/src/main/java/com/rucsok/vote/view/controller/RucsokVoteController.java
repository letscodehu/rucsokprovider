package com.rucsok.vote.view.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.vote.service.VoteService;
import com.rucsok.vote.service.transform.VoteTransformer;
import com.rucsok.vote.view.model.RucsokVoteRequest;
import com.rucsok.vote.view.model.RucsokVoteResponse;

@RestController
public class RucsokVoteController {

	@Autowired
	VoteTransformer transformer;
	
	@Autowired
	private VoteService voteService;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/vote", method = RequestMethod.POST)
	public RucsokVoteResponse voteForRucsok(@RequestBody RucsokVoteRequest request, Principal principal) {
		boolean success = true;
		try {
			voteService.createVote(transformer.transformFromRucsokVoteRequest(request), principal);
		} catch (Exception e) {
			success = false;
		}
		
		return new RucsokVoteResponse(success);
	}
	
}
