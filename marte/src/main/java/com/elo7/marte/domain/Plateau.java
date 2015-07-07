package com.elo7.marte.domain;


public class Plateau {

	private Coordinates coordinates;
	private Explorer explorer;

	public Plateau(Coordinates coordinates) {
		super();
		this.coordinates = coordinates;
	}

	public void setExplorer(Explorer explorer) {
		this.explorer = explorer;
	}
	
	public Explorer getExplorer() {
		return explorer;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	public Plateau create() {
		return this;
	}

}
