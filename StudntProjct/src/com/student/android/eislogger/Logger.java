package com.student.android.eislogger;

import android.util.Log;

/**
 * This class would be used for logging. 
 * It use the android default logger for
 * logging.
 * <br>
 * <br>
 * We can enable/disbale the logging globally via 
 * {@code ENABLE_CONSOLE_DEBUG}
 *  <br>
 *  <br>
 *  Usage example
 *  <br>
 *  <code>
 *  Logger.printMessage(TAG, "Print Message", Logger.DEBUG);
 *  </code>
 * <br>
 * <br>
 * TAG is generally a used as a static string in each class containing
 *  the class name. This is to identify from which class the log was generated.
 *  
 *  <br>
 * <br>
 * 
 * {@code ALL, INFO, DEBUG, WARN, ERROR } are the different logging levels
 * provided by this class. Debug logs are compiled in but stripped at runtime. 
	 * Error, Warning and Info logs are always kept. 
 * 
 * @author Praful
 *
 */
public class Logger
{
	/**
	 * Flag to enable/disable logging
	 */
	public static final boolean ENABLE_CONSOLE_DEBUG = true;
	public static final boolean TEST_FLAG = false;
	/**
	 * flag for java error message display
	 */
	public static final boolean ENABLE_JAVA_ERROR_MESSAGE_DISPLAY = true;

	/*  
	 * Debug logs are compiled in but stripped at runtime. 
	 * Error, warning and info logs are always kept. 
	 * */

	public static final int ALL = 1;
	public static final int INFO = 6;
	public static final int DEBUG = 11;
	public static final int WARN = 16;
	public static final int ERROR = 21;
	//	public static final int FATAL = 26;

	private static final int CURRENT_LOGING_LEVEL = ALL;
	private static StringBuffer errorLog = new StringBuffer(2048);

	/**
	 * Method used to print logs to DDMS Logcat
	 * @param tag String to identify the log. Recomended use of 
	 * class name as tag
	 * @param msg Actual error message to print
	 * @param logingLevel Sets the loging level of log. Can contain values 
	 * {@code ALL, INFO, DEBUG, WARN, ERROR }
	 */
	public static void printMessage(String tag, String msg, int logingLevel)
	{
		if (ENABLE_CONSOLE_DEBUG)
		{
			if (logingLevel >= CURRENT_LOGING_LEVEL)
			{
				Log.d("Delim", "-----------------------------");
				switch (logingLevel)
				{
				case INFO:
					Log.i(tag, msg);
					break;
				case DEBUG:
					Log.d(tag, msg);
					break;
				case WARN:
					Log.w(tag, msg);
					break;
				case ERROR:
					Log.e(tag, msg);
					break;

				}

			}
		}
	}
}
