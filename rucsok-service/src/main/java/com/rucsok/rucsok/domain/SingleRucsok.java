package com.rucsok.rucsok.domain;

public class SingleRucsok {

	private Rucsok current;
	private Rucsok previous;
	private Rucsok next;

	public Rucsok getCurrent() {
		return current;
	}

	public void setCurrent(Rucsok current) {
		this.current = current;
	}

	public Rucsok getPrevious() {
		return previous;
	}

	public void setPrevious(Rucsok previous) {
		this.previous = previous;
	}

	public Rucsok getNext() {
		return next;
	}

	public void setNext(Rucsok next) {
		this.next = next;
	}

}
