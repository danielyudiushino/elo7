package com.elo7.marte.domain;

public abstract class BaseState implements State {
	
	@Override
	public boolean canMove(Coordinates plateauCoordinates, Coordinates coordinates) {
		Coordinates newCoordinates = move(coordinates);
		return plateauCoordinates.getLat().isBiggerThan(newCoordinates.getLat())
				|| plateauCoordinates.getLng().isBiggerThan(newCoordinates.getLng())
				|| newCoordinates.getLat().isLessThan(LatVO.ZERO)
				|| newCoordinates.getLng().isLessThan(LngVO.ZERO);
	}

}
