package com.rucsok.rucsok.view.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.rucsok.service.ListedRucsokService;
import com.rucsok.rucsok.view.model.ListedRucsokView;
import com.rucsok.rucsok.view.model.RucsokView;
import com.rucsok.rucsok.view.transform.RucsokTransformer;

@RestController
public class ListHotRucsokController {
	
	public static final String REQUEST_MAPPING = "/rucsok/hot";

	@Autowired
	private ListedRucsokService rucsokService;
	
	@Autowired
	private RucsokTransformer rucsokTransformer;

	@RequestMapping(name = "hotrucsok", path = REQUEST_MAPPING, method = RequestMethod.GET)
	public List<ListedRucsokView> getFreshRucsok(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate date) {
		return rucsokTransformer.transformToListedView(rucsokService.getListedRucsok(date));
	}
	
}
