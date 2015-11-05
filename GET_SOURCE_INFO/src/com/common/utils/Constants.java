package com.common.utils;

import java.util.ArrayList;

public class Constants {


	public static final int NUM_LAGACY_FILES = 30;

	public static final ArrayList<String> USERS = new ArrayList<String>() {
		{
			add("ravi.maddi"); // koncsvryueytyrvp
			add("ravi.maddi2");
			add("nineightseven");
			add("kumar.sysanalyst"); // xqnmsmcqathwdgzb

		}
	};
	public static final ArrayList<String> USER_IDS = new ArrayList<String>() {
		{
			add("Kumar@123");// add("koncsvryueytyrvp");
			add("Kumar@123");
			add("Kumar@123");
			add("xqnmsmcqathwdgzb");

		}
	};

	public static final ArrayList<String> BLOCK_COMPANIES = new ArrayList<String>() {
		{
			add("cisco");
			add("donotreply");
			add("mailer-daemon");
			add("info@");
			// add("kronos");
			add("maddi");
			add("notification");
			add("no-reply");
			add("noreply");
			// add("varite");
			add("rave-tech");
			// add("realsoft");
		}
	};

	// FOR ANY MORE INFORMATION LOOK INTO
	public static final String FILE_DATETIME_FORMAT = "yyyy_MMM_d_HH_mm_ss";
	public static final String FILE_DATE_FORMAT = "yyyy_MMM_d";

	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static final String USERNAME_PATTERN = "^[a-z0-9_-]{6,20}$";

	public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})";

	public static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";

	public static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

	public static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public static final String TIME12HOURS_PATTERN = "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)";

	public static final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

	public static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

	public static final String HTML_TAG_PATTERN = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";

	public static final String HTML_A_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";

	public static final String HTML_A_TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";

	// TO CREATE THE LIST FILES

	public static final String TECH_VIDEOS = "E:/TECH VIDEOS";
	public static final String MY_PRESENTATIONS = "D:/MY PRESENTATIONS";
	public static final String LATEST_SOURCES = "D:/LATEST SOURCE";
	public static final String CHAGANTI_PRAVACHENAMS = "E:/MY MUSIC/CHAGANTI KOTESWARARAO";

	public static final String TECH_VIDEOS_FLNM = "E:/HOME/FILES/TECH_VIDEOS";
	public static final String MY_PRESENTATIONS_FLNM = "E:/HOME/FILES/MY_PRESENTATIONS";
	public static final String LATEST_SOURCES_FLNM = "E:/HOME/FILES/LATEST_SOURCE";
	public static final String CHAGANTI_PRAVACHENAMS_FLNM = "E:/HOME/FILES/CHAGANTI_KOTESWARARAO";

	public static final ArrayList<String> TECH_VIDEOS_LIST = new ArrayList<String>() {
		{
			add("E:/TECH VIDEOS");
			add("ravi.maddi");
		}
	};
	public static final ArrayList<String> MY_PRESENTATIONS_LIST = new ArrayList<String>() {
		{
			add("rave-tech");
			add("ravi.maddi");
		}
	};
	public static final ArrayList<String> LATEST_SOURCES_LIST = new ArrayList<String>() {
		{
			add("rave-tech");
			add("ravi.maddi");
		}
	};
	public static final ArrayList<String> CHAGANTI_PRAVACHENAMS_lIST = new ArrayList<String>() {
		{
			add("rave-tech");
			add("ravi.maddi");
		}
	};
}
