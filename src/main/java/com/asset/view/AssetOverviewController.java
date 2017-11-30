package com.asset.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.controlsfx.dialog.Dialogs;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenProperty;
import com.asset.tool.MyTestUtil;
import com.asset.view.assets.AssetsQueryController;
import com.asset.view.hidden.HiddenDetailController;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AssetOverviewController extends AssetAsSwitch{

	 @FXML
	 private Label rightTitleLabel;
 	 
	 /*
      * 查询条件菜单
      */
	 @FXML
	 private TextField keyWord;
	 @FXML
	 private SplitMenuButton level;	 
	 @FXML
	 private SplitMenuButton plan;	 
	 @FXML
	 private SplitMenuButton instance;	 
	 //查询按钮
	 @FXML
	 private Button search;
	 
	 @FXML
	 private ImageView searchImage;
	 
	 private ObservableList<HiddenProperty> hiddenList;
	 
	 @FXML
	 private TableView<HiddenProperty> hiddenTable;
	 
	 @FXML
	 private TableColumn<HiddenProperty,Integer> C1;
	 
	 @FXML
	 private TableColumn<HiddenProperty,String> C2;
	 
	 @FXML
	 private TableColumn<HiddenProperty,String> C3;
	 
	 @FXML
	 private TableColumn<HiddenProperty,Integer> C4;
	 
	 @FXML
	 private TableColumn<HiddenProperty,String> C5;
	 
	 @FXML
	 private TableColumn<HiddenProperty,String> C6;
	 
	 @FXML
	 private TableColumn<HiddenProperty,Integer> C7;
	 
	 @FXML
	 private TableColumn<HiddenProperty,Integer> C8;
	 
	 @FXML
	 private TableColumn<HiddenProperty,String> C9;
	 
	 @FXML
	 private TableColumn<HiddenProperty,String> C10;
	 
	 @FXML
	 private TableColumn<HiddenProperty,String> C11;
	 
	 @FXML
	 private Pagination pagination;
	 
	 private List<Hidden> hiddens;
	 
	 private Stage dialogStage;
	 
	 private int i=0;
	 
	 private Integer offset=0;
	 
	 private Integer limit=10;
	 
	 private Map<String,String> searchMap=new HashMap<>();
	 
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
		 
	     	     
	     List<MenuItem> menuItems=new ArrayList<>();
	     String a[]={"1","2","3"};
	     for(int i=0;i<3;i++){
	    	 menuItems.add(new MenuItem(String.valueOf(i)));
	    	 String aa=a[i];
	    	 menuItems.get(i).setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					level.setText(aa);
				}
			});
	    	level.getItems().add(menuItems.get(i));
	     }
	     
	     
	     
	     
	    //整改进度 :
	     MenuItem exhale=new MenuItem("已发整改通知");
	    
	     plan.getItems().addAll(exhale);
	     
	     exhale.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				plan.setText("已发整改通知");
			}
		  });
	     
	    //隐患情况 :
	     MenuItem bigness=new MenuItem("具有重大消防隐患");
	     
	     instance.getItems().addAll(bigness);
	     
	     bigness.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				instance.setText("具有重大消防隐患");
			}
		  });
	    
	    //搜索
	    
	     search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				 Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("search");
					alert.setHeaderText(level.getText());
					alert.setContentText(keyWord.getText());
					alert.showAndWait();
				 String search="%"+keyWord.getText()+"%";
				 
				 if(!search.equals("")){
				   searchMap.put("HiddenInstance like ", search);
				 }else {
					searchMap.clear();
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
	    
	    hiddenTable.getSelectionModel().selectedItemProperty().addListener(
	    		new ChangeListener<HiddenProperty>() {

					@Override
					public void changed(ObservableValue<? extends HiddenProperty> observable, HiddenProperty oldValue,
							HiddenProperty newValue) {
						// TODO Auto-generated method stub
						if(i>=1){
							if(newValue!=null)
						      table(newValue);
						}else{
							i++;
						}
					}
				 }
	    		);
	    
	 }
	 
	 private void table(HiddenProperty newValue){
		 try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(AssetOverviewController.class.getResource("detail/AssetOverviewDetail.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("隐患");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            HiddenDetailController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setTableView(hiddenTable,offset,limit,searchMap,pagination,C1, C2, C3, C4, C5, C6, C7, C8);
	            
	            Map map=assets.selectAllHidden(limit, offset, null, null, searchMap);

	   	        hiddens= (List<Hidden>) map.get("rows");
	            
	            Iterator<Hidden> iterator=hiddens.iterator();
	            
	            Hidden hidden=null;
	            
	            while(iterator.hasNext()){
	            	Hidden h=iterator.next();
	            	if(newValue.getId().get()==h.getId()){
	            		hidden=h;
	            		break;
	            	}
	            }
	            
	            controller.setHidden(hidden);

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
		  
		  
		  map=assets.selectAllHidden(limit, offset, sort, order, search);

	     hiddens= (List<Hidden>) map.get("rows");
	     
	     MyTestUtil.print(hiddens);
	     
	     hiddenList= (ObservableList<HiddenProperty>) new RowData(hiddens,HiddenProperty.class).get();
	     java.util.Iterator<HiddenProperty> iterator=hiddenList.iterator();
	    
	     while (iterator.hasNext()) {
			System.out.println("hiddenlist="+iterator.next().getDate());
		}
	     
	    hiddenTable.setItems(hiddenList);

	     C1.setCellValueFactory(
	                cellData -> cellData.getValue().getId().asObject());
	     C2.setCellValueFactory(
	   		    cellData->cellData.getValue().getGUID());
	     C3.setCellValueFactory(
	    		    cellData->cellData.getValue().getName());
	     C4.setCellValueFactory(
	    		    cellData->cellData.getValue().getHidden_level().asObject());
	     C5.setCellValueFactory(
	    		    cellData->cellData.getValue().getDetail());
	     C6.setCellValueFactory(
	    		    cellData->cellData.getValue().getHappen_time());
	     C7.setCellValueFactory(
	    		    cellData->cellData.getValue().getPrincipal().asObject());
	     C8.setCellValueFactory(
	    		 cellData->cellData.getValue().getType().asObject());
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
