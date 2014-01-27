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

public class VegetableActivity extends ListActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);

		  final String[] Ingredients = getResources().getStringArray(R.array.VegetableName);

		  setListAdapter(new ArrayAdapter<String>(this, R.layout.ingredienttab, Ingredients));
		  ListView lv = getListView();
		  lv.setTextFilterEnabled(true);
		  lv.setCacheColorHint(00000000);
		  lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		    	if (position == 0) {
					String url = "market://details?id=com.Willshyre.KitchenCopilotGold";
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);

		    	} else {
		      // When clicked, set the text to the case
			    Intent intent = new Intent().setClass(getBaseContext(), VegetableDisplayActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
		    	}}
		    
		  });
			
	}
}
