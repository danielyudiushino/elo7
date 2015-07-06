package com.elo7.marte.domain;


public class LatVO {

	public static LatVO ZERO = new LatVO(0);
	
	private int value;

	public LatVO(int value) {
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
	
	public boolean isBiggerThan(LatVO lat) {
		return this.value > lat.value; 
	}
	
	public boolean isLessThan(LatVO lat) {
		return this.value < lat.value; 
	}


}
