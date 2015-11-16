package com.student.android.eismodel;

public class Constant {
	public static final String ID_COLUMN = "_id";

	public static final String TABLE_LOGIN = "login_details";
	public static final String COL_USERNAME = "userame";
	public static final String COL_PASSWORD = "password";

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
	
	public static final String TABLE_LEAVE = "leave_details";
	public static final String COL_LEAVE_REPORTEDBY = "leave_reportedby";
	public static final String COL_LEAVE_REPORTEDTO = "leave_reportedto";
	public static final String COL_LEAVE_TYPE = "leave_type";
	public static final String COL_LEAVE_ENTITLEMENT = "leave_entitlement";
	public static final String COL_LEAVE_FROM = "leave_from";
	public static final String COL_LEAVE_TO = "leave_to";
	public static final String COL_LEAVE_REASON = "leave_reason";
	public static final String COL_LEAVE_STATUS = "leave_status";
	public static final String COL_LEAVE_DAYS = "leave_days";
	
	public static final String COL_LEAVE_ALL = ID_COLUMN + "," + COL_LEAVE_REPORTEDBY + "," + COL_LEAVE_REPORTEDTO + "," + 
	COL_LEAVE_TYPE  + "," + COL_LEAVE_ENTITLEMENT + "," + COL_LEAVE_FROM  + "," + COL_LEAVE_TO
	 + "," + COL_LEAVE_REASON  + "," + COL_LEAVE_STATUS  + "," +  COL_LEAVE_DAYS;
	
	
	public static final int DIALOG_DATE_ID = 0;
	public static final int DIALOG_LEAVETYPE_ID = 1;
	public static final int DIALOG_LEAVEENTITLEMENT_ID = 2;

	public static final String LEAVE_STATUS_NEW = "pending";
	public static final String LEAVE_STATUS_APPROVED = "approved";
	public static final String LEAVE_STATUS_REJECTED = "rejected";

}
