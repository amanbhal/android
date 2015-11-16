package com.student.android.eisdata;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.student.android.eismodel.Constant;
import com.student.android.eismodel.DataModel;
import com.student.android.eismodel.Student;
import com.student.android.eismodel.Leave;
import com.student.android.eismodel.User;

public class DbManager {

	/**
	 * Singleton object of DbManager
	 */
	private static DbManager dbManager = null;
	private final DbHelper db;
	private final DataModel dataModel = DataModel.getInstance();
	
	/**
	 * Method to create the instance for the {@link DbManager} class. This
	 * method should be called before the getInstance() method
	 * 
	 * @param context Application context
	 */
	public static void createInstance(Context context)
	{
		dbManager = new DbManager(context);
	}
	
	
	/**
	 * Method to get the singleton object of the {@link DbManager} class
	 * 
	 * @return object of the {@link DbManager} class
	 * @throws IllegalStateException
	 *             if this method is called before calling the createInstance()
	 *             method.
	 */
	public static DbManager getInstance()
	{

		if (dbManager != null)
		{
			return dbManager;
		}
		else
		{
			throw new IllegalStateException();
		}
	}

	private DbManager(Context context)
	{
		db = new DbHelper(context);
	}

	public void openDatabase()
	{
		db.open();
	}
	public void closeDatabase()
	{
		db.close();
		
	}
	
