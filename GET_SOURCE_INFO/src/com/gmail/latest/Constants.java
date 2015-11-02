package com.gmail.latest;

import java.util.ArrayList;

public class Constants {

	// OPERATAION CONTROLERS
	public static final int SEND_TYPE = 0; // 0- send to latest file -daily,
											// 1- send to specific file,
											// 2- send to total,
											// 3- send to LATEST_N_FILES files,
											// 4- create total file

	public static final int SKIP_COUNT = 1910;
	public static final int LATEST_N_FILES = 0; // CHANGE WHEN SEND_TYPE IS 3
	public static final String SELECTED_FILE = null;// "C:/RAVI/DO/EMAILS/Input2013_Jan_25_10_24_14.txt";

	public static final String JOB_DESCRIPTION_FILE = "C:/RAVI/DO/JD/JD_";
	public static final boolean NEED_JOB_DESCRIPSTION = true;
	public static int NEXT_JD = 0;

	// STANDARD VALUES;
	public static final int MAX_SENT_LIMIT = 900;
	public static final int MAX_BBC_LIMIT = 15;
	public static final int MAX_ADDRESS_LIST_LIMIT = 60;// 60;
	public static final int MAX_JD_FILE_SIZE = 500000; // 100000; DEFAULT//

	public static final String PROFILE = "C:/RAVI/Ravikumar_Maddi_Profile29.doc";
	public static final String COVER_LETTER = "C:/RAVI/CL29.txt";
	public static final String SUBJECT = "Ravi - 11 years of experience in Core Java and J2EE, Groovy and Grails, Struts, Spring, Hibernate, Flex, Web services.";
	public static final String DO_DIR = "C:/RAVI/DO/";
	public static final String EMAILS_DIR = "C:/RAVI/DO/EMAILS/";
	public static final String EMAILS_FILE = "C:/RAVI/DO/EMAILS/EMAILSLIST_";
	public static final String LAST_DIR = "C:/RAVI/DO/LAST/";
	public static final String TOTAL_DIR = "C:/RAVI/DO/TOTAL/";
	public static final String TOTAL_FILE = "C:/RAVI/DO/TOTAL/TOTAL_";

	public static final String COUNT_DIR = "C:/RAVI/DO/COUNT/";
	public static final String PROCESSING_DIR_NM = "C:/RAVI/DO/processing/";
	public static final String PROCESSED_DIR_NM = "C:/RAVI/DO/processed/";
	public static final String EMAIL_FOLDER = "JAVA"; // "INTERVIEWS";//
	public static final String[] EMAIL_FOLDERS = { "JAVA" };// { "INTERVIEWS"
															// };//
	public static final String MAIN_USER = "ravi.maddi";// "kumar.sysanalyst";//
	public static final String MAIN_USER_ID = "koncsvryueytyrvp";// "xqnmsmcqathwdgzb";

	/*
	 * SEND_TYPE : 0 -
	 * DAILY...................................................... 1 - NUMBER OF
	 * LAGACY FILES SEND TOTAL
	 * FILE....................................................................
	 * 3 - FILE(SPICIFIC FILE)
	 * SENDING........................................................ 4 -
	 * CREATE TOTALs
	 * FILE..................................................................
	 */
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
