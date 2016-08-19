package com.rucsok.rucsok.view.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RucsokView {

	public static final String DATEFORMAT = "yyyy.MM.dd HH:mm";

	private String title;
	private String link;
	private long id;
	private String imageUrl;
	private String videoUrl;
	private String type;
	private String username;

	private LocalDateTime createdAt;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATEFORMAT);

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

//	public String getCreatedAtAsString() {
//		String result = "";
//		if (null != createdAt) {
//			result = createdAt.format(formatter);
//		}
//		return result;
//	}

	public void setCreatedAt(LocalDateTime createdDateTime) {
		this.createdAt = createdDateTime;
	}

}
