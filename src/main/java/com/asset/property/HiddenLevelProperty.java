package com.asset.property;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HiddenLevelProperty {
	
	private IntegerProperty id;
	private IntegerProperty hidden_level;
	private StringProperty level_text;
	private IntegerProperty state;
	
	public IntegerProperty getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}
	public IntegerProperty getHidden_level() {
		return hidden_level;
	}
	public void setHidden_level(Integer hidden_level) {
		this.hidden_level = new SimpleIntegerProperty(hidden_level);
	}
	public StringProperty getLevel_text() {
		return level_text;
	}
	public void setLevel_text(String level_text) {
		this.level_text = new SimpleStringProperty(level_text);
	}
	public IntegerProperty getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = new SimpleIntegerProperty(state);
	}

}
