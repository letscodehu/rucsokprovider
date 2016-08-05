package com.rucsok.rucsok.service.helper;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class DocumentParseHelper {
	
	private static final String CONTENT = "content";
	private static final String META_PROPERTY_OG_IMAGE = "meta[property=og:image]";
	private static final String META_PROPERTY_OG_TITLE = "meta[property=og:title]";
	private static final String META_PROPERTY_OG_VIDEO = "meta[property=og:video]";
	private static final String META_PROPERTY_OG_VIDEO_URL = "meta[property=og:video:url]";
	
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

	public String getVideoUrl(Document doc) {
		String imageUrl = null;
		Elements metaOgImage = doc.select(META_PROPERTY_OG_VIDEO);
		if (metaOgImage == null) {
			metaOgImage = doc.select(META_PROPERTY_OG_VIDEO_URL);
		}
		if (metaOgImage != null) {
			imageUrl = metaOgImage.attr(CONTENT);
		}
		return imageUrl;
	}
}
