package com.rucsok.pun.view.model;


import java.util.List;

import com.rucsok.pun.service.domain.Pun;

public class PunListResponse {

	private List<Pun> puns;
	private int size;
	private int current;
	private String next;
	private String previous;
	
	public int getSize() {
		return size;
	}

	public int getCurrent() {
		return current;
	}

	public String getNext() {
		return next;
	}

	public String getPrevious() {
		return previous;
	}

	
	public List<Pun> getPuns() {
		return puns;
	}

	public PunListResponse(List<Pun> puns, int current, String nextUrl, String previousUrl) {
		super();
		this.puns = puns;
		this.next = nextUrl;
		this.current = current;
		this.size = puns.size();
	}

}
