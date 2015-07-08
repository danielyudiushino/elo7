package com.elo7.marte.domain.ria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.elo7.marte.domain.ria.repository.ExplorerRepository;
import com.elo7.marte.domain.ria.state.State;
import com.elo7.marte.domain.ria.state.StateFactory;
import com.elo7.marte.domain.ria.strategy.Strategy;
import com.elo7.marte.domain.ria.vo.CoordinatesVO;

@Entity
@Table(name="EXPLORER")
public class Explorer {

	private int id;
	private Plateau plateau;
	private CoordinatesVO coordinates;
	private State state;
	
	public Explorer(Plateau plateau, CoordinatesVO coordinates, State state) {
		super();
		this.plateau = plateau;
		this.coordinates = coordinates;
		this.state = state;
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
	
	@Id
	@Column(name="ID")
	public int getId() {
		return id;
	}
	
	@Column(name="DIRECTION")
	public String getDirection() {
		return state.stateType();
	}
	
	@Column(name="LAT")
	public int getLat() {
		return coordinates.getLat().getValue();
	}
	
	@Column(name="LNG")
	public int getLng() {
		return coordinates.getLng().getValue();
	}
	
	public boolean canMove(Plateau plateau) {
		return this.state.canMove(plateau.getCoordinates(), this.coordinates);
	}
	
	public Explorer move(String command) throws Exception {
		Strategy strategy = strategy(command);
		strategy.move();
		return this;
	}
	
	private Strategy strategy(String command) {
		if(Command.MOVE.getValue().equals(command)) {
			//LINEAR
			return new Strategy() {
				@Override
				public void move() throws Exception {
					if(canMove(plateau)) {
						coordinates = state.move(coordinates);
					} else {
						throw new Exception("Out of range");
					}
				}
			};
		} else {
			//SPIN
			return new Strategy() {
				@Override
				public void move() throws Exception {
					setState(StateFactory.methodFactory(command));
				}
			};
		}
	}
	
	public Explorer save(ExplorerRepository repository) {
		return repository.save(this);
	}

}
