package com.rucsok.rucsok.view.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Optional;
import com.rucsok.rucsok.service.RucsokService;
import com.rucsok.rucsok.view.model.SingleRucsokView;
import com.rucsok.rucsok.view.transform.RucsokTransformer;
import com.rucsok.vote.service.VoteService;

@RestController
public class SingleRucsokController {
	public static final String REQUEST_MAPPING = "/rucsok/{id}";

	@Autowired
	private RucsokService rucsokService;
	
	@Autowired
	private VoteService voteService;
	
	@Autowired
	private RucsokTransformer rucsokTransformer;

	@RequestMapping(name = "getSingleRucsok", path = REQUEST_MAPPING, method = RequestMethod.GET)
	public SingleRucsokView getRucsok(@PathVariable("id") int id, Principal principal) {
		return rucsokTransformer.transformToSingleView(
				rucsokService.findRucsokById(id), 
				voteService.getVoteStatusForSingleRucsok(getPrincipalName(principal), Long.valueOf(id)));
	}

	private String getPrincipalName(Principal principal) {
		if (principal == null) {
			return null;
		}
		return principal.getName();
	}
}
