package com.asset.property.join;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.voucher.manage.daoSQL.annotations.SQLDateTime;
import com.voucher.manage.daoSQL.annotations.SQLString;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HiddenNeaten_JoinProperty {
	private IntegerProperty id;

	private StringProperty GUID;

	private StringProperty neaten_id;

	private StringProperty neaten_name;

	private StringProperty principal;

	private StringProperty happen_time;

	private StringProperty neaten_instance;

	private StringProperty date;

	private StringProperty remark;
	
	private StringProperty campusAdmin;
	/*
     * Hidden
     */
	private StringProperty name;
    
	private IntegerProperty hidden_level;
        
	private StringProperty detail;
    
   	private DoubleProperty progress;
    
	private IntegerProperty type;
	
	public IntegerProperty getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}
	
	public StringProperty getName() {
		return name;
	}
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}
	public IntegerProperty getHidden_level() {
		return hidden_level;
	}
	public void setHidden_level(Integer hidden_level) {
		this.hidden_level = new SimpleIntegerProperty(hidden_level);
	}
	public StringProperty getHappen_time() {
		return happen_time;
	}
	public void setHappen_time(Date happen_time) {
		SimpleDateFormat sdf  =   new  SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " ); 
		String datestr = sdf.format(happen_time); 
		this.happen_time = new SimpleStringProperty(datestr);
	}
	public StringProperty getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = new SimpleStringProperty(detail);
	}
	public DoubleProperty getProgress() {
		return progress;
	}
	public void setProgress(Double progress) {
		this.progress = new SimpleDoubleProperty(progress);
	}
	public IntegerProperty getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = new SimpleIntegerProperty(type);
	}
	public StringProperty getGUID() {
		return GUID;
	}
	public void setGUID(StringProperty gUID) {
		GUID = gUID;
	}
	public StringProperty getNeaten_id() {
		return neaten_id;
	}
	public void setNeaten_id(String neaten_id) {
		this.neaten_id = new SimpleStringProperty(neaten_id);
	}
	public StringProperty getNeaten_name() {
		return neaten_name;
	}
	public void setNeaten_name(String neaten_name) {
		this.neaten_name = new SimpleStringProperty(neaten_name);
	}
	public StringProperty getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = new SimpleStringProperty(principal);
	}
	public StringProperty getNeaten_instance() {
		return neaten_instance;
	}
	public void setNeaten_instance(String neaten_instance) {
		this.neaten_instance = new SimpleStringProperty(neaten_instance);
	}
	public StringProperty getDate() {
		return date;
	}
	public void setDate(Date date) {
		SimpleDateFormat sdf  =   new  SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " ); 
		String datestr = sdf.format(date); 
		this.date = new SimpleStringProperty(datestr);
	}
	public StringProperty getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = new SimpleStringProperty(remark);
	}
	public StringProperty getCampusAdmin() {
		return campusAdmin;
	}


	public void setCampusAdmin(String campusAdmin) {
		this.campusAdmin = new SimpleStringProperty(campusAdmin);
	}
	
}
