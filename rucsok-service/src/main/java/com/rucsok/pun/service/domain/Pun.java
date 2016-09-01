package com.rucsok.pun.service.domain;


public class Pun {

	private Long id;
	private String text;
	
	public Pun(Long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	public Long getId() {
		return id;
	}
	public String getText() {
		return text;
	}
	
	
}
