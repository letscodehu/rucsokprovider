package com.rucsok.rucsok.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.mockito.internal.creation.instance.InstantationException;

import com.rucsok.user.domain.User;

public class Rucsok {

	private long id;
	private String title;
	private String link;
	private String imageUrl;
	private String videoUrl;
	private RucsokType type;
	private User user;
	private LocalDateTime createdAt;

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

	public RucsokType getType() {
		return type;
	}

	public void setType(RucsokType type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
