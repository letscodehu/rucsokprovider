package com.rucsok.rucsok.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.rucsok.service.RucsokService;
import com.rucsok.rucsok.view.model.SingleRucsokView;
import com.rucsok.rucsok.view.transform.RucsokTransformer;

@RestController
public class SingleRucsokController {
	public static final String REQUEST_MAPPING = "/rucsok/{id}";

	@Autowired
	private RucsokService rucsokService;
	
	@Autowired
	private RucsokTransformer rucsokTransformer;

	@RequestMapping(name = "getSingleRucsok", path = REQUEST_MAPPING, method = RequestMethod.GET)
	public SingleRucsokView getRucsok(@PathVariable("id") int id) {
		return rucsokTransformer.transformToSingleView(rucsokService.findRucsokById(id));
	}
}
