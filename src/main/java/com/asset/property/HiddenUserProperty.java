package com.asset.property;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.voucher.manage.daoSQL.annotations.SQLDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HiddenUserProperty {

	private IntegerProperty id;

	private IntegerProperty principal;

	private StringProperty campusAdmin;
	
	private StringProperty principal_name;

	private StringProperty phone;
	
	private StringProperty business;

	private IntegerProperty purview;

	private StringProperty enter_time;

	private StringProperty register_time;

	private StringProperty rework_time;
	
	
	public IntegerProperty getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id =new  SimpleIntegerProperty(id);
	}


	public IntegerProperty getPrincipal() {
		return principal;
	}


	public void setPrincipal(Integer principal) {
		this.principal = new SimpleIntegerProperty(principal);
	}


	public StringProperty getPrincipal_name() {
		return principal_name;
	}


	public void setPrincipal_name(String principal_name) {
		this.principal_name = new SimpleStringProperty(principal_name);
	}


	public StringProperty getBusiness() {
		return business;
	}


	public void setBusiness(String business) {
		this.business = new SimpleStringProperty(business);
	}


	public StringProperty getCampusAdmin() {
		return campusAdmin;
	}


	public void setCampusAdmin(String campusAdmin) {
		this.campusAdmin = new SimpleStringProperty(campusAdmin);
	}


	public StringProperty getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = new SimpleStringProperty(phone);
	}


	public IntegerProperty getPurview() {
		return purview;
	}


	public void setPurview(Integer purview) {
		this.purview = new SimpleIntegerProperty(purview);
	}


	public StringProperty getEnter_time() {
		return enter_time;
	}


	public void setEnter_time(Date enter_time) {
		SimpleDateFormat sdf  =   new  SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " ); 
		String datestr = sdf.format(enter_time); 
		this.enter_time = new SimpleStringProperty(datestr);
	}


	public StringProperty getRegister_time() {
		return register_time;
	}


	public void setRegister_time(Date register_time) {
		SimpleDateFormat sdf  =   new  SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " ); 
		String datestr = sdf.format(register_time); 
		this.register_time = new SimpleStringProperty(datestr);
	}


	public StringProperty getRework_time() {
		return rework_time;
	}

	public void setRework_time(Date rework_time) {
		SimpleDateFormat sdf  =   new  SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " ); 
		String datestr = sdf.format(rework_time); 
		this.rework_time = new SimpleStringProperty(datestr);
	}


}
