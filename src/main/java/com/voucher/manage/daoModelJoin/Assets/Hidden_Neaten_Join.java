package com.voucher.manage.daoModelJoin.Assets;

import java.io.Serializable;
import java.util.Date;

import com.voucher.manage.daoSQL.annotations.SQLDateTime;
import com.voucher.manage.daoSQL.annotations.SQLDouble;
import com.voucher.manage.daoSQL.annotations.SQLInteger;
import com.voucher.manage.daoSQL.annotations.SQLString;

public class Hidden_Neaten_Join implements Serializable{

	 @SQLInteger(name="id")
		private Integer id;

	    @SQLString(name="GUID")
		private String GUID;

	    @SQLString(name="instance")
		private String instance;

	    @SQLDateTime(name="date")
		private Date date;

	    /*
	     * Hidden
	     */
	    
	    @SQLString(name="name")
		private String name;
	    
	    @SQLInteger(name="hidden_level")
		private Integer hidden_level;
	    
	    @SQLDateTime(name="happen_time")
	   	private Date happen_time;
	    
	    @SQLString(name="detail")
		private String detail;
	    
	    @SQLDouble(name="progress")
	   	private Double progress;
	    
	    @SQLInteger(name="type")
		private Integer type;
	    
		public void setId(Integer id){
			this.id = id;
		}

		public Integer getId(){
			return id;
		}

		public void setGUID(String GUID){
			this.GUID = GUID;
		}

		public String getGUID(){
			return GUID;
		}

		public void setInstance(String instance){
			this.instance = instance;
		}

		public String getInstance(){
			return instance;
		}

		public void setDate(Date date){
			this.date = date;
		}

		public Date getDate(){
			return date;
		}
		
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getHidden_level() {
			return hidden_level;
		}

		public void setHidden_level(Integer hidden_level) {
			this.hidden_level = hidden_level;
		}

		public Date getHappen_time() {
			return happen_time;
		}

		public void setHappen_time(Date happen_time) {
			this.happen_time = happen_time;
		}

		public String getDetail() {
			return detail;
		}

		public void setDetail(String detail) {
			this.detail = detail;
		}

		public Double getProgress() {
			return progress;
		}

		public void setProgress(Double progress) {
			this.progress = progress;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}
		
}
