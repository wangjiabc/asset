package com.asset.property;


import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HiddenProperty {
	 
		private IntegerProperty id;


		private IntegerProperty HiddenLevel;

	 
		private StringProperty ChangeSpeed;

	
		private StringProperty HiddenInstance;


		private DoubleProperty doubletest;

		private FloatProperty floattest;

		private LongProperty longtest;
		
		private StringProperty time;
		
		public IntegerProperty getId() {
			return id;
		}


		public void setId(Integer id) {
			System.out.println("id="+id);
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


		public DoubleProperty getDoubletest() {
			return doubletest;
		}


		public void setDoubletest(Double doubletest) {
			this.doubletest = new SimpleDoubleProperty(doubletest);
		}


		public FloatProperty getFloattest() {
			return floattest;
		}


		public void setFloattest(Float floattest) {
			this.floattest = new SimpleFloatProperty(floattest);
		}


		public LongProperty getLongtest() {
			return longtest;
		}


		public void setLongtest(Long longtest) {
			this.longtest = new SimpleLongProperty(longtest);
		}


		public StringProperty getTime() {
			return time;
		}


		public void setTime(Date time) {
			SimpleDateFormat sdf  =   new  SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " ); 
			String datestr = sdf.format(time); 
			this.time = new SimpleStringProperty(datestr);
		}


		
}
