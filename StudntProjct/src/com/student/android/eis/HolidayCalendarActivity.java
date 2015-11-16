package com.student.android.eis;

import com.student.android.eis.R;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HolidayCalendarActivity extends BaseActivity{
	private LinearLayout mNavigationbBar;
	private TextView txt_header;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holiday_calendar);
        initUI();
    }

	private void initUI() {
		// TODO Auto-generated method stub
		mNavigationbBar = (LinearLayout)findViewById(R.id.layout_header);
		txt_header = (TextView)mNavigationbBar.findViewById(R.id.tv_header);
		txt_header.setText("Holiday Calender 2012");
	}
}
