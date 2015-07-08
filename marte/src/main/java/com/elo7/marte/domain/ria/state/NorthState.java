package com.elo7.marte.domain.ria.state;

import com.elo7.marte.domain.ria.vo.CoordinatesVO;

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
		return "N";
	}
	
}
