package com.asset.view;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AssetOverviewController extends AssetAsSwitch{
	
	 @FXML
	 private ChoiceBox choiceBox;
	
	 @FXML
	 private ImageView search;
	 
	 @FXML
	 private Label rightTitleLabel;
	 
	 public AssetOverviewController() {
		// TODO Auto-generated constructor stub
		 super(); 
	}
	 
	 @Override
	 protected void initCurrent() {
		 URL url = getClass().getResource("");
		 String filePath=url.toString()+"Image";
		 Image image = new Image(filePath+"/home.jpg");
	     homepage.setImage(image);
	     
	     image=new Image(filePath+"/search.png");
	     search.setImage(image);
	     
	     rightTitleLabel.setText("主页");
	     
	     choiceBox.getItems().addAll("q","e"," ");
	 }
}
