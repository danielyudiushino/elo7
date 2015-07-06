package com.elo7.marte.domain;

public class Coordinates {
	
	private LatVO lat;
	private LngVO lng;
	
	public Coordinates(int lat, int lng) {
		this.lat = new LatVO(lat);
		this.lng = new LngVO(lng);
	}

	public LatVO getLat() {
		return lat;
	}

	public LngVO getLng() {
		return lng;
	}
	
}
