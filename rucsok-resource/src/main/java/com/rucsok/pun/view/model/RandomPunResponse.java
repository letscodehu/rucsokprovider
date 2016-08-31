package com.rucsok.pun.view.model;

import com.rucsok.pun.service.domain.Pun;

public class RandomPunResponse {

	private Pun pun;
	
	public String getText() {
		return pun.getText();
	}
	
	public RandomPunResponse(Pun pun) {
		super();
		this.pun = pun;
	}

	public Long getId() {
		return pun.getId();
	}
	
	
}
