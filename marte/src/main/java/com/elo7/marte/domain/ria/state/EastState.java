package com.elo7.marte.domain.ria.state;

import com.elo7.marte.domain.ria.vo.CoordinatesVO;

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
		return "L";
	}

}
