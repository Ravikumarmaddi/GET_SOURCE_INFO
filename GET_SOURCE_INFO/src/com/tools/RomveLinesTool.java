package com.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RomveLinesTool {
	// static final Logger logger = Logger.getLogger(RomveLinesTool.class);

	public static void main(String[] args) {

		ArrayList<String> rl = new ArrayList<String>();
		rl.add("??");
		rl.add("Dictionary");
		rl.add("");
		rl.add("");
		removeLines("E:/HOME/DIC.txt", rl);
	}

	public static String removeLines(String sourceFileName,
			final ArrayList<String> rl) {
		FileReader iF = null;
		BufferedReader ib = null;
		
		FileWriter oF = null;
		BufferedWriter ob = null;
		String oFL = "E:/HOME/DIC_Final.txt";
		StringBuilder sb = null;
		try {
			File f = new File(sourceFileName);
			if (f.exists()) {
				iF = new FileReader(f);
				ib = new BufferedReader(iF);
				oF = new FileWriter(f);
				ob = new BufferedWriter(oF);
				sb = new StringBuilder();
				String t = null;
				while ((t = ib.readLine()) != null) {
					t = t.trim();
					boolean isValid = true;
					for (int i = 0; i < rl.size() && !rl.get(i).equals("") && isValid == true; i++) {
						
						if (t.contains(rl.get(i))) {
							isValid = false;
						}
						if(isValid){sb.append(t);
						System.out.println(t);}
					}
					
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ib!=null)ib.close();
				if(iF!=null)iF.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}
}
