package com.elo7.marte.resources.ro;


public class ExplorerRO {
	
	private int id;
	private int plateauId;
	private int lat;
	private int lng;
	private String direction;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlateauId() {
		return plateauId;
	}
	public void setPlateauId(int plateauId) {
		this.plateauId = plateauId;
	}
	public int getLat() {
		return lat;
	}
	public void setLat(int lat) {
		this.lat = lat;
	}
	public int getLng() {
		return lng;
	}
	public void setLng(int lng) {
		this.lng = lng;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
}
