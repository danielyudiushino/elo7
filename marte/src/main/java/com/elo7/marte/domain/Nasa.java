package com.elo7.marte.domain;

public class Nasa {

	private Mars mars;
	
	public Nasa() {
		this.mars = new Mars();
	}
	
	public Plateau definePlateauToExplore(Coordinates coordinates) {
		return this.mars.definePlateau(coordinates);
	}
	
	public Explorer createExplorer() {
		return new Explorer();
	}
	
	public Explorer move(Explorer explorer, String command) throws Exception {
		if(Command.MOVE.getValue().equals(command)) {
			explorer.move(new LinearStrategy(mars.getPlateau()));
		} else {
			explorer.move(new SpinStrategy(command));
		}
		return explorer;
	}
}
