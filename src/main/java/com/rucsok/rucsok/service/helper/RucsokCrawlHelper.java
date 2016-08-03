package com.rucsok.rucsok.service.helper;

import java.io.IOException;
import java.util.Optional;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rucsok.rucsok.domain.Rucsok;

@Component
public class RucsokCrawlHelper {

	private static final String HTTPS = "https://";
	private static final String HTTP = "http://";

	@Autowired
	private UrlFetchHelper urlFetchHelper;

	public Rucsok createRucsokFromUrl(String url) throws IOException {
		Rucsok rucsok = new Rucsok();
		Optional<Document> documentFromUrl = urlFetchHelper.fetchUrl(checkHttpPrefix(url));
		setRucsokUrl(url, rucsok);
		setRucsokImage(url, rucsok, documentFromUrl);
		setRucsokTitle(url, rucsok, documentFromUrl);
		return rucsok;
	}

	private void setRucsokImage(String url, Rucsok rucsok, Optional<Document> documentFromUrl) throws IOException {
		if (documentFromUrl.isPresent()) {
			rucsok.setImage(urlFetchHelper.getImage(documentFromUrl.get()));
		} else {
			rucsok.setImage(checkHttpPrefix(url));
		}
	}

	private void setRucsokTitle(String url, Rucsok rucsok, Optional<Document> documentFromUrl) throws IOException {
		if (documentFromUrl.isPresent()) {
			rucsok.setImage(urlFetchHelper.getTitle(documentFromUrl.get()));
		}
	}

	private void setRucsokUrl(String url, Rucsok rucsok) {
		rucsok.setLink(checkHttpPrefix(url));
	}

	private String checkHttpPrefix(String url) {
		if (!url.contains(HTTP) && !url.contains(HTTPS)) {
			url = HTTP + url;
		}
		return url;
	}

}
