package com.rucsok.rucsok.service;

import static org.mockito.Mockito.when;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.service.exception.IllegalRucsokArgumentException;
import com.rucsok.rucsok.service.helper.RucsokCrawlHelper;
import com.rucsok.rucsok.view.transform.RucsokTransformer;
import com.rucsok.user.service.UserService;
import com.rucsok.user.transform.UserTransformer;

@Service
public class RucsokCrawlerService {

	private RucsokCrawlHelper crawlHelper;

	private UserService userService;
	
	@Autowired
	private UserTransformer userTransformer;

	@Autowired
	public void setCrawlHelper(RucsokCrawlHelper crawlHelper) {
		this.crawlHelper = crawlHelper;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Rucsok crawl(String url, String username) {
		checkIfUserExists(username);
		Rucsok crawlRucsokFromUrl = crawlRucsokFromUrl(url);
		setRucsokUser(crawlRucsokFromUrl, username);
		return crawlRucsokFromUrl;
	}

	private Rucsok crawlRucsokFromUrl(String url) {
		try {
			return crawlHelper.createRucsokFromUrl(url);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private void setRucsokUser(Rucsok crawledRucsok, String username) {
		crawledRucsok.setUser(userTransformer.transformEntityToUser(userService.findUserByName(username)));
	}

	private void checkIfUserExists(String username) {
		if (!userService.isUserExists(username)) {
			throw new IllegalRucsokArgumentException();
		}
	}

}
