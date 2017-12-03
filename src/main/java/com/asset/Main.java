package com.asset;

import java.io.IOException;


import com.asset.view.AssetInformController;
import com.asset.view.AssetMessageController;
import com.asset.view.AssetOverviewController;
import com.asset.view.AssetWorkController;
import com.asset.view.HiddenQueryController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application{
	    private Stage primaryStage;
	    private BorderPane rootLayout;
	    
	    public Main() {
	    	
	    }
	    
	    @Override
	    public void start(Stage primaryStage) {
	        this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("安全隐患监管系统");

	        initRootLayout();
	        
	       showAssetOverview();

	    }

	    /**
	     * Initializes the root layout.
	     */
	    public void initRootLayout() {
	        try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
	            rootLayout = (BorderPane) loader.load();

	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout);
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * Shows the person overview inside the root layout.
	     */
	    //主界面
	    public void showAssetOverview() {
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("view/AssetOverview.fxml"));
	            AnchorPane personOverview = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(personOverview);

	            // Give the controller access to the main app.
	            AssetOverviewController controller = loader.getController();
	            controller.setMain(this);

	        } catch (IOException e) {
	            e.printStackTrace();

	        }
	    }


	  //messgae界面
	    public void showAssetMessage() {
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("view/AssetMessage.fxml"));
	            AnchorPane personOverview = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(personOverview);

	            // Give the controller access to the main app.
	            AssetMessageController controller = loader.getController();
	            controller.setMain(this);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	  //inform界面
	    public void showAssetInform() {
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("view/AssetInform.fxml"));
	            AnchorPane personOverview = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(personOverview);

	            // Give the controller access to the main app.
	            AssetInformController controller = loader.getController();
	            controller.setMain(this);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	  //work界面
	    public void showAssetWork() {
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("view/AssetWork.fxml"));
	            AnchorPane personOverview = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(personOverview);

	            // Give the controller access to the main app.
	            AssetWorkController controller = loader.getController();
	            controller.setMain(this);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    //work界面
	    public void showHiddenQuery() {
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("view/HiddenQuery.fxml"));
	            AnchorPane personOverview = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(personOverview);

	            // Give the controller access to the main app.
	            HiddenQueryController controller = loader.getController();
	            controller.setMain(this);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void main(String args[]) {
			new Main().launch("");
		}
}
