package com.asset.view.infowrite;


import java.util.Date;

import org.controlsfx.dialog.Dialogs;

import com.asset.database.Connect;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Hidden;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.Alert.AlertType;

public class InfoWriteController {
	@FXML
	private TextField hiddenlevel;
	@FXML
	private TextField changespeed;
	@FXML
	private TextField hiddneinstance;
	@FXML
	private TextField doubletest;
	@FXML
	private TextField floattest;
	@FXML
	private TextField longtest;
	
	@FXML
	private Button post;
	
	private Stage dialogStage;
	
	public InfoWriteController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
    private void initialize() {
		
		//限制输入为数字
		 hiddenlevel.textProperty().addListener(new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		            if (!newValue.matches("\\d*")) {
		                hiddenlevel.setText(newValue.replaceAll("[^\\d]", ""));
		            }
		        }
		    });
		
		
		//限制输入为浮点数
		 doubletest.textProperty().addListener(new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		            if (!newValue.matches("[0-9]*(\\.?)[0-9]*")) {
		                doubletest.setText(newValue.replaceAll("[^\\d*(\\.?)]", ""));
		            }
		        }
		    });
		 
		 
		//限制输入为浮点数
		 floattest.textProperty().addListener(new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		            if (!newValue.matches("[0-9]*(\\.?)[0-9]*")) {
		                floattest.setText(newValue.replaceAll("[^\\d*(\\.?)]", ""));
		            }
		        }
		    });
		 
		//限制输入为数字
		 longtest.textProperty().addListener(new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		            if (!newValue.matches("\\d*")) {
		                longtest.setText(newValue.replaceAll("[^\\d]", ""));
		            }
		        }
		    });
		 
		 
		post.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Hidden hidden=new Hidden();
				Date date=new Date();
				try{
					if(!hiddenlevel.getText().equals(""))
					hidden.setHiddenLevel(Integer.parseInt(hiddenlevel.getText()));
					if(!changespeed.getText().equals(""))
					hidden.setChangeSpeed(changespeed.getText());
					if(!hiddneinstance.getText().equals(""))
					hidden.setHiddenInstance(hiddneinstance.getText());
					if(!doubletest.getText().equals(""))
					hidden.setDoubletest(Double.parseDouble(doubletest.getText()));
					if(!floattest.getText().equals(""))
					hidden.setFloattest(Float.parseFloat(floattest.getText()));
					if(!longtest.getText().equals(""))
					hidden.setLongtest(Long.parseLong(longtest.getText()));
					hidden.setTime(date);
				
				Assets assets=new Connect().getAssets();
				
				int i=assets.insertIntoHidden(hidden);
				
				
							 				
				if(i==0){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框");
					alert.setHeaderText("错误");
					alert.setContentText("写入失败");
					alert.showAndWait();
				}else if(i==1){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("对话框");
					alert.setHeaderText("插入数据");
					alert.setContentText("写入成功");
					alert.showAndWait();
					handleCancel();
				 }
				}catch (Exception e) {
					// TODO: handle exception
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框");
					alert.setHeaderText("错误");
					alert.setContentText("写入失败");
					alert.showAndWait();
				}
			}
		});
	}
	
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
	
	@FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
