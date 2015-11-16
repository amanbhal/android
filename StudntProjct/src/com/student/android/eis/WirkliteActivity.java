package com.student.android.eis;

import java.util.ArrayList;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.student.android.adapter.MyAdapter;
import com.student.android.eislogger.Logger;
import com.student.android.eismodel.Constant;
import com.student.android.eis.R;


public class WirkliteActivity extends BaseActivity{
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
		txt_header.setText(dataString);
		if (dataString.equalsIgnoreCase("mentors")) {
			myCursor = dbManager.getAllstudentsRecord(Constant.TABLE_STUDENT, Constant.COL_STD_ALL, Constant.COL_STD_DESIGNATION, "Teacher");
		} else {
			myCursor = dbManager.getAllstudentsRecord(Constant.TABLE_STUDENT, Constant.COL_STD_ALL, Constant.COL_STD_DEPARTMENT, dataString);
		}
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
					pushActivity(WirkliteActivity.this, StudentDetailsActivity.class.getName(), mBundle);
				}
			});
		}
	}

	private void initUI() {
		// TODO Auto-generated method stub
		mNavigationbBar = (LinearLayout)findViewById(R.id.layout_header);
		txt_header = (TextView)mNavigationbBar.findViewById(R.id.tv_header);
		list_students = (ListView) findViewById(R.id.list_employees);

//		btn_header = (Button) mNavigationbBar.findViewById(R.id.btn_header);
//		btn_header.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				showDialog(0);
//			}
//		});
	}

	protected Dialog onCreateDialog(final int id) {

		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.search_employee);
		dialog.setTitle("Search student");
		dialog.setCancelable(true);
		final RadioGroup rGroup = (RadioGroup) dialog.findViewById(R.id.rg_search);
		//rGroup.setOnCheckedChangeListener(changeListener);
		Button cancelButton = (Button) dialog.findViewById(R.id.btn_cancel);
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		Button searchButton = (Button) dialog.findViewById(R.id.btn_search);
		
		final EditText searchText = (EditText) dialog.findViewById(R.id.et_search);

		searchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final String searchString = searchText.getText().toString();

				// TODO Auto-generated method stub
				if (searchString.trim().equalsIgnoreCase("")) {
					searchToastMethod();
				} else {
					int id = rGroup.getCheckedRadioButtonId();
					Logger.printMessage("Button Clicked", Integer.toString(id), Logger.DEBUG);
					String searchId = null;

					switch (id) {
					case R.id.radio_id:
						searchId = "id";
						break;
					case R.id.radio_name:
						//tv_leavetype.setText("Sick");
						searchId = "name";
						break;

					default:
						break;
					}
					searchMethod(searchId, searchString);
					dialog.dismiss();

				}
			}
		});
		return dialog;
	}
	
	protected void onPrepareDialog(final int id, final Dialog dialog) {
		final EditText searchText = (EditText) dialog.findViewById(R.id.et_search);
		searchText.setText("");
	}
	
	private void searchToastMethod()
	{
		String text = "Fill in the search field";
		Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
		toast.show();
	}

	private void searchMethod(String categoryString , String name) {	
		Logger.printMessage("Category Name", categoryString + " " + name, Logger.DEBUG);

		int columnIndex;
		if (categoryString.equalsIgnoreCase("name")) {
			columnIndex = 2;
		}
		else {
			columnIndex = 1;
		}

		ArrayList<String> idArrayList = new ArrayList<String>();
		myCursor.moveToFirst();
		while (myCursor.isAfterLast() == false) {
			
			String columnString  = myCursor.getString(columnIndex);
			if (columnString.contains(name)) {
				idArrayList.add(myCursor.getString(1));
			}
			myCursor.moveToNext();
		}
		
		if (idArrayList.size()>0) {
			String text = idArrayList.size() + "Record Found";
			Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
			toast.show();
			
			StringBuilder concatString = new StringBuilder();

			for (int i = 0; i < idArrayList.size(); i++) {
				String tempString = idArrayList.get(i);
				if (i==0) {
					concatString.append("'"+ tempString + "'");
				} 
				else {
					concatString.append(","+ "'"+ tempString + "'");
				}
			}
			Bundle mBundle = new Bundle();
			mBundle.putString("data", concatString.toString());
			//myCursor.close();
			//dbManager.closeDatabase();
			pushActivity(WirkliteActivity.this, SearchResultActivity.class.getName(), mBundle);
	
			
		} else {
			String text = "No Record Found";
			Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		myCursor.close();
		super.onDestroy();
	}
}
