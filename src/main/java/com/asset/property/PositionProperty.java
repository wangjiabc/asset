package com.asset.property;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

public class PositionProperty {
	private StringProperty guid;
	private StringProperty province;
	private StringProperty city;
	private StringProperty district;
	private StringProperty street;
	private StringProperty street_number;
	private DoubleProperty lng;
	private DoubleProperty lat;
	private StringProperty date;
	public StringProperty getGuid() {
		return guid;
	}
	public void setGuid(StringProperty guid) {
		this.guid = guid;
	}
	public StringProperty getProvince() {
		return province;
	}
	public void setProvince(StringProperty province) {
		this.province = province;
	}
	public StringProperty getCity() {
		return city;
	}
	public void setCity(StringProperty city) {
		this.city = city;
	}
	public StringProperty getDistrict() {
		return district;
	}
	public void setDistrict(StringProperty district) {
		this.district = district;
	}
	public StringProperty getStreet() {
		return street;
	}
	public void setStreet(StringProperty street) {
		this.street = street;
	}
	public StringProperty getStreet_number() {
		return street_number;
	}
	public void setStreet_number(StringProperty street_number) {
		this.street_number = street_number;
	}
	public DoubleProperty getLng() {
		return lng;
	}
	public void setLng(DoubleProperty lng) {
		this.lng = lng;
	}
	public DoubleProperty getLat() {
		return lat;
	}
	public void setLat(DoubleProperty lat) {
		this.lat = lat;
	}
	public StringProperty getDate() {
		return date;
	}
	public void setDate(StringProperty date) {
		this.date = date;
	}
	

}
