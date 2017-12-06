package com.voucher.manage.daoModelJoin.Assets;

import java.io.Serializable;
import java.util.Date;

import com.voucher.manage.daoSQL.annotations.SQLDateTime;
import com.voucher.manage.daoSQL.annotations.SQLDouble;
import com.voucher.manage.daoSQL.annotations.SQLInteger;
import com.voucher.manage.daoSQL.annotations.SQLString;

public class Hidden_Jion implements Serializable{

	private static final long serialVersionUID = 1L;

    @SQLInteger(name="id")
	private Integer id;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="name")
	private String name;

    @SQLInteger(name="hidden_level")
	private Integer hidden_level;

    @SQLString(name="detail")
	private String detail;

    @SQLDouble(name="progress")
   	private Double progress;
    
    @SQLDateTime(name="happen_time")
	private Date happen_time;

    @SQLInteger(name="principal")
	private Integer principal;

    @SQLInteger(name="type")
	private Integer type;

    @SQLString(name="state")
	private String state;

    @SQLString(name="remark")
	private String remark;

    @SQLDateTime(name="date")
	private Date date;
    
    
    /*
     * Hidden_Level
     */
    
    @SQLString(name="level_text")
	private String level_text;
    
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

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setHidden_level(Integer hidden_level){
		this.hidden_level = hidden_level;
	}

	public Integer getHidden_level(){
		return hidden_level;
	}

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return detail;
	}

	public void setProgress(Double progress){
		this.progress = progress;
	}

	public Double getProgress(){
		return progress;
	}
	
	public void setHappen_time(Date happen_time){
		this.happen_time = happen_time;
	}

	public Date getHappen_time(){
		return happen_time;
	}

	public void setPrincipal(Integer principal){
		this.principal = principal;
	}

	public Integer getPrincipal(){
		return principal;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public Integer getType(){
		return type;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return remark;
	}

	public void setDate(Date date){
		this.date = date;
	}

	public Date getDate(){
		return date;
	}
    
	
	public void setLevel_text(String level_text){
		this.level_text = level_text;
	}

	public String getLevel_text(){
		return level_text;
	}
	
}
