package com.student.android.eis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.student.android.eislogger.Logger;
import com.student.android.eismodel.Constant;
import com.student.android.eismodel.Leave;
import com.student.android.eis.R;

public class LeaveDetailsActivity extends BaseActivity{
	private LinearLayout mNavigationbBar;
	private TextView txt_header;
	private TextView tv_leavetype;
	private TextView tv_entitlement;
	private TextView tv_from;
	private TextView tv_to;
	private TextView tv_appliedfor;
	private TextView tv_leavereason;
	private Button btn_approve;
	private Button btn_reject;
	private String leaveId;
	private String leaveType;
	private String leaveEmpId;
	private Leave myLeave;
	//private 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leave_details);
        initUI();
        fillData();
    }

	private void fillData() {
		// TODO Auto-generated method stub
		leaveId = getIntent().getExtras().getString("leaveId");
		leaveType = getIntent().getExtras().getString("leaveType");
		if (leaveType.equalsIgnoreCase("history")) {
			btn_approve.setVisibility(Button.GONE);
			btn_reject.setVisibility(Button.GONE);
		}

		Logger.printMessage("Leave Id Received", leaveId, Logger.DEBUG);
		myLeave = dbManager.getLeaveDetails(Constant.TABLE_LEAVE, Constant.COL_LEAVE_ALL, Constant.ID_COLUMN, leaveId);
		if (myLeave != null) {
			tv_leavetype.setText(myLeave.getmType());
			tv_entitlement.setText(myLeave.getmEntitlement());
			tv_from.setText(myLeave.getmFrom());
			tv_to.setText(myLeave.getmTo());
			tv_appliedfor.setText(myLeave.getmDays());
			tv_leavereason.setText(myLeave.getmReason());
			leaveEmpId = myLeave.getmReportedBy();

		} else {

		}
	}

	private void initUI() {
		// TODO Auto-generated method stub
		mNavigationbBar = (LinearLayout)findViewById(R.id.layout_header);
		txt_header = (TextView)mNavigationbBar.findViewById(R.id.tv_header);
		txt_header.setText("Leave Detail");		
		tv_leavetype = (TextView) findViewById(R.id.tv_leavetype);	
		tv_entitlement = (TextView) findViewById(R.id.tv_entitlement);		
		tv_from = (TextView) findViewById(R.id.tv_from);		
		tv_to = (TextView) findViewById(R.id.tv_to);		
		tv_appliedfor = (TextView) findViewById(R.id.tv_appliedfor);
		tv_leavereason = (TextView) findViewById(R.id.tv_leavereason);
		btn_approve = (Button) findViewById(R.id.btn_approve);
		btn_approve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                approveButtonClicked();
 }
  });
		btn_reject = (Button) findViewById(R.id.btn_reject);
		btn_reject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               rejectButtonClicked();
 }
  });
	}

	private void approveButtonClicked()
	{

		boolean success = dbManager.updateLeaveStatus(Constant.COL_LEAVE_STATUS, Constant.LEAVE_STATUS_APPROVED, leaveId);
		
		if (success) {
			String text = "Leave Approved.";
			Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
			toast.show();
			pushActivityClearTop(LeaveDetailsActivity.this, ManageLeaveActivity.class.getName(), true);

		} else {
			String text = "Leave could not be approved. Try Again.";
			Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	private void rejectButtonClicked()
	{
		boolean success = dbManager.updateLeaveStatus(Constant.COL_LEAVE_STATUS, Constant.LEAVE_STATUS_REJECTED, leaveId);
		
		if (success) {
			String leaveCountString;
			String columnString;
			if (myLeave.getmType().equalsIgnoreCase("casual")) {
				columnString = Constant.COL_STD_CASUALLEAVE;
			} 
			else {
				columnString = Constant.COL_STD_SICKLEAVE;
			}
			leaveCountString = dbManager.getstudentRecord(Constant.TABLE_STUDENT, columnString, Constant.COL_STD_ID, leaveEmpId);

			if (leaveCountString != null) {
				int leaveCount = Integer.parseInt(leaveCountString);
				int finalLeaveCount = leaveCount + Integer.parseInt(tv_appliedfor.getText().toString());
				
				boolean isLeaveAdded = dbManager.updatestudent(columnString, Integer.toString(finalLeaveCount), leaveEmpId);
				if (isLeaveAdded) {
					String text = "Leave Rejected.";
					Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
					toast.show();	
					pushActivityClearTop(LeaveDetailsActivity.this, ManageLeaveActivity.class.getName(), true);
				}
				else {
					String text = "Leave could not be Updated. Try Again";
					Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
					toast.show();
				}
			}

			//pushActivityClearTop(LeaveDetailsActivity.this, ManageLeaveActivity.class.getName(), true);

		} else {
			String text = "Leave could not be rejected. Try Again.";
			Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	

}
