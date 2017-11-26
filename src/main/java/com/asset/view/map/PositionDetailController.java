package com.asset.view.map;

import controller.model.Position;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PositionDetailController {

	@FXML
	TextField province;
	
	@FXML
	TextField city;
	
	@FXML
	TextField district;
	
	@FXML
	TextField street;
	
	@FXML
	TextField streetNumber;
	
	@FXML
	TextField lng;
	
	@FXML
	TextField lat;
	
	Position position;
	
	public PositionDetailController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	 private void initialize() {
		
	}
	
	public void setPosition(Position position){
		System.out.println("position="+position.getProvince());
		province.setText(position.getProvince());
		city.setText(position.getCity());
		district.setText(position.getDistrict());
		street.setText(position.getStreet());
		streetNumber.setText(position.getStreetNumber());
		lng.setText(String.valueOf(position.getLng()));
		lat.setText(String.valueOf(position.getLat()));
	}
	
}
