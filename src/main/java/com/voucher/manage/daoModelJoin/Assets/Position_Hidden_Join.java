package com.voucher.manage.daoModelJoin.Assets;

import java.io.Serializable;
import java.util.Date;

import com.voucher.manage.daoSQL.annotations.SQLDateTime;
import com.voucher.manage.daoSQL.annotations.SQLDouble;
import com.voucher.manage.daoSQL.annotations.SQLInteger;
import com.voucher.manage.daoSQL.annotations.SQLString;

public class Position_Hidden_Join implements Serializable{
	private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="province")
	private String province;

    @SQLString(name="city")
	private String city;

    @SQLString(name="district")
	private String district;

    @SQLString(name="street")
	private String street;

    @SQLString(name="street_number")
	private String street_number;

    @SQLDouble(name="lng")
	private Double lng;

    @SQLDouble(name="lat")
	private Double lat;

    @SQLDateTime(name="date")
	private Date date;
    
    /*
     * Hidden
     */
    
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
    
     
    public void setGUID(String GUID){
		this.GUID = GUID;
	}

	public String getGUID(){
		return GUID;
	}

	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return province;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setDistrict(String district){
		this.district = district;
	}

	public String getDistrict(){
		return district;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getStreet(){
		return street;
	}

	public void setStreet_number(String street_number){
		this.street_number = street_number;
	}

	public String getStreet_number(){
		return street_number;
	}

	public void setLng(Double lng){
		this.lng = lng;
	}

	public Double getLng(){
		return lng;
	}

	public void setLat(Double lat){
		this.lat = lat;
	}

	public Double getLat(){
		return lat;
	}

	public void setDate(Date date){
		this.date = date;
	}

	public Date getDate(){
		return date;
	}
    
	
	/*
     * Hidden
     */
	
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
	
	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return remark;
	}
	
}
