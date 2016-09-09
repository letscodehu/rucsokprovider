package com.rucsok.pun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PunGetInsertFormController {

	public static final String VIEW_NAME = "pun/add";

	@RequestMapping(path = "/pun/add", method =  RequestMethod.GET)
	public String index() {
		return VIEW_NAME;
	}
	
}
