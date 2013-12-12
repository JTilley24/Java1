package com.jtilley.java1;
//Created by Justin Tilley on 12/11/13.
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class ConnectionCheck {

	public Boolean checkConnection(Context context){
		Boolean connection = false;
		ConnectivityManager cManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo network = cManager.getActiveNetworkInfo();
		if(network != null){
			if(network.isConnected()){
				System.out.println("Connection is " + network.getTypeName());
				connection = true;
			}
		}
		
		
		return connection;
	}
}
