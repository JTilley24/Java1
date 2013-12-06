package com.jtilley.java1;

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


public class MainActivity extends Activity {
	Context mContext;
	String[] mListItems;
	String selectedCar;
	String selectedOutput;
	int cylinders;
	double engine;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = this;
		mListItems = getResources().getStringArray(R.array.cars_array);
		selectedOutput = "HP";
		
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
				selectedCar = json.readJSON(mListItems[position]);
				cylinders = cars.valueOf(mListItems[position]).setCyl();
				engine = cars.valueOf(mListItems[position]).setEngine();
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
		final RadioButton[] rb = new RadioButton[2];
		final RadioGroup rg = new RadioGroup(mContext);
		LinearLayout.LayoutParams radioParams = new RadioGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		radioParams.setMargins(20, 20, 20, 20);
		rg.setLayoutParams(radioParams);
		rg.setOrientation(RadioGroup.HORIZONTAL);
		rg.setGravity(Gravity.CENTER);
		
		//Set OnClick Listener for Radio Buttons
		OnClickListener onRadioButtonClicked = new OnClickListener(){
			 public void onClick(View v){
				RadioButton button = (RadioButton) v;
				v.setSelected(true);
				selectedOutput = (String) button.getText();
			 }
		 };
		 
		 //Create Individual Radio Buttons
		for(int i = 0; i < 2; i++)
		{
			rb[i] = new RadioButton(this);
			if(i == 0){
				rb[i].setText("HP");
				rb[i].setId(1);
				rb[i].setOnClickListener(onRadioButtonClicked);
			} else{
				rb[i].setText("MPG");
				rb[i].setId(2);
				rb[i].setOnClickListener(onRadioButtonClicked);
			}
			rg.addView(rb[i]);
		}
		rg.check(1);
		
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
				output newOutput = new output(engine, cylinders);
				if(selectedOutput == "HP"){	
					txtView.setText(selectedCar + "\r" + "HorsePower: " + newOutput.hp);
				}else if(selectedOutput == "MPG"){
					txtView.setText(selectedCar + "\r" + "Miles Per Gallon: " + newOutput.mpg);
				}
			}
			
		};
		bt.setOnClickListener(onButtonClicked);
		
		//Add UI Elements to View
		linearLayoutMain.addView(title);
		linearLayoutMain.addView(viewSpinner);
		linearLayoutMain.addView(rg);
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
