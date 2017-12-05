package com.asset.tool;

import java.util.regex.Pattern;

public class FileType {

	public static boolean testImage(String fileType) {
	   	 
	      String pattern = "png|gif|jpeg|jpg|bmp";
	 
	      boolean isMatch = Pattern.matches(pattern, fileType.toLowerCase());
	      
	      return isMatch;
	}
	
	public static boolean testDoc(String fileType) {
	   	 
	      String pattern = "doc|docx|docm|dotx";
	 
	      boolean isMatch = Pattern.matches(pattern, fileType.toLowerCase());
	      
	      return isMatch;
	}
	
	public static boolean testXls(String fileType) {
	   	 
	      String pattern = "xls|xlsx|xlsm|xltm|xlsb";
	 
	      boolean isMatch = Pattern.matches(pattern, fileType.toLowerCase());
	      
	      return isMatch;
	}
	
	public static boolean testPdf(String fileType) {
   	 
	      String pattern = "pdf";
	 
	      boolean isMatch = Pattern.matches(pattern, fileType.toLowerCase());
	      
	      return isMatch;
	}
	
	
	
}
