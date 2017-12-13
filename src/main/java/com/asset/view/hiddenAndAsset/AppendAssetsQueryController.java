package com.asset.view.hiddenAndAsset;

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
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Assets;
import com.voucher.manage.daoModel.Assets.Position;
import com.voucher.manage.daoModelJoin.RoomInfo_Position;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppendAssetsQueryController {
	 
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
	 private Pagination pagination;
	 
	 private List<RoomInfo_Position> roomInfos;
	 
	 private Stage dialogStage;
	 
	 private int i=0;
	 
	 private Integer offset=0;
	 
	 private Integer limit=10;
	 
	 private Map<String,String> searchMap=new HashMap<>();
	 
	 Assets assets= new Connect().get();
	 
	 private Hidden hidden;
	 
     public void setHidden(Hidden hidden){
    	 this.hidden=hidden;
     }
	 
	 public AppendAssetsQueryController() {
		// TODO Auto-generated constructor stub
	 }
	 
	 @FXML
     public void initialize() {
	    
	    //搜索
	    
	     search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				 String search="%"+keyWord.getText()+"%";
				 
				 if(!search.equals("")){
				   searchMap.put("[TTT].[dbo].[RoomInfo].Address like ", search);
				 }else {
					searchMap.clear();
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
	    
	   
 
	    roomTable.getSelectionModel().selectedItemProperty().addListener(
	    		new ChangeListener<RoomInfo_PositionProperty>() {

					@Override
					public void changed(ObservableValue<? extends RoomInfo_PositionProperty> observable, RoomInfo_PositionProperty oldValue,
							RoomInfo_PositionProperty newValue) {
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
	 
	 private void table(RoomInfo_PositionProperty newValue){
		 MyTestUtil.print(newValue);
		 try {
	          String asset_GUID=newValue.getGUID().get();
	          String assetName=newValue.getAddress().get();
	          String hidden_GUID=hidden.getGUID();
	          
	          Hidden_Assets hidden_Assets=new Hidden_Assets();
	          
	          hidden_Assets.setAsset_GUID(asset_GUID);
	          hidden_Assets.setHidden_GUID(hidden_GUID);
	          
	          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		        alert.setTitle("安全信息");
		        alert.setHeaderText("添加");
		        alert.setContentText("是否添加"+assetName+"信息");

		        ButtonType btnType1 = new ButtonType("确定");
		        ButtonType btnType2 = new ButtonType("取消");
		     

		        alert.getButtonTypes().setAll(btnType1, btnType2);

		        Optional<ButtonType> result = alert.showAndWait();
		        result.ifPresent(buttonType -> {
		            if (buttonType == btnType1) {
		              try{
		                 int i=assets.insertIntoHidden_Assets(hidden_Assets);
		                if(i==1){
		                	alert.setTitle("安全信息");
							alert.setHeaderText("操作");
							alert.setContentText("添加"+assetName+"信息"+"成功");
							alert.showAndWait();
							handleCancel();
		                }else{
		                	Alert alert2 = new Alert(AlertType.ERROR);
							alert2.setTitle("异常堆栈对话框");
							alert2.setHeaderText("错误");
							alert2.setContentText("添加"+assetName+"信息"+"失败");
							alert2.showAndWait();
		                }
		                }catch (Exception e) {
							// TODO: handle exception
		                	Alert alert2 = new Alert(AlertType.ERROR);
							alert2.setTitle("异常堆栈对话框");
							alert2.setHeaderText("错误");
							alert2.setContentText("添加"+assetName+"信息"+"失败");
							alert2.showAndWait();
						}
		            } else if (buttonType == btnType2) {
		            	System.out.println("点击了取消");
		            } 
		        });
	          
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
	 
	 void setRoomInfoList(Integer offset,Integer limit,Map search){

	      String sort=null;
	      String order=null;
	     
		  Map map=new HashMap<>();
		  
		  
		  map=assets.findAllRoomInfo_Position(limit, offset, sort, order, search);

	     roomInfos= (List<RoomInfo_Position>) map.get("rows");
	     
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
	     
	     pagination.setPageCount(page);
	 
	 }
	 
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	  }
	 
	 @FXML
	 private void handleCancel() {
	       dialogStage.close();
	 }
}

