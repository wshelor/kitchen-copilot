package com.Willshyre.KitchenCopilot.Ingredients;



import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.Willshyre.KitchenCopilot.R;


public class VegetableDisplayActivity extends Activity {
	
	String CurrentVegetable;
	String CurrentVegetableSubstitution;
	String CurrentVegetableDescription;

	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.vegetablesonclicked);

		  Bundle bundle = this.getIntent().getExtras();
		  int position = bundle.getInt("position");

		  final String[] Vegetable = getResources().getStringArray(R.array.VegetableName);
		  final String[] VegetablePicking= getResources().getStringArray(R.array.VegetableChoice);
		  final String[] VegetableDescription = getResources().getStringArray(R.array.VegetableDescription);

	    	CurrentVegetable = Vegetable[position];
	    	CurrentVegetableSubstitution = VegetablePicking[position];
	    	CurrentVegetableDescription = VegetableDescription[position];

   	   TextView CurrentVegetableTV = (TextView) findViewById(R.id.CurrentVegetable);
   	   TextView CurrentVegetableSubstitutionTV = (TextView) findViewById(R.id.BestVegetable);
   	   TextView CurrentVegetableDescriptionTV = (TextView) findViewById(R.id.VegetableDescription);
	   CurrentVegetableTV.setText(CurrentVegetable);
	   CurrentVegetableSubstitutionTV.setText(CurrentVegetableSubstitution);
	   CurrentVegetableDescriptionTV.setText(CurrentVegetableDescription);			
	}
}
