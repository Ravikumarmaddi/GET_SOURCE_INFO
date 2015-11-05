package com.common;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.common.utils.Constants;
import com.utils.CommonUtils;

public class FileUtils {

	public static void main1(String[] args) {
		String fileName = "D:/HOME/KUMAR_SEARCH/CL28.txt";
		String coverLetter = convertTxtFileToString(fileName);
		System.out.println(coverLetter);
	}

	public static void main(String[] args) {
		String fileName = "C:/RAVI/DO/processing/PROCESS_COUNT_Total2012_Sep_20_18_28_32.txt";
		int coverLetter = convertTxtFileToInt(fileName);
		System.out.println(coverLetter);

	}

	public static String convertTxtFileToString(String sourceFileName) {
		try {
			File f = new File(sourceFileName);
			StringBuilder sb = null;
			if (f.exists()) {
				FileInputStream iStream = new FileInputStream(f);
				Integer i;
				sb = new StringBuilder();
				while ((i = new Integer(iStream.read())) != null) {
					if (i > 0) {
						sb.append((char) i.intValue());
						// System.out.println((char) i.intValue());
					} else {
						return sb.toString();
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int convertTxtFileToInt(String sourceFileName) {
		try {
			File f = new File(sourceFileName);
			StringBuilder sb = null;
			if (f.exists()) {
				FileInputStream iStream = new FileInputStream(f);
				Integer i;
				sb = new StringBuilder();
				while ((i = new Integer(iStream.read())) != null) {
					if (i > 0) {
						sb.append((char) i.intValue());
						// System.out.println((char) i.intValue());
					} else {

						return Integer.parseInt(sb.toString());
					}
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static String createNewFile2(String fileNM) {
		try {
			File file = new File("fileNM").getCanonicalFile();
			if (!file.exists()) {
				if (new File(fileNM).createNewFile()) {
				}
			}
		} catch (IOException e) {
			fileNM = "C:/RAVI/DO/INPUT.txt";
		}
			return fileNM;
	}
	public static void saveStringToFile(String textFileName, String info) {
		System.out.println("saveProcessedCount()  -->>");
		FileOutputStream out = null;
		File savedFile = new File(textFileName);
		if (savedFile.delete()) {
			createNewFile(textFileName);
			System.out.println("saveProcessedCount()  -> " + textFileName);
		} else {
			textFileName = textFileName.replace(".html", "") + "_"
					+ CommonUtils.currentDate(Constants.FILE_DATETIME_FORMAT)
					+ ".html";
			System.out.println("saveProcessedCount()  -> " + textFileName);
			savedFile.delete();
		}
		try {
			out = new FileOutputStream(savedFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		DataOutputStream dout = null;
		StringBuffer sb = new StringBuffer();
		try {
			dout = new DataOutputStream(out);
			dout.writeBytes(info);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void saveStringBufferToFile(String textFileName,
			StringBuilder info) {
		System.out.println("saveStringBufferToFile()  -->>");
		FileOutputStream out = null;
		
		textFileName = textFileName + "_"
				+ CommonUtils.currentDate(Constants.FILE_DATETIME_FORMAT) + ".html";
		FileUtils.createNewFile(textFileName);
		File f = new File(textFileName);
		try {
			out = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(out);
			dout.writeBytes(info.toString());
			System.out.println("saveStringBufferToFile()  -> Success; File: " + textFileName + " Size:" + f.length());


		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("saveStringBufferToFile()  <<-- ");
	}
	
	public static File createNewFile(String fileNM) {
		System.out.println("createNewFile()  -->>");
		File file =null;
		try {
			file = new File(fileNM).getCanonicalFile();
			if (!file.exists()) {
				if (file.createNewFile()) {
					System.out.println("createNewFile() -> File created: " + fileNM);
				}
			}
		} catch (IOException e) {
			System.out.println("createNewFile() -> Unable to create file.");
			System.exit(1);
		}
		System.out.println("createNewFile()  <<--");
		return file;
		
	}


}
