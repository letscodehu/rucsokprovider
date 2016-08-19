package com.rucsok.rucsok.repository.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.rucsok.user.repository.domain.UserEntity;

@Entity(name = "Rucsok")
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
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	@OneToMany(mappedBy = "vote", fetch = FetchType.LAZY)
	private Set<VoteEntity> votes;

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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdDateTime) {
		this.createdAt = createdDateTime;
	}

}
