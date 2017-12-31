package com.asset.property;

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
}
