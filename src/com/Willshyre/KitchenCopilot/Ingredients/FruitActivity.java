package com.Willshyre.KitchenCopilot.Ingredients;



import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.Willshyre.KitchenCopilot.R;


public class FruitActivity extends ListActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);

		  final String[] Ingredients = getResources().getStringArray(R.array.FruitName);

		  setListAdapter(new ArrayAdapter<String>(this, R.layout.ingredienttab, Ingredients));
		  ListView lv = getListView();
		  lv.setTextFilterEnabled(true);
		  lv.setCacheColorHint(00000000);
		  lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		    	if (position == 0) {
		    		Intent intent = new Intent(Intent.ACTION_VIEW);
		    		intent.setData(Uri.parse("market://details?id=com.Willshyre.KitchenCopilot"));
		    		startActivity(intent);
		    	} else {
		      // When clicked, set the text to the case
			    Intent intent = new Intent().setClass(getBaseContext(), FruitDisplayActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
		    }}
		    
		  });
			
	}
}
