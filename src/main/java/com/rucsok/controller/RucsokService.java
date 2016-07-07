package com.rucsok.controller;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.rucsok.entity.Rucsok;


@Service
public class RucsokService {

	public Rucsok crawl(String url) throws IOException {
		if (!url.contains("http://") && !url.contains("https://")) {
			url = "http://" + url;
		}
		Rucsok r = new Rucsok();
		Document doc = Jsoup.connect(url).get();
		r.setLink(url);
		r.setTitle(getTitle(doc));
		r.setImage(getImage(doc));
		return r;
	}

	private String getTitle(Document doc) {
		String title;
		Elements metaOgTitle = doc.select("meta[property=og:title]");
		if (metaOgTitle!=null) {
			title = metaOgTitle.attr("content");
		}
		else {
			title = doc.title();
		}
		return title;
	}

	private String getImage(Document doc) {
		String imageUrl = null;
		Elements metaOgImage = doc.select("meta[property=og:image]");
		if (metaOgImage!=null) {
			imageUrl = metaOgImage.attr("content");
		}
		return imageUrl;
	}

}
