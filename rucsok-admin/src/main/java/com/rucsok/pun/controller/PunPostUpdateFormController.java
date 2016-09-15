package com.rucsok.pun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rucsok.pun.model.PunUpdateForm;
import com.rucsok.pun.service.PunUpdateService;
import com.rucsok.pun.transform.PunUpdateTransformer;

@Controller
public class PunPostUpdateFormController {

	private static final String URL_EDIT = "/pun/edit";
		
	private PunUpdateService service;
	
	private PunUpdateTransformer transformer;
	
	@Autowired
	public PunPostUpdateFormController(PunUpdateService service, PunUpdateTransformer transformer) {
		super();
		this.service = service;
		this.transformer = transformer;
	}
	
	@RequestMapping(path = URL_EDIT, method = RequestMethod.POST )
	public String index(@ModelAttribute PunUpdateForm mockPun, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		service.updatePun(transformer.convertToPun(mockPun));
		return null;
	}
	
}
