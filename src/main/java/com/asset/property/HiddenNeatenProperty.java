package com.asset.property;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HiddenNeatenProperty {
	private IntegerProperty id;
	private StringProperty guid;
	private StringProperty date;
	private StringProperty instance;
	private StringProperty campusAdmin;
	
	public IntegerProperty getId() {
		return id;
	}
	public void setId(IntegerProperty id) {
		this.id = id;
	}
	public StringProperty getGuid() {
		return guid;
	}
	public void setGuid(StringProperty guid) {
		this.guid = guid;
	}
	public StringProperty getDate() {
		return date;
	}
	public void setDate(StringProperty date) {
		this.date = date;
	}
	public StringProperty getInstance() {
		return instance;
	}
	public void setInstance(StringProperty instance) {
		this.instance = instance;
	}
	public StringProperty getCampusAdmin() {
		return campusAdmin;
	}


	public void setCampusAdmin(String campusAdmin) {
		this.campusAdmin = new SimpleStringProperty(campusAdmin);
	}
}
