package com.student.android.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.student.android.eismodel.Constant;
import com.student.android.eis.R;

public class LeaveHistoryAdapter extends CursorAdapter {

	Cursor myCursor;
	Context mContext;

	public LeaveHistoryAdapter(Context context, Cursor c) {
		super(context, c);
		myCursor = c;
		mContext = context;
		// TODO Auto-generated constructor stub
	}

	private LayoutInflater mInflater;


	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		ViewHolder holder = (ViewHolder) view.getTag();

		if (holder == null) {
			holder = new ViewHolder();
			holder.emp_Name = (TextView) view.findViewById(R.id.tv_empname);
			holder.leave_days = (TextView) view
					.findViewById(R.id.tv_leavedays);
			view.setTag(holder);
		} 
		
		holder.emp_Name.setText(cursor.getString(cursor
				.getColumnIndexOrThrow(Constant.COL_LEAVE_FROM)));
		holder.leave_days.setText(cursor.getString(cursor
				.getColumnIndexOrThrow(Constant.COL_LEAVE_STATUS)));
		holder.emp_Name.setTag(cursor.getString(cursor
				.getColumnIndexOrThrow(Constant.ID_COLUMN)));

	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub

		mInflater = (LayoutInflater) mContext
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = mInflater.inflate(R.layout.cell_leave, null);
		bindView(view, context, cursor);
		return view;
	}


	static class ViewHolder {
		TextView emp_Name;
		TextView leave_days;

	}
}
