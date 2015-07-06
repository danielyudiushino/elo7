package com.elo7.marte.domain;

public enum Command {
	
	NORTH("N"), SOUTH("S"), WEST("O"), EAST("L");

	private String value;

	private Command(String value) {
		this.value = value;
	}
	
	public static Command get(String value) {
		for(Command command : values()) {
			if(command.value.equals(value))
				return command;
		}
		return null;
	}
	
}
