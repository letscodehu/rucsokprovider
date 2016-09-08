package com.rucsok.comment.repository.domain;

import java.io.Serializable;
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

import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.user.repository.domain.UserEntity;

@Entity(name = "Comment")
public class CommentEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String text;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rucsok_id")
	private RucsokEntity rucsok;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	private CommentEntity parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	private Set<CommentEntity> replies;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public RucsokEntity getRucsok() {
		return rucsok;
	}

	public void setRucsok(RucsokEntity rucsok) {
		this.rucsok = rucsok;
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

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public CommentEntity getParent() {
		return parent;
	}

	public void setParent(CommentEntity parent) {
		this.parent = parent;
	}

	public Set<CommentEntity> getReplies() {
		return replies;
	}

	public void setReplies(Set<CommentEntity> replies) {
		this.replies = replies;
	}

}
