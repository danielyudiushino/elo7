package com.elo7.marte.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.elo7.marte.domain.repository.PlateauRepository;
import com.elo7.marte.domain.vo.CoordinatesVO;
import com.elo7.marte.domain.vo.LatVO;
import com.elo7.marte.domain.vo.LngVO;

@Entity
@Table(name="PLATEAU")
public class Plateau {

	private int id;
	private CoordinatesVO coordinates;
	
	protected Plateau() {}
	
	public Plateau(CoordinatesVO coordinates) {
		super();
		this.coordinates = coordinates;
	}

	@Transient
	public CoordinatesVO getCoordinates() {
		return coordinates;
	}
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public void setCoordinates(CoordinatesVO coordinates) {
		this.coordinates = coordinates;
	}

	@Column(name="LAT")
	public int getLat() {
		return coordinates != null ? coordinates.getLat().getValue() : 0;
	}
	
	public void setLat(int lat) {
		if(coordinates == null) {
			coordinates = new CoordinatesVO();
		}
		coordinates.setLat(new LatVO(lat));
	}
	
	@Column(name="LNG")
	public int getLng() {
		return coordinates != null ? coordinates.getLng().getValue() : 0;
	}
	
	public void setLng(int lng) {
		if(coordinates == null) {
			coordinates = new CoordinatesVO();
		}
		coordinates.setLng(new LngVO(lng));
	}
	
	public Plateau save(PlateauRepository repository) {
		return repository.save(this);
	}

}
