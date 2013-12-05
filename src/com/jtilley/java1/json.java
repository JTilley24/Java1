package com.jtilley.java1;

import org.json.JSONException;
import org.json.JSONObject;

public class json {

	
	public static JSONObject buildJSON(){
		JSONObject carsObject = new JSONObject();
		
		try{
			JSONObject queryObject = new JSONObject();
			
			for(cars car : cars.values()){
				JSONObject modelsObject = new JSONObject();
				
				modelsObject.put("make", car.setMake());
				modelsObject.put("model", car.setModel());
				modelsObject.put("cyl", car.setCyl());
				modelsObject.put("engine", car.setEngine());
				modelsObject.put("style", car.setStyle());
				queryObject.put(car.name().toString(), modelsObject);
			}
			carsObject.put("cars", queryObject);
		} catch(JSONException e){
			e.printStackTrace();
		}
		return carsObject;
	}
	
	public static String readJSON(String selected) {
		
		String result, make, model, cyl, engine, style;
		
		JSONObject object = buildJSON();
		
		try{
			make = object.getJSONObject("cars").getJSONObject(selected).getString("make");
			model = object.getJSONObject("cars").getJSONObject(selected).getString("model");
			cyl = object.getJSONObject("cars").getJSONObject(selected).getString("cyl");
			engine = object.getJSONObject("cars").getJSONObject(selected).getString("engine");
			style = object.getJSONObject("cars").getJSONObject(selected).getString("style");
			
			result = "Make: " + make + "\r\n"
					+ "Model: " + model + "\r\n"
					+ "Cylinders: " + cyl + "\r\n"
					+ "Engine: " + engine + "\r\n"
					+ "Style: " + style + "\r\n";
		}catch(JSONException e){
			e.printStackTrace();
			result = e.toString();
		}
		
		return result;
	}
	
}
