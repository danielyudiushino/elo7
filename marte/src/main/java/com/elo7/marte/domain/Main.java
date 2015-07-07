package com.elo7.marte.domain;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		Nasa nasa = new Nasa();
		
		Plateau plateau = nasa.definePlateauToExplore(new Coordinates(5, 5));
		
		Explorer explorer = nasa.createExplorer().sentTo(plateau).in(new Coordinates(0, 3), "N");
		
		nasa.move(explorer, "M");
		nasa.move(explorer, "M");
		nasa.move(explorer, "L");
		nasa.move(explorer, "M");
		nasa.move(explorer, "M");
		nasa.move(explorer, "S");
		nasa.move(explorer, "M");
		
		System.out.println(explorer);
		
	}

}
