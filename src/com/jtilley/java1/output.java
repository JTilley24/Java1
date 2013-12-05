package com.jtilley.java1;

public class output {
	public double hp;
	public double mpg;
	double engine;
	int cyl;
	



	public output(double engine, int cyl) {
		if(cyl == 4){
			hp = (double) (engine * cyl * 20);
			mpg = (double) (engine * 20);
		}else if(cyl == 6){
			hp = (double) (engine * cyl * 12);
			mpg = (double) (engine * 6);
		}else  if(cyl == 8){
			hp = (double) (engine * cyl * 8);
			mpg = (double) (engine * 2.5);
		}
	}
	
}
