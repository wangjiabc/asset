package com.asset;

import com.voucher.manage.daoModel.Assets.Hidden_User;

public class Singleton {
	private static Singleton instance = new Singleton();
	
	private String campusAdmin;
	
	private final static String mapUrl="http://220.166.104.133/voucher/"; 
	
	private final static String path=System.getProperties().getProperty("user.home")+"\\Desktop\\bb\\doc\\";
	
	private Hidden_User hidden_User=null;
	
    private Singleton (){    	
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
		if(hidden_User==null){
			hidden_User=new Hidden_User();
	    	hidden_User.setPrincipal_name("admin");
	    	hidden_User.setPurview(0);
	    	hidden_User.setCampusAdmin("admin");
		}
		return hidden_User;
	}

	public void setHidden_User(Hidden_User hidden_User) {
		this.hidden_User = hidden_User;
	}
  
}
