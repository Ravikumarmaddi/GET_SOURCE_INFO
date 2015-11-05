package com.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class PrintFilesTool {

   public static void main(String[] args) {
        PrintFilesTool pft = new PrintFilesTool();
        String dir = "E:/MY MUSIC/CHAGANTI VENKATESWARLU";
        System.out.println("============================================================================================================");
        System.out.println(dir);
        String a = "" ;
        for (int i = 0; i < dir.length(); i++) {
			a = a + "-";
		}
        System.out.println(a);
        pft.printFiles(dir);
        System.out.println("============================================================================================================");
        
    }

    private void printFiles(String dir) {
        File parentPath = new File(dir);
        List<String> files = list(parentPath);

        for (String file : files) {
            System.out.println(file);
        }
    }

    protected List<String> list(File parent) {
        return listFiles(parent, parent);
    }

    protected List<String> listFiles(File parent, File folder) {
        List<String> lstFiles = new ArrayList<String>(25);
        if (folder.isDirectory()) {

            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        lstFiles.addAll(listFiles(parent, file));
                    } else {
                        String path = file.getPath();
                        String offset = parent.getPath();

                        path = path.substring(offset.length());
                        lstFiles.add(path);
                    }
                }
            }
        }

        return lstFiles;
    }
}