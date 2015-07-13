package com.elo7.marte.domain.strategy;

import com.elo7.marte.domain.Explorer;
import com.elo7.marte.domain.state.StateFactory;

public class SpinStrategy implements Strategy {
	
	private Explorer explorer;
	private String command;
	
	public SpinStrategy(Explorer explorer, String command) {
		super();
		this.explorer = explorer;
		this.command = command;
	}
	
	@Override
	public void move() throws Exception {
		explorer.setState(StateFactory.commandFactory(explorer.getState(), command));
	}

}
