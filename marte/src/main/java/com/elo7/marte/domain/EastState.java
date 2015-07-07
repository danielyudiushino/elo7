package com.elo7.marte.domain;

public class EastState implements State {

	@Override
	public Coordinates move(Coordinates coordinates) {
		return new Coordinates(coordinates.getLat().plus(), coordinates.getLng().getValue());
	}
	
	@Override
	public boolean canMove(Coordinates plateauCoordinates, Coordinates coordinates) {
		return plateauCoordinates.getLat().isBiggerThan(coordinates.getLat());
	}

}
