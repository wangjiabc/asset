package com.voucher.manage.daoModelJoin;

import java.io.Serializable;
import java.util.Date;

import com.voucher.manage.daoSQL.annotations.SQLDateTime;
import com.voucher.manage.daoSQL.annotations.SQLDouble;
import com.voucher.manage.daoSQL.annotations.SQLString;

public class RoomInfo_Position implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@SQLString(name="GUID")
	private String GUID;
	
	@SQLString(name="Address")
	private String Address;
	
	@SQLString(name="Num")
	private String Num;
	
	@SQLString(name="Region")
	private String Region;
	
	@SQLDateTime(name="InDate")
	private Date InDate;
	
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
	
	@SQLString(name="ManageRegion")
    private String ManageRegion;
	
	public String getGUID() {
		return GUID;
	}

	public void setGUID(String gUID) {
		GUID = gUID;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getRegion() {
		return Region;
	}

	public void setRegion(String region) {
		Region = region;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Date getInDate() {
		return InDate;
	}

	public void setInDate(Date inDate) {
		InDate = inDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet_number() {
		return street_number;
	}

	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNum() {
		return Num;
	}

	public void setNum(String num) {
		Num = num;
	}
	
	public void setManageRegion(String ManageRegion){
		this.ManageRegion = ManageRegion;
	}

	public String getManageRegion(){
		return ManageRegion;
	}
	
}
