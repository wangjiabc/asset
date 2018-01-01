package com.asset.view;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import com.asset.Main;
import com.asset.Singleton;
import com.asset.database.Connect;
import com.asset.view.hidden.HiddenDetailController;
import com.asset.view.infowrite.InfoWriteController2;
import com.asset.view.map.BaiduMapController;
import com.rmi.server.Assets;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	  Label firstName0Label;
	 
	 @FXML
	  Label firstName2Label;
	 
	 @FXML
	  Label firstName22Label;
	 
	 @FXML
	  Label firstName3Label;
	
	 @FXML
	  Label firstName4Label;
	
	 @FXML
	  Label firstName5Label;
	
	 @FXML
	  Label firstName6Label;
	 
	 
	 @FXML
	  Label leftTitleLabel;
	
	 @FXML
	  Button hiddenWrite;
	 
	 @FXML
	  Button hiddenMap;
	 
	 @FXML
	 Button exitButton;
	 
	 Assets assets= new Connect().get();
	 
	 @FXML
	 protected void initialize() {
		    URL url = getClass().getResource("");
	    	
	    	String filePath=url.toString()+"Image";
	        Image image = new Image(filePath+"/people.jpg");

	        hardImage.setImage(image);
	        
	        image = new Image(filePath+"/bar.jpg");
	        bar.setImage(image);
	        
	        firstName0Label.setText("兴泸资产管理有限公司");
	        
	        String userName=Singleton.getInstance().getHidden_User().getPrincipal_name();
	        String business=Singleton.getInstance().getHidden_User().getBusiness();
            firstName2Label.setText(userName);
            if(!userName.equals("admin")){
            	firstName22Label.setText("("+business+")");
            }else {
            	firstName22Label.setText("(系统管理员)");
			}
            int not=assets.findNotHidden();
            
            firstName3Label.setText(String.valueOf(not)+"处");
            
            int in=assets.findInHidden();
            
            firstName4Label.setText(String.valueOf(in)+"处");
            
            String last=assets.findLastHidden();
            
            firstName5Label.setText(String.valueOf(last));
            
            String ignore=assets.findIgnoreHidden();
            
            firstName6Label.setText(String.valueOf(ignore)+"天");
            
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
					if(Singleton.getInstance().getHidden_User().getPurview()>2){
						Alert alert2 = new Alert(AlertType.WARNING);
						alert2.setTitle("警告对话框");
						alert2.setHeaderText("警告");
						alert2.setContentText("你没有新建隐患的权限");
						alert2.showAndWait();
						return ;
					}
					try {
						   FXMLLoader loader = new FXMLLoader();
				            loader.setLocation(AssetAsSwitch.class.getResource("infowrite/InfoWrite2.fxml"));
				            AnchorPane page = (AnchorPane) loader.load();

				            // Create the dialog Stage.
				            Stage dialogStage = new Stage();
				            dialogStage.setTitle("信息录入");
				            dialogStage.initModality(Modality.APPLICATION_MODAL);
				            Scene scene = new Scene(page);
				            dialogStage.setScene(scene);

				            // Set the person into the controller.
				            InfoWriteController2 controller = loader.getController();
				            controller.setDialogStage(dialogStage);
				            
				            // Show the dialog and wait until the user closes it
				            dialogStage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
                  
            
            exitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Platform.exit();
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
	 @FXML
	 private void hiddenButtonAction(){
	     // get a handle to the stage
	    main.showHiddenQuery();
	 }
	 @FXML
	 private void hiddenMapAction(){
		 main.showHiddenMap();
	 }
}
