package com.rucsok.pun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rucsok.pun.model.PunInsertForm;

@Controller
public class PunGetInsertFormController {

	public static final String PUN_ADD_URL = "/puns/add";
	public static final String VIEW_NAME = "pun/add";

	@ModelAttribute("punInsertForm")
	public PunInsertForm insertForm() {
		return new PunInsertForm();
	}
	
	
	@RequestMapping(path = PUN_ADD_URL, method =  RequestMethod.GET)
	public String index() {
		return VIEW_NAME;
	}
	
}
