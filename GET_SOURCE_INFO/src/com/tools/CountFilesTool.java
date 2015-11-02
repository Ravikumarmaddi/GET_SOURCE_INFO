package com.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import com.common.FileUtils;
import com.common.TechData;

public class CountFilesTool {

	public static void main(String[] args) {
		CountFilesTool cft = new CountFilesTool();
		String dirNM = "";
		cft.countFiles(dirNM);
	}

	static int count = 0;

	public void countFiles(String dirNM) {
		File dir = new File(dirNM);
		// File dir = new File("E:/MY PRESENTATIONS");
		// File dir = new File("E:/LATEST SOURCE");
		File[] subDir = dir.listFiles();
		ArrayList<TechData> techList = new ArrayList<TechData>();
		TechData tt = null;
		for (int i = 0; i < subDir.length; i++) {
			count = 0;
			tt = new TechData();
			if (subDir[i].isDirectory()) {
				count(subDir[i]);
				tt.setTechNM(subDir[i].getName());
				tt.setCount(count);
				techList.add(tt);
			}
		}
		Collections.sort(techList);
		createView(techList, dir);
	}

	private void createView(ArrayList<TechData> techList, File dir) {
		StringBuilder htmlPage = new StringBuilder();
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
		htmlPage.append("</table></body></html>");
		String fileNM = dir.getAbsolutePath() + "\\" + dir.getName() + ".html";
		System.out.println(fileNM);
		FileUtils.saveStringToFile(fileNM, htmlPage.toString());
	}

	private void count(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File f : files) {
					if (f.isDirectory()) {
						count(f);
					} else {
						count++;
					}
				}
			}
		}
	}

}