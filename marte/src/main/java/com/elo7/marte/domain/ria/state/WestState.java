package com.elo7.marte.domain.ria.state;

import com.elo7.marte.domain.ria.vo.CoordinatesVO;
import com.elo7.marte.domain.ria.vo.LatVO;

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
		return "O";
	}
	
}
