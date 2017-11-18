package com.asset.property;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HiddenProperty {
	 
		private IntegerProperty id;


		private IntegerProperty HiddenLevel;

	 
		private StringProperty ChangeSpeed;

	
		private StringProperty HiddenInstance;


		public IntegerProperty getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = new SimpleIntegerProperty(id);
		}


		public IntegerProperty getHiddenLevel() {
			return HiddenLevel;
		}


		public void setHiddenLevel(Integer hiddenLevel) {
			HiddenLevel = new SimpleIntegerProperty(hiddenLevel);
		}


		public StringProperty getChangeSpeed() {
			return ChangeSpeed;
		}


		public void setChangeSpeed(String changeSpeed) {
			ChangeSpeed = new SimpleStringProperty(changeSpeed);
		}


		public StringProperty getHiddenInstance() {
			return HiddenInstance;
		}


		public void setHiddenInstance(String hiddenInstance) {
			HiddenInstance = new SimpleStringProperty(hiddenInstance);
		}


		
}
