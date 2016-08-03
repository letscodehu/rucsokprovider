package com.rucsok.rucsok.service.helper;

import java.io.IOException;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class UrlFetchHelper {
	
	private static final String CONTENT = "content";
	private static final String META_PROPERTY_OG_IMAGE = "meta[property=og:image]";
	private static final String META_PROPERTY_OG_TITLE = "meta[property=og:title]";
		
	public Optional<Document> fetchUrl(String url) throws IOException {
		try {
			return Optional.of(Jsoup.connect(url).get());
		} catch (UnsupportedMimeTypeException ex) {
			return Optional.ofNullable(null);
		}
	}	

	public String getTitle(Document doc) {
		String title;
		Elements metaOgTitle = doc.select(META_PROPERTY_OG_TITLE);
		if (metaOgTitle != null) {
			title = metaOgTitle.attr(CONTENT);
		} else {
			title = doc.title();
		}
		return title;
	}

	public String getImage(Document doc) {
		String imageUrl = null;
		Elements metaOgImage = doc.select(META_PROPERTY_OG_IMAGE);
		if (metaOgImage != null) {
			imageUrl = metaOgImage.attr(CONTENT);
		}
		return imageUrl;
	}
}
