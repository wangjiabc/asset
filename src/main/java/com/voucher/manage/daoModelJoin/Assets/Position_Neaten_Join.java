package com.voucher.manage.daoModelJoin.Assets;

import java.io.Serializable;
import java.util.Date;

import com.voucher.manage.daoSQL.annotations.SQLDateTime;
import com.voucher.manage.daoSQL.annotations.SQLDouble;
import com.voucher.manage.daoSQL.annotations.SQLInteger;
import com.voucher.manage.daoSQL.annotations.SQLString;

public class Position_Neaten_Join implements Serializable{
	private static final long serialVersionUID = 1L;

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

    
    /*
     * Hidden_Neaten
     */
    
    @SQLInteger(name="id")
	private Integer id;

    @SQLString(name="GUID")
	private String GUID;

    @SQLInteger(name="exist")
	private Integer exist;

    @SQLString(name="neaten_id")
	private String neaten_id;

    @SQLString(name="neaten_name")
	private String neaten_name;

    @SQLString(name="principal")
	private String principal;

    @SQLDateTime(name="happen_time")
	private Date happen_time;

    @SQLString(name="neaten_instance")
	private String neaten_instance;

    @SQLDateTime(name="update_time")
	private Date update_time;

    @SQLDateTime(name="date")
	private Date date;

    @SQLString(name="remark")
	private String remark;

    @SQLString(name="campusAdmin")
	private String campusAdmin;

    @SQLString(name="terminal")
	private String terminal;

    
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

	public void setExist(Integer exist){
		this.exist = exist;
	}

	public Integer getExist(){
		return exist;
	}

	public void setNeaten_id(String neaten_id){
		this.neaten_id = neaten_id;
	}

	public String getNeaten_id(){
		return neaten_id;
	}

	public void setNeaten_name(String neaten_name){
		this.neaten_name = neaten_name;
	}

	public String getNeaten_name(){
		return neaten_name;
	}

	public void setPrincipal(String principal){
		this.principal = principal;
	}

	public String getPrincipal(){
		return principal;
	}

	public void setHappen_time(Date happen_time){
		this.happen_time = happen_time;
	}

	public Date getHappen_time(){
		return happen_time;
	}

	public void setNeaten_instance(String neaten_instance){
		this.neaten_instance = neaten_instance;
	}

	public String getNeaten_instance(){
		return neaten_instance;
	}

	public void setUpdate_time(Date update_time){
		this.update_time = update_time;
	}

	public Date getUpdate_time(){
		return update_time;
	}

	public void setDate(Date date){
		this.date = date;
	}

	public Date getDate(){
		return date;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return remark;
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
