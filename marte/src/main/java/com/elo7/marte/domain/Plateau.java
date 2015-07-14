package com.elo7.marte.domain;

import java.util.HashSet;
import java.util.Set;

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
import com.elo7.marte.exception.BusinessException;
import com.elo7.marte.exception.Error;

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
	
	public Plateau validate() {
		Set<Error> errors = new HashSet<Error>();
		
		if(coordinates.getLat().getValue() <= 0) {
			errors.add(new Error("The minimum lat of the plateau is 1"));
		}
		if(coordinates.getLng().getValue() <= 0) {
			errors.add(new Error("The minimum lng of the plateau is 1"));
		}
		
		if(!errors.isEmpty()) {
			throw new BusinessException(errors);
		}
		
		return this;
	}

}
