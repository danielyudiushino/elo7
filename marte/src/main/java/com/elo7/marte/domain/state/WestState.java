package com.elo7.marte.domain.state;

import com.elo7.marte.domain.Direction;
import com.elo7.marte.domain.vo.CoordinatesVO;
import com.elo7.marte.domain.vo.LatVO;

public class WestState implements State {

	@Override
	public CoordinatesVO move(CoordinatesVO coordinates) {
		return new CoordinatesVO(coordinates.getLat().minus(), coordinates.getLng().getValue());
	}
	
	@Override
	public boolean canMove(CoordinatesVO plateauCoordinates, CoordinatesVO coordinates) {
		return coordinates.getLat().isBiggerThan(LatVO.ZERO);
	}

	@Override
	public String stateType() {
		return Direction.WEST.getValue();
	}

	@Override
	public State lState() {
		return new SouthState();
	}

	@Override
	public State rState() {
		return new NorthState();
	}
	
}
