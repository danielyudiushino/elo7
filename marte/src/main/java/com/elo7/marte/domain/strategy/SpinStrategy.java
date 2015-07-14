package com.elo7.marte.domain.strategy;

import com.elo7.marte.domain.Command;
import com.elo7.marte.domain.Explorer;
import com.elo7.marte.exception.BusinessException;

public class SpinStrategy implements Strategy {
	
	private Explorer explorer;
	private String command;
	
	public SpinStrategy(Explorer explorer, String command) {
		super();
		this.explorer = explorer;
		this.command = command;
	}
	
	@Override
	public void move() {
		if(Command.RIGHT.getValue().equals(command)) {
			explorer.setState(explorer.getState().rState());
		} else if(Command.LEFT.getValue().equals(command)) {
			explorer.setState(explorer.getState().lState());
		} else {
			throw new BusinessException("Invalid command");
		}
	}

}
