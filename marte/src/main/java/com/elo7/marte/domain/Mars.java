package com.elo7.marte.domain;

public class Mars {
	
	private Plateau plateau;

	public Plateau getPlateau() {
		return plateau;
	}

	public Plateau definePlateau(Coordinates coordinates) {
		this.plateau = new Plateau(coordinates).create();
		return this.plateau;
	}

}
