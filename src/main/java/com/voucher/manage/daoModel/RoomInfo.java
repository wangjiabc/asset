package com.voucher.manage.daoModel;

import java.io.Serializable;

public class RoomInfo implements Serializable{
   private static final long serialVersionUID = 1L; 	

   private String GUID;
   
   private String Num;
   
   private String OriginalNum;
   
   private String OriginalAddress;
   
   private String Address;

   public String getGUID() {
	  return GUID;
   }

   public void setGUID(String gUID) {
	  GUID = gUID;
   }

   public String getNum() {
	  return Num;
   }

    public void setNum(String num) {
	  Num = num;
    }

    public String getOriginalNum() {
	  return OriginalNum;
    } 

public void setOriginalNum(String originalNum) {
	OriginalNum = originalNum;
}

public String getOriginalAddress() {
	return OriginalAddress;
}

public void setOriginalAddress(String originalAddress) {
	OriginalAddress = originalAddress;
}

public String getAddress() {
	return Address;
}

public void setAddress(String address) {
	Address = address;
}
}
