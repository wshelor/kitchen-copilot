package com.Willshyre.KitchenCopilot.Ingredients;



import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.Willshyre.KitchenCopilot.R;

public class FruitDisplayActivity extends Activity {
	
	String CurrentFruit;
	String CurrentFruitSubstitution;
	String CurrentFruitDescription;

	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.fruitonclicked);

		  Bundle bundle = this.getIntent().getExtras();
		  int position = bundle.getInt("position");

		  final String[] Fruits = getResources().getStringArray(R.array.FruitName);
		  final String[] FruitPicking= getResources().getStringArray(R.array.FruitChoice);
		  final String[] FruitDescription = getResources().getStringArray(R.array.FruitDescription);

	    	CurrentFruit = Fruits[position];
	    	CurrentFruitSubstitution = FruitPicking[position];
	    	CurrentFruitDescription = FruitDescription[position];

   	   TextView CurrentFruitTV = (TextView) findViewById(R.id.CurrentFruit);
   	   TextView CurrentFruitSubstitutionTV = (TextView) findViewById(R.id.BestFruit);
   	   TextView CurrentFruitDescriptionTV = (TextView) findViewById(R.id.FruitDescription);
	   CurrentFruitTV.setText(CurrentFruit);
	   CurrentFruitSubstitutionTV.setText(CurrentFruitSubstitution);
	   CurrentFruitDescriptionTV.setText(CurrentFruitDescription);			
	}
}
