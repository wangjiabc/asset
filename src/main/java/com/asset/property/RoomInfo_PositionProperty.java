package com.asset.property;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RoomInfo_PositionProperty {
	private StringProperty GUID;
	   
	private StringProperty Num;
	   
	private StringProperty Region;
	   
	private StringProperty InDate;
	   
	private StringProperty Address;
	
	private StringProperty ManageRegion;
	/*
	 * leftjoin避免空指针
	 */
	private DoubleProperty lng=new SimpleDoubleProperty();

	private DoubleProperty lat=new SimpleDoubleProperty();

	private StringProperty date;
	
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

	

	public void setAddress(String Address) {
		this.Address=new SimpleStringProperty(Address);
	}
	
	public StringProperty getAddress() {
		return Address;
	}

	public DoubleProperty getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = new SimpleDoubleProperty(lng);
	}

	public DoubleProperty getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = new SimpleDoubleProperty(lat);
	}

	public StringProperty getDate() {
		return date;
	}

	public void setDate(Date date) {
		SimpleDateFormat sdf  =   new  SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " ); 
		String datestr = sdf.format(date); 
		this.date = new SimpleStringProperty(datestr);
	}

	public StringProperty getRegion() {
		return Region;
	}

	public void setRegion(String region) {
		Region = new SimpleStringProperty(region);
	}

	public StringProperty getInDate() {
		return InDate;
	}

	public void setInDate(Date inDate) {
		SimpleDateFormat sdf  =   new  SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " ); 
		String datestr = sdf.format(inDate); 
		this.InDate = new SimpleStringProperty(datestr);
	}
	
	public StringProperty getManageRegion() {
		return ManageRegion;
	}


	public void setManageRegion(String manageRegion) {
		ManageRegion = new SimpleStringProperty(manageRegion);
	}
	
}
