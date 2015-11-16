package com.student.android.eisrequest;

import android.content.Context;

import com.student.android.eisdata.DbManager;
import com.student.android.eismodel.DataModel;
import com.student.android.eismodel.DeviceInformation;

public class RequestProcessor {
	private static RequestProcessor instance;

	private final DbManager dbManager;
	private final DataModel dm = DataModel.getInstance();
	private final Context appContext;

	private final DeviceInformation deviceInfo;
	private static final Object mutex = new Object();

	private RequestProcessor(Context context) {

		DbManager.createInstance(context.getApplicationContext());
		dbManager = DbManager.getInstance();
		appContext = context.getApplicationContext();

		deviceInfo = new DeviceInformation();

	}

	/**
	 * Factory method to get singletone object of the class
	 * 
	 * @param context
	 *            Application Context
	 */
	public static void createInstance(Context context) {
		if (instance != null) {
			return;
		}
		synchronized (mutex) {
			if (instance == null) {
				instance = new RequestProcessor(context);
			}
		}
	}

	public static RequestProcessor getInstance() {
		return instance;
	}

}
