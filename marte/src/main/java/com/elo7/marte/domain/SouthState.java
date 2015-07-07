package com.elo7.marte.domain;

public class SouthState implements State {

	@Override
	public Coordinates move(Coordinates coordinates) {
		return new Coordinates(coordinates.getLat().getValue(), coordinates.getLng().minus());
	}
	
	@Override
	public boolean canMove(Coordinates plateauCoordinates, Coordinates coordinates) {
		return coordinates.getLng().isBiggerThan(LngVO.ZERO);
	}

}
