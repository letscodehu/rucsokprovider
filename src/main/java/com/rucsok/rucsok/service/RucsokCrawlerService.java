package com.rucsok.rucsok.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.service.helper.RucsokCrawlHelper;

@Service
public class RucsokCrawlerService {

	private RucsokCrawlHelper crawlHelper;

	@Autowired
	public void setCrawlHelper(RucsokCrawlHelper crawlHelper) {
		this.crawlHelper = crawlHelper;
	}

	public Rucsok crawl(String url) {
		try {
			return crawlHelper.createRucsokFromUrl(url);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
