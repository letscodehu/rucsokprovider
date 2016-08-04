package com.rucsok.rucsok.domain;

public class Rucsok {

	private long id;
	private String title;
	private String link;
	private String imageUrl;
	private String videoUrl;

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

}
