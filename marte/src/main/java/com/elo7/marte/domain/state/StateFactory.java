package com.elo7.marte.domain.state;

import com.elo7.marte.domain.Direction;

public class StateFactory {
	
	public static State directionFactory(String direction) {
		State state = null;
		switch (Direction.get(direction)) {
		case NORTH:
			state = new NorthState();
			break;
		case SOUTH:
			state = new SouthState();
			break;
		case EAST:
			state = new EastState();
			break;
		case WEST:
			state = new WestState();
			break;
		}
		return state;
	}

}
