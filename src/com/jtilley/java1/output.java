package com.jtilley.java1;

public class output {
	public double hp;
	public double mpg;
	private double engine;
	private int cyl;
	



	public output(double engine, int cyl) {
		
		hp = (double) (engine + cyl);
		
	}
	
}
