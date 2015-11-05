package com.tools;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;

import com.common.FileUtils;
import com.common.TechData;
import com.common.utils.Constants;

/**
 * TODO: 
 * 1. PRINT ALL FILES AND FOLDERS.
 * 2. DO NOT PRINT UNWANTED FILES.
 * 3. BUT LISTOUT UNWANTED FILES.
 * 3. IDENTIFY THE FILES IN WRONG PLACE.
 * 4. 
 * @author RAVI
 *
 */
public class CountAndPrintDiresTool {
	static String dirNM = "E:/TECH VIDEOS";
    static String tabs = "    ";
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
		Collections.sort(techList);
		createView(techList, new File(dirNM));
	}

	public static void countFiles(String dirNM) {
		File dir = new File(dirNM);
		techList = new ArrayList<TechData>();
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
						System.out.println(tabs + f.getName());
						filesList.add(f.getName());
						tabs = tabs + "     ";
					} else {
						count++;
						filesList.add(f.getName());
						System.out.println(tabs + String.format( "%50s", f.getName()) +"     "+ f.length());
					}
				}
				tabs = tabs.substring(tabs.length() - 4);
			}
		}
	}

}