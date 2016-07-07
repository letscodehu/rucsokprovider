package com.rucsok.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Rucsok {

	@Id
	private long id;
	private String quote;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public RucsokType getType() {
		return type;
	}

	public void setType(RucsokType type) {
		this.type = type;
	}

	private RucsokType type;
	
	private enum RucsokType {
		QUOTE, LINK
	}
	
	
}
