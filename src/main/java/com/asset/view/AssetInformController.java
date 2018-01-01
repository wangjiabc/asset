package com.asset.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.ss.formula.functions.T;

import com.asset.Singleton;
import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.join.HiddenNeaten_JoinProperty;
import com.asset.property.join.HiddenNeaten_JoinProperty;
import com.asset.tool.MenuType;
import com.asset.tool.MyTestUtil;
import com.asset.view.check.CheckInfoDetailController;
import com.asset.view.detail.AddCheckInfoDetailController;
import com.asset.view.neaten.NeatenDetailController;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Neaten;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Neaten_Join;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Neaten_Join;

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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AssetInformController extends AssetAsSwitch{

	@FXML
	 private Label rightTitleLabel;
	
	 private ObservableList<HiddenNeaten_JoinProperty> hiddenNeaten;
	 
	 @FXML
	 private TableView<HiddenNeaten_JoinProperty> hiddenNeatenTable;
	 
	 @FXML
	 private TableColumn<HiddenNeaten_JoinProperty,Integer> C1;
	 
	 @FXML
	 private TableColumn<HiddenNeaten_JoinProperty,String> C2;
	 
	 @FXML
	 private TableColumn<HiddenNeaten_JoinProperty,String> C3;
	 
	 @FXML
	 private TableColumn<HiddenNeaten_JoinProperty,String> C4;
	 
	 @FXML
	 private TableColumn<HiddenNeaten_JoinProperty,String> C5;
	 
	 @FXML
	 private TableColumn<HiddenNeaten_JoinProperty,String> C6;
	 
	 @FXML
	 private TableColumn<HiddenNeaten_JoinProperty,ProgressBar> C7;
	 
	 @FXML
	 private TableColumn<HiddenNeaten_JoinProperty,String> C8;
	 
	 @FXML
	 private Pagination pagination;
	 
	 @FXML
	 private ChoiceBox<T> hiddenName;//隐患名字
	 private List<Hidden> hidden;
	 private Integer hiddenValue;
	 
	 //查询按钮
	 @FXML
	 private Button search;
	 
	 private Map<String,String> searchMap=new HashMap<>();
	 
	 private List<Hidden_Neaten_Join> hidden_Neaten_Joins;
	 
     private Stage dialogStage;
	 
	 private Integer offset=0;
	 
	 private Integer limit=10;
	 
	 private final Desktop desktop = Desktop.getDesktop();
	 private Stage stage;
	 private File file;
	 private File docFile;
	 
	 @FXML
	 private ContextMenu contextMenu;
	 
	 Assets assets= new Connect().get();
	
	 public AssetInformController() {
		// TODO Auto-generated constructor stub
		 super();
	}
	 
	 @Override
	 protected void initCurrent() {
		 
		 URL url = getClass().getResource("");
		 String filePath=url.toString()+"Image";
		 Image image = new Image(filePath+"/inform.jpg");
	     homepage.setImage(image);
	     
	     rightTitleLabel.setText("整改记录");
	 
	     Map map=assets.selectAllHidden(1000, 0, null, null, searchMap);
			hidden=(List<Hidden>) map.get("rows");
			Iterator<Hidden> iterator=hidden.iterator();
			List levels = new ArrayList<>();
			while (iterator.hasNext()) {
				levels.add(iterator.next().getName());
				System.out.println("levels="+levels);
			}

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
		     
		   //搜索
			    
		     search.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					
					 if(hiddenValue!=null){
					   String search=hidden.get(hiddenValue).getGUID();
					   searchMap.put("[Assets].[dbo].[Hidden_Neaten].GUID=", search);
					 }
					 
					 setHiddenNeaten(0,10,searchMap);
				}
			  });
		    
		     
		     pagination.setPageFactory((Integer pageIndex)->{
			    	if (pageIndex >= 0) {
			    		offset=pageIndex*10;
			    		limit=10;
			    		System.out.println("pagination="+offset+" ______"+limit);
			    		setHiddenNeaten(offset, limit, searchMap);
			    		 Label mLabel = new Label();  
			                mLabel.setText("这是第" + (pageIndex+1) + "页");  
			                return mLabel;  
		            } else {
		                return null;
		            }
			    });
		     
		     hiddenNeatenTable.setRowFactory( tv -> {
			        TableRow<HiddenNeaten_JoinProperty> row = new TableRow<>();
			        row.setOnMouseClicked(event -> {
			            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
			            	HiddenNeaten_JoinProperty rowData = row.getItem();
			            	table(rowData);
			            }
			        });
			        
			        row.setOnContextMenuRequested(event->{
			        	
		        	    contextMenu.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								try{
									
								  String neaten_id=row.getItem().getNeaten_id().get();
								  String name=row.getItem().getNeaten_name().get();
								  String menuType=MenuType.get(event.getTarget().toString());
								  System.out.println(menuType);								  
								  
								  if(menuType.equals("m1")){
									  
									  if(Singleton.getInstance().getHidden_User().getPurview()>1){
											Alert alert2 = new Alert(AlertType.WARNING);
											alert2.setTitle("警告对话框");
											alert2.setHeaderText("警告");
											alert2.setContentText("你没有删除安全整顿记录的的权限");
											alert2.showAndWait();
											return ;
										 }
									  
									  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
								        alert.setTitle("删除");
								        alert.setHeaderText("安全整顿记录");
								        alert.setContentText("是否删除"+name+"信息");

								        ButtonType btnType1 = new ButtonType("确定");
								        ButtonType btnType2 = new ButtonType("取消");
								     

								        alert.getButtonTypes().setAll(btnType1, btnType2);

								        Optional<ButtonType> result = alert.showAndWait();
								        result.ifPresent(buttonType -> {
								            if (buttonType == btnType1) {
								                try{
								                String[] where={"[Assets].[dbo].[Hidden_Neaten].neaten_id=",neaten_id};
					                            Hidden_Neaten hidden_Neaten=new Hidden_Neaten();
					                            hidden_Neaten.setWhere(where);
					                            
								                int i=assets.deleteHiddenNeaten(hidden_Neaten);
								                if(i==1){
								                	alert.setTitle("安全整顿记录");
													alert.setHeaderText("操作");
													alert.setContentText("删除"+name+"成功");
													alert.showAndWait();
													hiddenNeatenTable.setItems(null);
													setHiddenNeaten(offset, limit,searchMap);
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
									  HiddenNeaten_JoinProperty rowData = row.getItem();
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
		 
	 
	  private void table(HiddenNeaten_JoinProperty newValue){
		  try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("neaten/NeatenDetail.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("安全隐患整顿记录");
	            dialogStage.initModality(Modality.APPLICATION_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            NeatenDetailController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setTableView(hiddenNeatenTable,offset,limit,searchMap,pagination,C1, C2, C3, C4, C5, C6, C7, C8);
	            	     
	            System.out.println("neaten_id="+newValue.getNeaten_id());
	            searchMap.put("[Assets].[dbo].[Hidden_Neaten].neaten_id=",newValue.getNeaten_id().get());
	            
	            System.out.println("neatenid="+newValue.getNeaten_id().get());
	            
	            Map map=assets.selectAllHiddenNeaten(limit, offset, null, null, searchMap);
	            MyTestUtil.print(map);
	            List<Hidden_Neaten_Join> hidden_Neaten_Joins= (List<Hidden_Neaten_Join>) map.get("rows");
	            MyTestUtil.print(hidden_Neaten_Joins);

	            try{
	               Hidden_Neaten_Join hidden_Neaten_Join=hidden_Neaten_Joins.get(0);          
	               controller.setHiddenNeaten(hidden_Neaten_Join);
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
		 
		 void setHiddenNeaten(Integer offset,Integer limit,Map search){

		      String sort=null;
		      String order=null;
		     
			  Map map=new HashMap<>();
			  
			  	
			  map=assets.selectAllHiddenNeaten(limit, offset, sort, order, search);
			  
		     hidden_Neaten_Joins=  (List<Hidden_Neaten_Join>) map.get("rows");
		     MyTestUtil.print( hidden_Neaten_Joins);
		     
		     hiddenNeaten= (ObservableList<HiddenNeaten_JoinProperty>) new RowData(hidden_Neaten_Joins,HiddenNeaten_JoinProperty.class).get();
		     

		    hiddenNeatenTable.setItems(hiddenNeaten);
	       
		     C1.setCellValueFactory(
		                cellData -> cellData.getValue().getId().asObject());
		     C2.setCellValueFactory(
		   		    cellData->cellData.getValue().getName());
		     C3.setCellValueFactory(
		    		    cellData->cellData.getValue().getPrincipal());
		     C4.setCellValueFactory(
		    		    cellData->cellData.getValue().getNeaten_name());
		     C5.setCellValueFactory(
		    		    cellData->cellData.getValue().getNeaten_instance());
		     C6.setCellValueFactory(
		    		    cellData->cellData.getValue().getHappen_time());	
		     
		     C7.setCellValueFactory(
		    		    new Callback<TableColumn.CellDataFeatures<HiddenNeaten_JoinProperty,ProgressBar>, ObservableValue<ProgressBar>>() {
							
							@Override
							public ObservableValue<ProgressBar> call(CellDataFeatures<HiddenNeaten_JoinProperty, ProgressBar> param) {
								// TODO Auto-generated method stub
								DoubleProperty d=param.getValue().getProgress();
								Double dd=d.doubleValue();
								ProgressBar progressBar=new ProgressBar();
								progressBar.setProgress(dd);
								return new SimpleObjectProperty<ProgressBar>(progressBar);
							}
						});
						
		      C8.setCellValueFactory(
		    		    cellData->cellData.getValue().getDate());
		     
		     int total=(int) map.get("total");
		     int page=total/10;
		     
		     if(total-page*10>0)
	          page++;	     
		     System.out.println("page="+page);
		     pagination.setPageCount(page);
		     	     
		 }
	 
}
