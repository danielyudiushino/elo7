package com.elo7.marte.domain.ria.state;

import com.elo7.marte.domain.ria.Command;

public class StateFactory {
	
	public static State methodFactory(String command) {
		State state = null;
		switch (Command.get(command)) {
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
		default:
			break;
		}
		
		return state;
	}

}
