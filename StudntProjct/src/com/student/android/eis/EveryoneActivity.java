package com.student.android.eis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class EveryoneActivity extends BaseActivity{
	private LinearLayout mNavigationbBar;
	private TextView txt_header;
	private Button btn_student_departments;
	private Button btn_mentors;	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.everyone);
        
        initUI();
        setListeners();
    }

	private void setListeners() {
		// TODO Auto-generated method stub
		btn_student_departments = (Button) findViewById(R.id.btn_student_departments);
		btn_student_departments.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                departmentsButtonClicked();
	 }


	  });
	       
		btn_mentors = (Button) findViewById(R.id.btn_mentors);
		btn_mentors.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                studentButtonClicked();
	 }
	  });
	}
	
	private void studentButtonClicked() {
		// TODO Auto-generated method stub
		Bundle mBundle = new Bundle();
		mBundle.putString("data", "Mentors");

		pushActivity(this, WirkliteActivity.class.getName(),mBundle);
		
	}
	private void departmentsButtonClicked() {
		// TODO Auto-generated method stub
		pushActivity(this, DepartmentsActivity.class.getName());

	}
	private void initUI() {
		// TODO Auto-generated method stub
		mNavigationbBar = (LinearLayout)findViewById(R.id.layout_header);
		txt_header = (TextView)mNavigationbBar.findViewById(R.id.tv_header);
		txt_header.setText("All Categories");
	}
}
