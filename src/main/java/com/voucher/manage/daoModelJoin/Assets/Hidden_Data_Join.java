package com.voucher.manage.daoModelJoin.Assets;

import java.io.Serializable;
import java.util.Date;

import com.voucher.manage.daoSQL.annotations.SQLDateTime;
import com.voucher.manage.daoSQL.annotations.SQLDouble;
import com.voucher.manage.daoSQL.annotations.SQLInteger;
import com.voucher.manage.daoSQL.annotations.SQLString;

public class Hidden_Data_Join implements Serializable{

	private static final long serialVersionUID = 1L;

    @SQLInteger(name="id")
	private Integer id;

    @SQLString(name="GUID")
	private String GUID;

    @SQLInteger(name="exist")
	private Integer exist;
    
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

    
    
    /*
     * Hidden_Level
     */
    
    @SQLString(name="level_text")
	private String level_text;
    
    /*
     * Hidden_Type
     */
    
    @SQLString(name="hidden_type")
	private String hidden_type;
    
    /*
     * Hidden_User
     */
    @SQLString(name="principal_name")
   	private String principal_name;

       @SQLString(name="business")
   	private String business;
   
       
       @SQLString(name="NAME")
   	private String NAME;

       @SQLString(name="TYPE")
   	private String TYPE;

       @SQLString(name="URI")
   	private String URI;

       @SQLDateTime(name="date")
   	private Date date;

       @SQLString(name="campusAdmin")
   	private String campusAdmin;

       @SQLString(name="terminal")
   	private String terminal;
       
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

   	public void setNAME(String NAME){
   		this.NAME = NAME;
   	}

   	public String getNAME(){
   		return NAME;
   	}

   	public void setTYPE(String TYPE){
   		this.TYPE = TYPE;
   	}

   	public String getTYPE(){
   		return TYPE;
   	}

   	public void setURI(String URI){
   		this.URI = URI;
   	}

   	public String getURI(){
   		return URI;
   	}

  
       


	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setExist(Integer exist){
		this.exist = exist;
	}

	public Integer getExist(){
		return exist;
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
	
	public void setHidden_type(String hidden_type){
		this.hidden_type = hidden_type;
	}

	public String getHidden_type(){
		return hidden_type;
	}
	

	public void setPrincipal_name(String principal_name){
		this.principal_name = principal_name;
	}

	public String getPrincipal_name(){
		return principal_name;
	}

	public void setBusiness(String business){
		this.business = business;
	}

	public String getBusiness(){
		return business;
	}
	
	public void setCampusAdmin(String campusAdmin){
		this.campusAdmin = campusAdmin;
	}

	public String getCampusAdmin(){
		return campusAdmin;
	}

	public void setTerminal(String terminal){
		this.terminal = terminal;
	}

	public String getTerminal(){
		return terminal;
	}
}
