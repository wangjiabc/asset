package com.asset.view.map;

import java.util.Date;

import com.asset.database.Connect;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Position;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class PositionDetailController {

	@FXML
	TextField province;
	
	@FXML
	TextField city;
	
	@FXML
	TextField district;
	
	@FXML
	TextField street;
	
	@FXML
	TextField lng;
	
	@FXML
	TextField lat;
	
	@FXML
	Label text;
	
	@FXML
	Button button;
	
	@FXML
	Button cancel;
	
	private Stage dialogStage;
	
	private Position position;
	
	private String Address;
	
	private String GUID;
	
	@FXML
	private WebView mapview2;
	
	public PositionDetailController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	private void initialize() {
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				Assets assets=new Connect().get();
				
			 try{
				Date date=new Date();
				position.setDate(date);
				int i=assets.updatePosition(position);
				
				if(i==0){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框0");
					alert.setHeaderText("错误");
					alert.setContentText("写入失败");
					alert.showAndWait();
				}else if(i==1){
					/*
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("对话框");
					alert.setHeaderText("更新数据");
					alert.setContentText("写入成功");
					alert.showAndWait();
					*/
					try{
						mapview2.getEngine().executeScript("script()");
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					handleCancel();
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
		
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				handleCancel();
			}
			
		});
		
	}
	
	public void setPosition(Position position,String Address,String GUID,WebView mapview2){
		this.position=position;
		this.Address=Address;
		this.GUID=GUID;
		this.mapview2=mapview2;
		text.setText("你要更新"+Address+"的位置为");
		province.setText(position.getProvince());
		city.setText(position.getCity());
		district.setText(position.getDistrict());
		street.setText(position.getStreet());
		lng.setText(String.valueOf(position.getLng()));
		lat.setText(String.valueOf(position.getLat()));
	}
	
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
	
	  @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }
	
}
