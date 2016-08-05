package com.rucsok.rucsok.service.helper;

import org.jsoup.nodes.Document;

public interface DocumentParser {
	
	public String getImageUrl(Document doc);
	public String getTitle(Document doc);
	public String getVideoUrl(Document doc);

}
