package com.elo7.marte.domain;

public class Explorer {

	private Coordinates coordinates;
	private State state;
	
	public Explorer(Coordinates coordinates, State state) {
		super();
		this.coordinates = coordinates;
		this.state = state;
	}
	
	public void setState(State state) {
		this.state = state;
	}

	public void move(Coordinates plateauCoordinates) throws Exception {
		if(this.state.canMove(plateauCoordinates, this.coordinates))
			coordinates = this.state.move(this.coordinates);
		else
			throw new Exception("Out of range");
	}
	
}
