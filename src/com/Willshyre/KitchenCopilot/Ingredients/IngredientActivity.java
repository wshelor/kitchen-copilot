package com.Willshyre.KitchenCopilot.Ingredients;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.Willshyre.KitchenCopilot.R;

public class IngredientActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.guidebooktab);

	    //sets up buttons to start the main activity with whichever tab open.
	    ImageView SpicesButton = (ImageView) findViewById(R.id.iSpicesButton);
        SpicesButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent Spices = new Intent(IngredientActivity.this, SpiceActivity.class);
				startActivity(Spices);
			}
		});
	    ImageView FruitsButton = (ImageView) findViewById(R.id.iFruitsButton);
        FruitsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent tab = new Intent(IngredientActivity.this, FruitActivity.class);
				tab.putExtra("tab", 1);
				startActivity(tab);
			}
		});
	    ImageView VegetablesButton = (ImageView) findViewById(R.id.iVegetablesButton);
        VegetablesButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent tab = new Intent(IngredientActivity.this, VegetableActivity.class);
				startActivity(tab);
			}
		});
	    ImageView TipsButton = (ImageView) findViewById(R.id.iTipsButton);
        TipsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent tab = new Intent(IngredientActivity.this, TipsActivity.class);
				startActivity(tab);
			}
		});

	}
		

}


