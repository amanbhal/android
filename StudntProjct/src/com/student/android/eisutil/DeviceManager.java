package com.student.android.eisutil;

import java.lang.reflect.Field;

import android.telephony.TelephonyManager;

public class DeviceManager {

	private static final String MANUFACTURER = "MANUFACTURER";
	private static final String MODEL = "MODEL";
	private static final String UNAVAILABLE = "(unavailable)";
	private static final String ERROR = "error";
	public static TelephonyManager teleManager;

	public DeviceManager()
	{

	}

	/**Returns the Device Id of the device
	 * @return device id
	 */
	public String getDeviceID()
	{

		return teleManager.getDeviceId();
	}

	/**
	 * @return the type of device
	 */
	public String getDevice()
	{
		return "Android";
	}

	/** Returns the manufacturer name of the device;
	 * @return name of manufacturer
	 */
	public String getDeviceManuf()
	{
		String manufacturer = null;
		try
		{
			Class<android.os.Build> buildClass = android.os.Build.class;
			Field field = buildClass.getField(MANUFACTURER);
			manufacturer = (String) field.get(new android.os.Build());
		}
		catch (NoSuchFieldException e)
		{
			manufacturer = UNAVAILABLE;
		}
		catch (Exception e)
		{
			manufacturer = ERROR;
		}
		return manufacturer;
	}

	/** Returns the detail of the device model
	 * @return
	 */
	public String getDeviceModel()
	{
		String model = null;
		try
		{
			Class<android.os.Build> buildClass = android.os.Build.class;
			Field field = buildClass.getField(MODEL);
			model = (String) field.get(new android.os.Build());
		}
		catch (NoSuchFieldException e)
		{
			model = UNAVAILABLE;
		}
		catch (Exception e)
		{
			model = ERROR;
		}
		return model;
	}

	/**
	 * @return version of device OS
	 */
	public String getDeviceOSVersion()
	{
		return teleManager.getDeviceSoftwareVersion();
	}

}
