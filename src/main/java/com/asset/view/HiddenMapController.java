package com.asset.view;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONObject;
import com.asset.database.Connect;
import com.asset.view.hidden.HiddenDetailController;
import com.asset.view.map.AppendAssetController;
import com.asset.view.map.AppendAssetsQueryController;
import com.asset.view.map.AppendHiddenQueryController;
import com.asset.view.map.PositionDetailController;
import com.asset.view.map.QueryAssetController;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Position;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Join;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HiddenMapController extends AssetAsSwitch{
	@FXML
	 private Label rightTitleLabel;
	
	 @FXML
	 ImageView imageFile;
	 
	 @FXML
	 Label docLabel;
	
		@FXML
	 	private WebView mapview;
	 	
		@FXML
	 	private WebView mapview2;
		
	 	private WebEngine webEngine;
	 	
	 	private WebEngine webEngine2;
	 	
	 	private static final String mapUrl="http://localhost:8080/voucher/"; 
	 
	 	 Assets assets= new Connect().get();
	 	
	@Override
	void initCurrent() {
		// TODO Auto-generated method stub
		 URL url = getClass().getResource("");
		 String filePath=url.toString()+"Image";
		 Image image = new Image(filePath+"/inform.jpg");
	     homepage.setImage(image);
	     
	     rightTitleLabel.setText("隐患地图");
	     

	 	Assets assets= new Connect().get();

	 		 try {
				final URI loginUri=new URI(mapUrl+"seller/toLogin.do");
				final URI betakeUri=new URI(mapUrl+"baiduMap/get.do");
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		 
	 		/* BasicNameValuePair user;
	 		 BasicNameValuePair password;
	 		 String c;
	 		 user = new BasicNameValuePair("campusAdmin", "1");
	 	     password = new BasicNameValuePair("password", "123");
	 	     c=HttpTools.getCookie(loginUri, user, password);
	 	     Map<String, List<String>> headers = new LinkedHashMap<String, List<String>>();
	 	     headers.put("Set-Cookie", Arrays.asList(c));
	 	     try{
	 	         java.net.CookieHandler.getDefault().put(betakeUri, headers);
	 	      }catch(NullPointerException e) {
	 				// TODO: handle exception
	 		 }
	 	     */
	 	     webEngine=mapview.getEngine();
	 	     
	 	     webEngine2=mapview2.getEngine();
	 	
				webEngine.load(mapUrl+"baidumap/queryMap.html");
				mapview.getEngine().setOnAlert((WebEvent<String> wEvent)->{
					System.out.println(wEvent);
					queryTable(wEvent.getData());
				});
			
				
				webEngine2.load(mapUrl+"baidumap/appendMap.html");
				mapview2.getEngine().setOnAlert((WebEvent<String> wEvent)->{
					System.out.println(wEvent);
					appendTable(wEvent.getData());
					webEngine.reload();
				});
				
	}

	
	
	 private void appendTable(String webEvent){
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
	            loader.setLocation(AppendAssetsQueryController.class.getResource("AppendHiddenQuery.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("选择要添加位置的资产");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            AppendHiddenQueryController controller = loader.getController();
	            
	            controller.setPosition(position);
	            controller.setDialogStage(dialogStage);
	            
	            // Show the dialog and wait until the user closes it
	            dialogStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	
	 
	 
	 private void queryTable(String webEvent){
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
	            loader.setLocation(AssetOverviewController.class.getResource("hidden/HiddenDetail.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("隐患");
	            dialogStage.initModality(Modality.APPLICATION_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            HiddenDetailController controller = loader.getController();
	            controller.setDialogStage(dialogStage);

	            Map searchMap=new HashMap<>();
	            
	            searchMap.put("[Assets].[dbo].[Position].lng=", lng);
	            searchMap.put("[Assets].[dbo].[Position].lat=", lat);
	            
	   	        List<Hidden_Join> hidden_Jions= assets.selectHiddenOfMap(searchMap);
	   	        
	   	        Hidden_Join hidden_Join=hidden_Jions.get(0);
	   	        
	   	        controller.setHidden(hidden_Join);
	   	        
	            // Show the dialog and wait until the user closes it
	            dialogStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	 
}