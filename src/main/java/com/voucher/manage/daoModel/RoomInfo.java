package com.voucher.manage.daoModel;

import java.io.Serializable;
import java.util.Date;

import com.voucher.manage.daoSQL.annotations.DBTable;
import com.voucher.manage.daoSQL.annotations.SQLDateTime;
import com.voucher.manage.daoSQL.annotations.SQLFloat;
import com.voucher.manage.daoSQL.annotations.SQLString;


@DBTable(name="[TTT].[dbo].[RoomInfo]")
public class RoomInfo implements Serializable{
   private static final long serialVersionUID = 1L; 	

   @SQLString(name="GUID")
   public String GUID;
   
   @SQLString(name="Num")
   private String Num;
   
   @SQLString(name="OriginalNum")
   private String OriginalNum;
   
   @SQLString(name="OriginalAddress")
   private String OriginalAddress;
   
   @SQLString(name="Address")
   private String Address;

   @SQLString(name="Region")
   private String Region;
   
   @SQLFloat(name="BuildArea")
   private Float BuildArea;
   
   @SQLDateTime(name="InDate")
   private Date InDate;
   
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



 
 

	public String getRegion() {
		return Region;
	}

	public void setRegion(String region) {
		Region = region;
	}


	public Float getBuildArea() {
		return BuildArea;
	}

	public void setBuildArea(Float buildArea) {
		BuildArea = buildArea;
	}

	public Date getInDate() {
		return InDate;
	}

	public void setInDate(Date inDate) {
		InDate = inDate;
	}

}
