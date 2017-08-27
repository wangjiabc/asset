package com.asset.view;

import java.net.URL;

import com.asset.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AssetAsSwitch {
	  Main main;
	
	  String filePath;
	  
	 @FXML
	  ImageView bar;
	 	 
	 @FXML
	 ImageView hardImage;
	 
	 @FXML
	 ImageView homepage;
	 
	 @FXML
	 ImageView note;
	 
	 @FXML
	 ImageView report;
	 
	 @FXML
	 ImageView danger;
	 
	 @FXML
	 ImageView file;
	 
	 @FXML
	  Label firstNameLabel;
	 
	 @FXML
	  Label leftTitleLabel;
	
	 @FXML
	 protected void initialize() {
		    URL url = getClass().getResource("");
	    	
	    	String filePath=url.toString()+"Image";
	        Image image = new Image(filePath+"/apple.png");

	        hardImage.setImage(image);
	        
	        image = new Image(filePath+"/bar.jpg");
	        bar.setImage(image);
            firstNameLabel.setText("aaa");
           
            
            image = new Image(filePath+"/note.png");
	        note.setImage(image);
            
	        image = new Image(filePath+"/report.png");
	        report.setImage(image);
	        
	        image = new Image(filePath+"/danger.jpg");
	        danger.setImage(image);
	        
	        image = new Image(filePath+"/file.png");
	        file.setImage(image);
	        
            leftTitleLabel.setText("今日提醒");
            
            initCurrent();
	        
	 }
	 
	 
	 abstract void initCurrent();
	 
	 public void setMain(Main main) {
	        this.main = main;

	    }
	
	 @FXML
	 private void mainButtonAction(){
	     // get a handle to the stage
	    main.showAssetOverview();
	 }
	
	 @FXML
	 private void informButtonAction(){
	     // get a handle to the stage
	    main.showAssetInform();
	 }
	 
	 @FXML
	 private void messageButtonAction(){
	     // get a handle to the stage
	    main.showAssetMessage();
	 }
	 @FXML
	 private void workButtonAction(){
	     // get a handle to the stage
	    main.showAssetWork();
	 }
}
