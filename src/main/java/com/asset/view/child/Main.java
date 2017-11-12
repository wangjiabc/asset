package com.asset.view.child;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("DeviceView.fxml"));
		Scene frame = new Scene(root);
		primaryStage.isResizable();
		primaryStage.setTitle("Bank Business");
		primaryStage.setScene(frame);
		primaryStage.centerOnScreen();
		primaryStage.show();
	
	} 

	
	public static void main(String args[]) {
		new Main().launch("");
	}
}
