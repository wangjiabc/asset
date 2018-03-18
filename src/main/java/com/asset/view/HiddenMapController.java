package com.asset.view;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONObject;
import com.asset.Singleton;
import com.asset.database.Connect;
import com.asset.tool.MyTestUtil;
import com.asset.view.hidden.HiddenDetailController;
import com.asset.view.map.AppendAssetsQueryController;
import com.asset.view.map.AppendHiddenQueryController;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Position;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Join;
import com.voucher.manage.model.Users;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.Alert.AlertType;
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
		
		@FXML
	 	private WebView mapview3;
		
		//@FXML
	 	//private WebView mapview4;
		
		@FXML
	 	private Tab HiddenQuery;
		
		@FXML
	 	private Tab HiddenAppend;
		
		@FXML
	 	private Tab AssetMap;
		
		@FXML
	 	private Tab PatrolMap;
		
		 @FXML
		  Button hiddenWrite;
		
	 	private WebEngine webEngine;
	 	
	 	private WebEngine webEngine2;
	 	
	 	private WebEngine webEngine3;
	 	
	 	//private WebEngine webEngine4;
	 	
	 	private static final String mapUrl=Singleton.getInstance().getMapUrl();
	 
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

	 	hiddenWrite.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				main.showAssetOverview();
			}
			
		});
	 	
	 	
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
	 		
	 		webEngine3=mapview3.getEngine();
	 		
	 		//webEngine4=mapview4.getEngine();
	 		
	 		webEngine.load(mapUrl+"baidumap/queryMap.html");
	 		
			mapview.getEngine().setOnAlert((WebEvent<String> wEvent)->{
				System.out.println(wEvent);
				queryTable(wEvent.getData());
			});
			
			
			HiddenQuery.setOnSelectionChanged(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub						 	    	 	
					webEngine.reload();
				}
				
			}); 
			
			webEngine2.load(mapUrl+"baidumap/appendMap.html");
			
			mapview2.getEngine().setOnAlert((WebEvent<String> wEvent)->{
				System.out.println(wEvent);
				appendTable(wEvent.getData());
				webEngine.reload();
			});

			
	 		webEngine3.load(mapUrl+"baidumap/assetMap.html");
	 		
	 		mapview3.getEngine().setOnAlert((WebEvent<String> wEvent)->{
				System.out.println(wEvent);
				appendAssetTable(wEvent.getData());
			});
	 		
	 		//webEngine4.load(mapUrl+"baidumap/patrolMap.html");
	 		
				
	}

	
	
	 private void appendTable(String webEvent){
		 JSONObject jsonObject=JSONObject.parseObject(webEvent);
		 String type=jsonObject.getString("type");		//百度地图事件类型
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
			 
			 if(Singleton.getInstance().getHidden_User().getPurview()>2){
					Alert alert2 = new Alert(AlertType.WARNING);
					alert2.setTitle("警告对话框");
					alert2.setHeaderText("警告");
					alert2.setContentText("你没有修改位置的权限");
					alert2.showAndWait();
					mapview2.getEngine().executeScript("script()");
					return ;
				}
			 
			  if(type.equals("onclick")){
				  
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(AppendAssetsQueryController.class.getResource("AppendHiddenQuery.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("选择要添加位置的隐患");
	            dialogStage.initModality(Modality.APPLICATION_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            AppendHiddenQueryController controller = loader.getController();
	            
	            controller.setPosition(position,mapview2);
	            controller.setDialogStage(dialogStage);
	            
	            // Show the dialog and wait until the user closes it
	            dialogStage.show();
	            
			  }else if(type.equals("ondragend")){
				  
				  JSONObject ja=JSONObject.parseObject(jsonObject.getString("ja"));
				  
				  String slng=ja.getString("lng");
				  String slat=ja.getString("lat");
				  
				  String GUID=assets.getGUIDByPosition(slng, slat);
				  
				  int i=0;
				  
				  if(GUID!=null){				  
					  Date date=new Date();
					  position.setGUID(GUID);
					  position.setDate(date);					  
					  i=assets.updatePosition(position);
				  }
				  				  
				  if(i==0){
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("异常堆栈对话框0");
						alert.setHeaderText("错误");
						alert.setContentText("写入失败");
						alert.showAndWait();
					}
				  
				  mapview2.getEngine().executeScript("script()");
				  
			  }
			   
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
	            
	            searchMap.put("[Position].lng=", lng);
	            searchMap.put("[Position].lat=", lat);
	            
	   	        List<Hidden_Join> hidden_Jions= assets.selectHiddenOfMap(searchMap);
	   	        
	   	        Hidden_Join hidden_Join=hidden_Jions.get(0);
	   	    
	   	        Map searchMap0=new HashMap<>();
	  		  
	  		    searchMap0.put("[Hidden_Assets].hidden_GUID=", hidden_Join.getGUID());
	  		    Map searchMap2=new HashMap<>();
	  		    searchMap2.put("[Hidden_Check].GUID=", hidden_Join.getGUID());
	  		    Map searchMap3=new HashMap<>();
	  		    searchMap3.put("[Hidden_Neaten].GUID=", hidden_Join.getGUID());
	  		    
	  		    controller.setTableView(null,0,10,searchMap,searchMap0,searchMap2,searchMap3,null,null, null, null, null, null, null, null, null,null,null,null,null);
	            
	  		    Map searchMap4=new HashMap<>();
	  		    searchMap4.put("[Hidden].GUID=", hidden_Join.getGUID());
	  		    Map map=assets.selectAllHidden_Jion(10, 0, "date", "desc", searchMap4);
	            
	   	        hidden_Jions= (List<Hidden_Join>) map.get("rows");
	            
	            Iterator<Hidden_Join> iterator=hidden_Jions.iterator();
	            
	            Hidden_Join hidden_Join2=null;
	            
	            while(iterator.hasNext()){
	            	Hidden_Join h=iterator.next();
	            	try{
	            	 if(hidden_Join.getGUID().equals(h.getGUID())){
	            		 hidden_Join2=h;
	            		 
	            		 if(hidden_Join2.getTerminal()!=null&&hidden_Join2.getTerminal().equals("Wechat")){
	            				try{
	            					String openId=hidden_Join2.getCampusAdmin();	            		
	            					//在详情里把openid换成昵称
	            					System.out.println("openId="+openId);
	            					Users users=assets.getWetchatUsers(openId);	      
	            					MyTestUtil.print(users);
	            					hidden_Join2.setCampusAdmin(users.getNickname());
	            				}catch (Exception e) {
								// TODO: handle exception
	            					e.printStackTrace();
	            				}
	            		}
	    	            controller.setHidden(hidden_Join2);
	            		break;
	            	  }
	            	}catch (NullPointerException e) {
						// TODO: handle exception
					}
	            }
	  		    
	            // Show the dialog and wait until the user closes it
	            dialogStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 
	 private void appendAssetTable(String webEvent){
		 JSONObject jsonObject=JSONObject.parseObject(webEvent);
		 String type=jsonObject.getString("type");		//百度地图事件类型
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
			 
			 if(Singleton.getInstance().getHidden_User().getPurview()>2){
					Alert alert2 = new Alert(AlertType.WARNING);
					alert2.setTitle("警告对话框");
					alert2.setHeaderText("警告");
					alert2.setContentText("你没有修改位置的权限");
					alert2.showAndWait();
					mapview3.getEngine().executeScript("script()");
					return ;
				}
			 
			 if(type.equals("onclick")){

	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(AppendAssetsQueryController.class.getResource("AppendAssetsQuery.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("选择要添加位置的资产");
	            dialogStage.initModality(Modality.APPLICATION_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            AppendAssetsQueryController controller = loader.getController();
	            
	            controller.setPosition(position,mapview3);
	            controller.setDialogStage(dialogStage);
	            
	            // Show the dialog and wait until the user closes it
	            dialogStage.show();
	            
			 }else if(type.equals("ondragend")){
				  
				  JSONObject ja=JSONObject.parseObject(jsonObject.getString("ja"));
				  
				  String slng=ja.getString("lng");
				  String slat=ja.getString("lat");
				  
				  String GUID=assets.getGUIDByPosition(slng, slat);
				  
				  int i=0;
				  
				  if(GUID!=null){				  
					  Date date=new Date();
					  position.setGUID(GUID);
					  position.setDate(date);					  
					  i=assets.updatePosition(position);
				  }
				  				  
				  if(i==0){
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("异常堆栈对话框0");
						alert.setHeaderText("错误");
						alert.setContentText("写入失败");
						alert.showAndWait();
					}
				  
				  mapview3.getEngine().executeScript("script()");
				  
			  }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	 
}
