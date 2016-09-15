package com.rucsok.pun.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rucsok.pun.model.PunInsertForm;
import com.rucsok.pun.service.PunInsertService;
import com.rucsok.pun.transform.PunInsertTransformer;

@Controller
public class PunPostInsertFormController {

	
	private PunInsertTransformer transformer;
	private PunInsertService service;
	
	@Autowired
	public PunPostInsertFormController(PunInsertTransformer transformer, PunInsertService service) {
		super();
		this.service = service;
		this.transformer = transformer;
	}

	public static final String REDIRECTVIEW = "redirect:" + PunListController.PUN_INDEX;

	@RequestMapping(path = PunGetInsertFormController.PUN_ADD_URL, method= RequestMethod.POST)
	public String index(@ModelAttribute @Valid PunInsertForm form, BindingResult result, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return PunGetInsertFormController.VIEW_NAME;
		}
		service.createPun(transformer.convert(form));
		return REDIRECTVIEW;
	}
	
}
