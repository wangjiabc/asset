package com.asset.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.controlsfx.dialog.Dialogs;

import com.asset.Singleton;
import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.MenuType;
import com.asset.tool.MyTestUtil;
import com.asset.view.assets.AssetsQueryController;
import com.asset.view.check.CheckInfoDetailController;
import com.asset.view.check.SelectCheckInfoController;
import com.asset.view.hidden.HiddenDetailController;
import com.asset.view.hiddenAndAsset.AppendAssetsQueryController;
import com.asset.view.infowrite.InfoWriteController2;
import com.asset.view.neaten.AugmentNeatenDetailController;
import com.asset.view.neaten.SelectNeatenController;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Level;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Check_Join;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Join;
import com.voucher.manage.model.Users;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AssetOverviewController extends AssetAsSwitch{

	 @FXML
	 private Label rightTitleLabel;
 	 
	 /*
      * 查询条件菜单
      */
	 @FXML
	 private TextField keyWord;
	 @FXML
	 private ChoiceBox hiddenLevel;	 
	 
	 private Integer hiddenLevelValue;
	 
	 @FXML
	 private ChoiceBox hiddenProgress;	 
	 private String progress;
	 
	 @FXML
	 private ChoiceBox hiddenType;	 
	 private List<Hidden_Type> hidden_Types;
	 private Integer hiddenTypeValue;
	 
	 //查询按钮
	 @FXML
	 private Button search;
	 
	 @FXML
	 private ImageView searchImage;
	 
	 private ObservableList<Hidden_JoinProperty> hiddenList;
	 
	 @FXML
	 private TableView<Hidden_JoinProperty> hiddenTable;
	 
	// @FXML
	// private TableColumn<Hidden_JoinProperty,Integer> C1;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C2;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C3;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C4;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C5;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,ProgressBar> C6;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C7;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C8;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C9;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C10;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C11;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C12;
	 
	 @FXML
	 private ContextMenu contextMenu;
	 
	 @FXML
	 private Pagination pagination;
	 
	 @FXML
	 private MenuItem m1;
	 
	 @FXML
	  Button hiddenWrite;
	 
	// private List<Hidden> hiddens;
	 
	 private List<Hidden_Join> hidden_Jions;
	 
	 private Stage dialogStage;
	 
	 private Integer offset=0;
	 
	 private Integer limit=10;
	 
	 private Map<String,String> searchMap=new HashMap<>();
	 
	 private List<String> names=new ArrayList<String>();
	 
	 private List<byte[]> fileBytes=new ArrayList<byte[]>();
	 
	 Assets assets= new Connect().get();
	 
	 public AssetOverviewController() {
		// TODO Auto-generated constructor stub
		 super();  
	 }
	 
	 @Override
	 protected void initCurrent() {
		 URL url = getClass().getResource("");
		 String filePath=url.toString()+"Image";
		 Image image = new Image(filePath+"/home.jpg");
	     homepage.setImage(image);
	     
	     rightTitleLabel.setText("主页");
	     
 
		 Image image2=new Image(filePath+"/search.png");
	     searchImage.setImage(image2);
		 
	     Image delImage=new Image(filePath+"/del.jpg");
	     ImageView imageView0=new ImageView();
	     imageView0.setFitWidth(25);
	     imageView0.setFitHeight(25);
	     imageView0.setImage(delImage);
	     contextMenu.getItems().get(0).setGraphic(imageView0);
	     Image addImage=new Image(filePath+"/add.jpg");
	     ImageView imageView=new ImageView();
	     imageView.setFitWidth(25);
	     imageView.setFitHeight(25);
	     imageView.setImage(addImage);
	     contextMenu.getItems().get(2).setGraphic(imageView);
	     Image menuImage=new Image(filePath+"/search.png");
	     ImageView imageView2=new ImageView();
	     imageView2.setFitWidth(25);
	     imageView2.setFitHeight(25);
	     imageView2.setImage(menuImage);
	     contextMenu.getItems().get(3).setGraphic(imageView2);


	     
	     List<Hidden_Level> hidden_levels=assets.setctAllHiddenLevel();
			Iterator<Hidden_Level> iterator=hidden_levels.iterator();
			List levels = new ArrayList<>();
			while (iterator.hasNext()) {
				levels.add(iterator.next().getLevel_text());
			}

			hiddenLevel.setItems(FXCollections.observableArrayList(levels));
			
			hiddenLevel.getSelectionModel().selectedIndexProperty().addListener(new
					 ChangeListener<Number>() {

						@Override
						public void changed(ObservableValue<? extends Number> observable, Number oldValue,
								Number newValue) {
							// TODO Auto-generated method stub
							int i=(int) newValue;
	                        hiddenLevelValue=hidden_levels.get(i).getHidden_level();	
	                        searchMap=new HashMap<>();  //清除查询条件
							System.out.println(hiddenLevelValue);
						}
				        
					});
	     
     
	    //整改进度 :
	    List hidden_progress=new ArrayList<>();
	    hidden_progress.add("未整改");
	    hidden_progress.add("整改中");
	    hidden_progress.add("已完成");
	    hiddenProgress.setItems(FXCollections.observableArrayList(hidden_progress));
	    
	    hiddenProgress.getSelectionModel().selectedIndexProperty().addListener(new 
	    		ChangeListener<Number>() {

					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue,
							Number newValue) {
						// TODO Auto-generated method stub
						int i=(int) newValue;
						progress=hidden_progress.get(i).toString();
						searchMap=new HashMap<>();  //清除查询条件
					}
			});
	    
	    //隐患情况 :
		hidden_Types=assets.selectAllHiddenType();
		Iterator<Hidden_Type> iterator3=hidden_Types.iterator();
		List hidden_types=new ArrayList<>();
		while(iterator3.hasNext()){
			    String type_text=iterator3.next().getHidden_type();
				 hidden_types.add(type_text);

		  }
	    hiddenType.setItems(FXCollections.observableArrayList(hidden_types));
	    
	    hiddenType.getSelectionModel().selectedIndexProperty().addListener(new 
				 ChangeListener<Number>() {
					
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						// TODO Auto-generated method stub
						int i=(int) newValue;
						hiddenTypeValue=hidden_Types.get(i).getType();
						searchMap=new HashMap<>();  //清除查询条件
					}
				});
	    
	    keyWord.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				String code=event.getCode().getName();
				if(code.equals("Enter")){
					if(keyWord.getText()!=null){
					  searchMap.put("[Hidden].name like ", "%"+keyWord.getText()+"%");
					}else{
						searchMap=new HashMap<>();
					}
					setRoomInfoList(0,10,searchMap);
				}
			}
		});
	    
	    //搜索	    
	     search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(hiddenLevelValue!=null){
				   String search=String.valueOf(hiddenLevelValue);
				   searchMap.put("[Hidden].Hidden_Level=", search);
				 }
				
				 if(keyWord.getText()!=null){
					 searchMap.put("[Hidden].name like ", "%"+keyWord.getText()+"%");
				 }
				 
				 if(hiddenTypeValue!=null){
					 searchMap.put("[Hidden].type=", String.valueOf(hiddenTypeValue));
				 }
				 
				 if(progress!=null){
					 if(progress.equals("未整改")){
						 searchMap.put("[Hidden].progress=","0");
					 }else if(progress.equals("整改中")){
						 searchMap.put("[Hidden].progress>", "0");
						 searchMap.put("[Hidden].progress<", "1");
					 }else{
						 searchMap.put("[Hidden].progress=", "1");
					 }
				 }
				 
				 setRoomInfoList(0,10,searchMap);
				 				 
			}
		  });
	     
	     
	    pagination.setPageFactory((Integer pageIndex)->{
	    	if (pageIndex >= 0) {
	    		offset=pageIndex*10;
	    		limit=10;
	    		setRoomInfoList(offset, limit, searchMap);
	    		 Label mLabel = new Label();  
	                mLabel.setText("这是第" + (pageIndex+1) + "页");  
	                return mLabel;  
            } else {
                return null;
            }
	    });
	    
	   
	 //   hiddenTable.getSelectionModel().selectedItemProperty().addListener(
	  //  		(observable, oldValue, newValue) ->table(newValue));
	    
	 /*   hiddenTable.getSelectionModel().selectedItemProperty().addListener(
	    		new ChangeListener<HiddenProperty>() {

					@Override
					public void changed(ObservableValue<? extends HiddenProperty> observable, HiddenProperty oldValue,
							HiddenProperty newValue) {
						// TODO Auto-generated method stub
						      table(newValue);
	
					      }
				     }
	    		);*/
	    
	    hiddenTable.setRowFactory( tv -> {
	        TableRow<Hidden_JoinProperty> row = new TableRow<>();
	        row.setOnMouseClicked(event -> {
	            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
	            	Hidden_JoinProperty rowData = row.getItem();
	            	table(rowData);
	            }
	        });
	        
	        row.setOnContextMenuRequested(event->{
	
	        	    contextMenu.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							// TODO Auto-generated method stub
							try{
								
								if(Singleton.getInstance().getHidden_User().getPurview()>2){
									Alert alert2 = new Alert(AlertType.WARNING);
									alert2.setTitle("警告对话框");
									alert2.setHeaderText("警告");
									alert2.setContentText("你没有修改隐患的权限");
									alert2.showAndWait();
									return ;
								}
								
							  String GUID=row.getItem().getGUID().get();
							  String Name=row.getItem().getName().get();
							  String menuType=MenuType.get(event.getTarget().toString());
							  System.out.println(menuType);
							  
							  Hidden_Join hidden_Jion=new Hidden_Join();
							  
							  hidden_Jion.setGUID(GUID);
							  hidden_Jion.setName(Name);
							  
							  if(menuType.equals("m1")){
								  if(Singleton.getInstance().getHidden_User().getPurview()>1){
										Alert alert2 = new Alert(AlertType.WARNING);
										alert2.setTitle("警告对话框");
										alert2.setHeaderText("警告");
										alert2.setContentText("你没有删除隐患的权限");
										alert2.showAndWait();
										return ;
									}
								  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
							        alert.setTitle("安全信息");
							        alert.setHeaderText("删除");
							        alert.setContentText("是否删除"+Name+"信息");

							        ButtonType btnType1 = new ButtonType("确定");
							        ButtonType btnType2 = new ButtonType("取消");
							     

							        alert.getButtonTypes().setAll(btnType1, btnType2);

							        Optional<ButtonType> result = alert.showAndWait();
							        result.ifPresent(buttonType -> {
							            if (buttonType == btnType1) {
							                try{
							                String[] where={"GUID=",GUID};
							                Hidden hidden =new Hidden();
							                hidden.setId(null);
							                hidden.setGUID(GUID);
							            	hidden.setWhere(where);
							                int i=assets.deleteHidden(hidden);
							                if(i==1){
							                	alert.setTitle("安全信息");
												alert.setHeaderText("操作");
												alert.setContentText("删除"+Name+"成功");
												alert.showAndWait();
												hiddenTable.setItems(null);
												setRoomInfoList(offset, limit,searchMap);
							                }else{
							                	Alert alert2 = new Alert(AlertType.ERROR);
												alert2.setTitle("异常堆栈对话框");
												alert2.setHeaderText("错误");
												alert2.setContentText("删除"+Name+"失败");
												alert2.showAndWait();
							                }
							                }catch (Exception e) {
												// TODO: handle exception
							                	Alert alert2 = new Alert(AlertType.ERROR);
												alert2.setTitle("异常堆栈对话框");
												alert2.setHeaderText("错误");
												alert2.setContentText("删除"+Name+"失败");
												alert2.showAndWait();
												e.printStackTrace();
											}
							            } else if (buttonType == btnType2) {
							            	System.out.println("点击了取消");
							            } 
							        });
							  }
							  
							  /*
							  if(menuType.equals("m2")){
								  try {
							            // Load the fxml file and create a new stage for the popup dialog.
							            FXMLLoader loader = new FXMLLoader();
							            loader.setLocation(getClass().getResource("check/AugmentCheckInfoDetail.fxml"));
							            AnchorPane page = (AnchorPane) loader.load();

							            // Create the dialog Stage.
							            Stage dialogStage = new Stage();
							            dialogStage.setTitle("添加"+hidden_Jion.getName()+"安全检查记录");
							            dialogStage.initModality(Modality.APPLICATION_MODAL);
							            Scene scene = new Scene(page);
							            dialogStage.setScene(scene);

							            // Set the person into the controller.
							            AugmentCheckInfoDetailController controller = loader.getController();
							            controller.setDialogStage(dialogStage);
							            
							            controller.setHiddenCheckInfo(hidden_Jion);
							              		          
							            // Show the dialog and wait until the user closes it
							            dialogStage.show();

							        } catch (IOException e) {
							            e.printStackTrace();
							        }
								 
							  }
							  */
							  
							  if(menuType.equals("m3")){
								  try {
							            // Load the fxml file and create a new stage for the popup dialog.
							            FXMLLoader loader = new FXMLLoader();
							            loader.setLocation(AssetOverviewController.class.getResource("neaten/AugmentNeatenDetail.fxml"));
							            AnchorPane page = (AnchorPane) loader.load();

							            // Create the dialog Stage.
							            Stage dialogStage = new Stage();
							            dialogStage.setTitle("添加"+hidden_Jion.getName()+"安全整改记录");
							            dialogStage.initModality(Modality.APPLICATION_MODAL);
							            Scene scene = new Scene(page);
							            dialogStage.setScene(scene);

							            // Set the person into the controller.
							            AugmentNeatenDetailController controller = loader.getController();
							            controller.setDialogStage(dialogStage);
							            
							            controller.setHiddenCheckInfo(hidden_Jion);
							              		          
							            // Show the dialog and wait until the user closes it
							            dialogStage.show();

							        } catch (Exception e) {
							            e.printStackTrace();
							        }
								
							  }
							  
							  if(menuType.equals("m4")){
								  try {
							            // Load the fxml file and create a new stage for the popup dialog.
							            FXMLLoader loader = new FXMLLoader();
							            loader.setLocation(getClass().getResource("check/SelectCheckInfo.fxml"));
							            AnchorPane page = (AnchorPane) loader.load();

							            // Create the dialog Stage.
							            Stage dialogStage = new Stage();
							            dialogStage.setTitle(Name+"安全巡查记录");
							            dialogStage.initModality(Modality.APPLICATION_MODAL);
							            Scene scene = new Scene(page);
							            dialogStage.setScene(scene);

							            // Set the person into the controller.
							            SelectCheckInfoController controller = loader.getController();
							          //  controller.setDialogStage(dialogStage);
										Map searchMap4=new HashMap<>();           	     
							            searchMap4.put("[Hidden_Check].GUID=",GUID);
							            
                                        controller.setSearch(searchMap4);						           
							          
							            // Show the dialog and wait until the user closes it
							            dialogStage.show();

							        } catch (Exception e) {
							            e.printStackTrace();
							        }
							  }
							  
							  if(menuType.equals("m5")){
								  try {
							            // Load the fxml file and create a new stage for the popup dialog.
							            FXMLLoader loader = new FXMLLoader();
							            loader.setLocation(getClass().getResource("neaten/SelectNeaten.fxml"));
							            AnchorPane page = (AnchorPane) loader.load();

							            // Create the dialog Stage.
							            Stage dialogStage = new Stage();
							            dialogStage.setTitle(Name+"隐患整改记录");
							            dialogStage.initModality(Modality.APPLICATION_MODAL);
							            Scene scene = new Scene(page);
							            dialogStage.setScene(scene);

							            // Set the person into the controller.
							            SelectNeatenController controller = loader.getController();
							          //  controller.setDialogStage(dialogStage);
										
							            Map searchMap5=new HashMap<>();
							            searchMap5.put("[Hidden_Neaten].GUID=",GUID);
							            
                                        controller.setSearch(searchMap5);						           
							          
							            // Show the dialog and wait until the user closes it
							            dialogStage.show();

							        } catch (Exception e) {
							            e.printStackTrace();
							        }
							  }
							  
							  if(menuType.equals("m6")){
								  try {
							            // Load the fxml file and create a new stage for the popup dialog.
							            FXMLLoader loader = new FXMLLoader();
							            loader.setLocation(getClass().getResource("hiddenAndAsset/AppendAssetsQuery.fxml"));
							            AnchorPane page = (AnchorPane) loader.load();
										  System.out.println("xxxxx      "+menuType);
							            // Create the dialog Stage.
							            Stage dialogStage = new Stage();
							            dialogStage.setTitle("选择要添加"+Name+"隐患的资产");
							            dialogStage.initModality(Modality.WINDOW_MODAL);
							            Scene scene = new Scene(page);
							            dialogStage.setScene(scene);

							            // Set the person into the controller.
							            AppendAssetsQueryController controller = loader.getController();
							            
							            Hidden hidden=new Hidden();
							            hidden.setGUID(GUID);
							            hidden.setName(Name);
							            
							            controller.setHidden(hidden);
							            controller.setDialogStage(dialogStage);
							            
							            // Show the dialog and wait until the user closes it
							            dialogStage.show();

							        } catch (Exception e) {
							            e.printStackTrace();
							        }
							  }
							  
							  
							  if(menuType.equals("m7")){
								  try {
							            // Load the fxml file and create a new stage for the popup dialog.
							          /*  FXMLLoader loader = new FXMLLoader();
							            loader.setLocation(getClass().getResource("assets/AssetsQuery.fxml"));
							            AnchorPane page = (AnchorPane) loader.load();
										  System.out.println("xxxxx      "+menuType);
							            // Create the dialog Stage.
							            Stage dialogStage = new Stage();
							            dialogStage.setTitle("查看"+Name+"隐患对应的资产");
							            dialogStage.initModality(Modality.WINDOW_MODAL);
							            Scene scene = new Scene(page);
							            dialogStage.setScene(scene);

							            // Set the person into the controller.
							            AssetsQueryController controller = loader.getController();
							           
							            Hidden hidden=new Hidden();
							            hidden.setGUID(GUID);
							            hidden.setName(Name);
							            
							            controller.setHidden(hidden);
							            controller.setDialogStage(dialogStage);
							            
							            // Show the dialog and wait until the user closes it
							            dialogStage.show();
                                        */
										Hidden_JoinProperty rowData = row.getItem();
						            	table(rowData);
									  
							        } catch (Exception e) {
							            e.printStackTrace();
							        }
							  }
							  
							 }catch(Exception e){
								
							}
							
						}
					});
	        });
	        return row ;
	    });
	    
	    
	    hiddenWrite.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					
					if(Singleton.getInstance().getHidden_User().getPurview()>2){
						Alert alert2 = new Alert(AlertType.WARNING);
						alert2.setTitle("警告对话框");
						alert2.setHeaderText("警告");
						alert2.setContentText("你没有新建隐患的权限");
						alert2.showAndWait();
						return ;
					}
					   FXMLLoader loader = new FXMLLoader();
			            loader.setLocation(AssetOverviewController.class.getResource("infowrite/InfoWrite2.fxml"));
			            AnchorPane page = (AnchorPane) loader.load();

			            // Create the dialog Stage.
			            Stage dialogStage = new Stage();
			            dialogStage.setTitle("信息录入");
			            dialogStage.initModality(Modality.APPLICATION_MODAL);
			            dialogStage.setResizable(false);
			            Scene scene = new Scene(page);
			            dialogStage.setScene(scene);

			            // Set the person into the controller.
			            InfoWriteController2 controller = loader.getController();
			            controller.setTableView(hiddenTable,offset,limit,searchMap,pagination,null, C2, C3, C4, C5, C6, C7, C8,C9,C10,C11,C12);
			            controller.setDialogStage(dialogStage);
			            
			            // Show the dialog and wait until the user closes it
			            dialogStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	    
	    
	 }
	 
	 private void table(Hidden_JoinProperty newValue){
		 try {
			  String GUID=newValue.getGUID().get();
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(AssetOverviewController.class.getResource("hidden/HiddenDetail.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("隐患");
	            dialogStage.initModality(Modality.APPLICATION_MODAL);
	            dialogStage.setResizable(false);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            HiddenDetailController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            
	            Map searchMap0=new HashMap<>();
	  		  
	  		    searchMap0.put("[Hidden_Assets].hidden_GUID=", GUID);
	  		    Map searchMap2=new HashMap<>();
	  		    searchMap2.put("[Hidden_Check].GUID=", GUID);
	  		    Map searchMap3=new HashMap<>();
	  		    searchMap3.put("[Hidden_Neaten].GUID=", GUID);
	  		    
	            controller.setTableView(hiddenTable,offset,limit,searchMap,searchMap0,searchMap2,searchMap3,pagination,null, C2, C3, C4, C5, C6, C7, C8,C9,C10,C11,C12);
	            
	           // Map map=assets.selectAllHidden(limit, offset, null, null, searchMap);

	            Map map=assets.selectAllHidden_Jion(limit, offset, "date", "desc", searchMap);
	            
	   	        hidden_Jions= (List<Hidden_Join>) map.get("rows");
	            
	            Iterator<Hidden_Join> iterator=hidden_Jions.iterator();
	            
	            Hidden_Join hidden_Jion=null;
	            
	            while(iterator.hasNext()){
	            	Hidden_Join h=iterator.next();
	            	try{
	            	 if(newValue.getId().get()==h.getId()){
	            		hidden_Jion=h;
	            		
	            		if(hidden_Jion.getTerminal()!=null&&hidden_Jion.getTerminal().equals("Wechat")){
	            				try{
	            					String openId=hidden_Jion.getCampusAdmin();	            		
	            					//在详情里把openid换成昵称
	            					System.out.println("openId="+openId);
	            					Users users=assets.getWetchatUsers(openId);	      
	            					MyTestUtil.print(users);
	            					hidden_Jion.setCampusAdmin(users.getNickname());
	            				}catch (Exception e) {
								// TODO: handle exception
	            					e.printStackTrace();
	            				}
	            		}
	    	            controller.setHidden(hidden_Jion);
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
	 
	 void setRoomInfoList(Integer offset,Integer limit,Map search){

	      String sort=null;
	      String order=null;
	     
		  Map map=new HashMap<>();
		  
		  
		//  map=assets.selectAllHidden(limit, offset, sort, order, search);

		//  map=assets.selectAllHidden_Jion(limit, offset, sort, order, search);
		  
		  map=assets.selectAllHidden_Jion(limit, offset, "date", "desc", search);
		  
	     hidden_Jions= (List<Hidden_Join>) map.get("rows");
	     
	     MyTestUtil.print(hidden_Jions);
	     
	     hiddenList= (ObservableList<Hidden_JoinProperty>) new RowData(hidden_Jions,Hidden_JoinProperty.class).get();
	     java.util.Iterator<Hidden_JoinProperty> iterator=hiddenList.iterator();
	    
	     while (iterator.hasNext()) {
			System.out.println("hiddenlist="+iterator.next().getDate());
		}
	     
	    hiddenTable.setItems(hiddenList);
        
	   //  C1.setCellValueFactory(
	     //           cellData -> cellData.getValue().getId().asObject());
	     C2.setCellValueFactory(
	   		    cellData->cellData.getValue().getName());
	     C3.setCellValueFactory(
	    		    cellData->cellData.getValue().getLevel_text());
	     C4.setCellValueFactory(
	    		    cellData->cellData.getValue().getDetail());
	     C5.setCellValueFactory(
	    		    cellData->cellData.getValue().getHappen_time());	     
	     C6.setCellValueFactory(
	    		    new Callback<TableColumn.CellDataFeatures<Hidden_JoinProperty,ProgressBar>, ObservableValue<ProgressBar>>() {
						
						@Override
						public ObservableValue<ProgressBar> call(CellDataFeatures<Hidden_JoinProperty, ProgressBar> param) {
							// TODO Auto-generated method stub
							DoubleProperty d=param.getValue().getProgress();
							Double dd=d.doubleValue();
							ProgressBar progressBar=new ProgressBar();
							progressBar.setProgress(dd);
							return new SimpleObjectProperty<ProgressBar>(progressBar);
						}
					});
	     C7.setCellValueFactory(
	    		    cellData->cellData.getValue().getPrincipal_name());
	     C8.setCellValueFactory(
	    		 cellData->cellData.getValue().getHidden_type());
	     C9.setCellValueFactory(
	    		 cellData->cellData.getValue().getUser_name());
	     C10.setCellValueFactory(
	    		 cellData->cellData.getValue().getRemark());
	     
	     C11.setCellValueFactory(
	    		 cellData->cellData.getValue().getDate());

	     C12.setCellValueFactory(
	    		 cellData->cellData.getValue().getManageRegion());
	     
	     int total=(int) map.get("total");
	     int page=total/10;
	     
	     if(total-page*10>0)
           page++;	
	     
	     if(total>0){
		     pagination.setPageCount(page);
	         }else {
	        	 pagination.setPageCount(1);
			}
	     	     
	 }
	 
}
