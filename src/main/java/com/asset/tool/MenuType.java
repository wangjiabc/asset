package com.asset.tool;

import java.util.regex.Pattern;

public class MenuType {

	public static String get(String target) {
		
		  String pattern1 = "MenuItem[id=m1, styleClass=[menu-item]]";
		  String pattern2 = "MenuItem[id=m2, styleClass=[menu-item]]";
		  String pattern3 = "MenuItem[id=m3, styleClass=[menu-item]]";
		  String pattern4 = "MenuItem[id=m4, styleClass=[menu-item]]";
		  String pattern5 = "MenuItem[id=m5, styleClass=[menu-item]]";
		  String pattern6 = "MenuItem[id=m6, styleClass=[menu-item]]";
		  String pattern7 = "MenuItem[id=m7, styleClass=[menu-item]]";
		  String pattern8 = "MenuItem[id=m8, styleClass=[menu-item]]";
		  String pattern9 = "MenuItem[id=m9, styleClass=[menu-item]]";
		  String pattern10 = "MenuItem[id=m10, styleClass=[menu-item]]";
		  
	      
	      if(target.equals(pattern1))
	    	   return "m1";
	      else if(target.equals(pattern2))
	    	   return "m2";
	      else if(target.equals(pattern3))
		        return "m3";
	      else if(target.equals(pattern4))
		        return "m4";
	      else if(target.equals(pattern5))
		        return "m5";
	      else if(target.equals(pattern6))
		        return "m6";
	      else if(target.equals(pattern7))
		        return "m7";
	      else if(target.equals(pattern8))
		        return "m8";
	      else if(target.equals(pattern9))
		        return "m9";
	      else if(target.equals(pattern10))
		        return "m10";
	      
	      return "m";
	}
	
}
