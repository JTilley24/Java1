package com.jtilley.java1;
//Justin Tilley Java 1

import java.util.ArrayList;
import com.jtilley.java1.ConnectionCheck;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends Activity {
	Context mContext;
	String[] mListItems;
	String[] styleArray;
	ArrayList<String> modelsArray;
	String selectedCar;
	String selectedStyle;
	String carsString;
	
	public static Boolean modelsPopulated = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = this;
		mListItems = getResources().getStringArray(R.array.cars_array);
		styleArray = getResources().getStringArray(R.array.styles_array);
		selectedStyle = "All";
		selectedCar = "Acura";
		final ListView modelsList = new ListView(mContext);
		
		//Check for Connection
		ConnectionCheck connection = new ConnectionCheck();
		if(connection.checkConnection(mContext)){
			Toast.makeText(mContext, "Connected", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(mContext, "Not Connected.Please connect and try again.", Toast.LENGTH_LONG).show();
		}
		
		//Create TextView for Results
				final TextView txtView = new TextView(mContext);
				txtView.setText("Please select vehicle and output.");
				txtView.setGravity(Gravity.CENTER);
				txtView.setTextSize(16);
		
		//Create Linear Layout
		final LinearLayout linearLayoutMain = new LinearLayout(mContext);
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
				txtView.setText("Please select vehicle and output.");
				if(modelsPopulated == true){
					linearLayoutMain.removeView(modelsList);
					linearLayoutMain.addView(txtView);
				}
			}
			public void onNothingSelected(AdapterView<?> arg0){
				
			}
		});
		
		//Create Title 
		TextView title = (TextView)getLayoutInflater().inflate(R.layout.titletext, null);
		title.setText("Auto Details App");
		
		//Create ImageView
		final ImageView styleImg = (ImageView)getLayoutInflater().inflate(R.layout.carsimage, null);
		styleImg.setImageResource(R.drawable.all);
		
		//Create Radio Buttons
		final RadioButton[] rb = new RadioButton[styleArray.length];
		final RadioGroup rg = (RadioGroup)getLayoutInflater().inflate(R.layout.carstyles, null);
		final RadioGroup rg2 =(RadioGroup)getLayoutInflater().inflate(R.layout.carstyles, null);
		
		
		//Set OnClick Listener for Radio Buttons
		OnClickListener onRadioButtonClicked = new OnClickListener(){
			 public void onClick(View v){
				RadioButton button = (RadioButton) v;
				button.setSelected(true);
				if(button.getId() == 0){
					selectedStyle = "All";
					styleImg.setImageResource(R.drawable.all);
					int checkedId = rg2.getCheckedRadioButtonId();
					if(checkedId >= 0){
						RadioButton checkedButton = (RadioButton) findViewById(checkedId);
						checkedButton.setChecked(false);
					}
				}else if((button.getId() == 1)){
					selectedStyle = (String) button.getText();
					styleImg.setImageResource(R.drawable.coupe);
					int checkedId = rg2.getCheckedRadioButtonId();
					if(checkedId >= 0){
						RadioButton checkedButton = (RadioButton) findViewById(checkedId);
						checkedButton.setChecked(false);
					}
				}else if((button.getId() == 2)){
					selectedStyle = (String) button.getText();
					styleImg.setImageResource(R.drawable.sedan);
					int checkedId = rg2.getCheckedRadioButtonId();
					if(checkedId >= 0){
						RadioButton checkedButton = (RadioButton) findViewById(checkedId);
						checkedButton.setChecked(false);
					}
				}else if((button.getId() == 3)){
					selectedStyle = (String) button.getText();
					styleImg.setImageResource(R.drawable.convertible);
					int checkedId = rg2.getCheckedRadioButtonId();
					if(checkedId >= 0){
						RadioButton checkedButton = (RadioButton) findViewById(checkedId);
						checkedButton.setChecked(false);
					}
				}
				else if(button.getId() == 4){
					selectedStyle = "Extended+Cab+Pickup";
					styleImg.setImageResource(R.drawable.pickup);
					int checkedId = rg.getCheckedRadioButtonId();
					if(checkedId >= 0){
						RadioButton checkedButton = (RadioButton) findViewById(checkedId);
						checkedButton.setChecked(false);
					}
				}else if(button.getId() == 5){
					selectedStyle = "4dr+SUV";
					styleImg.setImageResource(R.drawable.suv);
					int checkedId = rg.getCheckedRadioButtonId();
					if(checkedId >= 0){
						RadioButton checkedButton = (RadioButton) findViewById(checkedId);
						checkedButton.setChecked(false);
					}
				}else if(button.getId() == 6){
					selectedStyle = "Passenger+Minivan";
					styleImg.setImageResource(R.drawable.minivan);
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
			rb[i] = (RadioButton)getLayoutInflater().inflate(R.layout.stylebuttons, null);
			rb[i].setText(styleArray[i].toString());
			rb[i].setId(i);
			rb[i].setOnClickListener(onRadioButtonClicked);
			if(i <= (styleArray.length / 2)){
				rg.addView(rb[i]);
			} else{
				rg2.addView(rb[i]);
			}
		}
		rg.check(0);
		
		
		
		//Create Button and OnClick Listener
		Button bt = new Button(this);
		bt.setText("Select");
		bt.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		OnClickListener onButtonClicked = new OnClickListener(){
			public void onClick(View v){
				ConnectionCheck connection = new ConnectionCheck();
				if(connection.checkConnection(mContext)){
					JSONCars json = new JSONCars();
					ArrayList<String> jsonArray = json.readCarsJSON(mContext, selectedCar, selectedStyle);
					System.out.println(jsonArray);
					linearLayoutMain.removeView(txtView);
					modelsArray = new ArrayList<String>();
					
					if(modelsPopulated = true){
						linearLayoutMain.removeView(modelsList);
						
					}
					
					if(jsonArray.size() > 0){
						for(int i = 0; i < jsonArray.size(); i++){
							String tempString = selectedCar + " "  + jsonArray.get(i) + "\n";
							modelsArray.add(tempString);
						}
						
						ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, modelsArray);
						
						modelsList.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
						modelsList.setAdapter(listAdapter);
						modelsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
							
							@Override
							public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
								Toast.makeText(mContext, modelsArray.get(position), Toast.LENGTH_LONG).show();
								
							}
						});
						
						modelsPopulated = true;
						linearLayoutMain.addView(modelsList);
						
					}else{
						txtView.setText("No vehicles available. Please try again.");
						linearLayoutMain.addView(txtView);
						modelsPopulated = false;
					}
				}else{
					txtView.setText("Not Connected.Please connect and try again.");
					linearLayoutMain.addView(txtView);
					modelsPopulated = false;
				}
			}
		};
		
		bt.setOnClickListener(onButtonClicked);
		
		
		//Add UI Elements to View
		linearLayoutMain.addView(title);
		linearLayoutMain.addView(viewSpinner);
		linearLayoutMain.addView(rg);
		linearLayoutMain.addView(rg2);
		linearLayoutMain.addView(styleImg);
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
