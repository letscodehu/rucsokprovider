package com.rucsok.home.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@CrossOrigin(origins = "*")
	@RequestMapping(name = "home", path = "/")
	public String index() {
		return "index";
	}

}
