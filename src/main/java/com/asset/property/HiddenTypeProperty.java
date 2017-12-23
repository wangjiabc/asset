package com.asset.property;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class HiddenTypeProperty {
	private IntegerProperty id;
	private StringProperty hidden_type;
	public IntegerProperty getId() {
		return id;
	}
	public void setId(IntegerProperty id) {
		this.id = id;
	}
	public StringProperty getHidden_type() {
		return hidden_type;
	}
	public void setHidden_type(StringProperty hidden_type) {
		this.hidden_type = hidden_type;
	}

}
