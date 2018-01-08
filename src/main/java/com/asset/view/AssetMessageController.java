package com.asset.view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import com.asset.view.check.CheckInfoDetailController;
import com.asset.view.detail.AddCheckInfoDetailController;
import com.asset.view.hidden.HiddenDetailController;
import com.asset.view.infowrite.InfoWriteController2;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Check;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Check_Join;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Join;

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
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
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
	 private TableColumn<HiddenCheck_JoinProperty,ProgressBar> C7;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C8;
	 
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
	     
	 	Map map=assets.selectAllHidden(1000, 0, null, null, searchMap);
		hidden=(List<Hidden>) map.get("rows");
		Iterator<Hidden> iterator=hidden.iterator();
		List levels = new ArrayList<>();
		while (iterator.hasNext()) {
			levels.add(iterator.next().getName());
			System.out.println("levels="+levels);
		}

		
		hiddenWrite.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				main.showAssetOverview();
			}
			
		});
		
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
				   System.out.println("search="+search);
				   searchMap.put("[Assets].[dbo].[Hidden_Check].GUID=", search);
				 }
				 
				 setHiddenCheckList(0,10,searchMap);
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
								                String[] where={"[Assets].[dbo].[Hidden_Check].check_id=",check_id};
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
	            dialogStage.setTitle("安全隐患检查记录");
	            dialogStage.initModality(Modality.APPLICATION_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            CheckInfoDetailController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setTableView(hiddenCheckTable,offset,limit,searchMap,pagination,C1, C2, C3, C4, C5, C6, C7, C8);
	            	     
	            System.out.println("check_id="+newValue.getCheck_id());
	            searchMap.put("[Assets].[dbo].[Hidden_Check].check_id=",newValue.getCheck_id().get());
	            
	            Map map=assets.selectAllHiddenCheck(limit, offset, null, null, searchMap);
	            
	            List<Hidden_Check_Join> hidden_Check_Joins= (List<Hidden_Check_Join>) map.get("rows");
	            MyTestUtil.print(hidden_Check_Joins);
	            try{
	               Hidden_Check_Join hidden_Check_Join=hidden_Check_Joins.get(0);            
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

	      String sort=null;
	      String order=null;
	     
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
	   		    cellData->cellData.getValue().getName());
	     C3.setCellValueFactory(
	    		    cellData->cellData.getValue().getPrincipal());
	     C4.setCellValueFactory(
	    		    cellData->cellData.getValue().getCheck_name());
	     C5.setCellValueFactory(
	    		    cellData->cellData.getValue().getCheck_circs());
	     C6.setCellValueFactory(
	    		    cellData->cellData.getValue().getHappen_time());	
	     
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
					
	      C8.setCellValueFactory(
	    		    cellData->cellData.getValue().getDate());
	     
	     int total=(int) map.get("total");
	     int page=total/10;
	     
	     if(total-page*10>0)
          page++;	     
	     System.out.println("page="+page);
	     pagination.setPageCount(page);
	     	     
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
