package com.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindWordsDemo {
	private static final String REGEX = "\\w*Cockpit\\b";
	private static final TreeSet<String> words = new TreeSet<String>();
	private static final ArrayList<String> INPUT = new ArrayList<String>() {
		{
			add("abortResume(Component, Event) - Method in class de.hybris.platform.printcockpit.tools.HybrisThreadLocalListener");
			add("de.hybris.platform.admincockpit.session.impl.AbstractConstraintPerspective.AbstactConstraintNavigatorListener");
		}
	};

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
			txtscan = new Scanner(new File("D:/test.txt"));
			/*
			 * txtscan = new Scanner( new File(
			 * "D:/LATEST SOURCE/HYBRIS/HYBRIS FULL/Index (hybris Commerce Suite 5.7.0.0-SNAPSHOT).html"
			 * ));
			 */} catch (FileNotFoundException e) {
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