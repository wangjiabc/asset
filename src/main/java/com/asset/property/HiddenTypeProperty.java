package com.asset.property;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HiddenTypeProperty {
	
	private IntegerProperty id;
	private StringProperty hidden_type;
	
	public IntegerProperty getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}
	public StringProperty getHidden_type() {
		return hidden_type;
	}
	public void setHidden_type(String hidden_type) {
		this.hidden_type = new SimpleStringProperty(hidden_type);
	}

}
