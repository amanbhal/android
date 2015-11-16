package com.student.android.eis;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.student.android.eislogger.Logger;
import com.student.android.eismodel.Constant;
import com.student.android.eismodel.Student;
import com.student.android.eis.R;

public class StudentDetailsActivity extends BaseActivity{
	private LinearLayout mNavigationbBar;
	private TextView txt_header;
	private TextView tv_name;
	private TextView tv_designation;
	private TextView tv_empid;
	private TextView tv_reportingmanager;
	private TextView tv_department;
	private TextView tv_joiningdate;
	private TextView tv_club;
	private TextView tv_keyskills;
	private TextView tv_currentprojects;
	private TextView tv_pastprojets;
	private Button btn_email;
	private Button btn_phone;
	String name;
	private Student mystudent;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_details);
		name = getIntent().getExtras().getString("name");
		Logger.printMessage("Name Received", name, Logger.DEBUG);
		initUI();
		mystudent = dbManager.getstudentDetails(Constant.TABLE_STUDENT, Constant.COL_STD_ALL, Constant.COL_STD_ID, name);
		//myCursor = dbManager.getAllstudents();
		//myCursor.moveToFirst();
		if (mystudent != null) {
			fillData(mystudent);
		}
		else {
			{
				finish();
			}
		}
	}

	private void fillData(Student mystudent) {
		// TODO Auto-generated method stub

		tv_name.setText(mystudent.getmName());
		tv_designation.setText(mystudent.getmDesignation());
		tv_empid.setText("#" + mystudent.getmId());
		tv_reportingmanager.setText(mystudent.getmMentor());
		tv_department.setText(mystudent.getmDepartment());
		tv_joiningdate.setText(mystudent.getmBirthdate());
		tv_club.setText(mystudent.getmClub());
		tv_keyskills.setText(mystudent.getmKeySkills());
		tv_currentprojects.setText(mystudent.getmCurrentProject());
		tv_pastprojets.setText(mystudent.getmPastProject());
		btn_email.setText(mystudent.getmEmail());
		btn_phone.setText(mystudent.getmPhone());
	}


	private void initUI() {
		// TODO Auto-generated method stub
		mNavigationbBar = (LinearLayout)findViewById(R.id.layout_header);
		txt_header = (TextView)mNavigationbBar.findViewById(R.id.tv_header);
		txt_header.setText("student Details");

		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_designation = (TextView) findViewById(R.id.tv_designation);
		tv_empid = (TextView) findViewById(R.id.tv_empid);
		tv_reportingmanager = (TextView) findViewById(R.id.tv_reportingmanager);
		tv_department = (TextView) findViewById(R.id.tv_department);
		tv_joiningdate = (TextView) findViewById(R.id.tv_joiningdate);
		tv_club = (TextView) findViewById(R.id.tv_club);
		tv_keyskills = (TextView) findViewById(R.id.tv_keyskills);
		tv_currentprojects = (TextView) findViewById(R.id.tv_currentprojects);
		tv_pastprojets = (TextView) findViewById(R.id.tv_pastprojets);
		btn_email = (Button) findViewById(R.id.btn_email);
		btn_phone = (Button) findViewById(R.id.btn_phone);

		btn_phone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				call();
			}
		});
		
		
		btn_email.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendMail();
			}
		});
	}
	
	private void sendMail()
	{
		
	
	Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
    emailIntent.setType("text/plain");
    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{btn_email.getText().toString()});
    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "EIS App Mail");
   // emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");
    
    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	}
	
	private void call() {
	    try {
	        Intent callIntent = new Intent(Intent.ACTION_CALL);
	        callIntent.setData(Uri.parse("tel:"+btn_phone.getText().toString()));
	        startActivity(callIntent);
	    } catch (ActivityNotFoundException e) {
	    	Logger.printMessage("Activity Not found", "NIKHIL", Logger.DEBUG);
	    }
	}
}
