package com.asset.view;

import org.controlsfx.dialog.Dialogs;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;

public class RootLayoutController {
	@FXML
	private MenuItem exitItem;
	
	@FXML
	private MenuItem aboutItem;
	
	@FXML
	private void initialize() {
		exitItem.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Platform.exit();
			}
		});
		
		aboutItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("关于");
				alert.setHeaderText("四川帕岸信息科技有限公司");
				alert.setContentText("总经理 张征新");

				alert.showAndWait();
			}
		});
	}
}
