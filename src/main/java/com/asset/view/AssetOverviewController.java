package com.asset.view;

import java.net.URL;

import javafx.scene.image.Image;

public class AssetOverviewController extends AssetAsSwitch{
	
	 
	
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
	 }
}
