package com.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SearchInFile {

   /**
    * TODO
    * 
    * @param args
    */
   public static void main(String[] args) {
      File a = new File("C:\\dev\\sources\\eclipse\\eou.06.03.qa.int.dev.t3New\\java\\test.work\\src\\com\\utils\\sendto.txt");
      try {
         FileReader f = new FileReader(a);
         BufferedReader b = new BufferedReader(f);
         ArrayList<String> l = getEmailsList("@gmail", b);
         int length = l.size();
         Set<String> s = new TreeSet<String>(l);
         System.out.println("Length with Duplicates: " + length);
         for(int i=0;i<length;i++){
            s.add(l.get(i));
         }
         System.out.println("Length without Duplicates: " + s.size());
         Object[] ss= s.toArray();
         for(int i=0;i<ss.length;i++){
           System.out.println((String)ss[i]);
         }
         
      }
      catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
    
   }
   
   public static ArrayList<String> getEmailsList(String searchString, BufferedReader b){
      ArrayList<String> emails = new ArrayList<String>(200);
      try {
         String line = null;
         for (int i = 0; i < 12967; i++) {
            line = b.readLine();
            if (line.toLowerCase().contains(searchString)){
               if (line.contains(",")) {
                  StringTokenizer st = new StringTokenizer(line, ",");
                  String token = null;
                  for(int j=0;j<st.countTokens();i++)
                  token = st.nextToken();
                  if(token.contains(searchString)){
                     emails.add(token);
                 //    System.out.println(token);
                  }
               }
               else{
                  emails.add(line);
               }
            }
         }
        System.out.println(emails.size());
      }
      catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return emails;
   }
   
   ArrayList<String> removeDuplicateAndSort(String searchString, BufferedReader b){
      ArrayList<String> emails = new ArrayList<String>(200);
      try {
         String line = null;
         String token = null;
        
         for (int i = 0; i < 12967; i++) {
            line = b.readLine();
            if (line.contains("@")){//"@gmail")) {
               if (line.contains(",")) {
                  StringTokenizer st = new StringTokenizer(line, ",");
                  for(int j=0;j<st.countTokens();i++)
                  token = st.nextToken();
                  if(token.contains(searchString)){
                     emails.add(token);
                     System.out.println(token);
                  }
               }
            }
         }
        
      }
      catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return emails;
   }
   
}
