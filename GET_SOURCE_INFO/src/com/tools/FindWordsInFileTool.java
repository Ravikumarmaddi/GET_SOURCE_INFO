package com.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindWordsInFileTool {
	private static final String REGEX = "\\w*cockpit\\b";
	private static final TreeSet<String> words = new TreeSet<String>();

	public static void main(String[] args) {
		scanFile();
		printWords();
	}

	private static void printWords() {
		System.out.println("\n\n");
		for (String word : words) {
			System.out.println(word);
		}
	}

	private static void scanFile() {
		Scanner txtscan = null;
		try {
			txtscan = new Scanner(
					new File(
							"D:/test2.txt"));
/*			txtscan = new Scanner(
					new File(
							"D:/LATEST SOURCE/HYBRIS/HYBRIS FULL/Index (hybris Commerce Suite 5.7.0.0-SNAPSHOT).html"));
*/		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (txtscan.hasNextLine()) {
			String line = txtscan.nextLine();
			System.out.println(line);
			getMatchs(line);
		}
	}

	public static void getMatchs(String line) {
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(line); // get a matcher object

		while (m.find()) {
			words.add(m.group());
		}
	}
}