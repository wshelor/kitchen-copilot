package com.Willshyre.KitchenCopilot.Ingredients;



import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.Willshyre.KitchenCopilot.R;


public class TipsDisplayActivity extends Activity {
	
	String CurrentTip;
	String CurrentTipSubstitution;
	String CurrentTipDescription;

	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.tipsonclicked);

		  Bundle bundle = this.getIntent().getExtras();
		  int position = bundle.getInt("position");

		  final String[] Tip = getResources().getStringArray(R.array.TipName);
		  final String[] TipDescription = getResources().getStringArray(R.array.TipDescription);

	    	CurrentTip = Tip[position];
	    	CurrentTipDescription = TipDescription[position];

   	   TextView CurrentTipTV = (TextView) findViewById(R.id.CurrentTip);
   	   TextView CurrentTipDescriptionTV = (TextView) findViewById(R.id.TipDescription);
	   CurrentTipTV.setText(CurrentTip);
	   CurrentTipDescriptionTV.setText(CurrentTipDescription);			
	}
}
