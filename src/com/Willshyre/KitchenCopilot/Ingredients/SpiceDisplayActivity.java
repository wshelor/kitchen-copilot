package com.Willshyre.KitchenCopilot.Ingredients;



import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.Willshyre.KitchenCopilot.R;


public class SpiceDisplayActivity extends Activity {
	
	String CurrentIngredient;
	String CurrentIngredientSubstitution;
	String CurrentIngredientDescription;

	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.ingredientonclicked);

		  Bundle bundle = this.getIntent().getExtras();
		  int position = bundle.getInt("position");

		  final String[] Ingredients = getResources().getStringArray(R.array.SpiceName);
		  final String[] IngredientSubstitution= getResources().getStringArray(R.array.SpiceSubstitution);
		  final String[] IngredientDescription = getResources().getStringArray(R.array.SpiceDescription);

	    	CurrentIngredient = Ingredients[position];
	    	CurrentIngredientSubstitution = IngredientSubstitution[position];
	    	CurrentIngredientDescription = IngredientDescription[position];

   	   TextView CurrentIngredientTV = (TextView) findViewById(R.id.CurrentIngredient);
   	   TextView CurrentIngredientSubstitutionTV = (TextView) findViewById(R.id.Substitutions);
   	   TextView CurrentIngredientDescriptionTV = (TextView) findViewById(R.id.IngredientDescription);
	   CurrentIngredientTV.setText(CurrentIngredient);
	   CurrentIngredientSubstitutionTV.setText(CurrentIngredientSubstitution);
	   CurrentIngredientDescriptionTV.setText(CurrentIngredientDescription);
 
		  


				
				
		    
			
	}
}
