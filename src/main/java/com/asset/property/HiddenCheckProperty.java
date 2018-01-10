package com.asset.property;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HiddenCheckProperty {
	private IntegerProperty id;
	private IntegerProperty check_id;
	private StringProperty guid;
	private StringProperty check_circs;
	private StringProperty date;
	private StringProperty remark;
	private StringProperty campusAdmin;
	
	public IntegerProperty getId() {
		return id;
	}
	public void setId(IntegerProperty id) {
		this.id = id;
	}
	public IntegerProperty getCheck_id() {
		return check_id;
	}
	public void setCheck_id(IntegerProperty check_id) {
		this.check_id = check_id;
	}
	public StringProperty getGuid() {
		return guid;
	}
	public void setGuid(StringProperty guid) {
		this.guid = guid;
	}
	public StringProperty getCheck_circs() {
		return check_circs;
	}
	public void setCheck_circs(StringProperty check_circs) {
		this.check_circs = check_circs;
	}
	public StringProperty getDate() {
		return date;
	}
	public void setDate(StringProperty date) {
		this.date = date;
	}
	public StringProperty getRemark() {
		return remark;
	}
	public void setRemark(StringProperty remark) {
		this.remark = remark;
	}
	public StringProperty getCampusAdmin() {
		return campusAdmin;
	}


	public void setCampusAdmin(String campusAdmin) {
		this.campusAdmin = new SimpleStringProperty(campusAdmin);
	}

}
