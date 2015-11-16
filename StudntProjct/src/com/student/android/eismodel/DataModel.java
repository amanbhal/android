package com.student.android.eismodel;

import java.util.Hashtable;

import com.student.android.eislogger.Logger;


public class DataModel {

	public User mCurrentUser;
	private static DataModel instance;
	private final Hashtable<String, Object> model;
	private static Object mutex = new Object();
	private static final String TAG = DataModel.class.getSimpleName();

	private DataModel() {
		model = new Hashtable<String, Object>();
		mCurrentUser = new User();
	}
	
	public static DataModel getInstance() {

		if (instance != null) {
			return instance;
		}

		synchronized (mutex) {
			if (instance == null) {

				instance = new DataModel();
			}
		}

		return instance;
	}
	
	
	
	/**
	 * This method stores object in the data manager hash table
	 * 
	 * @param key
	 *            key for the object, that can be used to retrieve the object
	 *            later
	 * @param object
	 *            object to be stored
	 */
	public void storeObject(String key, Object object) {
		synchronized (mutex) {

			Logger.printMessage(TAG, "storing object " + object + " with key "
					+ key + " in data model ", Logger.INFO);

			model.put(key, object);

		}

	}

	/**
	 * Method to get data from the data manager hash table
	 * 
	 * @param key
	 *            key of the object user wants to get
	 * @return object previously stored with the given key or null if there was
	 *         no object stored with the given key
	 */
	public Object getObject(String key) {
		synchronized (mutex) {

			if (model == null) {
				return null;
			}
			Logger.printMessage(TAG, "reading object " + model.get(key)
					+ " with key " + key + " from data model ", Logger.INFO);
			return model.get(key);
		}
	}

	/**
	 * Method to remove object from the data model
	 * 
	 * @param key
	 *            key of the object to be removed from the data model
	 * @return true if the object was removed successfully or false
	 */
	public boolean removeObject(String key) {
		synchronized (mutex) {

			boolean outcome = false;

			Object object = model.remove(key);

			Logger.printMessage(TAG, "removing following from the data model. "
					+ "key " + key + " object " + object, Logger.DEBUG);

			if (object != null) {
				outcome = true;
			}
			return outcome;
		}
	}

	/**
	 * Method to remove all the data from the data model
	 * 
	 * @return true id the operation is successful, false otherwise
	 */
	public boolean resetDataModel() {
		Logger.printMessage(TAG, "resting data model", Logger.DEBUG);

		synchronized (mutex) {
			model.clear();
		}
		return true;
	}

}
