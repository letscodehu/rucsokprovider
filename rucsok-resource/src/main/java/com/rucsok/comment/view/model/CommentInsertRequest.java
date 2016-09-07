package com.rucsok.comment.view.model;

public class CommentInsertRequest {

	private int rucsokId;
	private int parentId;
	private String text;

	public int getRucsokId() {
		return rucsokId;
	}

	public void setRucsokId(int rucsokId) {
		this.rucsokId = rucsokId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
