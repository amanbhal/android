package com.student.android.eis;

import java.util.Calendar;

import android.R.bool;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.student.android.eislogger.Logger;
import com.student.android.eismodel.Constant;
import com.student.android.eismodel.Leave;
import com.student.android.eis.R;

public class ApplyLeaveActivity extends BaseActivity{
	private LinearLayout mNavigationbBar;
	private TextView txt_header;
	private TextView tv_casualleave;
	private TextView tv_sickleave;
	private TextView tv_leavetype;
	private TextView tv_entitlement;
	private TextView tv_from;
	private TextView tv_to;
	private TextView tv_appliedfor;
	private TextView tv_reportingmanager;
	private EditText et_leavereason;
	private Button btn_apply;
	private Button btn_cancel;
	
	private boolean isFrom = false;
	private String dateFrom;
	private String dateTo;
	private Calendar fromCalendar;
	private Calendar toCalendar;
	private String managerIdString;
	//private 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_leaves);
        initUI();
        fillData();
    }

	private void fillData() {
		// TODO Auto-generated method stub
		fromCalendar = Calendar.getInstance();
		toCalendar = Calendar.getInstance();
		tv_sickleave.setText(getIntent().getExtras().getString("sickleave"));
			tv_casualleave.setText(getIntent().getExtras().getString("casualleave"));
		
			managerIdString = dbManager.getstudentRecord(Constant.TABLE_STUDENT, Constant.COL_STD_MANAGER, Constant.COL_STD_ID, dataModel.mCurrentUser.getmId());
			String manager = null;
			if (managerIdString != null) {
				manager = dbManager.getstudentRecord(Constant.TABLE_STUDENT, Constant.COL_STD_NAME, Constant.COL_STD_ID, managerIdString);

			} else {
				manager = dbManager.getstudentManager(Constant.TABLE_STUDENT, Constant.COL_STD_MANAGER, Constant.COL_STD_ID, dataModel.mCurrentUser.getmId());
			}
			if (manager!=null) {
				tv_reportingmanager.setText(manager);
			}
			else {
				tv_reportingmanager.setText("Could not retrieve");
			}
			
	}

	private void initUI() {
		// TODO Auto-generated method stub
		mNavigationbBar = (LinearLayout)findViewById(R.id.layout_header);
		txt_header = (TextView)mNavigationbBar.findViewById(R.id.tv_header);
		txt_header.setText("Apply Leave");
		
		tv_casualleave = (TextView) findViewById(R.id.tv_casualleave);
		tv_sickleave = (TextView) findViewById(R.id.tv_sickleave);
		tv_leavetype = (TextView) findViewById(R.id.tv_leavetype);
		tv_leavetype.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	leavetypeClicked();
 }
  });
		tv_entitlement = (TextView) findViewById(R.id.tv_entitlement);
		tv_entitlement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	entitlementClicked();
 }
  });
		tv_from = (TextView) findViewById(R.id.tv_from);
		tv_from.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	fromClicked();
 }
  });
		tv_to = (TextView) findViewById(R.id.tv_to);
		tv_to.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toClicked();
 }
  });
		tv_appliedfor = (TextView) findViewById(R.id.tv_appliedfor);
		tv_reportingmanager = (TextView) findViewById(R.id.tv_reportingmanager);
		et_leavereason = (EditText) findViewById(R.id.et_leavereason);
		btn_apply = (Button) findViewById(R.id.btn_apply);
		btn_apply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                applyButtonClicked();
 }
  });
		btn_cancel = (Button) findViewById(R.id.btn_cancel);
		btn_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cancelButtonClicked();
 }
  });
	}
	private void leavetypeClicked()
	{
		showDialog(Constant.DIALOG_LEAVETYPE_ID);
	}
	
	private void entitlementClicked()
	{
		showDialog(Constant.DIALOG_LEAVEENTITLEMENT_ID);

	}
	
	private void fromClicked()
	{
		isFrom = true;
		showDialog(Constant.DIALOG_DATE_ID);
	}
	
	private void toClicked()
	{
		isFrom = false;
		showDialog(Constant.DIALOG_DATE_ID);
	}
	
	private void applyButtonClicked()
	{
		if (tv_leavetype.getText().toString().equalsIgnoreCase("Choose") || 
				tv_entitlement.getText().toString().equalsIgnoreCase("Choose") ||
				tv_to.getText().toString().equalsIgnoreCase("Choose") ||
				tv_from.getText().toString().equalsIgnoreCase("Choose") ||
				et_leavereason.getText().toString().equalsIgnoreCase("")) {
			 
			String text = "Fill All details";
			Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
			toast.show();
		} else {
			
			String leaveTypeString = tv_leavetype.getText().toString();
			int leaveLeftCount;
			if (leaveTypeString.equalsIgnoreCase("casual")) {
				leaveLeftCount = Integer.parseInt(tv_casualleave.getText().toString());
			}
			else {
				leaveLeftCount = Integer.parseInt(tv_sickleave.getText().toString());
			}
			int leaveAppliedCount = Integer.parseInt(tv_appliedfor.getText().toString());
			
			if (leaveAppliedCount>leaveLeftCount) {
				
				String text = "Leave count should be less than leaves applied";
				Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
				toast.show();
			}
			else {			
			Leave leave = new Leave();
			leave.setmType(tv_leavetype.getText().toString());
			leave.setmEntitlement(tv_entitlement.getText().toString());
			leave.setmReportedBy(dataModel.mCurrentUser.getmId());
			if (managerIdString == null) {
				managerIdString = dbManager.getstudentRecord(Constant.TABLE_STUDENT, Constant.COL_STD_MANAGER, Constant.COL_STD_ID, dataModel.mCurrentUser.getmId());
			}
			leave.setmReportedTo(managerIdString);

			leave.setmTo(tv_to.getText().toString());
			leave.setmFrom(tv_from.getText().toString());
			leave.setmReason(et_leavereason.getText().toString());
			leave.setmStatus(Constant.LEAVE_STATUS_NEW);
			leave.setmDays(tv_appliedfor.getText().toString());
			boolean success = dbManager.insertLeaveData(leave);
			if (success) {
				String leaveType = tv_leavetype.getText().toString();
				String columnString = null;
				String leavePending = null;
				if (leaveType.equalsIgnoreCase("casual")) {
					columnString = Constant.COL_STD_CASUALLEAVE;
					leavePending = tv_casualleave.getText().toString();
				} else {
					columnString = Constant.COL_STD_SICKLEAVE;
					leavePending = tv_sickleave.getText().toString();
				}
				int leaveCount = Integer.parseInt(leavePending);
				int finalLeaveCount = leaveCount - Integer.parseInt(tv_appliedfor.getText().toString());
				
				boolean isLeaveDeducted = dbManager.updatestudent(columnString, Integer.toString(finalLeaveCount), dataModel.mCurrentUser.getmId());
				if (isLeaveDeducted) {
					String text = "Leave applied successfully";
					Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
					toast.show();
					
					pushActivityClearTop(ApplyLeaveActivity.this, EveryoneActivity.class.getName(), true);
				}
				else {
					String text = "Leave could not be Updated. Try Again";
					Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
					toast.show();
				}

			} else {
				String text = "Leave could not be aplied. Try Again";
				Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
				toast.show();
			}
		}
		}
	}
	
	private void cancelButtonClicked()
	{
		finish();
	}
	
	@Override
	protected Dialog onCreateDialog(final int id) {
	  switch (id) {
	  case Constant.DIALOG_DATE_ID:
	  {
		  final Calendar c = Calendar.getInstance();
	    return new DatePickerDialog(this,d, c.get(Calendar.YEAR),
	                                c.get(Calendar.MONTH), 
	                                c.get(Calendar.DAY_OF_MONTH));
	  }
	  case Constant.DIALOG_LEAVETYPE_ID:
	  {
		  final Dialog dialog = new Dialog(this);
          dialog.setContentView(R.layout.leave_type);
          dialog.setTitle("Leave Type");
          dialog.setCancelable(true);
          final RadioGroup rGroup = (RadioGroup) dialog.findViewById(R.id.rg_leave);
          //rGroup.setOnCheckedChangeListener(changeListener);
          Button selectButton = (Button) dialog.findViewById(R.id.btn_select);
          selectButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int id = rGroup.getCheckedRadioButtonId();
				Logger.printMessage("Button Clicked", Integer.toString(id), Logger.DEBUG);

				switch (id) {
				case R.id.radio_casual:
					tv_leavetype.setText("Casual");
					break;
				case R.id.radio_sick:
					tv_leavetype.setText("Sick");

					break;

				default:
					break;
				}
				dialog.dismiss();
			}
		});
          return dialog;
	  }
	  case Constant.DIALOG_LEAVEENTITLEMENT_ID:
	  { 
		  final Dialog dialog = new Dialog(this);
          dialog.setContentView(R.layout.leave_entitlement);
          dialog.setTitle("Entitlement");
          dialog.setCancelable(true);
          final RadioGroup rGroup = (RadioGroup) dialog.findViewById(R.id.rg_entitlement);
          //rGroup.setOnCheckedChangeListener(changeListener);
          Button selectButton = (Button) dialog.findViewById(R.id.btn_select);
          selectButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int id = rGroup.getCheckedRadioButtonId();
				Logger.printMessage("Button Clicked", Integer.toString(id), Logger.DEBUG);

				switch (id) {
				case R.id.radio_fullday:
					tv_entitlement.setText("Full Day");
					break;
				case R.id.radio_halfday:
					tv_entitlement.setText("Half Day");

					break;

				default:
					break;
				}
				dialog.dismiss();
			}
		});
          return dialog;
	  }
	  default:
	    return super.onCreateDialog(id);
	  }
	}

	@Override
	protected void onPrepareDialog(final int id, final Dialog dialog) {
	  switch (id) {
	  case Constant.DIALOG_DATE_ID:
	  { //update to current time
	    final Calendar c = Calendar.getInstance();
	    ((DatePickerDialog) dialog).updateDate(c.get(Calendar.YEAR), 
	                                           c.get(Calendar.MONTH), 
	                                           c.get(Calendar.DAY_OF_MONTH));
	  }
	  break;
	  }
	}


	
	DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
		  public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			  

			  if (isFrom) {
				StringBuilder tDateFrom = new StringBuilder().append(dayOfMonth).append("-").append(monthOfYear+1).append("-")
		          .append(year).append(" ");
				fromCalendar.set(Calendar.MONTH, monthOfYear);
				fromCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				fromCalendar.set(Calendar.YEAR, year);
				
				dateFrom = tDateFrom.toString();
				tv_from.setText(dateFrom);
			} else {
				StringBuilder tDateTo = new StringBuilder().append(dayOfMonth).append("-").append(monthOfYear+1).append("-")
		          .append(year).append(" ");
				toCalendar.set(Calendar.MONTH, monthOfYear);
				toCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				toCalendar.set(Calendar.YEAR, year);
				
				dateTo = tDateTo.toString();
				tv_to.setText(dateTo);
			}
		  
		  if(!tv_from.getText().toString().equalsIgnoreCase("Choose") && 
				  !tv_to.getText().toString().equalsIgnoreCase("Choose"))
		  {
			  long days = daysBetween(fromCalendar, toCalendar) + 1;
			  tv_appliedfor.setText(Long.toString(days));
			  
		  }
		  }
		 };
	
		 public static long daysBetween(Calendar startDate, Calendar endDate) {
			  Calendar date = (Calendar) startDate.clone();
			  long daysBetween = 0;
			  while (date.before(endDate)) {
			    if (date.get(Calendar.DAY_OF_WEEK) != 6 && date.get(Calendar.DAY_OF_WEEK) != 7) {
				    daysBetween++;
				}
			  	  date.add(Calendar.DAY_OF_MONTH, 1);

			  }
			  return daysBetween;
			}
	
}
