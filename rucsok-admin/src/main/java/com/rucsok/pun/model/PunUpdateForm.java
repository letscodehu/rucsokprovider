package com.rucsok.pun.model;

public class PunUpdateForm {

	
	private long id;
	
	private String text;
	
	public PunUpdateForm(long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}
	
}
