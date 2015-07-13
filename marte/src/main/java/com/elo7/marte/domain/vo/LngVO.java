package com.elo7.marte.domain.vo;

public class LngVO {
	
	public static LngVO ZERO = new LngVO(0);

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
	
	public boolean isBiggerThan(LngVO lat) {
		return this.value > lat.value; 
	}
	
	public boolean isLessThan(LngVO lat) {
		return this.value < lat.value; 
	}

}
