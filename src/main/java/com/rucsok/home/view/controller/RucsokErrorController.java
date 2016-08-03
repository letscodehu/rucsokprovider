package com.rucsok.home.view.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class RucsokErrorController implements ErrorController {

	private final static String ERROR_PATH = "/error";

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

	@RequestMapping(value = ERROR_PATH)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public String index() {
		return "rucskoverflow";
	}
}
