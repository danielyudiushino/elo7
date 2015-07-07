package com.elo7.marte.domain;

public class WestState implements State {

	@Override
	public Coordinates move(Coordinates coordinates) {
		return new Coordinates(coordinates.getLat().minus(), coordinates.getLng().getValue());
	}
	
	@Override
	public boolean canMove(Coordinates plateauCoordinates, Coordinates coordinates) {
		return coordinates.getLat().isBiggerThan(LatVO.ZERO);
	}

}
