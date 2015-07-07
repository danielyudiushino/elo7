package com.elo7.marte.domain;

public class Explorer {

	private Coordinates coordinates;
	private State state;
	
	public void setState(State state) {
		this.state = state;
	}
	
	public void move(Strategy strategy) throws Exception {
		strategy.move(this);
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	public State getState() {
		return state;
	}
	
	public boolean canMove(Plateau plateau) {
		return this.state.canMove(plateau.getCoordinates(), this.coordinates);
	}
	
	public void move() throws Exception {
		coordinates = this.state.move(this.coordinates);
	}
	
	public Explorer sentTo(Plateau plateau) {
		plateau.setExplorer(this);
		return this;
	}
	
	public Explorer in(Coordinates coordinates, String state) {
		this.coordinates = coordinates;
		this.state = StateFactory.methodFactory(state);
		return this;
	}
	
}
