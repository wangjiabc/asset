package com.asset.view;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.poi.ss.formula.functions.T;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asset.Singleton;
import com.asset.database.Connect;
import com.asset.tool.MyTestUtil;
import com.asset.view.hidden.HiddenDetailController;
import com.asset.view.map.AppendAssetsQueryController;
import com.asset.view.map.AppendHiddenQueryController;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.daoModel.Assets.Position;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Join;
import com.voucher.manage.model.Users;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
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
		
		@FXML
	 	private Tab HiddenQuery;
		
		@FXML
	 	private Tab AssetMap;
		
		@FXML
	 	private Tab PatrolMap;
		
		 @FXML
		  Button hiddenWrite;
		
	 	private WebEngine webEngine;
	 	
	 	private WebEngine webEngine2;
	 	
	 	private WebEngine webEngine3;
	 	
	 	private List<RoomInfo> roomInfoLists;
		private String manageRegion;
		private List manageRegions=new ArrayList<>();
		
		private String roomProperty;
		private List roomPropertys=new ArrayList<>();
		
	 	@FXML
	 	private TextField textField1;
	 	@FXML
	 	private Button button1;
	 	@FXML
	 	private ChoiceBox<T> hiddenManageRegion1;	 		 		 	
	 	@FXML
	 	private CheckBox checkBox1;
	 	
	 	@FXML
	 	private TextField textField2;
	 	@FXML
	 	private Button button2;
	 	@FXML
	 	private ChoiceBox<T> hiddenManageRegion2;
	 	@FXML
	 	private ChoiceBox<T> hiddenRoomProperty2;
	 	@FXML
	 	private Label assetCount2;
	 	
	 	@FXML
	 	private ChoiceBox<T> hiddenCheckNames3;
	 	private List<Users> users;
		private Integer usersValue;
	 	@FXML
	 	private ChoiceBox<T> hiddenCheckDate3;
	 	@FXML
	 	private DatePicker startCheckDate3;
	 	@FXML
	 	private DatePicker endCheckDate3;
	 	@FXML
	 	private CheckBox checkBox3;
	 	@FXML
	 	private Label checkCount3;
	 	@FXML
	 	private Button checkSearch3;
	 	
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
	 		
	 		
	 		
	 		roomInfoLists=assets.selectManageRegion();
			 
			Iterator<RoomInfo> iterator1=roomInfoLists.iterator();
			 
			int i=0;
			
			while (iterator1.hasNext()) {
				
					String manage=iterator1.next().getManageRegion();
					if(manage!=null&&!manage.equals("")){
					  manageRegions.add(manage);
					  System.out.println("i="+i);
					  i++;
				}	
					
			}
	 		
			hiddenManageRegion1.setItems(FXCollections.observableArrayList(manageRegions));
			hiddenManageRegion2.setItems(FXCollections.observableArrayList(manageRegions));
			
			button1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					String s=textField1.getText();
					mapview.getEngine().executeScript("search('"+s+"')");
				}
				
			});
			
			hiddenManageRegion1.getSelectionModel().selectedIndexProperty().addListener(new 
					 ChangeListener<Number>() {
						
						@Override
						public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
							// TODO Auto-generated method stub
							int i=(int) newValue;
							String manage=(String) manageRegions.get(i);
							manageRegion=manage;
							System.out.println("i="+i+"  "+manageRegion);
							mapview.getEngine().executeScript("gradeChange('"+manageRegion+"')");
						}
					});
					
			
			checkBox1.selectedProperty().addListener(new ChangeListener<Boolean>() {
		           public void changed(ObservableValue<? extends Boolean> ov,
		                   Boolean old_val, Boolean new_val) {
		                   if(new_val){
		                	   checkBox1.setText("显示文字");
		                	   mapview.getEngine().executeScript("lableShow('1')");
		                   }else{
		                	   checkBox1.setText("隐藏文字"); 
		                	   mapview.getEngine().executeScript("lableShow('0')");
		                   }
		                   checkBox1.setSelected(new_val);
		                   hiddenManageRegion1.getSelectionModel().clearSelection();
		                   manageRegion=null;
		                }
		              });
			
			
			checkBox1.setText("隐藏文字");
			checkBox1.setSelected(false);
			
			webEngine.load(mapUrl+"baidumap/queryMap.html");
	 			 		
	 		
			mapview.getEngine().setOnAlert((WebEvent<String> wEvent)->{
				System.out.println(wEvent);
				JSONObject jsonObject=JSONObject.parseObject(wEvent.getData());
				String type=jsonObject.getString("type");		//百度地图事件类型
				if(type.equals("detail")){ 
					queryTable(wEvent.getData());
				}else if(type.equals("onclick")){
					appendTable(wEvent.getData());
				}
			});
			
			
			HiddenQuery.setOnSelectionChanged(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					checkBox1.setText("隐藏文字");
					checkBox1.setSelected(false);
					
					// TODO Auto-generated method stub						 	    	 	
					webEngine.reload();
				}
				
			}); 
			
			
			/*
			 * 资产地图
			 */

			button2.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					String s=textField2.getText();
					mapview2.getEngine().executeScript("search('"+s+"')");
				}
				
			});
			
			roomInfoLists=assets.selectRoomProperty();
			 
			Iterator<RoomInfo> iterator2=roomInfoLists.iterator();
			 
			i=0;
			
			while (iterator2.hasNext()) {
				
					String roomProperty=iterator2.next().getRoomProperty();
					if(roomProperty!=null&&!roomProperty.equals("")){
					  roomPropertys.add(roomProperty);
					  System.out.println("i="+i);
					  i++;
				}	
					
			}
			
			hiddenManageRegion2.getSelectionModel().selectedIndexProperty().addListener(new 
					 ChangeListener<Number>() {
						
						@Override
						public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
							// TODO Auto-generated method stub
							int i=(int) newValue;
							String manage=(String) manageRegions.get(i);
							manageRegion=manage;
							System.out.println("i="+i+"  "+manageRegion);
							if(manageRegion!=null&&!manageRegion.equals("")&&
									roomProperty!=null&&!roomProperty.equals("")){
								mapview2.getEngine().executeScript("gradeChangeAll('"+manageRegion+"','"+roomProperty+"')");
							}else if(manageRegion!=null&&!manageRegion.equals("")){
								mapview2.getEngine().executeScript("gradeChange1('"+manageRegion+"')");
							}else if(roomProperty!=null&&!roomProperty.equals("")){
								mapview2.getEngine().executeScript("gradeChange2('"+roomProperty+"')");
							}
							getCountAsset();
						}
					});
			
			hiddenRoomProperty2.setItems(FXCollections.observableArrayList(roomPropertys));
			
			hiddenRoomProperty2.getSelectionModel().selectedIndexProperty().addListener(new 
					 ChangeListener<Number>() {
						
						@Override
						public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
							// TODO Auto-generated method stub
							int i=(int) newValue;
							String roomPropert=(String) roomPropertys.get(i);
							roomProperty=roomPropert;
							System.out.println("i="+i+"  "+roomProperty);
							if(manageRegion!=null&&!manageRegion.equals("")&&
									roomProperty!=null&&!roomProperty.equals("")){
								mapview2.getEngine().executeScript("gradeChangeAll('"+manageRegion+"','"+roomProperty+"')");
							}else if(manageRegion!=null&&!manageRegion.equals("")){
								mapview2.getEngine().executeScript("gradeChange1('"+manageRegion+"')");
							}else if(roomProperty!=null&&!roomProperty.equals("")){
								mapview2.getEngine().executeScript("gradeChange2('"+roomProperty+"')");
							}
							getCountAsset();
						}
					});
									
			
			AssetMap.setOnSelectionChanged(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
								
					manageRegion=null; //清空资产分区选项
					hiddenManageRegion1.getSelectionModel().clearSelection();
					hiddenManageRegion2.getSelectionModel().clearSelection();
					roomProperty=null;
					hiddenRoomProperty2.getSelectionModel().clearSelection();					
					
					int total=assets.getAllRoomInfoPosition();
					
					assetCount2.setText("共"+total+"处");
					
					webEngine2.load(mapUrl+"baidumap/assetMap.html");

			 		
				}
				
			});
	 		
	 		mapview2.getEngine().setOnAlert((WebEvent<String> wEvent)->{
				System.out.println(wEvent);
					appendAssetTable(wEvent.getData());
			});
	 		
	 		
	 		/*
	 		 * 安全巡查路线图
	 		 */
	 		
	 		users=assets.getWetchatAllUsers(0);
			Iterator<Users> iterator=users.iterator();
			List names = new ArrayList<>();
			
			while (iterator.hasNext()) {
					names.add(iterator.next().getName());
			} 

			hiddenCheckNames3.setItems(FXCollections.observableArrayList(names));
			
			hiddenCheckNames3.getSelectionModel().selectedIndexProperty().addListener(new
					 ChangeListener<Number>() {

						@Override
						public void changed(ObservableValue<? extends Number> observable, Number oldValue,
								Number newValue) {
							// TODO Auto-generated method stub
							int i=(int) newValue;
	                        usersValue=i;
	                        addCheckMap();
						}
				        
					});
	 		
			List checkDate=new ArrayList<>();
			checkDate.add("今天");
			checkDate.add("一周内");
			checkDate.add("一月内");
			
			hiddenCheckDate3.setItems(FXCollections.observableArrayList(checkDate));
			
			hiddenCheckDate3.getSelectionModel().selectedIndexProperty().addListener(new
					 ChangeListener<Number>() {

						@Override
						public void changed(ObservableValue<? extends Number> observable, Number oldValue,
								Number newValue) {
							// TODO Auto-generated method stub
							int i=(int) newValue;
							
							Date sDate = null;
							Date eDate = null;
							
							Calendar calendar = Calendar.getInstance();
							
							calendar.set(Calendar.HOUR_OF_DAY, 0);  
						    calendar.set(Calendar.SECOND, 0);  
						    calendar.set(Calendar.MINUTE, 0);  
						    calendar.set(Calendar.MILLISECOND, 0);
						    
						    
	                        if(i==0){
	                        	
	                        	calendar.add(Calendar.DATE, -1);
	                        	
	                        	sDate=calendar.getTime();
	                        	
	                        	eDate=new Date();
	                        	
	                        }else if(i==1){
	                        	
	                        	calendar.add(Calendar.DATE, -7);
	                        	
	                        	sDate=calendar.getTime();
	                        	
	                        	eDate=new Date();
	                        	
	                        }else if(i==2){
	                        	
	                        	calendar.add(Calendar.MONTH, -1);
	                        	
	                        	sDate=calendar.getTime();
	                        	
	                        	eDate=new Date();
	                        	
	                        }
	                        
	                     SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
	               		 String yyyy=formatter.format(sDate);
	               		 formatter = new SimpleDateFormat("MM");
	               		 String mm=formatter.format(sDate);
	               		 formatter = new SimpleDateFormat("dd");
	               		 String dd=formatter.format(sDate);
	               		 startCheckDate3.setValue(LocalDate.of(Integer.parseInt(yyyy), Integer.parseInt(mm), Integer.parseInt(dd)));
	                     
	               		 formatter = new SimpleDateFormat("yyyy");
	               		 yyyy=formatter.format(eDate);
	               		 formatter = new SimpleDateFormat("MM");
	               		 mm=formatter.format(eDate);
	               		 formatter = new SimpleDateFormat("dd");
	               		 dd=formatter.format(eDate);
	               		 endCheckDate3.setValue(LocalDate.of(Integer.parseInt(yyyy), Integer.parseInt(mm), Integer.parseInt(dd)));
	               		 
	               		 addCheckMap();
	               		 
						}
				        
					});
			
						
			
			checkBox3.selectedProperty().addListener(new ChangeListener<Boolean>() {
		           public void changed(ObservableValue<? extends Boolean> ov,
		                   Boolean old_val, Boolean new_val) {
		                   if(new_val){
		                	   checkBox3.setText("显示时间");
		                	   mapview3.getEngine().executeScript("lableShow('1')");
		                   }else{
		                	   checkBox3.setText("隐藏时间"); 
		                	   mapview3.getEngine().executeScript("lableShow('0')");
		                   }
		                   addCheckMap();
		                }
		              });
			
			checkSearch3.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					addCheckMap();
				}
				
			});
			
	 		PatrolMap.setOnSelectionChanged(new EventHandler<Event>() {
				
				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					
					/*
					 *  设置初始时间
					 */
					
					Date sDate = null;
					Date eDate = null;
					
					Calendar calendar = Calendar.getInstance();
					
					calendar.set(Calendar.HOUR_OF_DAY, 0);  
				    calendar.set(Calendar.SECOND, 0);  
				    calendar.set(Calendar.MINUTE, 0);  
				    calendar.set(Calendar.MILLISECOND, 0);
				    
				    calendar.add(Calendar.DATE, -1);
                	
                	sDate=calendar.getTime();
                	
                	eDate=new Date();
				    
                	SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
              		 String yyyy=formatter.format(sDate);
              		 formatter = new SimpleDateFormat("MM");
              		 String mm=formatter.format(sDate);
              		 formatter = new SimpleDateFormat("dd");
              		 String dd=formatter.format(sDate);
              		 startCheckDate3.setValue(LocalDate.of(Integer.parseInt(yyyy), Integer.parseInt(mm), Integer.parseInt(dd)));
                    
              		 formatter = new SimpleDateFormat("yyyy");
              		 yyyy=formatter.format(eDate);
              		 formatter = new SimpleDateFormat("MM");
              		 mm=formatter.format(eDate);
              		 formatter = new SimpleDateFormat("dd");
              		 dd=formatter.format(eDate);
              		 endCheckDate3.setValue(LocalDate.of(Integer.parseInt(yyyy), Integer.parseInt(mm), Integer.parseInt(dd)));
                	
              		 hiddenCheckDate3.getSelectionModel().select(0);
              		 
              		 /*
              		  * 
              		  */
              		 
        		 
					webEngine3.load(mapUrl+"baidumap/checkMap.html");
					
					checkBox3.setText("隐藏时间");
              		checkBox3.setSelected(false);
			 		
				}
			});
	 		
	 		mapview3.getEngine().setOnAlert((WebEvent<String> wEvent)->{
				System.out.println(wEvent.getData());
			});	
	}

	
	private void addCheckMap(){
		
		String sort="date";
	    String order="desc";
		
		Map searchMap=new HashMap<>();
		
		Users user=users.get(usersValue);
		
		String campusAdmin=user.getOpenId();
		
		String sTime = null;
		
		String eTime = null;
		
		searchMap.put("[Hidden_Check].campusAdmin=", campusAdmin);
		
		SimpleDateFormat sdf  =   new  SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		
		 if(startCheckDate3.getValue()!=null){
			 LocalDate localDate=startCheckDate3.getValue();
			 Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			 Date date = Date.from(instant);
			 sTime=sdf.format(date);
			 
			 System.out.println("sTime="+sTime);
			 
			 searchMap.put("[Hidden_Check].date > ", sTime);
		 }
		
		 if(endCheckDate3.getValue()!=null){
			 LocalDate localDate2=endCheckDate3.getValue();
			 Instant instant2 = Instant.from(localDate2.atStartOfDay(ZoneId.systemDefault()));
			 Date date2 = Date.from(instant2);
			 eTime=sdf.format(date2);
			 
			 System.out.println("eTime="+eTime);
			 
			 searchMap.put("[Hidden_Check].date < ", eTime);
		 }
		
		 Map map=assets.selectAllHiddenCheckPosition(1000, 0, sort, order, searchMap);		 
		 
		 int total=(int) map.get("total");
		 
		 checkCount3.setText("共"+String.valueOf(total)+"处");
		 
		 System.out.println("campusAdmin="+campusAdmin+"\n"+sTime+"\n"+eTime);
		 
		 mapview3.getEngine().executeScript("checkMap('"+campusAdmin+"','"+sTime+"','"+eTime+"')");
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
					mapview.getEngine().executeScript("script()");
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
	            
	            controller.setPosition(position,mapview);
	            controller.setDialogStage(dialogStage);
	            
	            // Show the dialog and wait until the user closes it
	            dialogStage.show();
	            
			  }else if(type.equals("ondragend")){
				  
				  JSONObject ja=JSONObject.parseObject(jsonObject.getString("ja"));
				  MyTestUtil.print(ja);
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
				  
				  mapview.getEngine().executeScript("script()");
				  
			  }
			   
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
	
	 private void getCountAsset(){
		 Map search=new HashMap<>();
		 search.put("[Position].GUID !=","");
		 if(manageRegion!=null&&!manageRegion.equals("")){
			 search.put(Singleton.ROOMDATABASE+".[dbo].[RoomInfo].ManageRegion = ", manageRegion);
		 }
		 if(roomProperty!=null&&!roomProperty.equals("")){
			 search.put(Singleton.ROOMDATABASE+".[dbo].[RoomInfo].RoomProperty = ", roomProperty);
		 }
		 Map map=assets.findAllRoomInfo_Position(1, 0, null, null, search);
		 int total=(int) map.get("total");
		 assetCount2.setText("共"+total+"处");
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
		 position.setIs_roomInfo(1);
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
	 
}
