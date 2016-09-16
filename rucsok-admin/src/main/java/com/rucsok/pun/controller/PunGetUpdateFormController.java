package com.rucsok.pun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rucsok.pun.model.PunUpdateForm;
import com.rucsok.pun.service.PunUpdateService;
import com.rucsok.pun.transform.PunUpdateTransformer;

@Controller
public class PunGetUpdateFormController {

	public static final String VIEW_NAME = "/pun/edit";
	private static final String PUN_UPDATE_URL = "/puns/edit/{id}";
	
	private PunUpdateService service;
	
	private PunUpdateTransformer transformer;
	
	@Autowired
	public PunGetUpdateFormController(PunUpdateService service, PunUpdateTransformer transformer) {
		super();
		this.transformer = transformer;
		this.service = service;
	}

	@RequestMapping(path = PUN_UPDATE_URL, method = RequestMethod.GET)
	public String index() {
		return VIEW_NAME;
	}

	@ModelAttribute("punUpdateForm")
	public PunUpdateForm punUpdateForm(@PathVariable("id") long id) {
		return transformer.convert(service.getPun(id));		
	}

}
