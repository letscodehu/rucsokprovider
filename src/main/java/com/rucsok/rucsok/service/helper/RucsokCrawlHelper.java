package com.rucsok.rucsok.service.helper;

import java.io.IOException;
import java.util.Optional;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.service.transform.RucsokTypeTransform;

@Component
public class RucsokCrawlHelper {

	private static final String HTTPS = "https://";
	private static final String HTTP = "http://";

	@Autowired
	private DocumentParser documentParser;

	@Autowired
	private UrlFetchHelper urlFetcher;
	
	@Autowired
	private RucsokTypeTransform rucsokTypeTransformer;

	public Rucsok createRucsokFromUrl(String url) throws IOException {
		Rucsok rucsok = new Rucsok();
		Optional<Document> documentFromUrl = urlFetcher.fetchUrl(checkHttpPrefix(url));
		setRucsokUrl(url, rucsok);
		setRucsokImageUrl(url, rucsok, documentFromUrl);
		setRucsokTitle(rucsok, documentFromUrl);
		setRucsokVideoUrl(rucsok, documentFromUrl);
		setRucsokType(rucsok);
		return rucsok;
	}

	private void setRucsokType(Rucsok rucsok) {
		rucsok.setType(rucsokTypeTransformer.getRucsokTypeFromString(rucsok.getVideoUrl()));
	}
	
	private void setRucsokImageUrl(String url, Rucsok rucsok, Optional<Document> documentFromUrl) throws IOException {
		if (documentFromUrl.isPresent()) {
			rucsok.setImageUrl(documentParser.getImageUrl(documentFromUrl.get()));
		} else {
			rucsok.setImageUrl(checkHttpPrefix(url));
		}
	}

	private void setRucsokTitle(Rucsok rucsok, Optional<Document> documentFromUrl) throws IOException {
		if (documentFromUrl.isPresent()) {
			rucsok.setTitle(documentParser.getTitle(documentFromUrl.get()));
		}
	}
	
	private void setRucsokVideoUrl(Rucsok rucsok, Optional<Document> documentFromUrl) throws IOException {
		if (documentFromUrl.isPresent()) {
			rucsok.setVideoUrl(documentParser.getVideoUrl(documentFromUrl.get()));
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
