package com.commonsware.cz;


import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Browser extends Activity {
	WebView browser;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		browser=(WebView)findViewById(R.id.webkit);
		
		browser.loadUrl("http://www.google.com");
	}
}