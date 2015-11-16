package com.student.android.eismodel;

import com.student.android.eisutil.DeviceManager;

/**
 * This class holds the various device related data to be used in server 
 * requests. It holds the Device uniquie Id, Name of manufacturer, 
 * device version, and model name of the device
 * @author kshitij
 *
 */
public class DeviceInformation
{
	private String deviceID;
	private String device;
	private String deviceManuf;
	private String deviceModel;
	private String deviceVersion;

	public DeviceInformation()
	{
		populateDeviceInfo();
	}

	/**
	 * This method fetches various details about the device and stores them
	 */
	private void populateDeviceInfo()
	{
		DeviceManager manager = new DeviceManager();
		this.device = manager.getDevice();
		this.deviceID = manager.getDeviceID();
		this.deviceManuf = manager.getDeviceManuf();
		this.deviceModel = manager.getDeviceModel();
		this.deviceVersion = manager.getDeviceOSVersion();
	}

	/**
	 * @return the deviceID
	 */
	public String getDeviceID()
	{
		return deviceID;
	}

	/**
	 * @return the device
	 */
	public String getDevice()
	{
		return device;
	}

	/**
	 * @return the deviceManuf
	 */
	public String getDeviceManuf()
	{
		return deviceManuf;
	}

	/**
	 * @return the deviceModel
	 */
	public String getDeviceModel()
	{
		return deviceModel;
	}

	/**
	 * @return the deviceVersion
	 */
	public String getDeviceVersion()
	{
		return deviceVersion;
	}

}
