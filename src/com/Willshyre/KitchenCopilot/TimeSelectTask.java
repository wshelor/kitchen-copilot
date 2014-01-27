package com.Willshyre.KitchenCopilot;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TimePicker;


public class TimeSelectTask extends TimePickerDialog {
	

	public TimeSelectTask(Context context, int theme,
			OnTimeSetListener callBack, int hourOfDay, int minute,
			boolean is24HourView) {
		super(context, theme, callBack, hourOfDay, minute, is24HourView);
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onTimeChanged(TimePicker thisOne, int hourIn, int minuteIn) {
		CharSequence minuteInText;
		CharSequence hourInText;
		CharSequence currentTitle;
		
		if (minuteIn < 10) {minuteInText = ("0" + (minuteIn));} 
		else {minuteInText = ("" + (minuteIn));}
		if (hourIn < 10) {hourInText = ("0" + (hourIn));} 
		else {hourInText = ("" + (hourIn));}
   		currentTitle = ("" + hourInText + ":" + minuteInText + ":00");
   	
		this.setTitle(currentTitle);
	}
}