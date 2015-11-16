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

public class MyAdapter extends CursorAdapter {

	Cursor myCursor;
	Context mContext;

	public MyAdapter(Context context, Cursor c) {
		super(context, c);
		myCursor = c;
		mContext = context;
		// TODO Auto-generated constructor stub
	}

	private LayoutInflater mInflater;

//	public View getView(int position, View convertView, ViewGroup parent) {
//		ViewHolder holder;
//		if (convertView == null) {
//			mInflater = (LayoutInflater) mContext
//					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			convertView = mInflater.inflate(R.layout.cell_main, null);
//			holder = new ViewHolder();
//			holder.emp_Name = (TextView) convertView
//					.findViewById(R.id.tv_empname);
//			holder.emp_Designation = (TextView) convertView
//					.findViewById(R.id.tv_empdesignation);
//
//			convertView.setTag(holder);
//		} else {
//			holder = (ViewHolder) convertView.getTag();
//		}
////		holder.emp_Name.setText(myCursor.getString(myCursor
////				.getColumnIndexOrThrow(DbHelper.COL_STD_NAME)));
////		holder.emp_Designation.setText(myCursor.getString(myCursor
////				.getColumnIndexOrThrow(DbHelper.COL_STD_DESIGNATION)));
//
//		return convertView;
//	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		ViewHolder holder = (ViewHolder) view.getTag();

		if (holder == null) {
			holder = new ViewHolder();
			holder.emp_Name = (TextView) view.findViewById(R.id.tv_empname);
			holder.emp_Designation = (TextView) view
					.findViewById(R.id.tv_empdesignation);
			view.setTag(holder);
		} 
		
		holder.emp_Name.setText(cursor.getString(cursor
				.getColumnIndexOrThrow(Constant.COL_STD_NAME)));
		holder.emp_Designation.setText(cursor.getString(cursor
				.getColumnIndexOrThrow(Constant.COL_STD_DESIGNATION)));
		holder.emp_Name.setTag(cursor.getString(cursor
				.getColumnIndexOrThrow(Constant.COL_STD_ID)));
//		view.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				ViewHolder holder = (ViewHolder) v.getTag();
//				Logger.printMessage("element",holder.emp_Name.getText().toString(),Logger.DEBUG);
//				v.getParent().getParent().getParent();
//			}
//		});
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub

		mInflater = (LayoutInflater) mContext
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = mInflater.inflate(R.layout.cell_main, null);
		bindView(view, context, cursor);
		return view;
	}

//	@Override
//	   public void bindView(View view, Context context, Cursor cursor) {
//	       super.bindView(view, context, cursor);
//
//	       ViewHolder holder = (ViewHolder) view.getTag();
//	       if (holder == null) {
//	           holder = new ViewHolder();
//	           holder.textView1 = (TextView) view.findViewById(R.id.text1);
//	           holder.textView2 = (TextView) view.findViewById(R.id.text2);
//	           holder.column1 = cursor.getColumnIndexOrThrow("column1");
//	           holder.column2 = cursor.getColumnIndexOrThrow("column2");
//	           view.setTag(holder);
//	       }
//
//	       holder.textView1.setText(cursor.getString(holder.column1));
//	       holder.textView2.setText(cursor.getString(holder.column2));
//	   }
	
	static class ViewHolder {
		TextView emp_Name;
		TextView emp_Designation;

	}
}
