package com.asset.property;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class HiddenLevelProperty {
	private IntegerProperty id;
	private IntegerProperty hidden_level;
	private StringProperty level_text;
	private IntegerProperty state;
	public IntegerProperty getId() {
		return id;
	}
	public void setId(IntegerProperty id) {
		this.id = id;
	}
	public IntegerProperty getHidden_level() {
		return hidden_level;
	}
	public void setHidden_level(IntegerProperty hidden_level) {
		this.hidden_level = hidden_level;
	}
	public StringProperty getLevel_text() {
		return level_text;
	}
	public void setLevel_text(StringProperty level_text) {
		this.level_text = level_text;
	}
	public IntegerProperty getState() {
		return state;
	}
	public void setState(IntegerProperty state) {
		this.state = state;
	}

}
