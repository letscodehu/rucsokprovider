package com.rucsok.pun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rucsok.pun.service.PunListService;
import com.rucsok.pun.service.domain.Pun;

@Controller
public class PunListController {

	private static final String PUNS_INDEX_URL = "/puns";
	public static final String PUN_INDEX_VIEW = "pun/index";
	@Autowired
	private PunListService punListService;
	
	@ModelAttribute("puns")
	public Page<Pun> puns(Pageable pageRequest) {
		return punListService.listAll(pageRequest);
	}
	
	@RequestMapping(path = PUNS_INDEX_URL, method = RequestMethod.GET)
	public String index() {
		return PUN_INDEX_VIEW;
	}
	
}
