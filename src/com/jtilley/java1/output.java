package com.jtilley.java1;

public class output {
	public double hp;
	public double mpg;
	double engine;
	int cyl;
	
	//Determine Outputs for HorsePower and Mile Per Gallon
	public output(double engine, int cyl) {
		//Switch Statement for Cylinders
		switch(cyl){
			case 4:{
				hp = (double) (engine * cyl * 20);
				mpg = (double) (engine * 20);
				break;
			}
			case 6:{
				hp = (double) (engine * cyl * 12);
				mpg = (double) (engine * 6);
				break;
			}
			case 8:{
				hp = (double) (engine * cyl * 8);
				mpg = (double) (engine * 2.5);
				break;
			}
		}
	}
	
}
