package com.rucsok.pun.service.domain;

import java.util.List;

public class PageOfPuns {

	private List<Pun> puns;
	private int total;
	private int current;
	private String next;
	private String previous;
	
	public List<Pun> getPuns() {
		return puns;
	}
	public int getTotal() {
		return total;
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
	
	
	public static class PageBuilder {
		
		private List<Pun> puns;
		private int total;
		private int current;
		private String next;
		private String previous;
		
		public void setPrevious(String previous) {
			this.previous = previous;
		}

		public void setPuns(List<Pun> puns) {
			this.puns = puns;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public void setCurrent(int current) {
			this.current = current;
		}

		public void setNext(String next) {
			this.next = next;
		}

		public PageOfPuns build() {
			return new PageOfPuns(this);
		}
		
	}
	
	private PageOfPuns(PageBuilder builder) {
		this.puns = builder.puns;
		this.current = builder.current;
		this.next = builder.next;
		this.previous = builder.previous;
		this.total = builder.total;
	}
}
