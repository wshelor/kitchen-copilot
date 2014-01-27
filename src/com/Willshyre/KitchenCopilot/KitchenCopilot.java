package com.Willshyre.KitchenCopilot;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class KitchenCopilot extends Activity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

	    final ImageView MainIcon = (ImageView) findViewById(R.id.KitchenCopilotGoldIcon);
	    MainIcon.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alertbox = new AlertDialog.Builder(KitchenCopilot.this);
				alertbox.setTitle("CREDITS");
				alertbox.setIcon(R.drawable.kitchencopiloticon);
				alertbox.setCancelable(false);
			    alertbox.setMessage("" +
	            		"  Concept and Programming: \n" +
	            		"               Will Shelor  \n\n" +
	            		"                Guidebook: \n" +
	            		"             Maggie Shelor \n\n" +
	            		"          Layout and Design: \n" +
	            		"               Max O'Brien\n\n");
	 
	            alertbox.setPositiveButton("Close",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
	            });
	            alertbox.show();

			}
		});
      	
	    //sets up buttons to start the main activity with whichever tab open.
	    final ImageView ConverterImg = (ImageView) findViewById(R.id.ssconverterbutton);
        ConverterImg.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
                Intent tab = new Intent(KitchenCopilot.this, TabRunner.class);
				tab.putExtra("tab", 0);
				startActivity(tab);
			}
		});
	    ImageView TimerImg = (ImageView) findViewById(R.id.sstimerbutton);
        TimerImg.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
                Intent tab = new Intent(KitchenCopilot.this, TabRunner.class);
				tab.putExtra("tab", 1);
				startActivity(tab);
			}
		});
	    ImageView GuideImg = (ImageView) findViewById(R.id.ssguidebutton);
        GuideImg.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
                Intent tab = new Intent(KitchenCopilot.this, TabRunner.class);
				tab.putExtra("tab", 2);
				startActivity(tab);
			}
		});
	    ImageView SearchImg = (ImageView) findViewById(R.id.sssearchbutton);
        SearchImg.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
                Intent tab = new Intent(KitchenCopilot.this, TabRunner.class);
				tab.putExtra("tab", 3);
				startActivity(tab);
			}
		});

	}
		

}


