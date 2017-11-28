package com.asset.view.map;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;

import com.voucher.manage.daoModel.Position;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppendAssetController{

	@FXML
	private WebView mapview;
	
	private WebEngine webEngine;
	
	@FXML
	private Button button;

	public AppendAssetController(WebView mapview,WebEngine webEngine,Button button){
		this.mapview=mapview;
		this.webEngine=webEngine;
		this.button=button;
	}
	
	void initCurrent(){
		 button.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					webEngine.load("http://localhost:8080/voucher/baidumap/appendMap.html");
					mapview.getEngine().setOnAlert((WebEvent<String> wEvent)->{
						//System.out.println(wEvent);
						table(wEvent.getData());
					});
				}
			 });
	}
	
	 private void table(String webEvent){
		 JSONObject jsonObject=JSONObject.parseObject(webEvent);
		 String province=jsonObject.getString("province");
		 String city=jsonObject.getString("city");
		 String district=jsonObject.getString("district");
		 String street=jsonObject.getString("street");
		 String streetNumber=jsonObject.getString("streetNumber");
		 String lng=jsonObject.getString("lng");
		 String lat=jsonObject.getString("lat");
		 
		 Position position=new Position();
		 position.setProvince(province);
		 position.setCity(city);
		 position.setDistrict(district);
		 position.setStreet(streetNumber);
		 position.setStreet(streetNumber);
		 position.setLat(Double.valueOf(lat));
		 position.setLng(Double.valueOf(lng));
		 try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(AppendAssetsQueryController.class.getResource("AppendAssetsQuery.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("选择要添加位置的资产");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            AppendAssetsQueryController controller = loader.getController();
	            
	            controller.setPosition(position);
	            controller.setDialogStage(dialogStage);
	            
	            // Show the dialog and wait until the user closes it
	            dialogStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
}
