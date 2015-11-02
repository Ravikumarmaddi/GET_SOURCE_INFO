package com.tools;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyPresentations {

	static String currentFolder = null;
	static String myPresentations = "E:/MY PRESENTATIONS";
	static int copyCount = 0;

	static void process(File aFile) {
		if (aFile.isFile()) {
			if (aFile.getName().endsWith(".pptx")) {
				System.out.println("process() -> " + aFile.getName());
				File temp = null;
				if (!currentFolder.endsWith(".pptx")) {
					temp = new File(myPresentations + "/" + currentFolder);
				} else {
					temp = new File(myPresentations);
				}
				if (true) {
					if (!temp.exists()) {
						temp.mkdir();
					}
					if (temp.exists()) {
						File tempFile = new File( temp.getAbsolutePath() + "/" + aFile.getName());
						if (!tempFile.exists()) {
							try {
								if (tempFile.createNewFile()) {
									copyfile(aFile, tempFile);
									if (tempFile.exists()) {
										aFile.delete();
										System.out.println("File created: "
												+ tempFile.getAbsolutePath()
												+ "  Source: "
												+ aFile.getAbsolutePath());
										copyCount++;
									}
								} else {
									System.out
											.println("Not able to create File"
													+ tempFile
															.getAbsolutePath()
													+ "  Source: "
													+ aFile.getAbsolutePath());
								}
							} catch (IOException e) {
								System.out.println("Not able to create File"
										+ tempFile.getAbsolutePath()
										+ "  Source: "
										+ aFile.getAbsolutePath());
								e.printStackTrace();
							}
						}
					}
				}
				// System.out.println(spcs + "[FILE] " + aFile.getName());
			}
		} else if (aFile.isDirectory()) {
			// System.out.println(spcs + "[DIR] " + aFile.getName());
			File[] listOfFiles = aFile.listFiles();
			if (listOfFiles != null) {
				for (int i = 0; i < listOfFiles.length; i++)
					process(listOfFiles[i]);
			} else {
				System.out.println(" [ACCESS DENIED]");
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("main()  -->>");
		String nam = "E:/LATEST SOURCE";
		File f = new File(nam);
		File[] l = f.listFiles();
		for (int i = 0; i < l.length; i++) {
			currentFolder = l[i].getName();
			System.out.println("main()  -> " + currentFolder);
			process(l[i]);
		}
		System.out.println("main()  -> Total copied: " + copyCount);
		System.out.println("main()  <<--");
	}

	private static void copyfile(File f1, File f2) {
		System.out.println("copyfile()  -->>");
		try {
			InputStream in = new FileInputStream(f1);
			OutputStream out = new FileOutputStream(f2);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			System.out.println("copyfile()  -> File copied to "
					+ f2.getAbsolutePath() + "  Source: "
					+ f1.getAbsolutePath());
		} catch (FileNotFoundException ex) {
			System.out
					.println(ex.getMessage() + " in the specified directory.");
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("copyfile()  <<--");
	}

}