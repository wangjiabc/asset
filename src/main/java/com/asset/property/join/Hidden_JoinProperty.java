package com.asset.property.join;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.voucher.manage.daoSQL.annotations.SQLString;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Hidden_JoinProperty {
	private IntegerProperty id;

	private StringProperty GUID;

	private StringProperty name;

	private IntegerProperty hidden_level;

	private StringProperty detail;

	private DoubleProperty progress;
	
	private StringProperty happen_time;

	private IntegerProperty principal;

	private IntegerProperty type;

	private StringProperty state;

	private StringProperty remark;

	private StringProperty date;

	/*
    * Hidden_Level
    */
	private StringProperty level_text;
	
	
	/*
     * Hidden_Type
     */
    
	private StringProperty hidden_type;
	
		public IntegerProperty getId() {
			return id;
		}


		public void setId(Integer id) {
			System.out.println("id="+id);
			this.id = new SimpleIntegerProperty(id);
		}


		public StringProperty getGUID() {
			return GUID;
		}


		public void setGUID(String gUID) {
			GUID = new SimpleStringProperty(gUID);
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


		public StringProperty getDetail() {
			return detail;
		}


		public void setDetail(String detail) {
			this.detail = new SimpleStringProperty(detail);
		}


		public IntegerProperty getPrincipal() {
			return principal;
		}


		public void setPrincipal(Integer principal) {
			this.principal = new SimpleIntegerProperty(principal);
		}


		public IntegerProperty getType() {
			return type;
		}


		public void setType(Integer type) {
			this.type = new SimpleIntegerProperty(type);
		}


		public StringProperty getState() {
			return state;
		}


		public void setState(String state) {
			this.state = new SimpleStringProperty(state);
		}


		public StringProperty getRemark() {
			return remark;
		}


		public void setRemark(String remark) {
			this.remark = new SimpleStringProperty(remark);
		}


		public StringProperty getHappen_time() {
			return happen_time;
		}


		public void setHappen_time(Date happen_time) {
			SimpleDateFormat sdf  =   new  SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " ); 
			String datestr = sdf.format(happen_time); 
			this.happen_time = new SimpleStringProperty(datestr);
		}


		public StringProperty getDate() {
			return date;
		}


		public void setDate(Date date) {
			SimpleDateFormat sdf  =   new  SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " ); 
			String datestr = sdf.format(date); 
			this.date = new SimpleStringProperty(datestr);
		}


		public StringProperty getLevel_text() {
			return level_text;
		}


		public void setLevel_text(String level_text) {
			this.level_text = new SimpleStringProperty(level_text);
		}


		public DoubleProperty getProgress() {
			return progress;
		}


		public void setProgress(Double progress) {
			this.progress = new SimpleDoubleProperty(progress);
		}


		public StringProperty getHidden_type() {
			return hidden_type;
		}


		public void setHidden_type(String hidden_type) {
			this.hidden_type = new SimpleStringProperty(hidden_type);
		}
}
