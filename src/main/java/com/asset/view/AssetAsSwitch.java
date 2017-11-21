package com.asset.view;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import com.asset.Main;
import com.asset.view.hidden.HiddenDetailController;
import com.asset.view.hidden.HiddenQueryController;
import com.asset.view.infowrite.InfoWriteController;
import com.voucher.manage.daoModel.Assets.Hidden;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	  Button hiddenWrite;
	 
	 @FXML
	  Button hiddenQuery;
	 
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
            
            
            hiddenWrite.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					try {
						   FXMLLoader loader = new FXMLLoader();
				            loader.setLocation(AssetAsSwitch.class.getResource("infowrite/InfoWrite.fxml"));
				            AnchorPane page = (AnchorPane) loader.load();

				            // Create the dialog Stage.
				            Stage dialogStage = new Stage();
				            dialogStage.setTitle("信息录入");
				            dialogStage.initModality(Modality.WINDOW_MODAL);
				            Scene scene = new Scene(page);
				            dialogStage.setScene(scene);

				            // Set the person into the controller.
				            InfoWriteController controller = loader.getController();
				            controller.setDialogStage(dialogStage);
				            
				            // Show the dialog and wait until the user closes it
				            dialogStage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
            
            hiddenQuery.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					try {
						 // Load the fxml file and create a new stage for the popup dialog.
			            FXMLLoader loader = new FXMLLoader();
			            loader.setLocation(AssetAsSwitch.class.getResource("hidden/HiddenQuery.fxml"));
			            AnchorPane page = (AnchorPane) loader.load();

			            // Create the dialog Stage.
			            Stage dialogStage = new Stage();
			            dialogStage.setTitle("隐患查询");
			            dialogStage.initModality(Modality.WINDOW_MODAL);
			            Scene scene = new Scene(page);
			            dialogStage.setScene(scene);

			            // Set the person into the controller.
			            HiddenQueryController controller = loader.getController();
			            controller.setDialogStage(dialogStage);
			            
			            // Show the dialog and wait until the user closes it
			            dialogStage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
            
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