	/**
	 * Checks whether database tables has been created
	 * @param tablename name of the table to check if created
	 * @return Returns true if opened else returns false
	 */
	public boolean checkIfDataBasePresent(String tablename)
	{
		int count = 0;
		db.open();

		try
		{
			Cursor cursor = db.getAllValues(tablename);
			count = cursor.getCount();
			cursor.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
		db.close();

		if (count != 0)
			return true;
		else
			return false;
	}

	/**
	 * Method to insert login details in database
	 * 
	 * @return Returns true if success else return false
	 * 
	 */
	public boolean insertLoginDetails()
	{
		db.open();
		//db.emptyTable(DbHelper.TABLE_LOGIN);
		User user = (User) dataModel.getObject("CURRENT_USER");
		ContentValues cv = new ContentValues();
		cv.put(Constant.COL_USERNAME, user.getmId());
		cv.put(Constant.COL_PASSWORD, user.getPassword());
		//cv.put(db.COL_IS_AUTO_LOGIN, user.isAutoLogin() ? 1 : 0);

		long value = db.insertRows(cv, Constant.TABLE_LOGIN);
		db.close();

		if (value == -1)
			return false;
		else
			return true;
	}

	/**
	 * Method to retrieve login details from database
	 * @return Instance of User object
	 */
	public User getLoginDetails()
	{
		User user = new User();
		db.open();
		Cursor cursor = db.getAllValues(Constant.TABLE_LOGIN);
		try
		{
			cursor.moveToFirst();
			user.setmId(cursor.getString(0));
			user.setPassword(cursor.getString(1));
			//user.setAutoLogin((cursor.getInt(2) == 1) ? true : false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		db.close();
		dataModel.storeObject("CURRENT_USER", user);
		return user;
	}

	
	public boolean insertRowLoginTable(String id , String password)
	{
		db.open();
		//db.emptyTable(DbHelper.TABLE_LOGIN);
		
		ContentValues cv = new ContentValues();
		cv.put(Constant.COL_USERNAME, id);
		cv.put(Constant.COL_PASSWORD, password);
		//cv.put(db.COL_IS_AUTO_LOGIN, user.isAutoLogin() ? 1 : 0);

		long value = db.insertRows(cv, Constant.TABLE_LOGIN);
		db.close();

		if (value == -1)
			return false;
		else
			return true;
	}
	
	
	public boolean verifyUser(String username , String password)
	{
		db.open();
		String credentials[] = new String[2];
		credentials[0] = username;
		credentials[1] = password;
		Cursor myResult=null;
		int count=0;
		try
		{
		myResult = db.verifyCredentials(Constant.TABLE_LOGIN, credentials);
		count = myResult.getCount();
		
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			db.close();
			myResult.close();
		}
		if (count>0) {
			DataModel datamodel = DataModel.getInstance();
			//Logger.printMessage("MYRESULT", myResult.toString(), Logger.DEBUG);
			datamodel.mCurrentUser.setmId(username);
			datamodel.mCurrentUser.setPassword(password);

			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public boolean insertstudentData(ArrayList<Student> arrayList)
	{
		db.open();
		long value = 0;
		try
		{
		for (Student student : arrayList) {
			ContentValues cv = new ContentValues();
			cv.put(Constant.COL_STD_ID, student.getmId());
			cv.put(Constant.COL_STD_NAME, student.getmName());
			cv.put(Constant.COL_STD_DESIGNATION, student.getmDesignation());
			cv.put(Constant.COL_STD_MANAGER, student.getmMentor());
			cv.put(Constant.COL_STD_DEPARTMENT, student.getmDepartment());
			cv.put(Constant.COL_STD_JOININGDATE, student.getmBirthdate());
			cv.put(Constant.COL_STD_CLUB, student.getmClub());
			cv.put(Constant.COL_STD_EMAIL, student.getmEmail());
			cv.put(Constant.COL_STD_PHONE, student.getmPhone());
			cv.put(Constant.COL_STD_KEYSKILLS, student.getmKeySkills());
			cv.put(Constant.COL_STD_CURRENTPROJECT, student.getmCurrentProject());
			cv.put(Constant.COL_STD_PASTPROJECT, student.getmPastProject());
			cv.put(Constant.COL_STD_CASUALLEAVE, student.getmCasualLeave());
			cv.put(Constant.COL_STD_SICKLEAVE, student.getmSickLeave());

			value = db.insertRows(cv, Constant.TABLE_STUDENT);
					
		}
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			db.close();
		}
		
		if (value == -1) {
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public Cursor getAllstudents()
	{
		Cursor myCursor=null;
		db.open();
		try{
			myCursor = db.getAllValues(Constant.TABLE_STUDENT);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return myCursor;
		
	}

	public Cursor getAllstudentsRecord(String tableName, String requiredColumn , String columnName , String pValue)
	{
		Cursor myCursor=null;
		db.open();
		String[] value = new String[1];
		value[0] = pValue;
		try{
			myCursor =  db.getRow(tableName, requiredColumn, columnName, value);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return myCursor;
		
	}

	public Cursor searchstudentsRecord(String tableName, String requiredColumn , String columnName , String pValue)
	{
		Cursor myCursor=null;
		db.open();
		String[] value = new String[1];
		value[0] = pValue;
		try{
			myCursor =  db.searchRow(tableName, requiredColumn, columnName, value);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return myCursor;
		
	}
	
	public Student getstudentDetails(String tableName, String requiredColumn , String columnName , String pValue)
	{
		Cursor myCursor=null;
		Student mystudent = null;
		db.open();
		
		String[] value = new String[1];
		value[0] = pValue;
		try{
			myCursor =  db.getRow(tableName, requiredColumn, columnName, value);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			//db.close();
		}
		if (myCursor.getCount()>0) {
			myCursor.moveToFirst();
			mystudent = new Student();
			mystudent.setmId(myCursor.getString(1));
			mystudent.setmName(myCursor.getString(2));
			mystudent.setmDesignation(myCursor.getString(3));
			mystudent.setmDepartment(myCursor.getString(5));
			mystudent.setmBirthdate(myCursor.getString(6));
			mystudent.setmClub(myCursor.getString(7));
			mystudent.setmEmail(myCursor.getString(8));
			mystudent.setmPhone(myCursor.getString(9));
			mystudent.setmKeySkills(myCursor.getString(10));
			mystudent.setmCurrentProject(myCursor.getString(11));
			mystudent.setmPastProject(myCursor.getString(12));
			mystudent.setmCasualLeave(myCursor.getString(13));
			mystudent.setmSickLeave(myCursor.getString(14));

			String managerNameString = getstudentRecord(tableName, Constant.COL_STD_NAME, columnName, myCursor.getString(4));
			if (managerNameString != null) {
				mystudent.setmMentor(managerNameString);

			} else {
				mystudent.setmMentor("BOSS");

			}
		}
		db.close();
		myCursor.close();
		return mystudent;
		
	}
	
	public String getstudentRecord(String tableName, String requiredColumn , String columnName , String pValue) {
		db.open();
		String[] value = new String[1];
		value[0] = pValue;
		int count = 0;
		String retString = null;
		Cursor myCursor = null;
		try {
			myCursor = db.getRow(tableName, requiredColumn, columnName, value);
			count = myCursor.getCount();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		finally
		{
			db.close();		
		}
		if (count>0) {
			myCursor.moveToFirst();
			retString = myCursor.getString(0);
		}
		myCursor.close();
		return retString;
	}
	
	public String getstudentManager(String tableName, String requiredColumn , String columnName , String pValue) {
		db.open();
		String[] value = new String[1];
		value[0] = pValue;
		int count = 0;
		String retString = null;
		Cursor myCursor = null;
		try {
			myCursor = db.getRow(tableName, requiredColumn, columnName, value);
			count = myCursor.getCount();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		if (count>0) {
			myCursor.moveToFirst();
			String name = myCursor.getString(0);
			retString = getstudentRecord(tableName, Constant.COL_STD_NAME, columnName, name);

		}
		myCursor.close();
		db.close();
		return retString;
	}
	
	public boolean insertLeaveData(Leave leave)
	{
		db.open();
		long value = 0;
		try
		{
			ContentValues cv = new ContentValues();
			cv.put(Constant.COL_LEAVE_ENTITLEMENT, leave.getmEntitlement());
			cv.put(Constant.COL_LEAVE_FROM, leave.getmFrom());
			cv.put(Constant.COL_LEAVE_REASON, leave.getmReason());
			cv.put(Constant.COL_LEAVE_REPORTEDBY, leave.getmReportedBy());
			cv.put(Constant.COL_LEAVE_REPORTEDTO, leave.getmReportedTo());
			cv.put(Constant.COL_LEAVE_STATUS, leave.getmStatus());
			cv.put(Constant.COL_LEAVE_TO, leave.getmTo());
			cv.put(Constant.COL_LEAVE_TYPE, leave.getmType());
			cv.put(Constant.COL_LEAVE_DAYS, leave.getmDays());

			value = db.insertRows(cv, Constant.TABLE_LEAVE);
					
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	finally
	{
		db.close();
		
	}
		if (value == -1) {
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public Boolean updatestudent(String updateColumn , String value , String empId) {
		db.open();
		int count = 0;
		try {
			ContentValues cv = new ContentValues();
			cv.put(updateColumn, value);
			String whereValue[] = new String[1];
			whereValue[0] = empId;
			String where = Constant.COL_STD_ID + "=?";
			count = db.update(cv, where, Constant.TABLE_STUDENT,whereValue);
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			db.close();
		}
		if (count>0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Cursor getPendingLeaves(String empId)
	{
		Cursor myCursor=null;
		db.open();
		String value[] = new String[2];
		value[0] = empId;
		value[1] = Constant.LEAVE_STATUS_NEW;
		try{
			myCursor = db.getNewLeaveRow(Constant.TABLE_LEAVE, value);
			//count = myCursor.getCount();
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return myCursor;
	}
	
	public Cursor getAllLeaves(String empId)
	{
		Cursor myCursor=null;
		db.open();
		String value[] = new String[1];
		value[0] = empId;
		try{
			myCursor = db.getLeaveRow(Constant.TABLE_LEAVE, value);
			//count = myCursor.getCount();
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return myCursor;
	}
	
	
	public Leave getLeaveDetails(String tableName, String requiredColumn , String columnName , String pValue)
	{
		
		db.open();
		Cursor myCursor=null;
		Leave myLeave = null;
		String[] value = new String[1];
		value[0] = pValue;
		try{
			myCursor =  db.getRow(tableName, requiredColumn, columnName, value);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			//db.close();
		}
		if (myCursor.getCount()>0) {
			myCursor.moveToFirst();
			myLeave = new Leave();
			myLeave.setmDays(myCursor.getString(9));
			myLeave.setmEntitlement(myCursor.getString(4));
			myLeave.setmFrom(myCursor.getString(5));
			myLeave.setmReason(myCursor.getString(7));
			myLeave.setmTo(myCursor.getString(6));
			myLeave.setmType(myCursor.getString(3));
			myLeave.setmReportedBy(myCursor.getString(1));
		}
		myCursor.close();
		return myLeave;
		
	}
	
	public Boolean updateLeaveStatus(String updateColumn , String value , String leaveId) {
		db.open();
		int count = 0;
		try {
			ContentValues cv = new ContentValues();
			cv.put(updateColumn, value);
			String whereValue[] = new String[1];
			whereValue[0] = leaveId;
			String where = Constant.ID_COLUMN + "=?";
			count = db.update(cv, where, Constant.TABLE_LEAVE,whereValue);
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			db.close();
		}
		if (count>0) {
			return true;
		} else {
			return false;
		}
	}
}
