package com.elo7.marte.domain.state;

import com.elo7.marte.domain.Direction;
import com.elo7.marte.domain.vo.CoordinatesVO;

public class EastState implements State {

	@Override
	public CoordinatesVO move(CoordinatesVO coordinates) {
		return new CoordinatesVO(coordinates.getLat().plus(), coordinates.getLng().getValue());
	}
	
	@Override
	public boolean canMove(CoordinatesVO plateauCoordinates, CoordinatesVO coordinates) {
		return plateauCoordinates.getLat().isBiggerThan(coordinates.getLat());
	}

	@Override
	public String stateType() {
		return Direction.EAST.getValue();
	}

	@Override
	public State lState() {
		return new NorthState();
	}

	@Override
	public State rState() {
		return new SouthState();
	}
	
}
