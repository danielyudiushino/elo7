package com.elo7.marte.domain;

import com.elo7.marte.exception.BusinessException;

public enum Direction {
	
	NORTH("N"), SOUTH("S"), WEST("O"), EAST("L");

	private String value;

	private Direction(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static Direction get(String value) {
		for(Direction command : values()) {
			if(command.value.equals(value))
				return command;
		}
		throw new BusinessException("Invalid direction");
	}
	
}
