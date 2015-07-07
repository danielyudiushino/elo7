package com.elo7.marte.domain;

public class SpinStrategy implements Strategy {

	private String command;
	
	public SpinStrategy(String command) {
		super();
		this.command = command;
	}

	@Override
	public void move(Explorer explorer) throws Exception {
		explorer.setState(StateFactory.methodFactory(command));
	}
	
}
