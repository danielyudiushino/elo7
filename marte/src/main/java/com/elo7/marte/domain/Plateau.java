package com.elo7.marte.domain;

public class Plateau {

	private Coordinates coordinates;
	private Explorer explorer;

	public Plateau(Coordinates coordinates) {
		super();
		this.coordinates = coordinates;
	}

	public void command(String command)throws Exception {
		if(Command.MOVE.getValue().equals(command)) {
			explorer.move(coordinates);
		} else {
			State state = methodFactory(command);
			explorer.setState(state);
		}
	}
	
	private State methodFactory(String command) {
		State state = null;
		switch (Command.get(command)) {
		case NORTH:
			state = null;
			break;
		case SOUTH:
			state = null;
			break;
		case EAST:
			state = null;
			break;
		case WEST:
			state = null;
			break;
		default:
			break;
		}
		
		return state;
	}

}
