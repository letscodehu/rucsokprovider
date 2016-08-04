package com.rucsok.rucsok.service.helper;

import java.io.IOException;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class UrlFetchHelper {
	
	public Optional<Document> fetchUrl(String url) throws IOException {
		try {
			return Optional.of(Jsoup.connect(url).get());
		} catch (UnsupportedMimeTypeException ex) {
			return Optional.ofNullable(null);
		}
	}

}
