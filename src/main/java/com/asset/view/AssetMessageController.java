package com.asset.view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;

import org.apache.poi.ss.formula.functions.T;

import com.asset.Singleton;
import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.join.HiddenCheck_JoinProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.FileConvect;
import com.asset.tool.MenuType;
import com.asset.tool.MyTestUtil;
import com.asset.view.check.CheckAssetsQueryController;
import com.asset.view.check.CheckInfoDetailController;
import com.asset.view.detail.AddCheckInfoDetailController;
import com.asset.view.hidden.HiddenDetailController;
import com.asset.view.infowrite.InfoWriteController2;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Check;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Check_Join;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Join;
import com.voucher.manage.model.Users;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AssetMessageController extends AssetAsSwitch{
	
	@FXML
	 private Label rightTitleLabel;
	
	@FXML
	 private AnchorPane anchorPane;

	 @FXML
	 ImageView imageFile;
	 
	 @FXML
	 Label docLabel;
	 
     private ObservableList<HiddenCheck_JoinProperty> hiddenCheckList;
	 
	 @FXML
	 private TableView<HiddenCheck_JoinProperty> hiddenCheckTable;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,Integer> C1;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C2;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C3;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C4;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C5;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C6;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C7;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C8;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C9;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C10;
	 
	 @FXML
	 private Pagination pagination;
	 
	/*
	 @FXML
	 private ChoiceBox<T> hiddenName;//隐患名字
	 private List<Hidden> hidden;
	 private Integer hiddenValue;
	*/
	 
	 @FXML
	 private ChoiceBox<T> usersName;//隐患名字
	 private List<Users> users;
	 private Integer usersValue;
	 
	 @FXML
	 private ChoiceBox<T> hiddenManageRegion;
	 private List<RoomInfo> roomInfoLists;
	 private String manageRegion;
	 
	 private List manageRegions=new ArrayList<>();
	 
	 @FXML
	 private DatePicker startTime;
 	 
	 @FXML
	 private DatePicker endTime;
	 
	 @FXML
	 private TextField assetName;
	 
	 //查询按钮
	 @FXML
	 private Button search;
	 
	 @FXML
	 private Button addCheck;
	 
	 private Map<String,String> searchMap=new HashMap<>();
	 
	 private List<Hidden_Check_Join> hidden_Checks;
	 
     private Stage dialogStage;
	 
	 private Integer offset=0;
	 
	 private Integer limit=10;
	 
	 private final Desktop desktop = Desktop.getDesktop();
	 private Stage stage;
	 private File file;
	 private File docFile;
	 
	 @FXML
	 private ContextMenu contextMenu;
	 
	 @FXML
	  Button hiddenWrite;
	 
	 Assets assets= new Connect().get();
	 
	 public AssetMessageController() {
		// TODO Auto-generated constructor stub
		 super();	
		 stage=new Stage();
		 stage.setTitle("File Chooser Sample");
	}
	 
	 @Override
	 protected void initCurrent() {
		 
		 URL url = getClass().getResource("");
		 String filePath=url.toString()+"Image";
		 Image image = new Image(filePath+"/message.jpg");
	     homepage.setImage(image);
	     
	     rightTitleLabel.setText("检查记录");
	     
	     Image delImage=new Image(filePath+"/del.jpg");
	     ImageView imageView0=new ImageView();
	     imageView0.setFitWidth(25);
	     imageView0.setFitHeight(25);
	     imageView0.setImage(delImage);
	     contextMenu.getItems().get(0).setGraphic(imageView0);
	     
	    /* 
	 	Map map=assets.selectAllHidden(1000, 0, null, null, searchMap);
		hidden=(List<Hidden>) map.get("rows");
		Iterator<Hidden> iterator=hidden.iterator();
		List levels = new ArrayList<>();
		while (iterator.hasNext()) {
			levels.add(iterator.next().getName());
			System.out.println("levels="+levels);
		}
        */
	 
		hiddenWrite.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				main.showAssetOverview();
			}
			
		});
		
		/*
		
		hiddenName.setItems(FXCollections.observableArrayList(levels));
		
		hiddenName.getSelectionModel().selectedIndexProperty().addListener(new
				 ChangeListener<Number>() {

					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue,
							Number newValue) {
						// TODO Auto-generated method stub
						int i=(int) newValue;
                        hiddenValue=i;
					}
			        
				});
	     
	     */
		
		users=assets.getWetchatAllUsers(0);
		Iterator<Users> iterator=users.iterator();
		List names = new ArrayList<>();
		
		while (iterator.hasNext()) {
				names.add(iterator.next().getName());
		} 

		usersName.setItems(FXCollections.observableArrayList(names));
		
		usersName.getSelectionModel().selectedIndexProperty().addListener(new
				 ChangeListener<Number>() {

					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue,
							Number newValue) {
						// TODO Auto-generated method stub
						int i=(int) newValue;
                        usersValue=i;
                        searchMap=new HashMap<>();  //清除查询条件
					}
			        
				});
		
		
		roomInfoLists=assets.selectManageRegion();
		 
		 Iterator<RoomInfo> iterator5=roomInfoLists.iterator();
		 
		 List manageRegions=new ArrayList<>();
		 
		 while (iterator5.hasNext()) {
			String manage=iterator5.next().getManageRegion();
			if(!manage.equals(""))
			  manageRegions.add(manage);
		 }
		
		 hiddenManageRegion.setItems(FXCollections.observableArrayList(manageRegions));
		 
		 hiddenManageRegion.getSelectionModel().selectedIndexProperty().addListener(new 
				 ChangeListener<Number>() {
					
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						// TODO Auto-generated method stub
						int i=(int) newValue;
						String manage=(String) manageRegions.get(i);
						manageRegion=manage;
						System.out.println("i="+i+"  "+manageRegion);
					}
				});
		
		
	   //搜索		    
	     search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				/*
				 if(hiddenValue!=null){
				   String search=hidden.get(hiddenValue).getGUID();
				   System.out.println("search="+search);
				   searchMap.put("[Hidden_Check].GUID=", search);
				 }
				 */
				
				if(usersValue!=null){
					   String search=users.get(usersValue).getOpenId();
					   searchMap.put("[Hidden_Check].campusAdmin=", search);
					 }
				
				if(assetName.getText()!=null&&!assetName.getText().equals("")){
					System.out.println("assetName="+assetName.getText());
					searchMap.put("[TTT].[dbo].[RoomInfo].Address like ", "%"+assetName.getText()+"%");
				}
				
				SimpleDateFormat sdf  =   new  SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
				
				 if(startTime.getValue()!=null){
					 LocalDate localDate=startTime.getValue();
					 Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
					 Date date = Date.from(instant);
					 String sTime=sdf.format(date);
					 
					 System.out.println("sTime="+sTime);
					 
					 searchMap.put("[Hidden_Check].date > ", sTime);
				 }
				
				 if(endTime.getValue()!=null){
					 LocalDate localDate2=endTime.getValue();
					 Instant instant2 = Instant.from(localDate2.atStartOfDay(ZoneId.systemDefault()));
					 Date date2 = Date.from(instant2);
					 String eTime=sdf.format(date2);
					 
					 System.out.println("eTime="+eTime);
					 
					 searchMap.put("[Hidden_Check].date < ", eTime);
				 }
				 
				 if(manageRegion!=null&&!manageRegion.equals("")){
					 searchMap.put("[TTT].[dbo].[RoomInfo].ManageRegion = ", manageRegion);
				 }
				 
				 setHiddenCheckList(0,10,searchMap);
			}
		  });
	    
	     
	     addCheck.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				  FXMLLoader loader = new FXMLLoader();
		            loader.setLocation(CheckAssetsQueryController.class.getResource("CheckAssetsQuery.fxml"));
		            AnchorPane page = null;
		            try {
						 page = (AnchorPane) loader.load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
		            Stage dialogStage = new Stage();
		            dialogStage.setTitle("选择要添加安全巡查记录的资产");
		            dialogStage.initModality(Modality.APPLICATION_MODAL);
		            Scene scene = new Scene(page);
		            dialogStage.setScene(scene);
		            
		           CheckAssetsQueryController controller=loader.getController();
		           
		           controller.setTableView(hiddenCheckTable, limit, offset,pagination,C1, C2, C3, C4, C5, C6, C7, C8, C9, C10);
		           
		           controller.setDialogStage(dialogStage);
		            
		            // Show the dialog and wait until the user closes it
		            dialogStage.show();
		           

			}
	    	 
	     });
	     
	     pagination.setPageFactory((Integer pageIndex)->{
		    	if (pageIndex >= 0) {
		    		offset=pageIndex*10;
		    		limit=10;
		    		System.out.println("pagination="+offset+" ______"+limit);
		    		setHiddenCheckList(offset, limit, searchMap);
		    		 Label mLabel = new Label();  
		                mLabel.setText("这是第" + (pageIndex+1) + "页");  
		                return mLabel;  
	            } else {
	                return null;
	            }
		    });
	     
  
			 hiddenCheckTable.setRowFactory( tv -> {
			        TableRow<HiddenCheck_JoinProperty> row = new TableRow<>();
			        row.setOnMouseClicked(event -> {
			            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
			            	HiddenCheck_JoinProperty rowData = row.getItem();
			            	table(rowData);
			            }
			        });
			        
			        row.setOnContextMenuRequested(event->{
			        	
		        	    contextMenu.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								try{
									
								  String check_id=row.getItem().getCheck_id().get();
								  String name=row.getItem().getCheck_name().get();
								  String menuType=MenuType.get(event.getTarget().toString());
								  System.out.println(menuType);								  
								  
								  if(menuType.equals("m1")){
									  
									  if(Singleton.getInstance().getHidden_User().getPurview()>1){
											Alert alert2 = new Alert(AlertType.WARNING);
											alert2.setTitle("警告对话框");
											alert2.setHeaderText("警告");
											alert2.setContentText("你没有删除安全检查记录的的权限");
											alert2.showAndWait();
											return ;
										 }
									  
									  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
								        alert.setTitle("删除");
								        alert.setHeaderText("安全检查记录");
								        alert.setContentText("是否删除"+name+"信息");

								        ButtonType btnType1 = new ButtonType("确定");
								        ButtonType btnType2 = new ButtonType("取消");
								     

								        alert.getButtonTypes().setAll(btnType1, btnType2);

								        Optional<ButtonType> result = alert.showAndWait();
								        result.ifPresent(buttonType -> {
								            if (buttonType == btnType1) {
								                try{
								                String[] where={"check_id=",check_id};
					                            Hidden_Check hidden_Check=new Hidden_Check();
					                            hidden_Check.setWhere(where);
					                            
								                int i=assets.deleteHiddenCheck(hidden_Check);
								                if(i==1){
								                	alert.setTitle("安全检查记录");
													alert.setHeaderText("操作");
													alert.setContentText("删除"+name+"成功");
													alert.showAndWait();
													hiddenCheckTable.setItems(null);
													setHiddenCheckList(offset, limit,searchMap);
								                }else{
								                	Alert alert2 = new Alert(AlertType.ERROR);
													alert2.setTitle("异常堆栈对话框1");
													alert2.setHeaderText("错误");
													alert2.setContentText("删除"+name+"失败");
													alert2.showAndWait();
								                }
								                }catch (Exception e) {
													// TODO: handle exception
								                	e.printStackTrace();
								                	Alert alert2 = new Alert(AlertType.ERROR);
													alert2.setTitle("异常堆栈对话框2");
													alert2.setHeaderText("错误");
													alert2.setContentText("删除"+name+"失败");
													alert2.showAndWait();													
												}
								            } else if (buttonType == btnType2) {
								            	System.out.println("点击了取消");
								            } 
								        });
								  }
								  
								  
								  if(menuType.equals("m2")){
									  HiddenCheck_JoinProperty rowData = row.getItem();
						              table(rowData);
								  }
								  
								  
								}catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							}
		        	    });
			        });
			        
			        return row ;
			    });

			 
	 }
	 
	 
	 private void table(HiddenCheck_JoinProperty newValue){
		 try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("check/CheckInfoDetail.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("安全巡查记录");
	            dialogStage.initModality(Modality.APPLICATION_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            CheckInfoDetailController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setTableView(hiddenCheckTable,offset,limit,searchMap,pagination,C1, C2, C3, C4, C5, C6, C7, C8, C9, C10);
	            	     
	            System.out.println("check_id="+newValue.getCheck_id());
	            
	            Map searchMap1=new HashMap<>();
	            
	            searchMap1.put("[Hidden_Check].check_id=",newValue.getCheck_id().get());
	            
	            String sort="date";
	  	      String order="desc";
	            
	            Map map=assets.selectAllHiddenCheck(2, 0, sort, order, searchMap1);
	            
	            List<Hidden_Check_Join> hidden_Check_Joins= (List<Hidden_Check_Join>) map.get("rows");
	            MyTestUtil.print(hidden_Check_Joins);
	            try{
	               Hidden_Check_Join hidden_Check_Join=hidden_Check_Joins.get(0);  
	               if(hidden_Check_Join.getTerminal()!=null&&hidden_Check_Join.getTerminal().equals("Wechat")){
       				try{
       					String openId=hidden_Check_Join.getCampusAdmin();	            		
       					//在详情里把openid换成昵称
       					System.out.println("openId="+openId);
       					Users users=assets.getWetchatUsers(openId);	      
       					MyTestUtil.print(users);
       					hidden_Check_Join.setCampusAdmin(users.getNickname());
       				}catch (Exception e) {
						// TODO: handle exception
       					e.printStackTrace();
       				}
	               }
	               controller.setHiddenCheckInfo(hidden_Check_Join);
	            }catch (Exception e) {
					// TODO: handle exception
	            	e.printStackTrace();
				}
	            // Show the dialog and wait until the user closes it
	            dialogStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 void setHiddenCheckList(Integer offset,Integer limit,Map search){

		 String sort="date";
	      String order="desc";
	     
		  Map map=new HashMap<>();
		  
		  	
		  map=assets.selectAllHiddenCheck(limit, offset, sort, order, search);
		  
	     hidden_Checks= (List<Hidden_Check_Join>) map.get("rows");
	     MyTestUtil.print(hidden_Checks);
	     
	     hiddenCheckList= (ObservableList<HiddenCheck_JoinProperty>) new RowData(hidden_Checks,HiddenCheck_JoinProperty.class).get();
	     
	     java.util.Iterator<HiddenCheck_JoinProperty> iterator=hiddenCheckList.iterator();
	    
	     while (iterator.hasNext()) {
			System.out.println("hiddenlist="+iterator.next().getDate());
		}
	     
	    hiddenCheckTable.setItems(hiddenCheckList);
       
	     C1.setCellValueFactory(
	                cellData -> cellData.getValue().getId().asObject());
	     C2.setCellValueFactory(
	   		    cellData->cellData.getValue().getAddress());
	     C3.setCellValueFactory(
	    		    cellData->cellData.getValue().getPrincipal());
	     C4.setCellValueFactory(
	    		    cellData->cellData.getValue().getCheck_name());
	     C5.setCellValueFactory(
	    		    cellData->cellData.getValue().getCheck_circs());
	     C6.setCellValueFactory(
	    		    cellData->cellData.getValue().getHappen_time());	
	     /*
	     C7.setCellValueFactory(
	    		    new Callback<TableColumn.CellDataFeatures<HiddenCheck_JoinProperty,ProgressBar>, ObservableValue<ProgressBar>>() {
						
						@Override
						public ObservableValue<ProgressBar> call(CellDataFeatures<HiddenCheck_JoinProperty, ProgressBar> param) {
							// TODO Auto-generated method stub
							DoubleProperty d=param.getValue().getProgress();
							Double dd=d.doubleValue();
							ProgressBar progressBar=new ProgressBar();
							progressBar.setProgress(dd);
							return new SimpleObjectProperty<ProgressBar>(progressBar);
						}
					});
			*/	
	     
	     C7.setCellValueFactory(
	    		    cellData->cellData.getValue().getManageRegion());
	     
	      C8.setCellValueFactory(
	    		    cellData->cellData.getValue().getDate());
	     
	      C9.setCellValueFactory(
	    		    cellData->cellData.getValue().getUser_name());
	      	      
	      C10.setCellValueFactory(
	    		  	cellData->cellData.getValue().getDistrict());
	      
	     int total=(int) map.get("total");
	     int page=total/10;
	     
	     if(total-page*10>0)
          page++;	     
	     System.out.println("page="+page);
	     if(total>0){
		     pagination.setPageCount(page);
	         }else {
	        	 pagination.setPageCount(1);
			}
	     	     
	 }
	 
	 private void openFile(File file) {
	        EventQueue.invokeLater(() -> {
	            try {
	                desktop.open(file);
	            } catch (IOException ex) {
                    ex.printStackTrace();
	            }
	        });
	    }
}
