package com.asset.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RoomInfoProperty {
	private StringProperty GUID;
	   
	private StringProperty Num;
	   
	private StringProperty OriginalNum;
	   
	private StringProperty OriginalAddress;
	   
	private StringProperty Address;
	
	private StringProperty ManageRegion;
	
	public RoomInfoProperty() {
		// TODO Auto-generated constructor stub
	}

	public void setGUID(String GUID) {
		this.GUID=new SimpleStringProperty(GUID);
	}
	
	public StringProperty getGUID() {
		return GUID;
	}

	public void setNum(String Num) {
		this.Num=new SimpleStringProperty(Num);
	}
	
	public StringProperty getNum() {
		return Num;
	}

	public void setOriginalNum(String OriginalNum) {
		this.OriginalNum=new SimpleStringProperty(OriginalNum);
	}
	
	public StringProperty getOriginalNum() {
		return OriginalNum;
	}

	public void setOriginalAddress(String OriginalAddress) {
		this.OriginalAddress=new SimpleStringProperty(OriginalAddress);
	}
	
	public StringProperty getOriginalAddress() {
		return OriginalAddress;
	}

	public void setAddress(String Address) {
		this.Address=new SimpleStringProperty(Address);
	}
	
	public StringProperty getAddress() {
		return Address;
	}
	
	public StringProperty getManageRegion() {
		return ManageRegion;
	}


	public void setManageRegion(String manageRegion) {
		ManageRegion = new SimpleStringProperty(manageRegion);
	}
	
}
