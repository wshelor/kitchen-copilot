package com.Willshyre.KitchenCopilot;



import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class RecipeActivity extends Activity {

	private static WebView RecipeWebsite = null;
	public static String currentWebsite;
	public static int WebSwitcher;
	ImageView epicuriousButton; 
	ImageView allRecipesButton; 
	ImageView foodComButton; 
	ImageView myRecipesButton; 
	ImageView simplyRecipesButton; 
	ImageView vegWebButton;
	boolean netOpen;

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.siteselecttab);
	    netOpen = false;
	    epicuriousButton = (ImageView) findViewById(R.id.EpicuriousButton);
		epicuriousButton.setOnClickListener(new View.OnClickListener(){
		public void onClick(View view) {
			setContentView(R.layout.recipetab);
		    currentWebsite = "http://www.epicurious.com/";
		    loadRecipeWebsite();
		}});

		allRecipesButton = (ImageView) findViewById(R.id.AllRecipesButton);
		allRecipesButton.setOnClickListener(new View.OnClickListener(){
		public void onClick(View view) {
			setContentView(R.layout.recipetab);
		    currentWebsite = "http://mobile.allrecipes.com";
		    loadRecipeWebsite();
		}});

		foodComButton = (ImageView) findViewById(R.id.FoodComButton);
		foodComButton.setOnClickListener(new View.OnClickListener(){
		public void onClick(View view) {
			setContentView(R.layout.recipetab);
		    currentWebsite = "http://www.food.com";
		    loadRecipeWebsite();
		}});

		myRecipesButton = (ImageView) findViewById(R.id.MyRecipesButton);
		myRecipesButton.setOnClickListener(new View.OnClickListener(){
		public void onClick(View view) {
			setContentView(R.layout.recipetab);
		    currentWebsite = "http://m.myrecipes.com";
		    loadRecipeWebsite();
		}});

		simplyRecipesButton = (ImageView) findViewById(R.id.SimplyRecipesButton);
		simplyRecipesButton.setOnClickListener(new View.OnClickListener(){
		public void onClick(View view) {
			setContentView(R.layout.recipetab);
		    currentWebsite = "http://www.simplyrecipes.com";
		    loadRecipeWebsite();
		}});

		vegWebButton = (ImageView) findViewById(R.id.VegWebButton);
		vegWebButton.setOnClickListener(new View.OnClickListener(){
		public void onClick(View view) {
			setContentView(R.layout.recipetab);
		    currentWebsite = "http://www.VegWeb.com";
		    loadRecipeWebsite();
		}});
	}
	
	private class HelloWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        view.loadUrl(url);
	        return true;
	    }
	}
	
	public void onResume(Bundle savedInstanceState) {
	    final WebView RecipeWebsite = (WebView) findViewById(R.id.RecipeWebsite);
	    SharedPreferences prefs = getPreferences(0);
		String CurrentURL = prefs.getString("CurrentURL","");
	    RecipeWebsite.loadUrl(CurrentURL);	
	}
	
	public void onDestroy(Bundle savedInstanceState) {
	    final WebView RecipeWebsite = (WebView) findViewById(R.id.RecipeWebsite);
		RecipeWebsite.clearCache(true);	
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (netOpen)
		if ((keyCode == KeyEvent.KEYCODE_BACK) && RecipeWebsite.canGoBack()) {
	        RecipeWebsite.goBack();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	public void loadRecipeWebsite() {
		netOpen = true;
	    RecipeWebsite = (WebView) findViewById(R.id.RecipeWebsite);
	    RecipeWebsite.getSettings().setJavaScriptEnabled(true);
	    RecipeWebsite.setWebChromeClient(new WebChromeClient(){});
	    RecipeWebsite.loadUrl(currentWebsite);
	    RecipeWebsite.setWebViewClient(new HelloWebViewClient());
	    RecipeWebsite.setOnClickListener(new View.OnClickListener()
		{
	    	public void onClick(View view) {
	    		String currentURL = RecipeWebsite.getUrl();
	    		SharedPreferences sharedPref = getSharedPreferences("MyData",MODE_PRIVATE);
	    		SharedPreferences.Editor prefEdit = sharedPref.edit();
	    		prefEdit.putString("CurrentURL",currentURL);
	    		prefEdit.commit();
	    	}
		});
	    RecipeWebsite.requestFocus(View.FOCUS_DOWN);
	    RecipeWebsite.setOnTouchListener(new View.OnTouchListener() {
	        public boolean onTouch(View v, MotionEvent event) {
	            switch (event.getAction()) {
	                case MotionEvent.ACTION_DOWN:
	                case MotionEvent.ACTION_UP:
	                    if (!v.hasFocus()) {
	                        v.requestFocus();
	                    }
	                    break;
	            }
	            return false;
	        }
	    });

	};
}
