package com.rucsok.rucsok.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.rucsok.service.RucsokService;
import com.rucsok.rucsok.view.model.RucsokView;
import com.rucsok.rucsok.view.transform.RucsokTransformer;

@RestController
public class ListRucsokController {

	public static final String REQUEST_MAPPING = "/rucsok";

	@Autowired
	private RucsokService rucsokService;
	
	@Autowired
	private RucsokTransformer rucsokTransformer;

	@RequestMapping(name = "getrucsok", path = REQUEST_MAPPING, method = RequestMethod.GET)
	public List<RucsokView> getRucsok() {
		return rucsokTransformer.transformToView(rucsokService.findAll());
	}
}
