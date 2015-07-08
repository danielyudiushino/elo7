package com.elo7.marte.domain.ria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.elo7.marte.domain.ria.repository.PlateauRepository;
import com.elo7.marte.domain.ria.vo.CoordinatesVO;

@Entity
@Table(name="PLATEAU")
public class Plateau {

	private int id;
	private CoordinatesVO coordinates;
	
	public Plateau(CoordinatesVO coordinates) {
		super();
		this.coordinates = coordinates;
		this.id = 1;
	}

	@Transient
	public CoordinatesVO getCoordinates() {
		return coordinates;
	}
	
	@Id
	@Column(name="ID")
	public int getId() {
		return id;
	}

	@Column(name="LAT")
	public int getLat() {
		return coordinates.getLat().getValue();
	}
	
	@Column(name="LNG")
	public int getLng() {
		return coordinates.getLng().getValue();
	}
	
	public Plateau save(PlateauRepository repository) {
		return repository.save(this);
	}

}
