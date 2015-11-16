package com.student.android.eisparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;

import com.student.android.eislogger.Logger;
import com.student.android.eismodel.Student;

public class MyParser {

	ArrayList<Student> studentArray;
	Student student;

	public ArrayList<Student> parseData(Context context) throws XmlPullParserException,
			IOException {
		InputStream is = context.getAssets().open("student_data.xml");
		String myString = convertStreamToString(is);

		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();
		xpp.setInput(new StringReader(myString));
		int eventType = xpp.getEventType();
		String getTagName = "";
		while (eventType != XmlPullParser.END_DOCUMENT) {
			

			if (eventType == XmlPullParser.START_DOCUMENT) {
				studentArray = new ArrayList<Student>();

				Logger.printMessage(null, "Start document", Logger.DEBUG);

			} else if (eventType == XmlPullParser.START_TAG) {
				getTagName = xpp.getName();
				Logger.printMessage("Start tag ", xpp.getName(), Logger.DEBUG);

				if (getTagName.equalsIgnoreCase("Entity")) {
					student = new Student();
				}

			}

			else if (eventType == XmlPullParser.END_TAG) {
				getTagName = xpp.getName();

				if (getTagName.equalsIgnoreCase("Entity")) {
					studentArray.add(student);
				}
				Logger.printMessage("End tag ", xpp.getName(), Logger.DEBUG);
			}

			else if (eventType == XmlPullParser.TEXT) {
				String getText = xpp.getText();
				if (getTagName.equalsIgnoreCase("id")) {
					student.setmId(getText);
				}

				else if (getTagName.equalsIgnoreCase("name")) {
					student.setmName(getText);

				} else if (getTagName.equalsIgnoreCase("designation")) {
					student.setmDesignation(getText);

				} else if (getTagName.equalsIgnoreCase("mentor")) {
					student.setmMentor(getText);

				} else if (getTagName.equalsIgnoreCase("department")) {
					student.setmDepartment(getText);

				} else if (getTagName.equalsIgnoreCase("joiningdate")) {
					student.setmBirthdate(getText);

				} else if (getTagName.equalsIgnoreCase("club")) {
					student.setmClub(getText);

				} else if (getTagName.equalsIgnoreCase("email")) {
					student.setmEmail(getText);

				} else if (getTagName.equalsIgnoreCase("phone")) {
					student.setmPhone(getText);

				} else if (getTagName.equalsIgnoreCase("keyskills")) {
					student.setmKeySkills(getText);

				} else if (getTagName.equalsIgnoreCase("currentproject")) {
					student.setmCurrentProject(getText);

				} else if (getTagName.equalsIgnoreCase("pastproject")) {
					student.setmPastProject(getText);
				}
				else if (getTagName.equalsIgnoreCase("casualleave")) {
					student.setmCasualLeave(getText);
				}
				else if (getTagName.equalsIgnoreCase("sickleave")) {
					student.setmSickLeave(getText);
				}
				Logger.printMessage("Text ", xpp.getText(), Logger.DEBUG);
			}
		      eventType = xpp.next();
			if(eventType == XmlPullParser.TEXT &&  xpp.getText().equalsIgnoreCase("\n")) {
				// skip whitespace
				eventType = xpp.next();
			   }
		}
		int count = studentArray.size();
		Logger.printMessage("Array Count", Integer.toString(count),
				Logger.DEBUG);
		return studentArray;
	}

	public static String convertStreamToString(InputStream is)
			throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		is.close();
		return sb.toString();
	}
}
