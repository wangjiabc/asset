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

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.MenuType;
import com.asset.tool.MyTestUtil;
import com.asset.view.assets.AssetsQueryController;
import com.asset.view.check.AugmentCheckInfoDetailController;
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
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,Integer> C1;
	 
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
	 private ContextMenu contextMenu;
	 
	 @FXML
	 private Pagination pagination;
	 
	 @FXML
	 private MenuItem m1;
	 
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
							System.out.println(hiddenLevelValue);
						}
				        
					});
	     
     
	    //整改进度 :
	    
	     
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
					}
				});
	    
	    //搜索	    
	     search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(hiddenLevelValue!=null){
				   String search=String.valueOf(hiddenLevelValue);
				   searchMap.put("[Assets].[dbo].[Hidden].Hidden_Level=", search);
				 }
				
				 if(keyWord.getText()!=null){
					 searchMap.put("[Assets].[dbo].[Hidden].name like ", "%"+keyWord.getText()+"%");
				 }
				 
				 if(hiddenTypeValue!=null){
					 searchMap.put("[Assets].[dbo].[Hidden].type=", String.valueOf(hiddenTypeValue));
				 }
				 
				 setRoomInfoList(0,10,searchMap);
			}
		  });
	     
	     
	 //   setRoomInfoList(0,10,searchMap);
	     
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
								
							  String GUID=row.getItem().getGUID().get();
							  String Name=row.getItem().getName().get();
							  String menuType=MenuType.get(event.getTarget().toString());
							  System.out.println(menuType);
							  
							  Hidden_Join hidden_Jion=new Hidden_Join();
							  
							  hidden_Jion.setGUID(GUID);
							  hidden_Jion.setName(Name);
							  
							  if(menuType.equals("m1")){
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
							  
							  if(menuType.equals("m3")){
								  try {
							            // Load the fxml file and create a new stage for the popup dialog.
							            FXMLLoader loader = new FXMLLoader();
							            loader.setLocation(AssetOverviewController.class.getResource("neaten/AugmentNeatenDetail.fxml"));
							            AnchorPane page = (AnchorPane) loader.load();

							            // Create the dialog Stage.
							            Stage dialogStage = new Stage();
							            dialogStage.setTitle("添加"+hidden_Jion.getName()+"安全整顿记录");
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
							            dialogStage.setTitle(Name+"隐患检查记录");
							            dialogStage.initModality(Modality.APPLICATION_MODAL);
							            Scene scene = new Scene(page);
							            dialogStage.setScene(scene);

							            // Set the person into the controller.
							            SelectCheckInfoController controller = loader.getController();
							          //  controller.setDialogStage(dialogStage);
										Map searchMap4=new HashMap<>();           	     
							            searchMap4.put("[Assets].[dbo].[Hidden_Check].GUID=",GUID);
							            
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
							            dialogStage.setTitle(Name+"隐患整顿记录");
							            dialogStage.initModality(Modality.APPLICATION_MODAL);
							            Scene scene = new Scene(page);
							            dialogStage.setScene(scene);

							            // Set the person into the controller.
							            SelectNeatenController controller = loader.getController();
							          //  controller.setDialogStage(dialogStage);
										
							            Map searchMap5=new HashMap<>();
							            searchMap5.put("[Assets].[dbo].[Hidden_Neaten].GUID=",GUID);
							            
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
					   FXMLLoader loader = new FXMLLoader();
			            loader.setLocation(AssetOverviewController.class.getResource("infowrite/InfoWrite2.fxml"));
			            AnchorPane page = (AnchorPane) loader.load();

			            // Create the dialog Stage.
			            Stage dialogStage = new Stage();
			            dialogStage.setTitle("信息录入");
			            dialogStage.initModality(Modality.APPLICATION_MODAL);
			            Scene scene = new Scene(page);
			            dialogStage.setScene(scene);

			            // Set the person into the controller.
			            InfoWriteController2 controller = loader.getController();
			            controller.setTableView(hiddenTable,offset,limit,searchMap,pagination,C1, C2, C3, C4, C5, C6, C7, C8,C9,C10,C11);
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
	            controller.setTableView(hiddenTable,offset,limit,searchMap,pagination,C1, C2, C3, C4, C5, C6, C7, C8,C9,C10,C11);
	            
	           // Map map=assets.selectAllHidden(limit, offset, null, null, searchMap);

	            Map map=assets.selectAllHidden_Jion(limit, offset, null, null, searchMap);
	            
	   	        hidden_Jions= (List<Hidden_Join>) map.get("rows");
	            
	            Iterator<Hidden_Join> iterator=hidden_Jions.iterator();
	            
	            Hidden_Join hidden_Jion=null;
	            
	            while(iterator.hasNext()){
	            	Hidden_Join h=iterator.next();
	            	try{
	            	 if(newValue.getId().get()==h.getId()){
	            		hidden_Jion=h;
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
		  
		  map=assets.selectAllHidden_Jion(limit, offset, sort, order, search);
		  
	     hidden_Jions= (List<Hidden_Join>) map.get("rows");
	     
	     MyTestUtil.print(hidden_Jions);
	     
	     hiddenList= (ObservableList<Hidden_JoinProperty>) new RowData(hidden_Jions,Hidden_JoinProperty.class).get();
	     java.util.Iterator<Hidden_JoinProperty> iterator=hiddenList.iterator();
	    
	     while (iterator.hasNext()) {
			System.out.println("hiddenlist="+iterator.next().getDate());
		}
	     
	    hiddenTable.setItems(hiddenList);
        
	     C1.setCellValueFactory(
	                cellData -> cellData.getValue().getId().asObject());
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
	    		 cellData->cellData.getValue().getState());
	     C10.setCellValueFactory(
	    		 cellData->cellData.getValue().getRemark());
	     
	     C11.setCellValueFactory(
	    		 cellData->cellData.getValue().getDate());

	     int total=(int) map.get("total");
	     int page=total/10;
	     
	     if(total-page*10>0)
           page++;	     
	     
	     pagination.setPageCount(page);
	     	     
	 }
	 
}
