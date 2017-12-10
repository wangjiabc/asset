package com.asset.property.join;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HiddenCheck_JoinProperty {
	private IntegerProperty id;
	private StringProperty check_id;
	private StringProperty guid;
	private StringProperty check_circs;
	private StringProperty date;
	private StringProperty remark;
	private StringProperty check_name;
	private StringProperty principal;
	 /*
     * Hidden
     */
	private StringProperty name;
    
	private IntegerProperty hidden_level;
    
   	private StringProperty happen_time;
    
	private StringProperty detail;
    
   	private DoubleProperty progress;
    
	private IntegerProperty type;
	
	
	
	
	public IntegerProperty getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}
	public StringProperty getCheck_id() {
		return check_id;
	}
	public void setCheck_id(String check_id) {
		this.check_id = new SimpleStringProperty(check_id);
	}
	public StringProperty getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = new SimpleStringProperty(guid);
	}
	public StringProperty getCheck_circs() {
		return check_circs;
	}
	public void setCheck_circs(String check_circs) {
		this.check_circs = new SimpleStringProperty(check_circs);
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
	public StringProperty getCheck_name() {
		return check_name;
	}
	public void setCheck_name(String check_name) {
		this.check_name = new SimpleStringProperty(check_name);
	}
	public StringProperty getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = new SimpleStringProperty(principal);
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
	
}
