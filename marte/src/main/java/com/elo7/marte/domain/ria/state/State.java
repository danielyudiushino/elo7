package com.elo7.marte.domain.ria.state;

import com.elo7.marte.domain.ria.vo.CoordinatesVO;

public interface State {
	String stateType();
    CoordinatesVO move(CoordinatesVO coordinates);
    boolean canMove(CoordinatesVO plateauCoordinates, CoordinatesVO coordinates);
}

