package com.rucsok.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.request.RucsokCheckRequest;
import com.rucsok.request.RucsokInsertRequest;
import com.rucsok.response.GetRucsokResponse;
import com.rucsok.response.RucsokDeleteResponse;
import com.rucsok.rucsok.repository.dao.RucsokDao;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.transform.RucsokToGetRucsokResponse;

@RestController
public class RucsokController {

	@Autowired
	private RucsokDao repo;

	@Autowired
	private RucsokToGetRucsokResponse transformer;
	
	@Autowired
	private RucsokServiceValami rService;

	@RequestMapping(name = "checkrucsok", path = "/check-rucsok", method = RequestMethod.POST)
	public RucsokEntity checkRucsok(@RequestBody RucsokCheckRequest request) {
		RucsokEntity r = null;
		try {
			r = rService.crawl(request.url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	@RequestMapping(name = "postrucsok", path = "/rucsok", method = RequestMethod.POST)
	public RucsokEntity putRucsok(@RequestBody RucsokInsertRequest request) {
		if (null == repo.findByLink(request.rucsok.getLink())) {
			repo.save(request.rucsok);
		}
		return request.rucsok;
	}

	@RequestMapping(name = "delete-rucsok", path = "/rucsok", method = RequestMethod.DELETE)
	public RucsokDeleteResponse removeRucsok(@RequestParam long id) {
		repo.delete(repo.findOne(id));
		return new RucsokDeleteResponse(true);
	}
	

}
