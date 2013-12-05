package com.jtilley.java1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Context;
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
	String selectedCar;
	String selectedOutput;
	int cylinders;
	double engine;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = this;
		mListItems = getResources().getStringArray(R.array.cars_array);
		
		LinearLayout linearLayoutMain = new LinearLayout(mContext);
		linearLayoutMain.setOrientation(LinearLayout.VERTICAL);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		linearLayoutMain.setLayoutParams(lp);
		
		
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, mListItems);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner viewSpinner = new Spinner(mContext);
		viewSpinner.setAdapter(spinnerAdapter);
		lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		viewSpinner.setLayoutParams(lp);
		viewSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
				Toast.makeText(mContext, json.readJSON(mListItems[position]), Toast.LENGTH_LONG).show();
				selectedCar = json.readJSON(mListItems[position]);
				cylinders = cars.valueOf(mListItems[position]).setCyl();
				engine = cars.valueOf(mListItems[position]).setEngine();
				
				
				//output newOutput = new output(engine, cylinders);
				//System.out.println(newOutput.hp);
			}
			
			public void onNothingSelected(AdapterView<?> arg0){
				
			}
			
		});
		
		linearLayoutMain.addView(viewSpinner);
		
		
		
		final RadioButton[] rb = new RadioButton[2];
		final RadioGroup rg = new RadioGroup(mContext);
		rg.setLayoutParams(new RadioGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		rg.setOrientation(RadioGroup.HORIZONTAL);
		
		OnClickListener onRadioButtonClicked = new OnClickListener(){
			 public void onClick(View v){
				RadioButton button = (RadioButton) v;
				v.setSelected(true);
				selectedOutput = (String) button.getText();
			 }
		 };
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
		
		Button bt = new Button(this);
		bt.setText("Select");
		bt.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		OnClickListener onButtonClicked = new OnClickListener(){
			public void onClick(View v){
				Button button = (Button) v;
				if(selectedOutput == "HP"){
					System.out.println("HorsePower");
				}else if(selectedOutput == "MPG"){
					System.out.println("Mile Per Gallon");
				}
			}
			
		};
		bt.setOnClickListener(onButtonClicked);
		
		
		linearLayoutMain.addView(rg);
		linearLayoutMain.addView(bt);
	
		setContentView(linearLayoutMain);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
