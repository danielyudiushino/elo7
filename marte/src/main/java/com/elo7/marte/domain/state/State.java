package com.elo7.marte.domain.state;

import com.elo7.marte.domain.vo.CoordinatesVO;

public interface State {
	String stateType();
    CoordinatesVO move(CoordinatesVO coordinates);
    State lState();
    State rState();
}

