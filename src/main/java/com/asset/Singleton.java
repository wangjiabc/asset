package com.asset;

public class Singleton {
	private static Singleton instance = new Singleton();
	
	private String campusAdmin;
	
	private final static String mapUrl="http://127.0.0.1:8080/voucher/"; 
	
	private final static String path=System.getProperties().getProperty("user.home")+"\\Desktop\\bb\\doc\\";
	
    private Singleton (){}  
    
    public static Singleton getInstance() {  
    	return instance;  
    }

	public String getCampusAdmin() {
		return campusAdmin;
	}

	public void setCampusAdmin(String campusAdmin) {
		this.campusAdmin = campusAdmin;
	}

	public String getMapUrl() {
		return mapUrl;
	}


	public String getPath() {
		return path;
	}
  
}
