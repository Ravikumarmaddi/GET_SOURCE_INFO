package com.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import com.common.FileUtils;
import com.common.TechData;
import com.gmail.latest.Constants;

public class CountAndPrintDiresTool {
	static String dirNM = "E:/TECH VIDEOS";

	public static void main(String[] args) {
		CountAndPrintDiresTool cft = new CountAndPrintDiresTool();
		cft.countFiles(dirNM);
	}

	static int count = 0;
	static ArrayList<String> filesList = null;
	static ArrayList<TechData> techList = null;
	static StringBuilder htmlPage = null;

	public void countDirs(String dirNM) {
		techList = new ArrayList<TechData>();
		htmlPage = new StringBuilder();

		/*
		 * for (int i = 0; i < Constants.TECH_VIDEOS.size(); i++) {
		 * countFiles(Constants.TECH_VIDEOS.get(i)); }
		 */

		Collections.sort(techList);
		createView(techList, new File(dirNM));
	}

	public static void countFiles(String dirNM) {
		File dir = new File(dirNM);
		techList = new ArrayList<TechData>();
		htmlPage = new StringBuilder();
		// File dir = new File("E:/MY PRESENTATIONS");
		// File dir = new File("E:/LATEST SOURCE");
		File[] subDir = dir.listFiles();
		TechData tt = null;
		for (int i = 0; i < subDir.length; i++) {
			count = 0;
			filesList = new ArrayList<String>();
			tt = new TechData();
			if (subDir[i].isDirectory()) {
				count(subDir[i]);
				tt.setTechNM(subDir[i].getName());
				tt.setCount(count);
				tt.setFilesList(filesList);
				techList.add(tt);
			}
		}
		Collections.sort(techList);
		createView(techList, new File(dirNM));
	}

	public static void createView(ArrayList<TechData> techList, File dir) {
		// StringBuilder htmlPage = new StringBuilder();
		htmlPage.append("<html>");
		htmlPage.append("<h1>" + dir.getName() + "</h1><body>\r\n");
		htmlPage.append("<table>\r\n");
		TechData tt = null;
		for (int i = 0; i < techList.size(); i++) {
			tt = techList.get(i);
			String fnm = tt.getTechNM();
			htmlPage.append("<tr><td><b>"
					+ (fnm.length() > 20 ? fnm.substring(0, 20) : fnm)
					+ "</b></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>"
					+ tt.getCount() + "</b></td></tr>\r\n");
		}
		htmlPage.append("</table>");
		htmlPage.append("<table>\r\n");
		for (int i = 0; i < techList.size(); i++) {
			tt = techList.get(i);
			String fnm = tt.getTechNM();
			htmlPage.append("<tr><td><b>"
					+ (fnm.length() > 20 ? fnm.substring(0, 20) : fnm)
					+ "</b></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>"
					+ tt.getCount() + "</b></td></tr>\r\n");
			for (int j = 0; j < tt.getFilesList().size(); j++) {
				htmlPage.append("<tr><td colspan='2'><b>"
						+ tt.getFilesList().get(j) + "</b></td></tr>\r\n");
			}
		}
		htmlPage.append("</table></body></html>");
		String fileNM = dir.getAbsolutePath() + "\\" + dir.getName() + ".html";
		System.out.println(fileNM);
		FileUtils.saveStringToFile(fileNM, htmlPage.toString());
	}

	public static void count(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File f : files) {
					if (f.isDirectory()) {
						count(f);
					} else {
						count++;
						filesList.add(f.getName());
					}
				}
			}
		}
	}

}