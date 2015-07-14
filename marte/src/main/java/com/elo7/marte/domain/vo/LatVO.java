package com.elo7.marte.domain.vo;


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

	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		if(obj instanceof LatVO) {
			LatVO ob = (LatVO)obj;
			return value==ob.value;
		}
		return false;
	}

}
