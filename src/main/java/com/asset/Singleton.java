package com.asset;

import com.voucher.manage.daoModel.Assets.Hidden_User;

public class Singleton {
	private static Singleton instance = new Singleton();
	
	private String campusAdmin;
	
	private final static String mapUrl="http://127.0.0.1:8080/voucher/"; 
	
	private final static String path=System.getProperties().getProperty("user.home")+"\\Desktop\\bb\\doc\\";
	
	private Hidden_User hidden_User=new Hidden_User();
	
    private Singleton (){
    	hidden_User.setPrincipal_name("admin");
    	hidden_User.setPurview(0);
    	
    }  
    
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

	public Hidden_User getHidden_User() {
		return hidden_User;
	}

	public void setHidden_User(Hidden_User hidden_User) {
		this.hidden_User = hidden_User;
	}
  
}
