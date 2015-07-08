package com.elo7.marte.domain.am.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="EXPLORER")
public class Explorer {

	@Id
	@Column(name="ID")
	private int id;
	@Column(name="LAT")
	private int lat;
	@Column(name="LNG")
	private int lng;
	@Column(name="DIRECTION")
	private String direction;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PLATEAU")
	private Plateau plateau;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Plateau getPlateau() {
		return plateau;
	}
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	
}
