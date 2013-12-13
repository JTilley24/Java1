package com.jtilley.java1;
//Justin Tilley Java 1

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends Activity {
	Context mContext;
	String[] mListItems;
	String[] styleArray;
	String selectedCar;
	String selectedStyle;
	String carsString;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = this;
		mListItems = getResources().getStringArray(R.array.cars_array);
		styleArray = new String[] {"All", "Coupe", "Sedan", "Convertible", "Pickup", "SUV", "Minivan"};
		selectedStyle = "All";
		selectedCar = "Acura";
		
	
		//Check for Connection
		ConnectionCheck connection = new ConnectionCheck();
		if(connection.checkConnection(mContext)){
			Toast.makeText(mContext, "Connected", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(mContext, "Not Connected.Please connect and try again.", Toast.LENGTH_LONG).show();
		}
		
		//Create Linear Layout
		LinearLayout linearLayoutMain = new LinearLayout(mContext);
		linearLayoutMain.setOrientation(LinearLayout.VERTICAL);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		linearLayoutMain.setLayoutParams(lp);
		
		//Create Spinner
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, mListItems);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner viewSpinner = new Spinner(mContext);
		viewSpinner.setAdapter(spinnerAdapter);
		lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		viewSpinner.setLayoutParams(lp);
		
		//Set OnSelected Listener for Spinner
		viewSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
				selectedCar = mListItems[position];
			}
			public void onNothingSelected(AdapterView<?> arg0){
				
			}
		});
		
		//Create Title 
		TextView title = new TextView(mContext);
		title.setText("Auto Details App");
		title.setGravity(Gravity.CENTER);
		title.setTextSize(30);
		title.setTextColor(Color.BLUE);
		title.setTypeface(null, Typeface.BOLD);
		title.setBackgroundColor(Color.LTGRAY);
		
		//Create Radio Buttons
		final RadioButton[] rb = new RadioButton[styleArray.length];
		final RadioGroup rg = new RadioGroup(mContext);
		final RadioGroup rg2 = new RadioGroup(mContext);
		
		LinearLayout.LayoutParams radioParams = new RadioGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		radioParams.setMargins(20, 20, 20, 20);
		
		rg.setLayoutParams(radioParams);
		rg.setOrientation(RadioGroup.HORIZONTAL);
		rg.setGravity(Gravity.CENTER);
		
		rg2.setLayoutParams(radioParams);
		rg2.setOrientation(RadioGroup.HORIZONTAL);
		rg2.setGravity(Gravity.CENTER);
		
		//Set OnClick Listener for Radio Buttons
		OnClickListener onRadioButtonClicked = new OnClickListener(){
			 public void onClick(View v){
				RadioButton button = (RadioButton) v;
				button.setSelected(true);
				if(button.getId() < 4){
					selectedStyle = (String) button.getText();
					int checkedId = rg2.getCheckedRadioButtonId();
					if(checkedId >= 0){
						RadioButton checkedButton = (RadioButton) findViewById(checkedId);
						checkedButton.setChecked(false);
					}
				}else if(button.getId() == 4){
					selectedStyle = "Extended+Cab+Pickup";
					int checkedId = rg.getCheckedRadioButtonId();
					if(checkedId >= 0){
						RadioButton checkedButton = (RadioButton) findViewById(checkedId);
						checkedButton.setChecked(false);
					}
				}else if(button.getId() == 5){
					selectedStyle = "4dr+SUV";
					int checkedId = rg.getCheckedRadioButtonId();
					if(checkedId >= 0){
						RadioButton checkedButton = (RadioButton) findViewById(checkedId);
						checkedButton.setChecked(false);
					}
				}else if(button.getId() == 6){
					selectedStyle = "Passenger+Minivan";
					int checkedId = rg.getCheckedRadioButtonId();
					if(checkedId >= 0){
						RadioButton checkedButton = (RadioButton) findViewById(checkedId);
						checkedButton.setChecked(false);
					}
				}
			 }
		 };
		 
		 //Create Individual Radio Buttons
		for(int i = 0; i < styleArray.length; i++)
		{
			rb[i] = new RadioButton(this);
			rb[i].setText(styleArray[i]);
			rb[i].setId(i);
			rb[i].setOnClickListener(onRadioButtonClicked);
			if(i <= (styleArray.length / 2)){
				rg.addView(rb[i]);
			} else{
				rg2.addView(rb[i]);
			}
		}
		rg.check(0);
		
		//Create TextView for Results
		final TextView txtView = new TextView(mContext);
		txtView.setText("Please select vehicle and output.");
		txtView.setGravity(Gravity.CENTER);
		txtView.setTextSize(16);
		
		//Create Button and OnClick Listener
		Button bt = new Button(this);
		bt.setText("Select");
		bt.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		OnClickListener onButtonClicked = new OnClickListener(){
			public void onClick(View v){
				ConnectionCheck connection = new ConnectionCheck();
				if(connection.checkConnection(mContext)){
					JSONCars json = new JSONCars();
					ArrayList<String> modelsArray = json.readCarsJSON(mContext, selectedCar, selectedStyle);
					if(modelsArray.size() > 0){
						txtView.setText("");
						for(int i = 0; i < modelsArray.size(); i++){
							String tempString = selectedCar + " "  + modelsArray.get(i) + "\n";
							txtView.append(tempString);
						}
					}else{
						txtView.setText("No vehicles available. Please try again.");
					}
				}else{
					txtView.setText("Not Connected.Please connect and try again.");
				}
			}
		};
		
		bt.setOnClickListener(onButtonClicked);
		
		
		//Add UI Elements to View
		linearLayoutMain.addView(title);
		linearLayoutMain.addView(viewSpinner);
		linearLayoutMain.addView(rg);
		linearLayoutMain.addView(rg2);
		linearLayoutMain.addView(bt);
		linearLayoutMain.addView(txtView);
	
		setContentView(linearLayoutMain);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
