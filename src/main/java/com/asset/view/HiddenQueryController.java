package com.asset.view;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class HiddenQueryController extends AssetAsSwitch{
	
	@FXML
	 private Label rightTitleLabel;
	
	 public HiddenQueryController() {
		// TODO Auto-generated constructor stub
		 super();
	}
	 
	 @Override
	 protected void initCurrent() {
		 URL url = getClass().getResource("");
		 String filePath=url.toString()+"Image";
		 Image image = new Image(filePath+"/search.png");
	     homepage.setImage(image);
	     
	     rightTitleLabel.setText("隐患查询");
	 }
	 
}
