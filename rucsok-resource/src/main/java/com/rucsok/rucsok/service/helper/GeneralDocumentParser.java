package com.rucsok.rucsok.service.helper;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class GeneralDocumentParser implements DocumentParser{

	public static final String CONTENT = "content";
	public static final String META_PROPERTY_OG_IMAGE = "meta[property=og:image]";
	public static final String META_PROPERTY_OG_TITLE = "meta[property=og:title]";
	public static final String META_PROPERTY_OG_VIDEO = "meta[property=og:video]";
	public static final String META_PROPERTY_OG_VIDEO_URL = "meta[property=og:video:url]";
	
	@Override
	public String getImageUrl(Document doc) {
		String image = getMetaProperty(doc, META_PROPERTY_OG_IMAGE);
		if (null == image) {
			image = "";
		}
		return image;
	}
	
	@Override
	public String getTitle(Document doc) {
		String title = getMetaProperty(doc, META_PROPERTY_OG_TITLE);
		if (null == title) {
			title = doc.title();
		}
		return title;
	}

	@Override
	public String getVideoUrl(Document doc) {
		String video = getMetaProperty(doc, META_PROPERTY_OG_VIDEO);
		if(null == video || 0 == video.length()){
			video = getMetaProperty(doc, META_PROPERTY_OG_VIDEO_URL);
		}
		if(null == video){
			video = "";
		}
		return video;
	}

	protected String getMetaProperty(Document doc, String propertyName) {
		String result = null;
		Elements metaOgTitle = doc.select(propertyName);
		if (metaOgTitle != null) {
			result = metaOgTitle.attr(CONTENT);
		}
		return result;
	}

}
