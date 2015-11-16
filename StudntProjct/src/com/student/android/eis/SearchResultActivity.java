package com.student.android.eis;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.student.android.adapter.MyAdapter;
import com.student.android.eislogger.Logger;
import com.student.android.eismodel.Constant;
import com.student.android.eis.R;

public class SearchResultActivity extends BaseActivity{
	private LinearLayout mNavigationbBar;
	private TextView txt_header;
	private ListView list_students;
	private Button btn_header;
	private Cursor myCursor;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mentors);

		initUI();
		getAllstudents();
	}

	private void getAllstudents() {
		// TODO Auto-generated method stub
		String dataString = getIntent().getExtras().getString("data");
			myCursor = dbManager.searchstudentsRecord(Constant.TABLE_STUDENT, Constant.COL_STD_ALL, Constant.COL_STD_ID, dataString);
			int count = myCursor.getCount();
		if(myCursor!=null){
			list_students.setAdapter(new MyAdapter(this, myCursor));
			list_students.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					// TODO Auto-generated method stub
					TextView tv_name = (TextView) arg1.findViewById(R.id.tv_empname);
					Logger.printMessage("Sent Name", tv_name.getText().toString(), Logger.DEBUG);
					Bundle mBundle = new Bundle();
					mBundle.putString("name", tv_name.getTag().toString());
					//myCursor.close();
					//dbManager.closeDatabase();
					pushActivity(SearchResultActivity.this, StudentDetailsActivity.class.getName(), mBundle);
				}
			});
		}
	}

	private void initUI() {
		// TODO Auto-generated method stub
		mNavigationbBar = (LinearLayout)findViewById(R.id.layout_header);
		txt_header = (TextView)mNavigationbBar.findViewById(R.id.tv_header);
		list_students = (ListView) findViewById(R.id.list_employees);
		txt_header.setText("Search Results");

		btn_header = (Button) mNavigationbBar.findViewById(R.id.btn_header);
		btn_header.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(0);
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		myCursor.close();
		super.onDestroy();
	}
}
