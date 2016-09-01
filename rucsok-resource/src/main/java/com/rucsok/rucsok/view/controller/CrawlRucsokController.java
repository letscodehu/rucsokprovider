package com.rucsok.rucsok.view.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.rucsok.service.RucsokCrawlerService;
import com.rucsok.rucsok.view.model.RucsokCheckRequest;
import com.rucsok.rucsok.view.model.RucsokView;
import com.rucsok.rucsok.view.transform.RucsokTransformer;

@RestController
public class CrawlRucsokController {
	public static final String REQUEST_MAPPING = "/check-rucsok";

	private RucsokCrawlerService rucsokService;

	@Autowired
	public void setRucsokService(RucsokCrawlerService rucsokService) {
		this.rucsokService = rucsokService;
	}	

	@Autowired
	private RucsokTransformer rucsokTransformer;


	@CrossOrigin(origins = "*")
	@RequestMapping(name = "createrucsok", path = REQUEST_MAPPING, method = RequestMethod.POST)
	public RucsokView crawlRucsok(@RequestBody RucsokCheckRequest request, Principal principal) throws IOException {
		return rucsokTransformer.transformToView(rucsokService.crawl(request.getUrl(), principal.getName()));
	}

}
