package com.elo7.marte.domain.vo;



public class CoordinatesVO {
	
	private LatVO lat;
	private LngVO lng;
	
	public CoordinatesVO() {}
	
	public CoordinatesVO(int lat, int lng) {
		this.lat = new LatVO(lat);
		this.lng = new LngVO(lng);
	}

	public LatVO getLat() {
		return lat;
	}

	public LngVO getLng() {
		return lng;
	}

	public void setLat(LatVO lat) {
		this.lat = lat;
	}

	public void setLng(LngVO lng) {
		this.lng = lng;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		if(obj instanceof CoordinatesVO) {
			CoordinatesVO ob = (CoordinatesVO)obj;
			return lat.equals(ob.lat) && lng.equals(ob.lng);
		}
		return false;
	}
	
}
