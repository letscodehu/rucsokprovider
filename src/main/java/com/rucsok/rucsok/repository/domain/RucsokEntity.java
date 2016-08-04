package com.rucsok.rucsok.repository.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="Rucsok")
public class RucsokEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public RucsokEntity() {
		
	}
	
	public RucsokEntity(String title, String link, String image) {
		super();
		this.title = title;
		this.link = link;
		this.imageUrl = image;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String link;
	private String imageUrl;
	private String videoUrl;
	
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String image) {
		this.imageUrl = image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	
	
}
 