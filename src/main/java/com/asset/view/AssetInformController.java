package com.asset.view;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class AssetInformController extends AssetAsSwitch{

	@FXML
	 private Label rightTitleLabel;
	
	 public AssetInformController() {
		// TODO Auto-generated constructor stub
		 super();
	}
	 
	 @Override
	 protected void initCurrent() {
		 URL url = getClass().getResource("");
		 String filePath=url.toString()+"Image";
		 Image image = new Image(filePath+"/inform.jpg");
	     homepage.setImage(image);
	     
	     rightTitleLabel.setText("公告通知");
	 }
}
