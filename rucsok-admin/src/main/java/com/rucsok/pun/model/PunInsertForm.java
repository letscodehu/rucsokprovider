package com.rucsok.pun.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PunInsertForm {

	@NotNull
	@NotEmpty
	private String text;

	public PunInsertForm() {
	}
	
	public PunInsertForm(String punText) {
		this.text = punText;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
