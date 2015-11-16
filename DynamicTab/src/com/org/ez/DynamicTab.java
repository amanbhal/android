package com.org.ez;

import android.app.Activity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.TabHost;

public class DynamicTab extends Activity {
	private TabHost tabs=null;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);

		tabs=(TabHost)findViewById(R.id.tabhost);
		tabs.setup();
		
		TabHost.TabSpec spec=tabs.newTabSpec("buttontab");
		
		spec.setContent(R.id.buttontab);
		spec.setIndicator("Button");
		tabs.addTab(spec);
	}
	
	public void addTab(View v) {
		TabHost.TabSpec spec=tabs.newTabSpec("tag1");
		
		spec.setContent(new TabHost.TabContentFactory() {
			public View createTabContent(String tag) {
				return(new AnalogClock(DynamicTab.this));
			}
		});
		
		spec.setIndicator("Clock");
		tabs.addTab(spec);
	}
}
