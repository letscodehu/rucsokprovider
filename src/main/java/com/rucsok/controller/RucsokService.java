package com.rucsok.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.rucsok.entity.Rucsok;


@Service
public class RucsokService {

	
	@PersistenceContext
	private EntityManager em;

	@Transactional()
	public boolean save(Rucsok r) {
		em.persist(r);
		em.flush();
		return true;
	}
	
	
	public Rucsok crawl(String url) throws IOException {
		if (!url.contains("http://") && !url.contains("https://")) {
			url = "http://" + url;
		}
		Rucsok r = new Rucsok();
		r.setLink(url);
		try {
			Document doc = Jsoup.connect(url).get();
			r.setTitle(getTitle(doc));
			r.setImage(getImage(doc));
		} catch (UnsupportedMimeTypeException ex) {
			r.setImage(url);
		}
		return r;
	}

	private String getTitle(Document doc) {
		String title;
		Elements metaOgTitle = doc.select("meta[property=og:title]");
		if (metaOgTitle != null) {
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
