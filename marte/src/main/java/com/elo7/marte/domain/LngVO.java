package com.elo7.marte.domain;

public class LngVO {

	private int value;

	public LngVO(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public int plus() {
		return ++value;
	}
	
	public int minus() {
		return --value;
	}

}
