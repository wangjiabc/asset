package com.asset.view.child;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class DeviceViewController {
	@FXML
	private ComboBox<Person> comboBox;

	
	private ObservableList<Person> comboBoxData = FXCollections.observableArrayList();
	
	public DeviceViewController() {
		// TODO Auto-generated constructor stub
		comboBoxData.add(new Person("Hans", "Muster"));
		comboBoxData.add(new Person("Ruth", "Mueller"));
		comboBoxData.add(new Person("Heinz", "Kurz"));
		comboBoxData.add(new Person("Cornelia", "Meier"));
		comboBoxData.add(new Person("Werner", "Meyer"));
	}
	
	@FXML
	private void initialize() {
		// Init ComboBox
		comboBox.setItems(comboBoxData);
	}

	
	
}
