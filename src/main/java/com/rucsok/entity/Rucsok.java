package com.rucsok.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Rucsok {

	@Id
	private long id;
	private String title;
	private String link;
	private String image;
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
