package com.asset.property;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HiddenDataProperty {
	private IntegerProperty id;
	private StringProperty guid;
	private StringProperty name;
	private StringProperty type;
	private StringProperty url;
	private StringProperty date;

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

	public StringProperty getName() {
		return name;
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

	public StringProperty getType() {
		return type;
	}

	public void setType(StringProperty type) {
		this.type = type;
	}

	public StringProperty getUrl() {
		return url;
	}

	public void setUrl(StringProperty url) {
		this.url = url;
	}

	public StringProperty getDate() {
		return date;
	}

	public void setDate(StringProperty date) {
		this.date = date;
	}

	public void setDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
		String datestr = sdf.format(date);
		this.date = new SimpleStringProperty(datestr);
	}

}
