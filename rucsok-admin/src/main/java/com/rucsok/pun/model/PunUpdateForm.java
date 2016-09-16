package com.rucsok.pun.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PunUpdateForm {

	private long id;

	@NotNull
	@NotEmpty
	private String text;
	
	public PunUpdateForm() {
		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

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
