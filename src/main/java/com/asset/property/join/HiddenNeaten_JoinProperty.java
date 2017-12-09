package com.asset.property.join;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HiddenNeaten_JoinProperty {
	private IntegerProperty id;
	private StringProperty guid;
	private StringProperty date;
	private StringProperty instance;
	
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
