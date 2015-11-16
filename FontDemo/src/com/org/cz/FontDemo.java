package com.org.cz;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import java.io.File;

public class FontDemo extends Activity {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		
		TextView tv=(TextView)findViewById(R.id.custom);
		Typeface face=Typeface.createFromAsset(getAssets(),"fonts/HandmadeTypewriter.ttf");
		
		tv.setTypeface(face);
		
		File font=new File(Environment.getExternalStorageDirectory(),
											 "MgOpenCosmeticaBold.ttf");
		
		if (font.exists()) {
			tv=(TextView)findViewById(R.id.file);
			face=Typeface.createFromFile(font);
			
			tv.setTypeface(face);
		}
		else {
			findViewById(R.id.filerow).setVisibility(View.GONE);
		}
	}
}
