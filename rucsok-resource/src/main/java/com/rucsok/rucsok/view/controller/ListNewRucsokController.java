package com.rucsok.rucsok.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.rucsok.service.RucsokService;
import com.rucsok.rucsok.view.model.RucsokView;
import com.rucsok.rucsok.view.transform.RucsokTransformer;

@RestController
public class ListNewRucsokController {

	public static final String REQUEST_MAPPING = "/rucsok/new/{page}";

	@Autowired
	private RucsokService rucsokService;
	
	@Autowired
	private RucsokTransformer rucsokTransformer;

	@RequestMapping(name = "freshrucsok", path = REQUEST_MAPPING, method = RequestMethod.GET)
	public List<RucsokView> getFreshRucsok(@PathVariable("page") int pageNumber) {
		return rucsokTransformer.transformToView(rucsokService.findFresh(pageNumber));
	}
}
