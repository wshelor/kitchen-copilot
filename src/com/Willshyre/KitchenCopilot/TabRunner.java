package com.Willshyre.KitchenCopilot;




import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.Willshyre.KitchenCopilot.Ingredients.IngredientActivity;

@SuppressWarnings("deprecation")
public class TabRunner extends TabActivity {


	boolean isChanged=false;
	
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tabview);

	    //setup the tabs and an ad right below it
	    LinearLayout layout = (LinearLayout)findViewById(R.id.adtesting);
	    Resources res = getResources();
	    TabHost tabHost = getTabHost();
	    TabHost.TabSpec spec;  
	    Intent intent;  
	    
	    intent = new Intent().setClass(this, ConverterActivity.class);

	    //Initialize the Tabs
	    spec = tabHost.newTabSpec("Converter").setIndicator(" ",
	                      res.getDrawable(R.drawable.kcconvertertab))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, TimerActivity.class);
	    spec = tabHost.newTabSpec("Timer").setIndicator(" ",
	                      res.getDrawable(R.drawable.kctimertab))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, IngredientActivity.class);
	    spec = tabHost.newTabSpec("Ingredients").setIndicator(" ",
	                      res.getDrawable(R.drawable.kcguidetab))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, RecipeActivity.class);
	    spec = tabHost.newTabSpec("Recipes").setIndicator(" ",
	                      res.getDrawable(R.drawable.kcsearchtab))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
	    {
	        tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFFFFF")); //unselected
	    }
	    tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFFFF"));

	    Bundle bundle = this.getIntent().getExtras();
		  int position = bundle.getInt("tab");

	    tabHost.setCurrentTab(position);

	}
}


