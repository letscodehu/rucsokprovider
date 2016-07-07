package com.rucsok.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.entity.Rucsok;
import com.rucsok.repository.RucsokRepository;


@RestController
public class RucsokController {

	@Autowired
	private RucsokRepository repo;
	
	@Autowired
	private RucsokService rService;
	
	
	@RequestMapping(name="getrucsok", path = "/rucsok", method = RequestMethod.GET)
	public List<Rucsok> getRucsok() {
		return repo.getAllRucsok();
	}
	
	@RequestMapping(name="checkrucsok", path = "/check-rucsok", method = RequestMethod.POST)
	public Rucsok checkRucsok(@RequestBody RucsokCheckRequest request) {
		Rucsok r = null;
		try {
			r = rService.crawl(request.url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	@RequestMapping(name="postrucsok", path = "/rucsok", method = RequestMethod.POST)
	public Rucsok putRucsok(@RequestBody RucsokInsertRequest request) {
		return repo.save(request.rucsok);
	}
	
}
