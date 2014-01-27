package com.Willshyre.KitchenCopilot;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ConverterActivity extends Activity {
	EditText TextIn;
	EditText TextOut;
	Spinner SizeIn;
	Spinner SizeOut;
	Spinner Category;
	String[] SizeInShortArray; 
	String[] SizeOutShortArray; 
	int CurrentCategory;
	double SizeInMultiplier;
	double SizeOutMultiplier;
	boolean doConversion;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
		  setContentView(R.layout.convertertab);
		    
		    //-Declare Important Variables
			TextIn = (EditText) findViewById(R.id.NumIn);
			TextOut = (EditText) findViewById(R.id.NumOut);
			SizeInShortArray = getResources().getStringArray(R.array.SizeInArray);
			SizeOutShortArray = getResources().getStringArray(R.array.SizeOutArray);
			doConversion = true;
			
			//Setup Category Spinner
			Category = (Spinner) findViewById(R.id.CategorySpinner);
			final ArrayAdapter<CharSequence> CategoryArray = ArrayAdapter.createFromResource(this, R.array.Category, android.R.layout.simple_spinner_item);
			CategoryArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			Category.setAdapter(CategoryArray);
			CurrentCategory = 1;
			
				
			//Setup SizeIn Spinner
			SizeIn = (Spinner) findViewById(R.id.SizeInSpinner);
		    final ArrayAdapter<CharSequence> SizeInArray = ArrayAdapter.createFromResource(this, R.array.SizeInArray, android.R.layout.simple_spinner_item);
		    SizeInArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    final ArrayAdapter<CharSequence> SizeInArrayWeight = ArrayAdapter.createFromResource(this, R.array.SizeInWeight, android.R.layout.simple_spinner_item);
		    SizeInArrayWeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    SizeIn.setAdapter(SizeInArray);
    		SizeInMultiplier = 0.02083333;
	
			//Setup SizeOut Spinner	
			SizeOut = (Spinner) findViewById(R.id.SizeOutSpinner);
		    final ArrayAdapter<CharSequence> SizeOutArray = ArrayAdapter.createFromResource(this, R.array.SizeOutArray, android.R.layout.simple_spinner_item);
		    SizeOutArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    final ArrayAdapter<CharSequence> SizeOutArrayWeight = ArrayAdapter.createFromResource(this, R.array.SizeOutWeight, android.R.layout.simple_spinner_item);
		    SizeOutArrayWeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SizeOutMultiplier = 0.02083333;
		    SizeOut.setAdapter(SizeOutArray);
			Category.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
				{
					public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						if (arg2 == 0){
						    SizeIn.setAdapter(SizeInArray);
						    SizeOut.setAdapter(SizeOutArray);
							CurrentCategory = 1;
			        		SizeInMultiplier = 0.02083333;
						    SizeOutMultiplier = 0.00220462262;
						}
						if (arg2 == 1){
						    SizeIn.setAdapter(SizeInArrayWeight);
						    SizeOut.setAdapter(SizeOutArrayWeight);
							CurrentCategory = 2;
						}
						if (arg2 == 2){
							CurrentCategory = 3;
						}
					}
					public void onNothingSelected(AdapterView<?> arg0) {
					}});
						
			SizeIn.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
			{
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					//SizeIn
					if (CurrentCategory == 1){
						if (arg2 == 0){SizeInMultiplier = 0.02083333;}
						if (arg2 == 1){SizeInMultiplier = 0.0625;}
						if (arg2 == 2){SizeInMultiplier = 0.12;}
						if (arg2 == 3){SizeInMultiplier = 1;}
						if (arg2 == 4){SizeInMultiplier = 2;}
						if (arg2 == 5){SizeInMultiplier = 4;}
						if (arg2 == 6){SizeInMultiplier = 16;}
						if (arg2 == 7){SizeInMultiplier = 0.00422675284;}
						if (arg2 == 8){SizeInMultiplier = 4.22675284;}
					}
					if (CurrentCategory == 2){
						if (arg2 == 0){SizeInMultiplier = 0.00220462262;}
						if (arg2 == 1){SizeInMultiplier = 2.20462262;}
						if (arg2 == 2){SizeInMultiplier = 0.0625;}
						if (arg2 == 3) {SizeInMultiplier = 1;}
						if (arg2 == 4){SizeInMultiplier = 2000;}			
					}
					if (TextIn.getText().toString().matches("")){}
					else {
					{double NumIn = Double.parseDouble(TextIn.getText().toString());	
					if (NumIn > 0){
			       //-calculate and display final value
						doConversion = false;
						double FinalValue;
						FinalValue = NumIn * SizeInMultiplier * SizeOutMultiplier;
						FinalValue = Math.floor(FinalValue * 100 +.5)/100;
						TextOut.setText(Double.toString(FinalValue));
			        }}}	
					doConversion = true;
				}
				public void onNothingSelected(AdapterView<?> arg0) {
				}});
			SizeOut.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
			{
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					if (CurrentCategory == 1) {
						switch (arg2) {
							case 0: {SizeOutMultiplier = 48; break;}
							case 1: {SizeOutMultiplier = 16; break;}
							case 2: {SizeOutMultiplier = 8; break;}
							case 3: {SizeOutMultiplier = 1; break;}
							case 4: {SizeOutMultiplier = 0.5; break;}
							case 5: {SizeOutMultiplier = 0.25; break;}
							case 6: {SizeOutMultiplier = 0.0625; break;}
							case 7: {SizeOutMultiplier = 236.588237; break;}
							case 8: {SizeOutMultiplier = 0.236588236; break;}
						default: break;
						}
					}
					if (CurrentCategory == 2){
						switch (arg2) {
							case 0: {SizeOutMultiplier = 453.59237; break;}
							case 1: {SizeOutMultiplier = 0.45359237; break;}
							case 2: {SizeOutMultiplier = 16; break;}
							case 3: {SizeOutMultiplier = 1; break;}
							case 4: {SizeOutMultiplier = 0.0005; break;}
							default: break;
						}
					}
					if (TextIn.getText().toString().matches("")){} else {
					{double NumIn = Double.parseDouble(TextIn.getText().toString());	
					if (NumIn > 0){
			       //-calculate and display final value
						doConversion = false;
						double FinalValue;
						FinalValue = NumIn * SizeInMultiplier * SizeOutMultiplier;
						FinalValue = Math.floor(FinalValue * 100 +.5)/100;
			        TextOut.setText(Double.toString(FinalValue));
			        }				
					}}	
					doConversion = true;
				}
				public void onNothingSelected(AdapterView<?> arg0) {
				}});
	
			TextIn.setOnTouchListener(new Button.OnTouchListener()
				{
				public boolean onTouch(View arg0, MotionEvent arg1) {
					TextIn.setText("");
					TextOut.setText("");
					return false;
				}
				});
			TextOut.setOnTouchListener(new Button.OnTouchListener()
				{
				public boolean onTouch(View arg0, MotionEvent arg1) {
					TextIn.setText("");
					TextOut.setText("");
					return false;
				} 
				});
			TextIn.setOnFocusChangeListener(new OnFocusChangeListener() {
				  public void onFocusChange(View v, boolean hasFocus) {
					  if (hasFocus) {
				 		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				 		imm.showSoftInput(TextIn, InputMethodManager.SHOW_IMPLICIT);
						TextIn.addTextChangedListener(new CustomTextWatcher(TextIn, TextOut, true));
						TextOut.setFocusable(false);
				 	} else {InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				 		imm.hideSoftInputFromWindow(TextIn.getWindowToken(), InputMethodManager.SHOW_IMPLICIT);
				 	}
				}});
			TextOut.setOnFocusChangeListener(new OnFocusChangeListener() {
					public void onFocusChange(View v, boolean hasFocus) {
						if (hasFocus) {
							InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				 			imm.showSoftInput(TextIn, InputMethodManager.SHOW_IMPLICIT);
				 			TextOut.addTextChangedListener(new CustomTextWatcher(TextOut, TextIn, false));
				 			TextIn.setFocusable(false);
						} else {InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
							imm.hideSoftInputFromWindow(TextIn.getWindowToken(), InputMethodManager.SHOW_IMPLICIT);
						}
					}
				});
		}

private class CustomTextWatcher implements TextWatcher {
    private EditText mEditText;
    private EditText mEditText2;
    private boolean inout;
    public CustomTextWatcher(EditText e, EditText o, boolean i) {
        mEditText = e;
        mEditText2 = o;
    }
	public void afterTextChanged(Editable arg0) {
		if (doConversion) {
			doConversion = false;
		
		if (mEditText.getText().toString().matches("") || mEditText.getText().toString().matches(".")){}
			else {
			{double NumIn = Double.parseDouble(mEditText.getText().toString());	
			if (NumIn > 0){
	       //-calculate and display final value
				double FinalValue;
			if (inout) { FinalValue = NumIn * SizeInMultiplier * SizeOutMultiplier;}
	       else {FinalValue = NumIn * SizeInMultiplier * SizeOutMultiplier;}   
	       FinalValue = Math.floor(FinalValue * 100 +.5)/100;

	        mEditText2.removeTextChangedListener(this);
	        mEditText2.setText(Double.toString(FinalValue));
	        mEditText2.addTextChangedListener(this);
			}				
			}}	
			doConversion = true;
		}
	}
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
	}

}