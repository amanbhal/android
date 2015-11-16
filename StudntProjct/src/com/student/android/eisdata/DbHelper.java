package com.student.android.eisdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper {

	private static final String DATABASE_NAME = "WirkleEIS.db";
	//private static final String CLASS_SIMPLE_NAME = DbHelper.class.getSimpleName();

	public static final String TABLE_LOGIN = "login_details";
	public static final String COL_USERNAME = "userame";
	public static final String COL_PASSWORD = "password";
	public static final String ID_COLUMN = "_id";

	private static final String DATABASE_CREATE_TABLE_AUTOLOGIN_DATA = "create table "
		+ TABLE_LOGIN
		+ " ( "
		+ ID_COLUMN	+ " integer PRIMARY KEY,"
		+ COL_USERNAME
		+ " text not null,"
		+ COL_PASSWORD
		+ " text not null);";
	
	public static final String TABLE_STUDENT = "student_details";
	public static final String COL_STD_ID = "student_id";
	public static final String COL_STD_NAME = "student_name";
	public static final String COL_STD_DESIGNATION = "student_designation";
	public static final String COL_STD_MANAGER = "student_manager";
	public static final String COL_STD_DEPARTMENT = "student_department";
	public static final String COL_STD_JOININGDATE = "student_joiningdate";
	public static final String COL_STD_CLUB = "student_club";
	public static final String COL_STD_EMAIL = "student_email";
	public static final String COL_STD_PHONE = "student_phone";
	public static final String COL_STD_KEYSKILLS = "student_keyskills";
	public static final String COL_STD_CURRENTPROJECT = "student_currentproject";
	public static final String COL_STD_PASTPROJECT = "student_pastproject";
	public static final String COL_STD_CASUALLEAVE = "student_casualleave";
	public static final String COL_STD_SICKLEAVE = "student_sickleave";

	public static final String COL_STD_ALL = ID_COLUMN + "," + COL_STD_ID + "," + COL_STD_NAME + "," + 
	COL_STD_DESIGNATION  + "," + COL_STD_MANAGER + "," + COL_STD_DEPARTMENT  + "," + COL_STD_JOININGDATE
	 + "," + COL_STD_CLUB  + "," + COL_STD_EMAIL  + "," + COL_STD_PHONE  + "," + COL_STD_KEYSKILLS  + "," + 
	COL_STD_CURRENTPROJECT + "," +  COL_STD_PASTPROJECT + "," +  COL_STD_CASUALLEAVE +
	"," +  COL_STD_SICKLEAVE;

	
	private static final String DATABASE_CREATE_TABLE_STUDENT_DATA = "create table "
		+ TABLE_STUDENT + " ( " 
		+ ID_COLUMN	+ " integer PRIMARY KEY,"
		+ COL_STD_ID	+ " text ,"
		+ COL_STD_NAME + " text not null,"
		+ COL_STD_DESIGNATION + " text not null," 
		+ COL_STD_MANAGER + " text not null,"
		+ COL_STD_DEPARTMENT + " text not null,"
		+ COL_STD_JOININGDATE + " text not null,"
		+ COL_STD_CLUB + " text,"
		+ COL_STD_EMAIL + " text not null,"
		+ COL_STD_PHONE + " text not null,"
		+ COL_STD_KEYSKILLS + " text not null,"
		+ COL_STD_CURRENTPROJECT + " text not null,"
		+ COL_STD_PASTPROJECT + " text not null,"
		+ COL_STD_CASUALLEAVE + " text not null,"
		+ COL_STD_SICKLEAVE	+ " text not null);";

	public static final String TABLE_LEAVE = "leave_details";
	public static final String COL_LEAVE_ID = "leave_id";
	public static final String COL_LEAVE_REPORTEDBY = "leave_reportedby";
	public static final String COL_LEAVE_REPORTEDTO = "leave_reportedto";
	public static final String COL_LEAVE_TYPE = "leave_type";
	public static final String COL_LEAVE_ENTITLEMENT = "leave_entitlement";
	public static final String COL_LEAVE_FROM = "leave_from";
	public static final String COL_LEAVE_TO = "leave_to";
	public static final String COL_LEAVE_REASON = "leave_reason";
	public static final String COL_LEAVE_STATUS = "leave_status";
	public static final String COL_LEAVE_DAYS = "leave_days";

	private static final String DATABASE_CREATE_TABLE_LEAVE_DATA = "create table "
		+ TABLE_LEAVE + " ( " 
		+ ID_COLUMN	+ " integer PRIMARY KEY,"
		+ COL_LEAVE_REPORTEDBY + " text not null,"
		+ COL_LEAVE_REPORTEDTO + " text not null," 
		+ COL_LEAVE_TYPE + " text not null,"
		+ COL_LEAVE_ENTITLEMENT + " text not null,"
		+ COL_LEAVE_FROM + " text not null,"
		+ COL_LEAVE_TO + " text not null,"
		+ COL_LEAVE_REASON + " text not null,"
		+ COL_LEAVE_DAYS + " text not null,"
		+ COL_LEAVE_STATUS	+ " text not null);";

	private SQLiteDatabase myDatabase;

	/**
	 * Singleton object for the DbHelper class
	 */
	private final myDbhelper helper;
	
	/**
	 * Constructor
	 * @param context current application context
	 */
	public DbHelper(Context context)
	{
		helper = new myDbhelper(context, DATABASE_NAME, null, 1);
	}
	
	
	/**
	 * This method is used to open the database in Writable mode
	 * 
	 * @return instance of the database
	 */
	public DbHelper open()
	{
		try
		{
			myDatabase = helper.getWritableDatabase();
		}
		catch (SQLException ex)
		{
//			Logger.printMessage(CLASS_SIMPLE_NAME,
//					"::Writable database could not be created::" + ex.toString(),
//					Logger.DEBUG);
			myDatabase = helper.getReadableDatabase();
		}
		return this;
	}

	/**
	 * This method is used for closing the database
	 */
	public void close()
	{
		myDatabase.close();
	}

	/**
	 * This method is used for inserting values in the database table
	 * 
	 * @param values
	 *             contains values to be inserted in the table
	 * @param tableName name of the table in which row is to be updated
	 * @return the row ID of the newly inserted row, or -1 if an error occurred 
	 */

	public long insertRows(ContentValues values, String tableName)
	{
		long val = myDatabase.insert(tableName, null, values);
		return val;
	}

	/**
	 * This method is used for deleting values in the database table
	 * @param tableName name of the table from which contents need to be removed
	 * 
	 * @return the number of rows affected if a whereClause is passed in,
	 *  0 otherwise. To remove all rows and get a count pass "1" as the 
	 *  whereClause. 
	 */
	public int emptyTable(String tableName)
	{
		return myDatabase.delete(tableName, null, null);
	}

	/**
	 * This method is used for deleting values in the database table
	 * @param tableName Name of the table which needs to be removed
	 *
	 */
	public void removeTable(String tableName)
	{
		myDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
	}

	/**
	 * This method is used to update values in the database table
	 * 
	 * @param values
	 *             contains values to be updated
	 * @param where
	 *             contains condition for updating
	 * @param tableName name of the which needs to be updated
	 * @return number of rows updated
	 */
	public int update(ContentValues values, String where, String tableName, String[] whereValue)
	{
		int count = myDatabase.update(tableName, values, where, whereValue);
		return count;
	}

	/**
	 * This method is used to get the values from the database Table
	 * @param tableName name of table from which data is required
	 * 
	 * @return the cursor on the result of the query
	 */
	public Cursor getAllValues(String tableName)
	{
		Cursor myResult;
		myResult = myDatabase.query(tableName, null, null, null, null, null,
				null);

		return myResult;
	}
	
	public Cursor getRow(String tableName,String requiredColumn,String columnName,String[] value)
	{
		Cursor myResult;
		myResult = myDatabase.query(tableName, new String[]{requiredColumn}, columnName+"=?",value, null,
				null, null);
		return myResult;
	}
	
	public Cursor searchRow(String tableName,String requiredColumn,String columnName,String[] value)
	{
		Cursor myResult;
//		myResult = myDatabase.query(tableName, new String[]{requiredColumn}, columnName+" IN (?)",value, null,
//				null, null);
		myResult = myDatabase.rawQuery("SELECT "+ requiredColumn + " FROM "+tableName+"" +
				" WHERE "+ columnName +" IN (" + value[0] +")" , null);
		return myResult;
	} 
	
	public Cursor getNewLeaveRow(String tableName,String[] value)
	{
		Cursor myCursor = myDatabase.rawQuery("SELECT * FROM "+tableName+"" +
				" WHERE "+ COL_LEAVE_REPORTEDTO+" = ? and "+ COL_LEAVE_STATUS +" = ?;" , value);
		return myCursor;
	}
	
	public Cursor getLeaveRow(String tableName,String[] value)
	{
		Cursor myCursor = myDatabase.rawQuery("SELECT * FROM "+tableName+"" +
				" WHERE "+ COL_LEAVE_REPORTEDBY+" = ?;" , value);
		return myCursor;
	}
	
	public Cursor verifyCredentials(String tablename, String[] credentials )
	{
		Cursor myCursor = myDatabase.rawQuery("SELECT * FROM "+tablename+"" +
				" WHERE "+ COL_USERNAME+" = ? and "+ COL_PASSWORD +" = ?;" , credentials);
		return myCursor;
	}
	

	
	
	// Inner class
	private static class myDbhelper extends SQLiteOpenHelper
	{
		public myDbhelper(Context context, String name, CursorFactory factory,
				int version)
		{
			super(context, name, factory, version);
		}

		/**
		 * this method is called when the database is created first time
		 */
		@Override
		public void onCreate(SQLiteDatabase _db)
		{

			_db.execSQL(DATABASE_CREATE_TABLE_AUTOLOGIN_DATA);
			_db.execSQL(DATABASE_CREATE_TABLE_STUDENT_DATA);
			_db.execSQL(DATABASE_CREATE_TABLE_LEAVE_DATA);

			//_db.execSQL(DATABASE_CREATE_TABLE_student_DATA);
			//_db.execSQL(DATABASE_CREATE_TABLE_LEAVE_DATA);
		}

		/**
		 * this method is called when the database version is changed
		 */
		@Override
		public void onUpgrade(SQLiteDatabase _db, int _oldVersion,
				int _newVersion)
				{
/*
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CREATE_TABLE_AUTOLOGIN_DATA);
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CREATE_TABLE_student_DATA);
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CREATE_TABLE_LEAVE_DATA);
			onCreate(_db);
*/
		}
	}
	
}
