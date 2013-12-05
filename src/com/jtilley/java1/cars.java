package com.jtilley.java1;

public enum cars {

	Civic("Civic", "Honda", 4, 1.8, "Sedan"),
	Camaro("Camaro", "Chevy", 8, 6.2, "Coupe"),
	Dart("Dart", "Dodge", 4, 2.0,"Sedan"),
	Explorer("Explorer", "Ford", 6, 3.5, "SUV"),
	Sienna("Sienna", "Toyota", 6, 3.5, "Minivan");
	
	
	private final String model;
	private final String make;
	private final int cyl;
	private final double engine;
	private final String style;
	
	
	
	private cars(String model, String make, int cyl, double engine, String style){
		
			this.model = model;
			this.make = make;
			this.cyl = cyl;
			this.engine = engine;
			this.style = style;
	}
	
	public String setModel(){
		return model;
	}
	
	public String setMake(){
		return make;
	}
	
	public int setCyl(){
		return cyl;
	}
	
	public double setEngine(){
		return engine;
	}
	
	public String setStyle(){
		return style; 
	}
}
