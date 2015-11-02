package com.gmail.latest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.common.ObjectFileReadException;
import com.utils.CommonUtils;

public class MailUtil {
	static final Logger logger = Logger.getLogger(MailUtil.class);

	public static void main(String[] args) throws Exception {
		logger.info("main()  -->>");
		String a = "C:/RAVI/DO/EMAILS/EMAILSLIST_2014_Mar_25_12_16_23.txt";
		ArrayList<String> emails = loadArrayList(a);
		for (int i = 0; i < emails.size(); i++) {
			if (i % 10 == 0)
				logger.debug("printTotalFile() ->" + emails.get(i) + "\n");
			else
				logger.debug("printTotalFile() ->" + emails.get(i));
		}

		// System.out.println(checkEMail("ravi.maddi@gmail.com"));
		// createNewJDFile();
		// getProcessedCount("D:/work/MYTOOLS/src/com/common/aaa.txt");
		// saveProcessedCount("D:/work/MYTOOLS/src/com/common/aaa.txt", 555);
		// String a = "C:/RAVI/DO/emailsAddressListObject29Jul2011234815.txt";
		/*
		 * SendMails.selectedFileNM="C:/RAVI/DO/TOTAL/Total_2013_Feb_1_07_48_23.txt"
		 */
		// printTotalFile();
		// createTotalFile();
		// createAllEmailsFile();
		// getFileasString("C:/RAVI/CL28.txt");
		logger.info("main()  <<--");
	}

	public static void printTotalFile() {
		logger.info("printTotalFile()  -->>");
		File[] latestFiles = null;
		latestFiles = MailUtil.getLatestFilesList(Constants.TOTAL_DIR, 1);
		ArrayList<String> emails = loadArrayList(latestFiles[0]
				.getAbsolutePath());
		for (int i = 0; i < emails.size(); i++) {
			if (i % 10 == 0)
				logger.debug("printTotalFile() ->" + emails.get(i) + "\n");
			else
				logger.debug("printTotalFile() ->" + emails.get(i));
		}
		logger.info("printTotalFile()  <<--");
	}

	public static String createTotalFile() {
		logger.info("createTotalFile()  -->>");
		File[] inFiles = new File(Constants.EMAILS_DIR).listFiles();
		String curDate = CommonUtils
				.currentDate(Constants.FILE_DATETIME_FORMAT) + ".txt";
		String totalFileNM = Constants.TOTAL_FILE + curDate;
		String countFileNM = Constants.COUNT_DIR + "Total_" + curDate;
		createNewFile(totalFileNM);
		createNewFile(countFileNM);
		TreeSet<String> ts = new TreeSet<String>();
		ArrayList<String> temp = null;
		// for (int i = 0; i < 1; i++) {
		for (int i = 0; i < inFiles.length; i++) {
			if (inFiles[i].isFile()) {
				logger.debug("createTotalFile() ->FileName: " + inFiles[i]);
				temp = loadArrayList(inFiles[i].getAbsolutePath());
				if (temp != null) {
					ts.addAll(temp);
					logger.debug("createTotalFile() ->Email count: "
							+ temp.size());
				}
			}
		}
		logger.debug("createTotalFile() ->Total Email count: " + ts.size());
		ArrayList<String> a = new ArrayList<String>(ts);
		saveArrayList(a, totalFileNM);
		logger.info("createTotalFile()  <<--");
		return totalFileNM;
	}

