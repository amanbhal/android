package com.org.ez;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabDemo extends Activity {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);

		TabHost tabs=(TabHost)findViewById(R.id.tabhost);
		
		tabs.setup();
		
		TabHost.TabSpec spec=tabs.newTabSpec("tag1");
		
		spec.setContent(R.id.tab1);
		spec.setIndicator("Clock");
		tabs.addTab(spec);
		
		spec=tabs.newTabSpec("tag2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Button");
		tabs.addTab(spec);
	}
}