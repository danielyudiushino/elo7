package com.elo7.marte.domain.ria.state;

import com.elo7.marte.domain.ria.vo.CoordinatesVO;
import com.elo7.marte.domain.ria.vo.LngVO;

public class SouthState implements State {

	@Override
	public CoordinatesVO move(CoordinatesVO coordinates) {
		return new CoordinatesVO(coordinates.getLat().getValue(), coordinates.getLng().minus());
	}
	
	@Override
	public boolean canMove(CoordinatesVO plateauCoordinates, CoordinatesVO coordinates) {
		return coordinates.getLng().isBiggerThan(LngVO.ZERO);
	}
	
	@Override
	public String stateType() {
		return "S";
	}

}
