package com.elo7.marte.domain.strategy;

import com.elo7.marte.domain.Explorer;
import com.elo7.marte.domain.vo.CoordinatesVO;

public class LinearStrategy implements Strategy {
	
	private Explorer explorer;
	
	public LinearStrategy(Explorer explorer) {
		super();
		this.explorer = explorer;
	}

	@Override
	public void move() {
		CoordinatesVO coordinates = explorer.getState().move(explorer.getCoordinates());
		explorer.setCoordinates(coordinates);
	}
	
}