	public static void saveObject(Object saveObject, String fileName) {
		logger.info("saveObject()  -->>");
		if (fileName == null) {
			fileName = createNewEmailsFile();
		}
		File saveToFile = new File(fileName);
		FileOutputStream f_out = null;
		ObjectOutputStream obj_out = null;
		try {
			if (saveToFile.exists()) {
				f_out = new FileOutputStream(saveToFile, true);
			} else {
				fileName = createNewEmailsFile();
				f_out = new FileOutputStream(new File(fileName), true);
			}
			obj_out = new ObjectOutputStream(f_out);
			obj_out.writeObject(saveObject);
		} catch (FileNotFoundException e) {
			logger.error("saveObject() ->File not available.......................");
			logger.error("saveObject() ->" + e);
			System.exit(1);
		} catch (IOException e) {
			logger.error("saveObject() ->Invalid File path and name.......................");
			logger.error("saveObject() ->" + e);
			System.exit(2);
		} finally {
			try {
				if (obj_out != null)
					obj_out.close();
				if (f_out != null)
					f_out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		logger.debug("saveObject() ->Object Saved to " + fileName
				+ "........SUCCEFULLY");
		logger.info("saveObject()  <<--");
	}

	public static void saveArrayList(ArrayList<String> saveObject,
			String fileName) {
		logger.info("saveArrayList()  -->>");
		removeAllNullsEmptys(saveObject);
		ArrayList<String> finalList = filterArrayList(saveObject);
		if (finalList != null && finalList.getClass().equals(ArrayList.class)) {
			if (fileName == null) {
				fileName = createNewEmailsFile();
			}
			File saveToFile = new File(fileName);
			FileOutputStream f_out = null;
			ObjectOutputStream obj_out;
			try {
				if (saveToFile.exists()) {
					f_out = new FileOutputStream(saveToFile, true);
				} else {
					fileName = createNewEmailsFile();
					f_out = new FileOutputStream(new File(fileName), true);
				}
				obj_out = new ObjectOutputStream(f_out);
				obj_out.writeObject(finalList);
			} catch (FileNotFoundException e) {
				logger.error("saveArrayList() ->File not available.......................");
				logger.error("() ->" + e);
				System.exit(1);
			} catch (IOException e) {
				logger.error("saveArrayList() ->Invalid File path and name.......................");
				logger.error("() ->" + e);
				System.exit(2);
			}
		} else {
			logger.error("saveArrayList() ->Invalid object "
					+ finalList.getClass().toString() + ". Expected ArrayList");
		}
		logger.debug("saveArrayList() ->emails Saved to " + fileName
				+ "........SUCCEFULLY");
		logger.info("saveArrayList()  <<--");
	}

	public static String createNewEmailsFile() {
		logger.info("createNewFile()  -->>");
		String currentDate = CommonUtils
				.currentDate(Constants.FILE_DATETIME_FORMAT);
		String fileName = Constants.EMAILS_FILE + currentDate + ".txt";
		try {
			File file = new File(fileName).getCanonicalFile();
			if (!file.exists()) {
				if (new File(fileName).createNewFile()) {
					logger.debug("createNewFile() -> File created: " + fileName);
				}
			}
		} catch (IOException e) {
			logger.error("createNewFile() -> Unable to create emails file."
					+ e);
		}
		logger.info("createNewFile()  <<--");
		return fileName;
	}

	public static String createNewJDFile() {
		logger.info("createNewFile()  -->>");
		String currentDate = CommonUtils.getTodayDate();
		String fileName = Constants.JOB_DESCRIPTION_FILE + currentDate + "_" + (Constants.NEXT_JD++) + ".txt";
		try {
			File file = new File(fileName).getCanonicalFile();
			if (!file.exists()) {
				if (new File(fileName).createNewFile()) {
					logger.debug("createNewFile() -> File created: " + fileName);
				}
			} else {
				logger.debug("createNewFile() -> File already exist: "
						+ fileName);
			}
		} catch (IOException e) {
			logger.error("createNewFile() -> Unable to create emails file:" + fileName + "\n"
					+ e);
		}
		logger.info("createNewFile()  <<--");
		return fileName;
	}

	public static String createNewFile(String fileNM) {
		logger.info("createNewFile()  -->>");
		try {
			File file = new File("fileNM").getCanonicalFile();
			if (!file.exists()) {
				if (new File(fileNM).createNewFile()) {
					logger.debug("createNewFile() -> File created: " + fileNM);
				}
			}
		} catch (IOException e) {
			logger.error("createNewFile() -> Unable to create file.");
			fileNM = "C:/RAVI/DO/INPUT.txt";
			logger.error("createNewFile() -> Using default emails file: "
					+ fileNM);
			logger.error("() ->" + e);
		}
		logger.info("createNewFile()  <<--");
		return fileNM;
	}

	public static ArrayList<String> loadArrayList(String selectedFileNM) {
		logger.info("loadArrayList()  -->>");
		FileInputStream f_in = null;
		File savedFile = new File(selectedFileNM);
		Object obj = null;
		if (savedFile.exists()) {
			ObjectInputStream obj_in = null;
			try {
				f_in = new FileInputStream(savedFile);
				obj_in = new ObjectInputStream(f_in);
				obj = obj_in.readObject();
				ArrayList<String> temp1 = null;
				if (obj != null && obj.getClass().equals(ArrayList.class)) {
					temp1 = (ArrayList<String>) obj;
					return filterArrayList(temp1);
				} else {
					logger.error("loadArrayList() ->Wrong Object(ArrayList) Found :"
							+ obj.getClass());
					System.exit(1);
				}
			} catch (IOException e) {
				logger.error("loadArrayList() ->" + e);
				System.exit(1);
			} catch (ClassNotFoundException e) {
				logger.error("loadArrayList() ->" + e);
				System.exit(1);
			} catch (Exception e) {
				logger.error("loadArrayList() ->" + e);
				System.exit(1);
			} finally {
				try {
					if (obj_in != null)
						obj_in.close();
					if (f_in != null)
						f_in.close();
				} catch (IOException e) {
					logger.error("() ->" + e);
				}
			}
		} else {
			logger.error("loadArrayList() ->Invalid File:" + selectedFileNM);
		}
		return null;
	}

	public static ArrayList<String> loadTotalArrayList(String selectedFileNM) {
		logger.info("loadArrayList()  -->>");
		FileInputStream f_in = null;
		File savedFile = new File(selectedFileNM);
		Object obj = null;
		if (savedFile.exists()) {
			ObjectInputStream obj_in = null;
			try {
				f_in = new FileInputStream(savedFile);
				obj_in = new ObjectInputStream(f_in);
				obj = obj_in.readObject();
				ArrayList<String> temp1 = null;
				ArrayList<String> temp2 = null;
				int skipCount = 0;
				if (obj != null && obj.getClass().equals(ArrayList.class)) {
					temp1 = ((ArrayList<String>) obj);
					if (Constants.SKIP_COUNT > 0) {
						temp2 = new ArrayList<String>(temp1.subList(
								Constants.SKIP_COUNT, temp1.size() - 1));
					} else if (Constants.SKIP_COUNT == 0) {
						if (Constants.SEND_TYPE == 2) {
							skipCount = getProcessedCount(selectedFileNM
									.replaceFirst("TOTAL", "COUNT"));
							temp2 = new ArrayList<String>(temp1.subList(
									skipCount, temp1.size() - 1));
						} else {
							temp2 = temp1;
						}
					}
					return filterArrayList(temp2);
				} else {
					logger.error("loadArrayList() ->Wrong Object(ArrayList) Found :"
							+ obj.getClass());
				}
			} catch (IOException e) {
				logger.error("loadArrayList() ->" + e);
			} catch (ClassNotFoundException e) {
				logger.error("loadArrayList() ->" + e);
			} catch (Exception e) {
				logger.error("loadArrayList() ->" + e);
			} finally {
				try {
					if (obj_in != null)
						obj_in.close();
					if (f_in != null)
						f_in.close();
				} catch (IOException e) {
					logger.error("() ->" + e);
				}
			}
		} else {
			logger.error("loadArrayList() ->Invalid File:" + selectedFileNM);
		}
		return null;
	}

	private static ArrayList<String> filterArrayList(ArrayList<String> temp) {
		logger.info("filterArrayList()  -->>");
		removeAllNullsEmptys(temp);
		temp = new ArrayList<String>(validateEmails(temp));
		ArrayList<String> fairEmails = new ArrayList<String>(temp.size());
		boolean isBlocked = false;
		for (int i = 0; i < temp.size(); i++) {
			isBlocked = false;
			for (int j = 0; j < Constants.BLOCK_COMPANIES.size(); j++) {
				if (!isBlocked
						&& temp.get(i).contains(
								Constants.BLOCK_COMPANIES.get(j))) {
					isBlocked = true;
				}
			}
			if (!isBlocked) {
				fairEmails.add(temp.get(i));
			}
		}
		logger.debug("filterArrayList()  -> Before filter:" + temp.size());
		logger.debug("filterArrayList()  -> After filter:" + fairEmails.size());
		logger.info("filterArrayList()  <<--");
		return fairEmails;
	}

	public static Object loadObject(String savedObjectFileName, Class cl) {
		logger.info("loadObject()  -->>");
		FileInputStream f_in = null;
		File savedFile = new File(savedObjectFileName);
		Object obj = null;
		if (savedFile.exists()) {
			try {
				f_in = new FileInputStream(savedFile);
			} catch (FileNotFoundException e) {
				logger.error("loadObject() ->" + e);
			}
			ObjectInputStream obj_in = null;
			try {
				obj_in = new ObjectInputStream(f_in);
				obj = obj_in.readObject();
				if (obj != null && obj.getClass().equals(cl)) {
					return obj;
				} else {
					logger.error("loadObject() ->Wrong Object(ArrayList) Found :"
							+ obj.getClass());
				}
			} catch (IOException e) {
				logger.error("loadObject() ->" + e);
			} catch (ClassNotFoundException e) {
				logger.error("loadObject() ->" + e);
			} finally {
				try {
					obj_in.close();
					f_in.close();
				} catch (IOException e) {
					logger.error("loadObject() ->" + e);
				}
			}
		} else {
			logger.info("loadObject() ->Invalid File:" + savedObjectFileName);
		}
		return null;
	}

	public static String getFileasString(String textFileName)
			throws ObjectFileReadException {
		logger.info("getFileasString()  -->>");
		FileInputStream f_in = null;
		File savedFile = new File(textFileName);
		if (savedFile.exists()) {
			try {
				f_in = new FileInputStream(savedFile);
			} catch (FileNotFoundException e) {
				logger.error("() ->" + e);
				throw new ObjectFileReadException();
			} finally {
				try {
					f_in.close();
				} catch (IOException e) {
					logger.error("() ->" + e);
				}
			}
			DataInputStream din = null;
			StringBuffer sb = new StringBuffer();
			try {
				din = new DataInputStream(f_in);
				String a = null;
				while ((a = din.readLine()) != null) {
					sb.append(a);
					logger.debug(a);
				}
				return sb.toString();
			} catch (IOException e) {
				logger.error("() ->" + e);
				throw new ObjectFileReadException();
			} finally {
				try {
					din.close();
					f_in.close();
				} catch (IOException e) {
					logger.error("() ->" + e);
				}
			}
		} else {
			logger.error("getFileasString() ->Invalid File:" + textFileName);
		}
		logger.info("getFileasString()  <<--");
		return null;
	}

	public static int getProcessedCount(String textFileName)
			throws ObjectFileReadException {
		logger.info("getProcessedCount()  -->>");
		logger.debug("getProcessedCount()  ->" + textFileName);
		FileInputStream f_in = null;
		File savedFile = new File(textFileName);
		if (savedFile.exists()) {
			try {
				f_in = new FileInputStream(savedFile);
			} catch (FileNotFoundException e) {
				logger.error("() ->" + e);
			}
			DataInputStream din = null;
			StringBuffer sb = new StringBuffer();
			try {
				din = new DataInputStream(f_in);
				String tmp = din.readLine();
				if (tmp != null && !tmp.isEmpty()) {
					int count = Integer.parseInt(tmp);
					logger.debug("getProcessedCount() ->" + count);
					return count;
				} else
					return 0;
			} catch (IOException e) {
				logger.error("() ->" + e);
				throw new ObjectFileReadException();
			} finally {
				try {
					din.close();
					f_in.close();
				} catch (IOException e) {
					logger.error("() ->" + e);
				}
			}
		} else {
			logger.error("getProcessedCount() ->Invalid File:" + textFileName);
		}
		logger.info("getProcessedCount()  <<--");
		return 0;
	}

	public static void saveProcessedCount(String selectedFileNM, int count) {
		logger.info("saveProcessedCount()  -->>");
		FileOutputStream out = null;
		File savedFile = new File(selectedFileNM);
		if (savedFile.exists()) {
			DataOutputStream dout = null;
			try {
				out = new FileOutputStream(savedFile);
				StringBuffer sb = new StringBuffer();
				dout = new DataOutputStream(out);
				dout.writeBytes(String.valueOf(count));
			} catch (IOException e) {
				logger.error("() ->" + e);
			} finally {
				try {
					dout.close();
					out.close();
				} catch (IOException e) {
					logger.error("() ->" + e);
				}
			}
		} else {
			logger.error("saveProcessedCount() ->Invalid File:" + selectedFileNM);
		}
		logger.info("saveProcessedCount()  <<--");
	}

	public static void createAllEmailsFile() throws ObjectFileReadException {
		File[] inFiles = new File(Constants.DO_DIR).listFiles();
		String totalFileNM = Constants.TOTAL_DIR + "Total_"
				+ CommonUtils.currentDate(Constants.FILE_DATETIME_FORMAT)
				+ ".txt";
		createNewFile(totalFileNM);
		Set ts = new TreeSet<String>();
		ArrayList temp = null;
		for (int i = 0; i < inFiles.length; i++) {
			if (inFiles[i].isFile()) {
				logger.debug("createAllEmailsFile() ->FileName: " + inFiles[i]);
				temp = loadArrayList(inFiles[i].getAbsolutePath());
				if (temp != null) {
					ts.addAll(temp);
					logger.debug("createAllEmailsFile() ->Email count: "
							+ temp.size());
				}
			}
		}
		logger.debug("Total Email count: " + ts.size());
		ArrayList<String> a = new ArrayList<String>(ts);
		saveArrayList(a, totalFileNM);
		FileOutputStream fop = null;
		File file;
		try {
			file = new File("C:/RAVI/DO/EMAILS.txt");
			fop = new FileOutputStream(file);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// get the content in bytes
			for (int i = 0; i < a.size(); i++) {
				byte[] contentInBytes = (a.get(i) + ";\n\r").getBytes();
				fop.write(contentInBytes);
				// fop.flush();
			}
			logger.debug("Done");
		} catch (IOException e) {
			logger.error("() ->" + e);
		} finally {
			try {
				if (fop != null)
					fop.close();
			} catch (IOException e) {
				logger.error("() ->" + e);
			}
		}
	}

	public static void removeAllNullsEmptys(ArrayList<String> a) {
		logger.info("removeAllNullsEmptys()  -->>");
		logger.debug("removeAllNullsEmptys()  ->" + a.size());
		a.removeAll(Collections.singleton(null));
		a.removeAll(Collections.singleton(""));
		logger.debug("removeAllNullsEmptys()  ->" + a.size());
		logger.info("removeAllNullsEmptys()  <<--");
	}

	private static ArrayList<String> validateEmails(ArrayList<String> in) {
		logger.info("validateEmails()  -->>");
		ArrayList<String> temp = new ArrayList<String>(in.size());
		Pattern pattern;
		pattern = Pattern.compile(Constants.EMAIL_PATTERN);
		for (int i = 0; i < in.size(); i++) {
			if (pattern.matcher(in.get(i)).matches()) {
				temp.add(in.get(i));
			}
		}
		logger.info("validateEmails()  <<--");
		return temp;
	}

	public static boolean validateEmail(final String email) {
		logger.debug("validateEmail()  -->>");
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(Constants.EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		logger.debug("validateEmail()  <<--");
		return matcher.matches();
	}

	// Get latest files needed in a modify date order.
	public static File[] getLatestFilesList(final String directory,
			final int latestXFiles) {
		logger.info("getLatestFilesList()  -->>");
		File[] latestFiles = null;
		FilenameFilter textFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".txt")) {
					return true;
				} else {
					return false;
				}
			}
		};
		File dir = new File(directory);
		File[] files = dir.listFiles(textFilter);
		latestFiles = null;
		int i = 0;
		Arrays.sort(files, new Comparator<File>() {
			public int compare(File f1, File f2) {
				return Long.valueOf(f2.lastModified()).compareTo(
						f1.lastModified());
			}
		});
		if (latestXFiles > 0) {
			latestFiles = new File[latestXFiles];
			logger.debug("getLatestFilesList() ->\nGETTING LATEST FILES\n\n FILES SELECTED:\n");
			for (i = 0; i < latestXFiles; i++) {
				latestFiles[i] = files[i];
				logger.debug(i + "    " + files[i].toString());
			}
		} else {
			latestFiles = files;
		}
		logger.debug("getLatestFilesList() ->\n TOTAL FILES SELECTED: "
				+ (i + 1));
		logger.info("getLatestFilesList()  <<--");
		return latestFiles;
	}

	public static String truncateEmail(String email) {
		if (email != null) {
			if (email.contains("<") && email.contains(">"))
				return email.subSequence(email.indexOf('<') + 1,
						email.indexOf('>')).toString();
			else
				return (email.endsWith("")) ? "" : email;
		}
		return null;
	}

	public static String checkEMail(String eMail) {
		String temp = truncateEmail(eMail);
		if (temp != null && !temp.isEmpty()) {
			if (MailUtil.validateEmail(temp)) {
				return temp;
			}
		}
		return null;
	}

	public static void printTotalEmails() {
		List eMailList = MailUtil
				.loadArrayList(Constants.TOTAL_FILE);
		for (int i = 0; i < eMailList.size(); i++) {
			System.out.println(eMailList.get(i) + ",");
			if ((i + 1) % 10 == 0)
				System.out.println("\n");
		}
		System.out.println("TOTAL EMAILS: " + eMailList.size());
	}

}