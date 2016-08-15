package com.rucsok.rucsok.view.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RucsokPost {

	private String title;
	private String link;
	private String imageUrl;
	private String videoUrl;
	private String type;

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

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String rucsokType) {
		this.type = rucsokType;
	}

}
