package com.elo7.marte.domain.state;

import com.elo7.marte.domain.Direction;
import com.elo7.marte.domain.vo.CoordinatesVO;

public class SouthState implements State {

	@Override
	public CoordinatesVO move(CoordinatesVO coordinates) {
		return new CoordinatesVO(coordinates.getLat().getValue(), coordinates.getLng().minus());
	}
	
	@Override
	public String stateType() {
		return Direction.SOUTH.getValue();
	}

	@Override
	public State lState() {
		return new EastState();
	}

	@Override
	public State rState() {
		return new WestState();
	}
	
}
