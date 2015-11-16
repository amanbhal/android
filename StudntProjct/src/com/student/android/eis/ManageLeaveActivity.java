package com.student.android.eis;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.student.android.eismodel.Constant;
import com.student.android.eis.R;

public class ManageLeaveActivity extends BaseActivity{
	private LinearLayout mNavigationbBar;
	private TextView txt_header;
	private Button btn_applyleaves;
	private TextView tv_casualleave;
	private TextView tv_sickleave;
	private Cursor myCursor;
	private Button btn_pendingleaves;
	private Button btn_leavehistory;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manage_leaves);
		initUI();
		fillData();

	}

	private void fillData() {
		// TODO Auto-generated method stub
		String sickLeave = dbManager.getstudentRecord(Constant.TABLE_STUDENT, Constant.COL_STD_SICKLEAVE, Constant.COL_STD_ID, dataModel.mCurrentUser.getmId());
		String casualLeave = dbManager.getstudentRecord(Constant.TABLE_STUDENT, Constant.COL_STD_CASUALLEAVE, Constant.COL_STD_ID, dataModel.mCurrentUser.getmId());
		if (sickLeave!=null) {
			tv_sickleave.setText(sickLeave);
		}
		else {
			tv_sickleave.setText("Could not retrieve");
		}
		if (casualLeave!=null) {
			tv_casualleave.setText(casualLeave);
		}
		else {
			tv_casualleave.setText("Could not retrieve");
		}
		
		myCursor = dbManager.getPendingLeaves(dataModel.mCurrentUser.getmId());
		if (myCursor.getCount() == 0) {
			btn_pendingleaves.setVisibility(Button.GONE);
		}
		myCursor.close();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		mNavigationbBar = (LinearLayout)findViewById(R.id.layout_header);
		txt_header = (TextView)mNavigationbBar.findViewById(R.id.tv_header);
		txt_header.setText("Manage Leaves");
		tv_casualleave = (TextView) findViewById(R.id.tv_casualleave);
		tv_sickleave = (TextView) findViewById(R.id.tv_sickleave);

		btn_applyleaves = (Button) findViewById(R.id.btn_applyleave);
		btn_applyleaves.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				applyLeavesButtonClicked();
			}
		});
		btn_pendingleaves = (Button) findViewById(R.id.btn_pendingleaves);
		btn_pendingleaves.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				pendingLeavesButtonClicked();
			}

		});
		
		btn_leavehistory = (Button) findViewById(R.id.btn_leavehistory);
		btn_leavehistory.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				historyLeavesButtonClicked();
			}

		});
	}
	private void pendingLeavesButtonClicked() {
		// TODO Auto-generated method stub
		Bundle mBundle = new Bundle();
		mBundle.putString("dataString","pending");
		//myCursor.close();
		//dbManager.closeDatabase();
		pushActivity(ManageLeaveActivity.this, PendingLeavesActivity.class.getName(), mBundle);
		
		
	}
	
	private void historyLeavesButtonClicked() {
		// TODO Auto-generated method stub
		Bundle mBundle = new Bundle();
		mBundle.putString("dataString","history");
		//myCursor.close();
		//dbManager.closeDatabase();
		pushActivity(ManageLeaveActivity.this, PendingLeavesActivity.class.getName(), mBundle);
		
	}
	private void applyLeavesButtonClicked()
	{
		Bundle mBundle = new Bundle();
		mBundle.putString("sickleave", tv_sickleave.getText().toString());
		mBundle.putString("casualleave", tv_casualleave.getText().toString());
		pushActivity(ManageLeaveActivity.this, ApplyLeaveActivity.class.getName(), mBundle);
	}
}
