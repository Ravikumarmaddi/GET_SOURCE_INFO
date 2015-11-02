package com.common;

import java.util.ArrayList;

public class TechData implements Comparable {

	String techNM;
	int count;
	ArrayList<String> filesList = null;

	public String getTechNM() {
		return techNM;
	}

	public ArrayList<String> getFilesList() {
		return filesList;
	}

	public void setFilesList(ArrayList<String> filesList) {
		this.filesList = filesList;
	}

	public void setTechNM(String techNM) {
		this.techNM = techNM;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int compareTo(Object o) {
		return this.count - ((TechData) o).count;
	}
}
