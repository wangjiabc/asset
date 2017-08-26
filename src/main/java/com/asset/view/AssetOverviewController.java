package com.asset.view;

import java.net.URL;

import com.asset.Main;

import controller.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AssetOverviewController {
	private Main main;
	
	 @FXML
	 private ImageView bar;
	 
	 @FXML
	 private ImageView hardImage;
	 
	 @FXML
	 private Label firstNameLabel;
	 
	 @FXML
	 private Label leftTitleLabel;
	 
	 public AssetOverviewController() {
		// TODO Auto-generated constructor stub
	}
	 
	 @FXML
	 private void initialize() {
		 URL url = getClass().getResource("");
	    	
	    	String filePath=url.toString()+"Image";
	    	System.out.println("file="+filePath);
	        Image image = new Image(filePath+"/apple.png");

	        hardImage.setImage(image);
	        
	        image = new Image(filePath+"/bar.jpg");
	        bar.setImage(image);
           firstNameLabel.setText("aaa");
           
           leftTitleLabel.setText("今日提醒");
	        
	 }
	 
	 public void setMain(Main main) {
	        this.main = main;

	    }
}
