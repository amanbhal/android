package com.student.android.eis;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.student.android.adapter.LeaveAdapter;
import com.student.android.adapter.LeaveHistoryAdapter;
import com.student.android.eis.R;

public class PendingLeavesActivity extends BaseActivity{
	private LinearLayout mNavigationbBar;
	private TextView txt_header;
    private ListView list_leaves;
    private Cursor myCursor;
    String dataContentString = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_leaves);
        
        initUI();
        dataContentString = getIntent().getExtras().getString("dataString");
        if (dataContentString.equalsIgnoreCase("pending")) {
            getPendingLeaves();

		} else if (dataContentString.equalsIgnoreCase("history")){
			getLeavesHistory();
		}
    }

	private void getPendingLeaves() {
		// TODO Auto-generated method stub
		myCursor = dbManager.getPendingLeaves(dataModel.mCurrentUser.getmId());
		if(myCursor.getCount()>0){
			list_leaves.setAdapter(new LeaveAdapter(this, myCursor));
			list_leaves.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView tv_name = (TextView) arg1.findViewById(R.id.tv_empname);
				//Logger.printMessage("Sent I", tv_name.getText().toString(), Logger.DEBUG);
				String leaveId = tv_name.getTag().toString();
				openLeaveDetail(leaveId);

			}
		});
		}
	}

	private void getLeavesHistory() {	
		myCursor = dbManager.getAllLeaves(dataModel.mCurrentUser.getmId());
		if(myCursor.getCount()>0){
			list_leaves.setAdapter(new LeaveHistoryAdapter(this, myCursor));
			list_leaves.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView tv_name = (TextView) arg1.findViewById(R.id.tv_empname);
				//Logger.printMessage("Sent I", tv_name.getText().toString(), Logger.DEBUG);
				String leaveId = tv_name.getTag().toString();
				openLeaveDetail(leaveId);

			}
		});
		}	
	}
	private void initUI() {
		// TODO Auto-generated method stub
		mNavigationbBar = (LinearLayout)findViewById(R.id.layout_header);
		txt_header = (TextView)mNavigationbBar.findViewById(R.id.tv_header);
		txt_header.setText("Pending Leaves");
		list_leaves = (ListView) findViewById(R.id.list_leaves);
		
	}
	
	private void openLeaveDetail(String leaveId)
	{

		Bundle mBundle = new Bundle();
		mBundle.putString("leaveId", leaveId);
		mBundle.putString("leaveType", dataContentString);
		//myCursor.close();
		//dbManager.closeDatabase();
		pushActivity(PendingLeavesActivity.this, LeaveDetailsActivity.class.getName(), mBundle);
		
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		myCursor.close();
		super.onDestroy();
	}
	
}
