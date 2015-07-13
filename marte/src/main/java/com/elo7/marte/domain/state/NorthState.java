package com.elo7.marte.domain.state;

import com.elo7.marte.domain.Direction;
import com.elo7.marte.domain.vo.CoordinatesVO;

public class NorthState implements State {

	@Override
	public CoordinatesVO move(CoordinatesVO coordinates) {
		return new CoordinatesVO(coordinates.getLat().getValue(), coordinates.getLng().plus());
	}
	
	@Override
	public boolean canMove(CoordinatesVO plateauCoordinates, CoordinatesVO coordinates) {
		return plateauCoordinates.getLng().isBiggerThan(coordinates.getLng());
	}

	@Override
	public String stateType() {
		return Direction.NORTH.getValue();
	}

	@Override
	public State lState() {
		return new WestState();
	}

	@Override
	public State rState() {
		return new EastState();
	}
	
}
