package com.asset.view.hidden;

import java.util.Date;

import com.asset.database.Connect;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class HiddenDetailController {
	@FXML
	private TextField id;
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
	private TextField time;
	
	@FXML
	private Button update;
	
	private Hidden hidden;
	
	private Stage dialogStage;
	
	public HiddenDetailController() {
		// TODO Auto-generated constructor stub
	}
	
	 @FXML
	 private void initialize() {
		 update.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Date date=new Date();
				Hidden2 hidden2=new Hidden2();
				hidden2.setId(hidden.getId());
				try{
					if(!hiddenlevel.getText().equals(""))
					hidden2.setHiddenLevel(Integer.parseInt(hiddenlevel.getText()));
					if(!changespeed.getText().equals(""))
					hidden2.setChangeSpeed(changespeed.getText());
					if(!hiddneinstance.getText().equals(""))
					hidden2.setHiddenInstance(hiddneinstance.getText());
					if(!doubletest.getText().equals(""))
					hidden2.setDoubletest(Double.parseDouble(doubletest.getText()));
					if(!floattest.getText().equals(""))
					hidden2.setFloattest(Float.parseFloat(floattest.getText()));
					if(!longtest.getText().equals(""))
					hidden2.setLongtest(Long.parseLong(longtest.getText()));
					hidden2.setTime(date);
				
				Assets assets=new Connect().getAssets();
				
				
				int i=assets.updateHidden(hidden2);
				
				
							 				
				if(i==0){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框0");
					alert.setHeaderText("错误");
					alert.setContentText("写入失败");
					alert.showAndWait();
				}else if(i==1){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("对话框");
					alert.setHeaderText("插入数据");
					alert.setContentText("写入成功");
					alert.showAndWait();
				 }
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框2");
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
	 
	 /**
	     * Called when the user clicks cancel.
	     */
	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }
	    
	 public void setHidden(Hidden hidden){
		 this.hidden=hidden;
		 id.setText(String.valueOf(hidden.getId()));
		 hiddenlevel.setText(String.valueOf(hidden.getHiddenLevel()));
		 hiddneinstance.setText(hidden.getHiddenInstance());
		 changespeed.setText(hidden.getChangeSpeed());
		 doubletest.setText(String.valueOf(hidden.getDoubletest()));
		 floattest.setText(String.valueOf(hidden.getFloattest()));
		 longtest.setText(String.valueOf(hidden.getLongtest()));
		 time.setText(String.valueOf(hidden.getTime()));
	 }
}
