package com.org.ez;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Rotations3Activity extends Activity {
	static final int PICK_REQUEST=1337;
	Button viewButton=null;
	Uri contact=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		viewButton=(Button)findViewById(R.id.view);
		viewButton.setEnabled(contact!=null);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
																		Intent data) {
		if (requestCode==PICK_REQUEST) {
			if (resultCode==RESULT_OK) {
				contact=data.getData();
				viewButton.setEnabled(true);
			}
		}
	}

	public void pickContact(View v) {
		Intent i=new Intent(Intent.ACTION_PICK,
												Contacts.CONTENT_URI);
	
		startActivityForResult(i, PICK_REQUEST);
	}
	
	public void viewContact(View v) {
		startActivity(new Intent(Intent.ACTION_VIEW, contact));
	}

	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		
		LinearLayout container=(LinearLayout)findViewById(R.id.container);
		
		if (newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE) {
			container.setOrientation(LinearLayout.HORIZONTAL);
		}
		else {
			container.setOrientation(LinearLayout.VERTICAL);
		}
	}
}
