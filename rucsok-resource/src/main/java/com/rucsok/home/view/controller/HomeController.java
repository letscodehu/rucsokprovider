package com.rucsok.home.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class HomeController {

	@RequestMapping(name = "home", path = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

}
