package com.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.common.utils.Constants;

public class CommonUtils {
	static final Logger log = Logger.getLogger(CommonUtils.class);

	public static void main(String[] args) {
	}

	public static String removeChar(String s, char c) {

		{
			log.info("removeChar() -->>"); // entry
		}

		String r = "";

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != c)
				r += s.charAt(i);
		}

		{
			log.info("removeChar() <<--"); // exit
		}

		return r;
	}

	public static void copyDirectory(File sourceDir, File destDir)
			throws IOException {

		{
			log.info("copyDirectory() -->>"); // entry
		}

		if (!destDir.exists()) {
			destDir.mkdir();
		}
		File[] children = sourceDir.listFiles();
		File sourceChild = null;
		for (int i = 0; i < children.length; i++) {
			sourceChild = children[i];
			String name = sourceChild.getName();
			File destChild = new File(destDir, name);
			if (sourceChild.isDirectory()) {
				copyDirectory(sourceChild, destChild);
			} else {
				copyFile(sourceChild, destChild);
			}
		}

		{
			log.info("copyDirectory() <<--"); // exit
		}
	}

	public static void copyFile(File source, File dest) throws IOException {

		{
			log.info("copyFile() -->>"); // entry
		}
		if (!dest.exists()) {
			dest.createNewFile();
		}
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(source);
			out = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} finally {
			in.close();
			out.close();
		}

		{
			log.info("copyFile() <<--"); // exit
		}

	}

	public static boolean deleteResource(File resource) throws IOException {

		{
			log.info("deleteResource() -->>"); // entry
		}
		if (resource.exists()) {
			if (resource.isDirectory()) {
				File[] childFiles = resource.listFiles();
				File child = null;
				for (int i = 0; i < childFiles.length; i++) {
					child = childFiles[i];
					deleteResource(child);
				}
			}
		}

		{
			log.info("deleteResource() <<--"); // exit
		}

		return resource.delete();
	}

	public static void setContents(File file, String contents)
			throws FileNotFoundException, IOException {

		{
			log.info("setContents() -->>"); // entry
		}

		if (file == null) {
			throw new IllegalArgumentException("File should not be null.");
		}
		if (file.exists() && !file.canWrite()) {
			throw new IllegalArgumentException("File cannot be written: "
					+ file);
		}
		// use buffering
		Writer output = new BufferedWriter(new FileWriter(file));
		try {
			output.write(contents);
			output.flush();
		} finally {
			output.close();
		}

		{
			log.info("setContents() <<--"); // exit
		}

	}

	public static int countLines(File file) {
		try {
			LineNumberReader reader = new LineNumberReader(new FileReader(file));
			int numLines = 0;
			String line = "";
			while ((line = reader.readLine()) != null) {
			}
			numLines = reader.getLineNumber();
			reader.close();
			return numLines;
		} catch (Exception ex) {
			return 0;
		}
	}

	public static int readFirstLineAsNumber(File file) {
		int num = 0;
		LineNumberReader reader = null;
		try {
			reader = new LineNumberReader(new FileReader(file));
			String line = "";
			if ((line = reader.readLine()) != null) {
				num = Integer.parseInt(line);
			}
		} catch (Exception ex) {
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception ex) {
				}
			}
			return num;
		}
	}

	public static boolean createNotExistFile(File f) {
		log.info("createNotExistFile() -->>"); // entry
		log.info("createNotExistFile() --> Creates file if not exist.");
		if (!f.exists()) {
			try {
				if (f.createNewFile()) {
					log.info("createNotExistFile() --> new "
							+ f.getAbsolutePath() + " file created.");
					return true;
				}
			} catch (IOException e) {
				return false;
			}
		} else {
			log.info("createNotExistFile() --> File " + f.getAbsolutePath()
					+ " already exist.");
			return true;
		}
		log.info("createNotExistFile() <<--"); // exit

		return false;
	}

	public static boolean validateDate(String date) throws Exception {
		{
			log.info("validateDate() -->>"); // entry
		}
		Pattern pattern;
		Matcher matcher;
		// date check (yyyy/mm/dd)
		String DATE_PATTERN = "((19|20)\\d\\d)/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])";
		pattern = Pattern.compile(DATE_PATTERN);
		matcher = pattern.matcher(date);

		if (matcher.matches()) {
			matcher.reset();
			if (matcher.find()) {
				return true;
			} else {
				return false;
			}
		}
		log.info("validateDate() <<--"); // exit
		return false;
	}

	public static String getTodayDateTime() {
		return currentDate(Constants.FILE_DATETIME_FORMAT);
	}

	public static String getTodayDate() {
		return currentDate(Constants.FILE_DATE_FORMAT);
	}


	public static String currentDate(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(Calendar.getInstance().getTime());
	}
	private ArrayList<String> removeDuplicate(ArrayList<String> inputList) {
		TreeSet<String> treeSet = new TreeSet<String>();
		for (String item : inputList) {
			treeSet.add(item);
		}
		return new ArrayList<String>(treeSet);
	}
   /* public static void main(String arg[]){
    	System.out.println(CommonUtil.currentDate("yyyy_MMM_dd_HH_mm_ss"));
    }
*/
}
