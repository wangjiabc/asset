package com.asset.view.assets;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.controlsfx.dialog.Dialogs;

import com.asset.MainApp;
import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenProperty;
import com.asset.property.RoomInfoProperty;
import com.asset.property.RoomInfo_PositionProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.MenuType;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Assets;
import com.voucher.manage.daoModelJoin.RoomInfo_Position;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Assets_Join;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Join;

import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AssetsQueryController {
	 
	 /*
      * 查询条件菜单
      */
	 @FXML
	 private TextField keyWord;

	 //查询按钮
	 @FXML
	 private Button search;

	 
	 private ObservableList<RoomInfo_PositionProperty> roomList;
	 
	 @FXML
	 private TableView<RoomInfo_PositionProperty> roomTable;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty,String> C1;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty,String> C2;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty,String> C3;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty,String> C4;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty,String> C5;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty,Double> C6;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty,Double> C7;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty,String> C8;
	 
	 @FXML
	 private ContextMenu contextMenu;
	 
	 @FXML
	 private Pagination pagination;
	 
	 private List<Hidden_Assets_Join> roomInfos;
	 
	 private Stage dialogStage;
	 
	 private int i=0;
	 
	 private Integer offset=0;
	 
	 private Integer limit=10;
	 
	 private Map<String,String> searchMap=new HashMap<>();
	 
	 private Hidden hidden;
	 
	 Assets assets= new Connect().get();
	 
	 public AssetsQueryController() {
		// TODO Auto-generated constructor stub
	}
	
	 public void setHidden(Hidden hidden) {
		this.hidden = hidden;
		searchMap.put("[Assets].[dbo].[Hidden_Assets].[hidden_GUID] =", hidden.getGUID());
	}

	 
	 @FXML
     private void initialize() {	
	    //搜索
	    
	     search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				 Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("search");
					alert.setContentText(keyWord.getText());
					alert.showAndWait();
				 String search="%"+keyWord.getText()+"%";
				 
				 if(!search.equals("")){
					 searchMap.put("[TTT].[dbo].[RoomInfo].Address like ", search);
					// searchMap.put("[Assets].[dbo].[RoomInfo].Address like ", search);
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
	    
	   
	    roomTable.setRowFactory( tv -> {
	        TableRow<RoomInfo_PositionProperty> row = new TableRow<>();
	        row.setOnMouseClicked(event -> {
	            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
	            	RoomInfo_PositionProperty rowData = row.getItem();

	            }
	        });
	      
	        row.setOnContextMenuRequested(event->{
	        	
        	    contextMenu.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						try{
							
						  String GUID=row.getItem().getGUID().get();
						  String Address=row.getItem().getAddress().get();
						  String menuType=MenuType.get(event.getTarget().toString());
						  System.out.println(menuType);
						  
						  
						  if(menuType.equals("m1")){
							  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
						        alert.setTitle("安全信息");
						        alert.setHeaderText("删除");
						        alert.setContentText("是否删除"+Address+"信息");

						        ButtonType btnType1 = new ButtonType("确定");
						        ButtonType btnType2 = new ButtonType("取消");
						     

						        alert.getButtonTypes().setAll(btnType1, btnType2);

						        Optional<ButtonType> result = alert.showAndWait();
						        result.ifPresent(buttonType -> {
						            if (buttonType == btnType1) {
						                try{
						                String[] where={"[Assets].[dbo].[Hidden_Assets].asset_GUID=",GUID};
			                            Hidden_Assets hidden_Assets=new Hidden_Assets();
			                            hidden_Assets.setWhere(where);
			                            
						                int i=assets.deleteHidden_Assets(hidden_Assets);
						                if(i==1){
						                	alert.setTitle("安全信息");
											alert.setHeaderText("操作");
											alert.setContentText("删除"+Address+"成功");
											alert.showAndWait();
											roomTable.setItems(null);
											setRoomInfoList(offset, limit,searchMap);
						                }else{
						                	Alert alert2 = new Alert(AlertType.ERROR);
											alert2.setTitle("异常堆栈对话框");
											alert2.setHeaderText("错误");
											alert2.setContentText("删除"+Address+"失败");
											alert2.showAndWait();
						                }
						                }catch (Exception e) {
											// TODO: handle exception
						                	Alert alert2 = new Alert(AlertType.ERROR);
											alert2.setTitle("异常堆栈对话框");
											alert2.setHeaderText("错误");
											alert2.setContentText("删除"+Address+"失败");
											alert2.showAndWait();
											e.printStackTrace();
										}
						            } else if (buttonType == btnType2) {
						            	System.out.println("点击了取消");
						            } 
						        });
						  }
						}catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
        	    });
	        });
	       
	      return row;
	   });
	    
	}
	 
	 
	 void setRoomInfoList(Integer offset,Integer limit,Map search){

	      String sort=null;
	      String order=null;
	     
		  Map map=new HashMap<>();
		  
		  
		  map=assets.findAssetByHideen(limit, offset, sort, order, search);

	     roomInfos= (List<Hidden_Assets_Join>) map.get("rows");
	     
	     MyTestUtil.print(roomInfos);
	     
	     roomList= (ObservableList<RoomInfo_PositionProperty>) new RowData(roomInfos,RoomInfo_PositionProperty.class).get();
	     Iterator<RoomInfo_PositionProperty> iterator=roomList.iterator();
	    
	     while (iterator.hasNext()) {
			System.out.println("roominfos="+iterator.next().getAddress());
		}
	     
	    roomTable.setItems(roomList);

	     C1.setCellValueFactory(
	                cellData -> cellData.getValue().getAddress());
	     C2.setCellValueFactory(
	   		    cellData->cellData.getValue().getGUID());
	     C3.setCellValueFactory(
	    		    cellData->cellData.getValue().getRegion());
	     C4.setCellValueFactory(
	    		    cellData->cellData.getValue().getNum());
	     C5.setCellValueFactory(
	    		    cellData->cellData.getValue().getInDate());
	     C6.setCellValueFactory(
	    		 cellData->cellData.getValue().getLat().asObject());
	     C7.setCellValueFactory(
	    		 cellData->cellData.getValue().getLng().asObject());
	    
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
	 
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
}

