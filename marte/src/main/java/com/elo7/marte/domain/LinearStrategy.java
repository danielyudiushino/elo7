package com.elo7.marte.domain;

public class LinearStrategy implements Strategy {

	private Plateau plateau;
	
	public LinearStrategy(Plateau plateau) {
		super();
		this.plateau = plateau;
	}

	@Override
	public void move(Explorer explorer) throws Exception {
		if(explorer.canMove(plateau)) {
			explorer.move();
		} else {
			throw new Exception("Out of range");
		}
	}
	
}
