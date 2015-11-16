package com.org.cz;

import android.app.Activity;
import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Browser2 extends Activity {
	WebView browser;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		browser=(WebView)findViewById(R.id.webkit);
		
		browser.loadData("<html><body>Hello, world!</body></html>",
											"text/html", "UTF-8");
	}
}