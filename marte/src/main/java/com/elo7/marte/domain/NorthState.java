package com.elo7.marte.domain;

public class NorthState implements State {

	@Override
	public Coordinates move(Coordinates coordinates) {
		return new Coordinates(coordinates.getLat().getValue(), coordinates.getLng().plus());
	}
	
	@Override
	public boolean canMove(Coordinates plateauCoordinates, Coordinates coordinates) {
		return plateauCoordinates.getLng().isBiggerThan(coordinates.getLng());
	}

}
