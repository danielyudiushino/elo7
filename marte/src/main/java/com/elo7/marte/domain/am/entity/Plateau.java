package com.elo7.marte.domain.am.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PLATEAU")
public class Plateau {

	@Id
	@Column(name="ID")
	private int id;
	@Column(name="LAT")
	private int lat;
	@Column(name="LNG")
	private int lng;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLat() {
		return this.lat;
	}
	public void setLat(int lat) {
		this.lat = lat;
	}
	public int getLng() {
		return this.lng;
	}
	public void setLng(int lng) {
		this.lng = lng;
	}
	
}
