package com.student.android.eis;

import com.student.android.eis.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DepartmentsActivity extends BaseActivity{
	private LinearLayout mNavigationbBar;
	private TextView txt_header;
	private Button btn_mechanical;
	private Button btn_ece;
	private Button btn_cs;
	private Button btn_ic;
	private Button btn_it;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.departments);
        initUI();
    }

	private void initUI() {
		// TODO Auto-generated method stub
		mNavigationbBar = (LinearLayout)findViewById(R.id.layout_header);
		txt_header = (TextView)mNavigationbBar.findViewById(R.id.tv_header);
		txt_header.setText("Departments");
		
		btn_mechanical = (Button) findViewById(R.id.btn_mechanical);
		btn_mechanical.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				buttonPressed(v);
			}
		});
				
		
		btn_ece = (Button) findViewById(R.id.btn_ece);
		btn_ece.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				buttonPressed(v);
			}
		});
		
		btn_cs = (Button) findViewById(R.id.btn_cs);
		btn_cs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				buttonPressed(v);
			}
		});
		
		
		btn_ic = (Button) findViewById(R.id.btn_ic);
		btn_ic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				buttonPressed(v);
			}
		});
		
		
		btn_it = (Button) findViewById(R.id.btn_it);
		btn_it.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				buttonPressed(v);
			}
		});
	}
	private void buttonPressed(View v)
	{
		String dataString = null;
		switch (v.getId()) {
		case R.id.btn_mechanical:
			dataString = "MAE";
			break;
		case R.id.btn_ece:
			dataString = "ECE";

			break;
		case R.id.btn_cs:
			dataString = "CS";

			break;
		case R.id.btn_ic:
			dataString = "IC";

			break;
		case R.id.btn_it:
			dataString = "IT";

			break;
		default:
			break;
		}
		Bundle mBundle = new Bundle();
		mBundle.putString("data", dataString);

		pushActivity(DepartmentsActivity.this, WirkliteActivity.class.getName(),mBundle);
	}
}
