package com.Willshyre.KitchenCopilot;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.LightingColorFilter;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimerActivity extends Activity {

	
	//setup timer for normal mode
	public MediaPlayer AlarmNoise;
	public Button TimerStartButton;
	public Button TimerStopButton;
	public Button TimerResetButton;
	public Button TimerStartButton2;
	public Button TimerStopButton2;
	public Button TimerResetButton2;
	public Button TimerStartButton3;
	public Button TimerStopButton3;
	public Button TimerResetButton3;
	public TextView CurrentTime1;
	public TextView CurrentTime2;
	public TextView CurrentTime3;
	public TextView CurrentTime4;
	
	//setup variables for guide mode
	public Button TimerTypeToggle;
	public Button MealPrepButton;
	public Button Timer1PrepTime;
	public Button Timer1CooldownTime;
	public Button Timer2PrepTime;
	public Button Timer2CooldownTime;
	public Button Timer3PrepTime;
	public Button Timer3CooldownTime;
	public Button Timer4PrepTime;
	public Button Timer4CooldownTime;
	public LinearLayout Timer1ButtonLine;
	public LinearLayout Timer1PlannerLine;
	public LinearLayout Timer2ButtonLine;
	public LinearLayout Timer2PlannerLine;
	public LinearLayout Timer3ButtonLine;
	public LinearLayout Timer3PlannerLine;
	public LinearLayout Timer4PlannerLine;
	public LinearLayout Timer2MainLayout;
	public LinearLayout Timer3MainLayout;
	public LinearLayout Timer4MainLayout;
	public TextView MP1Title;
	public TextView MP2Title;
	public TextView MP3Title;
	public TextView MP4Title;
	public Button TimerAddButton;
	public Button TimerHideButton;
	
	public Vibrator myVibrate;
	private Handler ClockHandler = new Handler();
	private Handler GuideHandler = new Handler();
	public AudioManager audioManager;
	
	
	
	//initialize important values- variables
	public int Minutes;
	public int Hours;
	public int Seconds;
	public String HoursText;
	public String MinutesText;
	public String SecondsText;
	public long ExtraTime1;
	public long StartMillis1;
	public long CurrentMillis1;
	public long StartMillisPrep1;
	public long CurrentMillisPrep1;
	public long StartMillisCooldown1;
	public long CurrentMillisCooldown1;
	public long ExtraTime2;
	public long StartMillis2;
	public long CurrentMillis2;
	public long StartMillisPrep2;
	public long CurrentMillisPrep2;
	public long StartMillisCooldown2;
	public long CurrentMillisCooldown2;
	public long ExtraTime3;
	public long StartMillis3;
	public long CurrentMillis3;
	public long StartMillisPrep3;
	public long CurrentMillisPrep3;
	public long StartMillisCooldown3;
	public long CurrentMillisCooldown3;
	public long ExtraTime4;
	public long StartMillis4;
	public long CurrentMillis4;
	public long StartMillisPrep4;
	public long CurrentMillisPrep4;
	public long StartMillisCooldown4;
	public long CurrentMillisCooldown4;
	public long Timer1Total;
	public long Timer2Total;
	public long Timer3Total;
	public long Timer4Total;
	public long StartTime1;
	public long StartTime2;
	public long StartTime3;
	public long StartTime4;
	public long GuideStartTime;
	public long TotalGuideTime;
	public int pickerSeconds;
	public int pickerMinutes;
	public int pickerHours;
	public int CurrentAlarm;
	public int numberOfAlarms;
	public boolean GuideMode;
	public boolean running1;
	public boolean running2;
	public boolean running3;
	public boolean Timer1PrepStart;
	public boolean Timer1PrepDone;
	public boolean Timer1Done;
	public boolean Timer2PrepStart;
	public boolean Timer2PrepDone;
	public boolean Timer2Done;
	public boolean Timer3PrepStart;
	public boolean Timer3PrepDone;
	public boolean Timer3Done;
	public boolean Timer4PrepStart;
	public boolean Timer4PrepDone;
	public boolean Timer4Done;
	public boolean AudioIsRunning;
	public boolean MPIsRunning;
	public Typeface TextFont;
	
	
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
		setContentView(R.layout.timertab);
		AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.alarm);
		myVibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
   		audioManager = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
   		TextFont = Typeface.createFromAsset(getAssets(), "font/journal.ttf");
   		
		//Initiate Views- timer mode
		CurrentTime1 = (TextView) findViewById(R.id.CurrentTime1);
		CurrentTime2 = (TextView) findViewById(R.id.CurrentTime2);
		CurrentTime3 = (TextView) findViewById(R.id.CurrentTime3);
		CurrentTime4 = (TextView) findViewById(R.id.CurrentTime4);
		TimerStartButton = (Button) findViewById(R.id.TimerStartButton);
		TimerStopButton = (Button) findViewById(R.id.TimerStopButton);
		TimerResetButton = (Button) findViewById(R.id.TimerResetButton);
		TimerStartButton2 = (Button) findViewById(R.id.TimerStartButton2);
		TimerStopButton2 = (Button) findViewById(R.id.TimerStopButton2);
		TimerResetButton2 = (Button) findViewById(R.id.TimerResetButton2);
		TimerStartButton3 = (Button) findViewById(R.id.TimerStartButton3);
		TimerStopButton3 = (Button) findViewById(R.id.TimerStopButton3);
		TimerResetButton3 = (Button) findViewById(R.id.TimerResetButton3);
		
		//Initiate Views- guide mode
		TimerTypeToggle = (Button) findViewById(R.id.TimerTypeToggle);
		MealPrepButton = (Button) findViewById(R.id.MealPrepButton);
		Timer1PrepTime = (Button) findViewById(R.id.Timer1PrepTime);
		Timer1CooldownTime = (Button) findViewById(R.id.Timer1CooldownTime);
		Timer2PrepTime = (Button) findViewById(R.id.Timer2PrepTime);
		Timer2CooldownTime = (Button) findViewById(R.id.Timer2CooldownTime);
		Timer3PrepTime = (Button) findViewById(R.id.Timer3PrepTime);
		Timer3CooldownTime = (Button) findViewById(R.id.Timer3CooldownTime);
		Timer4PrepTime = (Button) findViewById(R.id.Timer4PrepTime);
		Timer4CooldownTime = (Button) findViewById(R.id.Timer4CooldownTime);
		Timer1ButtonLine = (LinearLayout) findViewById(R.id.Timer1ButtonLine);
		Timer1PlannerLine = (LinearLayout) findViewById(R.id.Timer1PlannerLine);
		Timer2ButtonLine = (LinearLayout) findViewById(R.id.Timer2ButtonLine);
		Timer2PlannerLine = (LinearLayout) findViewById(R.id.Timer2PlannerLine);
		Timer3ButtonLine = (LinearLayout) findViewById(R.id.Timer3ButtonLine);
		Timer3PlannerLine = (LinearLayout) findViewById(R.id.Timer3PlannerLine);
		Timer2MainLayout = (LinearLayout) findViewById(R.id.Timer2MainLayout);
		Timer3MainLayout = (LinearLayout) findViewById(R.id.Timer3MainLayout);
		Timer4MainLayout = (LinearLayout) findViewById(R.id.Timer4MainLayout);
		Timer4PlannerLine = (LinearLayout) findViewById(R.id.Timer4PlannerLine);
		MP1Title = (TextView) findViewById(R.id.MP1Title);
		MP2Title = (TextView) findViewById(R.id.MP2Title);
		MP3Title = (TextView) findViewById(R.id.MP3Title);
		MP4Title = (TextView) findViewById(R.id.MP4Title);
		MP1Title.setTypeface(TextFont);
		MP2Title.setTypeface(TextFont);
		MP3Title.setTypeface(TextFont);
		MP4Title.setTypeface(TextFont);
		
		TimerAddButton = (Button) findViewById(R.id.TimerAddButton);
		TimerHideButton = (Button) findViewById(R.id.TimerHideButton);

		//Initiate variables
		Hours = 0;
		Minutes = 0;
		Seconds = 0;
		StartMillis1 = 0;
		StartMillis2 = 0;
		StartMillis3 = 0;
		running1 = false;
		running2 = false;		
		running3 = false;
		GuideMode = false;
		AudioIsRunning = false;
		numberOfAlarms = 1;
		MPIsRunning = false;
		
		
		TimerTypeToggle.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFF00FF00));
		TimerTypeToggle.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				if (GuideMode == false) {
					MP1Title.setVisibility(View.VISIBLE);
					MealPrepButton.setVisibility(View.VISIBLE);
					Timer1ButtonLine.setVisibility(View.GONE);
					Timer1PlannerLine.setVisibility(View.VISIBLE);
					Timer2ButtonLine.setVisibility(View.GONE);
					TimerAddButton.setVisibility(View.VISIBLE);
					if (numberOfAlarms > 1) {
						Timer2PlannerLine.setVisibility(View.VISIBLE);
						MP2Title.setVisibility(View.VISIBLE);
						TimerHideButton.setVisibility(View.VISIBLE);
					} else { Timer2MainLayout.setVisibility(View.GONE);}
					Timer3ButtonLine.setVisibility(View.GONE);
					if (numberOfAlarms > 2) {
						Timer3PlannerLine.setVisibility(View.VISIBLE);
						MP3Title.setVisibility(View.VISIBLE);
					} else { Timer3MainLayout.setVisibility(View.GONE);}
					GuideMode = true;
					TimerTypeToggle.setText("Switch to Kitchen Timer");
				} else {
					MP1Title.setVisibility(View.GONE);
					MP2Title.setVisibility(View.GONE);
					MP3Title.setVisibility(View.GONE);
					MP4Title.setVisibility(View.GONE);
					CurrentTime2.setVisibility(View.VISIBLE);
					CurrentTime3.setVisibility(View.VISIBLE);
					MealPrepButton.setVisibility(View.GONE);
					Timer1ButtonLine.setVisibility(View.VISIBLE);
					Timer1PlannerLine.setVisibility(View.GONE);
					Timer2ButtonLine.setVisibility(View.VISIBLE);
					Timer2PlannerLine.setVisibility(View.GONE);
					Timer3ButtonLine.setVisibility(View.VISIBLE);
					Timer3PlannerLine.setVisibility(View.GONE);
					Timer2MainLayout.setVisibility(View.VISIBLE);
					Timer3MainLayout.setVisibility(View.VISIBLE);
					Timer4MainLayout.setVisibility(View.GONE);
					Timer4PlannerLine.setVisibility(View.GONE);
					TimerAddButton.setVisibility(View.GONE);
					TimerHideButton.setVisibility(View.GONE);
					GuideMode = false;
					TimerTypeToggle.setText("Switch to Meal Planner");
				}
			}});
		
		CurrentTime1.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				CurrentAlarm = 1;
				pickerSeconds = (int) (CurrentMillis1 / 1000);
				pickerHours = pickerSeconds / 3600;
				pickerMinutes = (pickerSeconds % 3600) / 60; 				
                showDialog(0);
			}});
		CurrentTime2.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				CurrentAlarm = 2;
				pickerSeconds = (int) (CurrentMillis2 / 1000);
				pickerHours = pickerSeconds / 3600;
				pickerMinutes = (pickerSeconds % 3600) / 60; 				
                showDialog(0);				
			}});	
		CurrentTime3.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				CurrentAlarm = 3;
				pickerSeconds = (int) (CurrentMillis3 / 1000);
				pickerHours = pickerSeconds / 3600;
				pickerMinutes = (pickerSeconds % 3600) / 60; 				
                showDialog(0);
			}});	
		CurrentTime4.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				CurrentAlarm = 10;
				pickerSeconds = (int) (CurrentMillis4 / 1000);
				pickerHours = pickerSeconds / 3600;
				pickerMinutes = (pickerSeconds % 3600) / 60; 				
                showDialog(0);
			}});	

		
		//Handle setting the title of the 3 meal planner items
		MP1Title.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
			    AlertDialog.Builder alert = new AlertDialog.Builder(TimerActivity.this);
			    alert.setTitle("Set Title");
			    alert.setMessage("Set a Title for Dish 1");

			     final EditText input = new EditText(TimerActivity.this);
			     alert.setView(input);
			        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
			        public void onClick(DialogInterface dialog, int whichButton) {  
			            String value = input.getText().toString();
			            if (value.equals("")) { MP1Title.setText("Dish 1- Click Here To Set Title");
			            } else { MP1Title.setText("Dish 1- " + value); }
			            return;                  
			           }
			         }); alert.show();
			}});
		MP2Title.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
			    AlertDialog.Builder alert = new AlertDialog.Builder(TimerActivity.this);
			    alert.setTitle("Set Title");
			    alert.setMessage("Set a Title for Dish 2");

			     final EditText input = new EditText(TimerActivity.this);
			     alert.setView(input);
			        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
			        public void onClick(DialogInterface dialog, int whichButton) {  
			            String value = input.getText().toString();
			            if (value.equals("")) { MP2Title.setText("Dish 2- Click Here To Set Title");
			            } else { MP2Title.setText("Dish 2- " + value); }
			            return;                  
			           }
			         }); alert.show();
			}});
		MP3Title.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
			    AlertDialog.Builder alert = new AlertDialog.Builder(TimerActivity.this);
			    alert.setTitle("Set Title");
			    alert.setMessage("Set a Title for Dish 3");

			     final EditText input = new EditText(TimerActivity.this);
			     alert.setView(input);
			        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
			        public void onClick(DialogInterface dialog, int whichButton) {  
			            String value = input.getText().toString();
			            if (value.equals("")) { MP3Title.setText("Dish 3- Click Here To Set Title");
			            } else { MP3Title.setText("Dish 3- " + value); }
			            return;                  
			           }
			         }); alert.show();
			}});
		MP4Title.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
			    AlertDialog.Builder alert = new AlertDialog.Builder(TimerActivity.this);
			    alert.setTitle("Set Title");
			    alert.setMessage("Set a Title for Dish 4");

			     final EditText input = new EditText(TimerActivity.this);
			     alert.setView(input);
			        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
			        public void onClick(DialogInterface dialog, int whichButton) {  
			            String value = input.getText().toString();
			            if (value.equals("")) { MP4Title.setText("Dish 4- Click Here To Set Title");
			            } else { MP4Title.setText("Dish 4- " + value); }
			            return;
			           }
			         }); alert.show();
			}});
		
		//-Handle the pressing of the three buttons for the first timer
		TimerStartButton.setOnClickListener(new View.OnClickListener(){
		public void onClick(View view) {
	            StartTime1 = SystemClock.elapsedRealtime();
	            ClockHandler.removeCallbacks(mUpdateTimeTask);
	            ClockHandler.postDelayed(mUpdateTimeTask, 100);
				TimerStartButton.setKeepScreenOn(true);
				getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);	
	            running1 = true;
        		screenOFFHandler.sendEmptyMessageDelayed(0, StartMillis1);
		}});
		TimerStopButton.setOnClickListener(new View.OnClickListener(){
		public void onClick(View view) {
			if (running2 == false && running3 == false) ClockHandler.removeCallbacks(mUpdateTimeTask);
			running1 = false;
    		StartMillis1 = (CurrentMillis1); 
    		AlarmNoise.pause();
    		AlarmNoise.reset();
    		TimerStartButton.setKeepScreenOn(false);			
		    String time1a = convertToTime(StartMillis1); 
		    CurrentTime1.setText(time1a);
		}});
		TimerResetButton.setOnClickListener(new View.OnClickListener(){
		public void onClick(View view) {
			if (running2 == false && running3 == false)
				ClockHandler.removeCallbacks(mUpdateTimeTask);
    		StartMillis1 = 0;
    		TimerStartButton.setKeepScreenOn(false);			
			running1 = false;
   			CurrentTime1.setText("00:00:00");
    		AlarmNoise.pause();
    		AlarmNoise.reset();
		}});

		//-Handle the pressing of the three buttons for the second timer
		TimerStartButton2.setOnClickListener(new View.OnClickListener(){
		public void onClick(View view) {
            StartTime2 = SystemClock.elapsedRealtime();
            ClockHandler.removeCallbacks(mUpdateTimeTask);
            ClockHandler.postDelayed(mUpdateTimeTask, 100);
            running2 = true;
			TimerStartButton2.setKeepScreenOn(true);
			getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);	
    		screenOFFHandler.sendEmptyMessageDelayed(0, StartMillis2);
		}});
		TimerStopButton2.setOnClickListener(new View.OnClickListener(){
		public void onClick(View view) {
			if (running1 == false && running3 == false) ClockHandler.removeCallbacks(mUpdateTimeTask);
			running2 = false;
    		StartMillis2 = (CurrentMillis2); 
			TimerStartButton2.setKeepScreenOn(false);
			   String time2a = convertToTime(StartMillis2); 
			   CurrentTime2.setText(time2a);
	    		AlarmNoise.pause();
	    		AlarmNoise.reset();
		}});
		TimerResetButton2.setOnClickListener(new View.OnClickListener(){
		public void onClick(View view) {
			if (running1 == false && running3 == false) ClockHandler.removeCallbacks(mUpdateTimeTask);
    		StartMillis2 = 0;
			running2 = false;			
			TimerStartButton2.setKeepScreenOn(false);
   			CurrentTime2.setText("00:00:00");
    		AlarmNoise.pause();
    		AlarmNoise.reset();
		}});

		//-Handle the pressing of the three buttons for the third timer
		TimerStartButton3.setOnClickListener(new View.OnClickListener(){
		public void onClick(View view) {
			StartTime3 = SystemClock.elapsedRealtime();
            ClockHandler.removeCallbacks(mUpdateTimeTask);
            ClockHandler.postDelayed(mUpdateTimeTask, 100);
            running3 = true;
			TimerStartButton3.setKeepScreenOn(true);
			getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);	
    		screenOFFHandler.sendEmptyMessageDelayed(0, StartMillis2);
    		}});
		TimerStopButton3.setOnClickListener(new View.OnClickListener(){
		public void onClick(View view) {
			if (running1 == false && running2 == false) ClockHandler.removeCallbacks(mUpdateTimeTask);
			running3 = false;
    		StartMillis3 = (CurrentMillis3); 
			TimerStartButton3.setKeepScreenOn(false);
			String time3a = convertToTime(StartMillis3); 
			CurrentTime3.setText(time3a);
    		AlarmNoise.pause();
    		AlarmNoise.reset();
		}});
		TimerResetButton3.setOnClickListener(new View.OnClickListener(){
		public void onClick(View view) {
			if (running1 == false && running2 == false) ClockHandler.removeCallbacks(mUpdateTimeTask);
    		StartMillis3 = 0;
			running3 = false;			
			TimerStartButton3.setKeepScreenOn(false);
   			CurrentTime3.setText("00:00:00");
    		AlarmNoise.pause();
    		AlarmNoise.reset();
		}});

		//Handle the pressing of the extra buttons for the Meal Planner
		Timer1PrepTime.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				CurrentAlarm = 4;
				pickerSeconds = (int) (CurrentMillisPrep1 / 1000);
				pickerHours = pickerSeconds / 3600;
				pickerMinutes = (pickerSeconds % 3600) / 60; 				
                showDialog(0);
			}}); 
		Timer1CooldownTime.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				CurrentAlarm = 5;
				pickerSeconds = (int) (CurrentMillisCooldown1 / 1000);
				pickerHours = pickerSeconds / 3600;
				pickerMinutes = (pickerSeconds % 3600) / 60; 				
                showDialog(0);
			}}); 
		Timer2PrepTime.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				CurrentAlarm = 6;
				pickerSeconds = (int) (CurrentMillisPrep2 / 1000);
				pickerHours = pickerSeconds / 3600;
				pickerMinutes = (pickerSeconds % 3600) / 60; 				
                showDialog(0);
			}}); 
		Timer2CooldownTime.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				CurrentAlarm = 7;
				pickerSeconds = (int) (CurrentMillisCooldown2 / 1000);
				pickerHours = pickerSeconds / 3600;
				pickerMinutes = (pickerSeconds % 3600) / 60; 				
                showDialog(0);
			}}); 
		Timer3PrepTime.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				CurrentAlarm = 8;
				pickerSeconds = (int) (CurrentMillisPrep3 / 1000);
				pickerHours = pickerSeconds / 3600;
				pickerMinutes = (pickerSeconds % 3600) / 60; 				
                showDialog(0);
			}}); 
		Timer3CooldownTime.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				CurrentAlarm = 9;
				pickerSeconds = (int) (CurrentMillisCooldown3 / 1000);
				pickerHours = pickerSeconds / 3600;
				pickerMinutes = (pickerSeconds % 3600) / 60; 				
                showDialog(0);
			}});     
		Timer4PrepTime.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				CurrentAlarm = 11;
				pickerSeconds = (int) (CurrentMillisPrep4 / 1000);
				pickerHours = pickerSeconds / 3600;
				pickerMinutes = (pickerSeconds % 3600) / 60; 				
                showDialog(0);
			}}); 
		Timer4CooldownTime.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				CurrentAlarm = 12;
				pickerSeconds = (int) (CurrentMillisCooldown4 / 1000);
				pickerHours = pickerSeconds / 3600;
				pickerMinutes = (pickerSeconds % 3600) / 60; 				
                showDialog(0);
			}});     

		TimerAddButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				switch (numberOfAlarms) {
				case 1:
					TimerHideButton.setVisibility(View.VISIBLE);
					Timer2MainLayout.setVisibility(View.VISIBLE);
					Timer2PlannerLine.setVisibility(View.VISIBLE);
					MP2Title.setVisibility(View.VISIBLE);
					break;
				case 2: 
					Timer3MainLayout.setVisibility(View.VISIBLE);
					Timer3PlannerLine.setVisibility(View.VISIBLE);
					MP3Title.setVisibility(View.VISIBLE);
					break;
				case 3:
					Timer4MainLayout.setVisibility(View.VISIBLE);
					Timer4PlannerLine.setVisibility(View.VISIBLE);
					MP4Title.setVisibility(View.VISIBLE);
					TimerAddButton.setVisibility(View.GONE);
					break;
				}
				numberOfAlarms ++;
			}});	
		TimerHideButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				switch (numberOfAlarms) {
				case 2: 
					TimerHideButton.setVisibility(View.GONE);
					Timer2MainLayout.setVisibility(View.GONE);
					Timer2PlannerLine.setVisibility(View.GONE);
					MP2Title.setVisibility(View.GONE);
					break;
				case 3:
					Timer3MainLayout.setVisibility(View.GONE);
					Timer3PlannerLine.setVisibility(View.GONE);
					MP3Title.setVisibility(View.GONE);
					break;
				case 4:
					Timer4MainLayout.setVisibility(View.GONE);
					Timer4PlannerLine.setVisibility(View.GONE);
					MP4Title.setVisibility(View.GONE);
					TimerAddButton.setVisibility(View.VISIBLE);
					break;
				}
				numberOfAlarms --;

			}});	

		
		//Set up the Meal Preparation Start/Stop Button
		MealPrepButton.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFF00FF00));
		MealPrepButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				if (MPIsRunning ) {
		    		AlarmNoise.pause();
		    		AlarmNoise.reset();
					MealPrepButton.setText("Start Meal Planner");
		    		GuideHandler.removeCallbacks(MealPlannerTimeTask);
					MPIsRunning = false;
				} else {
				startMealPrep();
				MealPrepButton.setText("Stop Meal Planner");
				MPIsRunning = true;
				}
			}});     
    }

    //Handles the creation of TimePicker Dialog Box
    private TimeSelectTask.OnTimeSetListener mTimeSetListener =
    	    new TimeSelectTask.OnTimeSetListener() {
    	        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    	        	if (CurrentAlarm == 1) {
    	        		StartMillis1 = (hourOfDay * 3600000) + (minute * 60000); 
  					   String timeResult = convertToTime(StartMillis1); 
  					   CurrentTime1.setText(timeResult);
    	        	}else if (CurrentAlarm == 2) {
    	        		StartMillis2 = (hourOfDay * 3600000) + (minute * 60000); 
  					   String timeResult = convertToTime(StartMillis2); 
  					   CurrentTime2.setText(timeResult);
    	        	}else if (CurrentAlarm == 3) {
    	        		StartMillis3 = (hourOfDay * 3600000) + (minute * 60000); 
 					   String timeResult = convertToTime(StartMillis3); 
 					   CurrentTime3.setText(timeResult);
    	        	}else if (CurrentAlarm == 4) {
    	        		StartMillisPrep1 = (hourOfDay * 3600000) + (minute * 60000); 
 					   String timeResult = convertToTime(StartMillisPrep1); 
 					   Timer1PrepTime.setText("  " + timeResult + "  ");
    	        	}else if (CurrentAlarm == 5) {
    	        		StartMillisCooldown1 = (hourOfDay * 3600000) + (minute * 60000); 
 					   String timeResult = convertToTime(StartMillisCooldown1); 
 					  Timer1CooldownTime.setText("  " + timeResult + "  ");
    	        	}else if (CurrentAlarm == 6) {
    	        		StartMillisPrep2 = (hourOfDay * 3600000) + (minute * 60000); 
 					   String timeResult = convertToTime(StartMillisPrep2); 
 					  Timer2PrepTime.setText("  " + timeResult + "  ");
    	        	}else if (CurrentAlarm == 7) {
    	        		StartMillisCooldown2 = (hourOfDay * 3600000) + (minute * 60000); 
 					   String timeResult = convertToTime(StartMillisCooldown2); 
 					  Timer2CooldownTime.setText("  " + timeResult + "  ");
    	        	}else if (CurrentAlarm == 8) {
    	        		StartMillisPrep3 = (hourOfDay * 3600000) + (minute * 60000); 
 					   String timeResult = convertToTime(StartMillisPrep3); 
 					  Timer3PrepTime.setText("  " + timeResult + "  ");
    	        	}else if (CurrentAlarm == 9) {
    	        		StartMillisCooldown3 = (hourOfDay * 3600000) + (minute * 60000); 
 					   String timeResult = convertToTime(StartMillisCooldown3); 
 					  Timer3CooldownTime.setText("  " + timeResult + "  ");
    	        	}else if (CurrentAlarm == 10) {
    	        		StartMillis4 = (hourOfDay * 3600000) + (minute * 60000); 
 					   String timeResult = convertToTime(StartMillis4); 
 					  CurrentTime4.setText(timeResult);
    	        	}else if (CurrentAlarm == 11) {
    	        		StartMillisPrep4 = (hourOfDay * 3600000) + (minute * 60000); 
 					   String timeResult = convertToTime(StartMillisPrep4); 
 					  Timer4PrepTime.setText("  " + timeResult + "  ");
    	        	}else if (CurrentAlarm == 12) {
    	        		StartMillisCooldown4 = (hourOfDay * 3600000) + (minute * 60000); 
 					   String timeResult = convertToTime(StartMillisCooldown4); 
 					  Timer4CooldownTime.setText("  " + timeResult + "  ");
    	        	}}
    };
    @Override
    protected Dialog onCreateDialog(int id) {
    	TimeSelectTask myTimePicker = new TimeSelectTask(this, 0, mTimeSetListener, pickerHours, pickerMinutes, true); 
    	myTimePicker.setTitle("Timer");
    	return myTimePicker; 
    }

	//Handle the countdown timer
	private Runnable mUpdateTimeTask = new Runnable() {
		   public void run() {
			   long thisInstanceStart = SystemClock.elapsedRealtime(); 
			   long thisInstanceStartMS = SystemClock.uptimeMillis();
			   if (running1) {	       
				   final long start = StartTime1;
				   long RunningMillis = thisInstanceStart - start;
				   CurrentMillis1 = StartMillis1 - RunningMillis;
				   if (CurrentMillis1 <= 0) {
						AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.alarm);
			   			CurrentTime1.setText("00:00:00");
			       		running1 = false;
			       		// Set the volume of played media to maximum.
			       		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
			       		AlarmNoise.start();
			       		myVibrate.vibrate(2000);			       		
				   }  else {
					   String time1a = convertToTime(CurrentMillis1); 
					   CurrentTime1.setText(time1a);
				   }
			   };
			   if (running2) {	       
				   final long start = StartTime2;
				   long RunningMillis = thisInstanceStart - start;
				   CurrentMillis2 = StartMillis2 - RunningMillis;
					   if (CurrentMillis2 <= 0) {
							AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.alarm);
						   	CurrentTime2.setText("00:00:00");
				       		running2 = false;
				       		audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
				       		AlarmNoise.start();
				    		myVibrate.vibrate(2000);
					   } else {
						   String time2a = convertToTime(CurrentMillis2); 
						   CurrentTime2.setText(time2a);
					   }			   				   
			   };
			   if (running3) {	       
				   final long start = StartTime3;
				   long RunningMillis = thisInstanceStart - start;
				   CurrentMillis3 = StartMillis3 - RunningMillis;
				   if (CurrentMillis3 <= 0) {
						AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.alarm);
			   			CurrentTime3.setText("00:00:00");
			       		running3 = false;
			       		audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
			       		AlarmNoise.start();			
			    		myVibrate.vibrate(2000);
				   }  else {
					   String time3a = convertToTime(CurrentMillis3); 
					   CurrentTime3.setText(time3a);
				   }
			   };
		       ClockHandler.postAtTime(this, thisInstanceStartMS + 1000);
		   }
		};

	//Handle Converts a number of milliseconds(long) to HH:mm:ss(string)
	private String convertToTime(long timeIn) {
		Seconds = (int) (timeIn / 1000);
		Hours = Seconds / 3600;
		Minutes = (Seconds % 3600) / 60; 
		Seconds = Seconds % 60;
		
		if (Seconds < 10) {SecondsText = ("0" + (Seconds));} 
   		else {SecondsText = ("" + (Seconds));}
   		
   		if (Minutes < 10) {MinutesText = ("0" + (Minutes));} 
   		else {MinutesText = ("" + (Minutes));}
   		
   		if (Hours < 10) {HoursText = ("0" + (Hours));} 
   		else {HoursText = ("" + (Hours));}

   		String timeOut = ("" + HoursText + ":" + MinutesText + ":" + SecondsText);		
		return timeOut;
	}

	private void startMealPrep() { 
		Timer1PrepStart = false;
		Timer1PrepDone = false;
		Timer1Done = false;
		Timer2PrepStart = false;
		Timer2PrepDone = false;
		Timer2Done = false;
		Timer3PrepStart = false;
		Timer3PrepDone = false;
		Timer3Done = false;

		TotalGuideTime = checkLongest(); 
		GuideStartTime = SystemClock.elapsedRealtime();
		GuideHandler.removeCallbacks(MealPlannerTimeTask);
		GuideHandler.postDelayed(MealPlannerTimeTask, 100);
	}

	private long checkLongest() {
		Timer1Total = StartMillis1 + StartMillisPrep1 + StartMillisCooldown1; 
		Timer2Total = StartMillis2 + StartMillisPrep2 + StartMillisCooldown2; 
		Timer3Total = StartMillis3 + StartMillisPrep3 + StartMillisCooldown3;
		Timer4Total = StartMillis4 + StartMillisPrep4 + StartMillisCooldown4;
		if (Timer1Total >= Timer2Total && Timer1Total >= Timer3Total && Timer1Total >= Timer4Total) { 
			ExtraTime1 = 0;
			ExtraTime2 = Timer1Total - Timer2Total;
			ExtraTime3 = Timer1Total - Timer3Total;
			ExtraTime4 = Timer1Total - Timer4Total;
			return Timer1Total; 
		} else if (Timer2Total > Timer3Total && Timer2Total >= Timer4Total) { 
			ExtraTime2 = 0;
			ExtraTime1 = Timer2Total - Timer1Total;
			ExtraTime3 = Timer2Total - Timer3Total;
			ExtraTime4 = Timer2Total - Timer4Total;
			return Timer2Total; 
		} else if (Timer3Total >= Timer4Total) { 
			ExtraTime3 = 0;
			ExtraTime1 = Timer3Total - Timer1Total;
			ExtraTime2 = Timer3Total - Timer2Total;
			ExtraTime4 = Timer3Total - Timer4Total;
			return Timer3Total; 
		} else { 
			ExtraTime4 = 0;
			ExtraTime1 = Timer4Total - Timer1Total;
			ExtraTime2 = Timer4Total - Timer2Total;
			ExtraTime3 = Timer4Total - Timer3Total;
			return Timer4Total; 
	}
	}

	private Runnable MealPlannerTimeTask = new Runnable() {
		   public void run() {
			   long thisInstanceStart = SystemClock.elapsedRealtime(); 
			   long thisInstanceStartMS = SystemClock.uptimeMillis();
			   final long start = GuideStartTime;
			   long RunningMillis = thisInstanceStart - start;

			   //MPTimer 1 calculations

			   if ((TotalGuideTime - RunningMillis ) <= Timer1Total) {
				   if ((TotalGuideTime - (StartMillis1 + StartMillisCooldown1 + RunningMillis)) >= 0) {
					   if (Timer1PrepStart == false && StartMillisPrep1 > 0 && AudioIsRunning == false){
				    		myVibrate.vibrate(2000);
							AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.startprepone);
							AlarmNoise.setOnPreparedListener(new OnPreparedListener() {
								public void onPrepared(MediaPlayer arg0) {
									audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
									AlarmNoise.start();									
								}
							});
							AudioIsRunning = true;
							AlarmNoise.setOnCompletionListener(new OnCompletionListener(){
								public void onCompletion(MediaPlayer arg0) {
									AudioIsRunning = false;									
								}
							});
						   Timer1PrepStart = true;
					   }
					   Timer1PrepTime.setText("  " + convertToTime(TotalGuideTime - (StartMillis1 + StartMillisCooldown1 + RunningMillis)) + "  ");
				   } else if ((TotalGuideTime - (StartMillisCooldown1 + RunningMillis)) >= 0) {
					   if (Timer1PrepDone == false && StartMillis1 > 0 && AudioIsRunning == false){
				    		myVibrate.vibrate(2000);
							AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.begincookingone);
							AlarmNoise.setOnPreparedListener(new OnPreparedListener() {
								public void onPrepared(MediaPlayer arg0) {
									audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
									AlarmNoise.start();									
								}
							});
							AudioIsRunning = true;
							AlarmNoise.setOnCompletionListener(new OnCompletionListener(){
								public void onCompletion(MediaPlayer arg0) {
									AudioIsRunning = false;									
								}
							});
						   Timer1PrepDone = true;
					   }
					   CurrentTime1.setText(convertToTime(TotalGuideTime - (StartMillisCooldown1 + RunningMillis)));
				   } else if ((TotalGuideTime - RunningMillis) >= 0) {
					   if (Timer1Done == false && StartMillisCooldown1 > 0 && AudioIsRunning == false){
				    		myVibrate.vibrate(2000);
							AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.startcoolingone);
							AlarmNoise.setOnPreparedListener(new OnPreparedListener() {
								public void onPrepared(MediaPlayer arg0) {
									audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
									AlarmNoise.start();									
								}
							});
							AudioIsRunning = true;
							AlarmNoise.setOnCompletionListener(new OnCompletionListener(){
								public void onCompletion(MediaPlayer arg0) {
									AudioIsRunning = false;									
								}
							});
						   Timer1Done = true;
					   }
					   Timer1CooldownTime.setText("  " + convertToTime(TotalGuideTime - RunningMillis) + "  ");
				   }
			   } 
			   
			   //MPTimer 2 calculations
			   
			   if ((TotalGuideTime - RunningMillis ) <= Timer2Total) {
				   if ((TotalGuideTime - (StartMillis2 + StartMillisCooldown2 + RunningMillis)) >= 0) {
					   if (Timer2PrepStart == false && StartMillisPrep2 > 0 && AudioIsRunning == false){
				    		myVibrate.vibrate(2000);
							AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.startpreptwo);
							AlarmNoise.setOnPreparedListener(new OnPreparedListener() {
								public void onPrepared(MediaPlayer arg0) {
									audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
									AlarmNoise.start();									
								}
							});
							AudioIsRunning = true;
							AlarmNoise.setOnCompletionListener(new OnCompletionListener(){
								public void onCompletion(MediaPlayer arg0) {
									AudioIsRunning = false;									
								}
							});
						   Timer2PrepStart = true;
					   }
					   Timer2PrepTime.setText("  " + convertToTime(TotalGuideTime - (StartMillis2 + StartMillisCooldown2 + RunningMillis)) + "  ");
				   } else if ((TotalGuideTime - (StartMillisCooldown2 + RunningMillis)) >= 0) {
					   if (Timer2PrepDone == false && StartMillis2 > 0 && AudioIsRunning == false){
				    		myVibrate.vibrate(2000);
							AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.begincookingtwo);
							AlarmNoise.setOnPreparedListener(new OnPreparedListener() {
								public void onPrepared(MediaPlayer arg0) {
									audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
									AlarmNoise.start();									
								}
							});
							AudioIsRunning = true;
							AlarmNoise.setOnCompletionListener(new OnCompletionListener(){
								public void onCompletion(MediaPlayer arg0) {
									AudioIsRunning = false;									
								}
							});
						   Timer2PrepDone = true;
					   }
					   CurrentTime2.setText(convertToTime(TotalGuideTime - (StartMillisCooldown2 + RunningMillis)));
				   } else if ((TotalGuideTime - RunningMillis) >= 0) {
					   if (Timer2Done == false && StartMillisCooldown2 > 0 && AudioIsRunning == false){
				    		myVibrate.vibrate(2000);
							AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.startcoolingtwo);
							AlarmNoise.setOnPreparedListener(new OnPreparedListener() {
								public void onPrepared(MediaPlayer arg0) {
									audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
									AlarmNoise.start();									
								}
							});
							AudioIsRunning = true;
							AlarmNoise.setOnCompletionListener(new OnCompletionListener(){
								public void onCompletion(MediaPlayer arg0) {
									AudioIsRunning = false;									
								}
							});
						   Timer2Done = true;
					   }
					   Timer2CooldownTime.setText("  " + convertToTime(TotalGuideTime - RunningMillis) + "  ");
				   }
			   } 

			   //MPTimer 3 calculations
			   
			   if ((TotalGuideTime - RunningMillis ) <= Timer3Total) {
				   if ((TotalGuideTime - (StartMillis3 + StartMillisCooldown3 + RunningMillis)) >= 0) {
					   if (Timer3PrepDone == false && StartMillisCooldown3 > 0 && AudioIsRunning == false){
				    		myVibrate.vibrate(2000);
							AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.startprepthree);
							AlarmNoise.setOnPreparedListener(new OnPreparedListener() {
								public void onPrepared(MediaPlayer arg0) {
									audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
									AlarmNoise.start();									
								}
							});
							AudioIsRunning = true;
							AlarmNoise.setOnCompletionListener(new OnCompletionListener(){
								public void onCompletion(MediaPlayer arg0) {
									AudioIsRunning = false;									
								}
							});
						   Timer3PrepDone = true;
					   }
					   Timer3PrepTime.setText("  " + convertToTime(TotalGuideTime - (StartMillis3 + StartMillisCooldown3 + RunningMillis)) + "  ");
				   } else if ((TotalGuideTime - (StartMillisCooldown3 + RunningMillis)) >= 0) {
					   if (Timer3PrepDone == false && StartMillis3 > 0 && AudioIsRunning == false){
				    		myVibrate.vibrate(2000);
							AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.begincookingthree);
							AlarmNoise.setOnPreparedListener(new OnPreparedListener() {
								public void onPrepared(MediaPlayer arg0) {
									audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
									AlarmNoise.start();									
								}
							});
							AudioIsRunning = true;
							AlarmNoise.setOnCompletionListener(new OnCompletionListener(){
								public void onCompletion(MediaPlayer arg0) {
									AudioIsRunning = false;									
								}
							});
						   Timer3PrepDone = true;
					   }
					   CurrentTime3.setText(convertToTime(TotalGuideTime - (StartMillisCooldown3 + RunningMillis)));
				   } else if ((TotalGuideTime - RunningMillis) >= 0) {
					   if (Timer3Done == false && StartMillisCooldown3 > 0 && AudioIsRunning == false){
				    		myVibrate.vibrate(2000);
							AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.startcoolingthree);
							AlarmNoise.setOnPreparedListener(new OnPreparedListener() {
								public void onPrepared(MediaPlayer arg0) {
									audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
									AlarmNoise.start();									
								}
							});
							AudioIsRunning = true;
							AlarmNoise.setOnCompletionListener(new OnCompletionListener(){
								public void onCompletion(MediaPlayer arg0) {
									AudioIsRunning = false;									
								}
							});
						   Timer3Done = true;
					   }
					   Timer3CooldownTime.setText("  " + convertToTime(TotalGuideTime - RunningMillis) + "  ");
				   }
			   } 

			   //MPTimer 4 calculations
			   
			   if ((TotalGuideTime - RunningMillis ) <= Timer4Total) {
				   if ((TotalGuideTime - (StartMillis4 + StartMillisCooldown4 + RunningMillis)) >= 0) {
					   if (Timer4PrepDone == false && StartMillisCooldown4 > 0 && AudioIsRunning == false){
				    		myVibrate.vibrate(2000);
							AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.startprepfour);
							AlarmNoise.setOnPreparedListener(new OnPreparedListener() {
								public void onPrepared(MediaPlayer arg0) {
									audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
									AlarmNoise.start();									
								}
							});
							AudioIsRunning = true;
							AlarmNoise.setOnCompletionListener(new OnCompletionListener(){
								public void onCompletion(MediaPlayer arg0) {
									AudioIsRunning = false;									
								}
							});
						   Timer4PrepDone = true;
					   }
					   Timer4PrepTime.setText("  " + convertToTime(TotalGuideTime - (StartMillis4 + StartMillisCooldown4 + RunningMillis)) + "  ");
				   } else if ((TotalGuideTime - (StartMillisCooldown4 + RunningMillis)) >= 0) {
					   if (Timer4PrepDone == false && StartMillis4 > 0 && AudioIsRunning == false){
				    		myVibrate.vibrate(2000);
							AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.begincookingfour);
							AlarmNoise.setOnPreparedListener(new OnPreparedListener() {
								public void onPrepared(MediaPlayer arg0) {
									audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
									AlarmNoise.start();									
								}
							});
							AudioIsRunning = true;
							AlarmNoise.setOnCompletionListener(new OnCompletionListener(){
								public void onCompletion(MediaPlayer arg0) {
									AudioIsRunning = false;									
								}
							});
						   Timer4PrepDone = true;
					   }
					   CurrentTime4.setText(convertToTime(TotalGuideTime - (StartMillisCooldown4 + RunningMillis)));
				   } else if ((TotalGuideTime - RunningMillis) >= 0) {
					   if (Timer4Done == false && StartMillisCooldown4 > 0 && AudioIsRunning == false){
				    		myVibrate.vibrate(2000);
							AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.startcoolingfour);
							AlarmNoise.setOnPreparedListener(new OnPreparedListener() {
								public void onPrepared(MediaPlayer arg0) {
									audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
									AlarmNoise.start();									
								}
							});
							AudioIsRunning = true;
							AlarmNoise.setOnCompletionListener(new OnCompletionListener(){
								public void onCompletion(MediaPlayer arg0) {
									AudioIsRunning = false;									
								}
							});
						   Timer4Done = true;
					   }
					   Timer4CooldownTime.setText("  " + convertToTime(TotalGuideTime - RunningMillis) + "  ");
				   }
			   } 

			   //Check to see if everything is done
			   
			   if ((TotalGuideTime - RunningMillis) < 0) {
		    		myVibrate.vibrate(5000);
					AlarmNoise = MediaPlayer.create(getBaseContext(), R.raw.alarm);
					audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
					if (TotalGuideTime > 0) {
						AlarmNoise.setOnCompletionListener(new OnCompletionListener(){
							public void onCompletion(MediaPlayer arg0) {
								AudioIsRunning = false;
								MealPrepButton.setText("Start Meal Planner");
							}});
						AlarmNoise.start();
					}
			   } else {	
			   GuideHandler.postAtTime(this, thisInstanceStartMS + 1000);
			   }
		   }
		};

	private Handler screenOFFHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        PowerManager powerManager = (PowerManager) TimerActivity.this
	                .getSystemService(Context.POWER_SERVICE);
	        long l = SystemClock.uptimeMillis();
	        powerManager.userActivity(l, false);//false will bring the screen back as bright as it was, true - will dim it
	    }
	};
}