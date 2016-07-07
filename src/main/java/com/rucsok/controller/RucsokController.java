package com.rucsok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.entity.Rucsok;
import com.rucsok.repository.RucsokRepository;


@RestController
public class RucsokController {

	@Autowired
	private RucsokRepository repo;
	
	
	
	@RequestMapping(name="getrucsok", path = "/rucsok", method = RequestMethod.GET)
	public List<Rucsok> getRucsok() {
		return repo.getAllRucsok();
	}
	
	@RequestMapping(name="postrucsok", path = "/rucsok", method = RequestMethod.POST)
	public String postRucsok() {
		return "";
	}
}
