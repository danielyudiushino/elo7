package com.elo7.marte.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.elo7.marte.domain.repository.ExplorerRepository;
import com.elo7.marte.domain.state.State;
import com.elo7.marte.domain.state.StateFactory;
import com.elo7.marte.domain.strategy.LinearStrategy;
import com.elo7.marte.domain.strategy.SpinStrategy;
import com.elo7.marte.domain.strategy.Strategy;
import com.elo7.marte.domain.vo.CoordinatesVO;
import com.elo7.marte.domain.vo.LatVO;
import com.elo7.marte.domain.vo.LngVO;
import com.elo7.marte.exception.BusinessException;
import com.elo7.marte.exception.Error;

@Entity
@Table(name="EXPLORER")
public class Explorer {

	private int id;
	private Plateau plateau;
	private CoordinatesVO coordinates;
	private State state;
	
	protected Explorer() {}
	
	public Explorer(Plateau plateau, CoordinatesVO coordinates, String state) {
		super();
		this.plateau = plateau;
		this.coordinates = coordinates;
		this.state = StateFactory.directionFactory(state);
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PLATEAU")
	public Plateau getPlateau() {
		return plateau;
	}

	@Transient
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	@Transient
	public CoordinatesVO getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(CoordinatesVO coordinates) {
		this.coordinates = coordinates;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	@Id
	@GeneratedValue
	@Column(name="ID")
	public int getId() {
		return id;
	}
	
	@Column(name="DIRECTION")
	public String getDirection() {
		return state != null ? state.stateType() : null;
	}
	
	public void setDirection(String direction) {
		state = StateFactory.directionFactory(direction);
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
	
	public Explorer move(String command) {
		Strategy strategy = strategy(command);
		strategy.move();
		return this;
	}
	
	private Strategy strategy(String command) {
		if(Command.MOVE.getValue().equals(command)) {
			return new LinearStrategy(this);
		} else {
			return new SpinStrategy(this, command);
		}
	}
	
	public Explorer save(ExplorerRepository repository) {
		return repository.save(this);
	}
	
	public Explorer validate() {
		Set<Error> errors = new HashSet<Error>();
		
		if(plateau == null) {
			errors.add(new Error("Plateau not found"));
		}
		if(coordinates.getLat().getValue() < 0) {
			errors.add(new Error("The minimum lat of the explorer is 0"));
		}
		if(coordinates.getLng().getValue() < 0) {
			errors.add(new Error("The minimum lng of the explorer is 0"));
		}
		
		if(!errors.isEmpty()) {
			throw new BusinessException(errors);
		} else if(invalidCoordinates()) {
			throw new BusinessException("Out of plateau range");
		}
		
		return this;
	}
	
	private boolean invalidCoordinates() {
		return coordinates.getLat().isBiggerThan(plateau.getCoordinates().getLat())
				|| coordinates.getLng().isBiggerThan(plateau.getCoordinates().getLng())
				|| coordinates.getLat().isLessThan(LatVO.ZERO)
				|| coordinates.getLng().isLessThan(LngVO.ZERO);
	}

}
