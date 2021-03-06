package com.jtilley.java1;
//Created by Justin Tilley on 12/11/13.
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.os.AsyncTask;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.io.BufferedInputStream;
import java.io.IOException;


public class JSONCars {
	public String responseString = "";
	public String urlString = "";
	public ArrayList<String> carArray;
	
	//Get JSON Data
	public JSONObject getCarsJSON(Context context, String make, String style){
		
		JSONObject carsObject = new JSONObject();
		try {
			if(style.toString() == "All"){
				urlString = "https://api.edmunds.com/api/vehicle/v2/"+ make + "/models?state=new&year=2013&view=basic&fmt=json&api_key=saw2xy7wdxjqfueuxkv5hm8w";
			}else{
				urlString = "https://api.edmunds.com/api/vehicle/v2/" + make +"/models?state=new&year=2013&category="+ style + "&view=basic&fmt=json&api_key=saw2xy7wdxjqfueuxkv5hm8w";
			}
			getData data = new getData();
			responseString = data.execute(urlString).get();
			carsObject = new JSONObject(responseString);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return carsObject;
	}
	
	//Gather Information from JSON
	public ArrayList<String> readCarsJSON(Context context, String make, String style){
		
		JSONObject modelsObject = getCarsJSON(context, make, style);
		try {
			JSONArray modelsArray = modelsObject.getJSONArray("models");
			carArray = new ArrayList<String>();
			for(int i = 0; i < modelsArray.length(); i++){
				JSONObject model = modelsArray.getJSONObject(i);
				String modelName = model.getString("name");
				carArray.add(modelName);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return carArray;
	}
	
	public static String getResponse(URL url){
		String response = "";
		
		try {
			URLConnection conn;
			conn = url.openConnection();
			BufferedInputStream bin = new BufferedInputStream(conn.getInputStream());
			byte[] contextByte = new byte[1024];
			int byteRead = 0;
			StringBuffer responseBuffer = new StringBuffer();
			while ((byteRead = bin.read(contextByte)) != -1){
				response = new String(contextByte, 0, byteRead);
				responseBuffer.append(response);
			}
			response = responseBuffer.toString();
		} catch (IOException e) {
			response = "No info \n" + e;
			e.printStackTrace();
		}
		
		return response;
	}
	
	public class getData extends AsyncTask<String, Void, String>{
		
		protected String doInBackground(String... params){
			String responseString = "";
			try {
				URL url = new URL (urlString);
				responseString = getResponse(url);
			} catch (Exception e) {
				responseString = "No info";
				e.printStackTrace();
			}
			return responseString;
		}
		
		protected void onPostExecute(String results){
			super.onPostExecute(results);
		}
	}
}
