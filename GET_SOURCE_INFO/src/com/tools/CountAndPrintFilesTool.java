package com.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import com.common.FileUtils;
import com.common.TechData;
import com.common.utils.Constants;

public class CountAndPrintFilesTool {

	public static void main(String[] args) {
		System.out.println("main()  -->>");
		CountAndPrintFilesTool cft = new CountAndPrintFilesTool();
		// SYSTEM
		//finalNM = Constants.TECH_VIDEOS_FLNM + "_SYS";
		//cft.countFiles(Constants.TECH_VIDEOS);
		//finalNM = Constants.MY_PRESENTATIONS_FLNM + "_SYS";
		//cft.countFiles(Constants.MY_PRESENTATIONS);
		//finalNM = Constants.LATEST_SOURCES_FLNM + "_SYS";
		//cft.countFiles(Constants.LATEST_SOURCES);
		//finalNM = Constants.CHAGANTI_PRAVACHENAMS_FLNM + "_SYS";
		//cft.countFiles(Constants.CHAGANTI_PRAVACHENAMS);
		
		// HARD DISK
		//finalNM = Constants.TECH_VIDEOS_FLNM + "_XDISK";
		//cft.countFiles(Constants.TECH_VIDEOS.replace("E:/", "H:/"));
		//finalNM = Constants.MY_PRESENTATIONS_FLNM + "_XDISK";
		//cft.countFiles(Constants.MY_PRESENTATIONS.replace("D:/", "H:/"));
		//finalNM = Constants.LATEST_SOURCES_FLNM + "_XDISK";
		//cft.countFiles(Constants.LATEST_SOURCES.replace("D:/", "H:/"));
		//finalNM = Constants.CHAGANTI_PRAVACHENAMS_FLNM + "_XDISK";
		//cft.countFiles(Constants.CHAGANTI_PRAVACHENAMS.replace("E:/MY MUSIC/", "H:/"));
		System.out.println("main()  <<-- ");
	}

	static int count = 0;
	static ArrayList<String> filesList = null;
	static String finalNM = null;

	public void countFiles(String dirNM) {
		System.out.println("countFiles()  -->>");
		File dir = new File(dirNM);
		File[] subDir = dir.listFiles();
		ArrayList<TechData> techList = new ArrayList<TechData>();
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
		createView(techList, dir);
		System.out.println("countFiles()  <<-- ");
	}

	private void createView(ArrayList<TechData> techList, File dir) {
		System.out.println("createView()  -->>");
		StringBuilder htmlPage = new StringBuilder();
		htmlPage.append("<html>");
		htmlPage.append("<h1>" + dir.getName() + "</h1>\r\n");
		htmlPage.append("<h2>" + dir.getAbsolutePath() + "</h2><body>\r\n");
		htmlPage.append("<table border=\"2\">\r\n");
		TechData tt = null;
		for (int i = 0; i < techList.size(); i++) {
			tt = techList.get(i);
			String fnm = tt.getTechNM();

			htmlPage.append("<tr><td><font size=\"5\" color=\"red\">"
					+ (fnm.length() > 20 ? fnm.substring(0, 20) : fnm)
					+ "</font></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=\"5\" color=\"green\">"
					+ tt.getCount() + "</font></td></tr>\r\n");
		}
		htmlPage.append("</table><br></br><br></br>");
		htmlPage.append("<table>\r\n");
		for (int i = 0; i < techList.size(); i++) {
			tt = techList.get(i);
			String fnm = tt.getTechNM();
			htmlPage.append("<tr><td rowspan=\"1\"><font size=\"4\" color=\"red\">"
					+ (fnm.length() > 20 ? fnm.substring(0, 20) : fnm)
					+ "</font></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=\"3\" color=\"red\">"
					+ tt.getCount() + "</font></td></tr>\r\n");
			for (int j = 0; j < tt.getFilesList().size(); j++) {
				htmlPage.append("<tr><td colspan='2'><font size=\"4\" color=\"green\">"
						+ tt.getFilesList().get(j) + "</font></td></tr>\r\n");
			}
		}
		htmlPage.append("</table></body></html>");
		FileUtils.saveStringBufferToFile(finalNM, htmlPage);
		System.out.println("createView()  <<-- ");
	}

	private void count(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File f : files) {
					if (f.isDirectory()) {
						count(f);
					} else {
						if(f.length() >  524288L ){
							count++;
							filesList.add(f.getName());}
					}
				}
			}
		}
	}

}