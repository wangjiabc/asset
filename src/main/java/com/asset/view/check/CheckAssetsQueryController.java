package com.asset.view.check;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.controlsfx.dialog.Dialogs;

import com.asset.MainApp;
import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenProperty;
import com.asset.property.RoomInfoProperty;
import com.asset.property.RoomInfo_PositionProperty;
import com.asset.property.join.HiddenCheck_JoinProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.daoModel.Assets.Position;
import com.voucher.manage.daoModelJoin.RoomInfo_Position;

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
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CheckAssetsQueryController {
	 
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
	 
	// @FXML
	// private TableColumn<RoomInfo_PositionProperty,String> C2;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty,String> C3;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty,String> C4;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty,String> C5;
	 
	// @FXML
	// private TableColumn<RoomInfo_PositionProperty,Double> C6;
	 
	// @FXML
	// private TableColumn<RoomInfo_PositionProperty,Double> C7;
	 
	 @FXML
	 private Pagination pagination;
	 
	 private List<RoomInfo_Position> roomInfos;
	 
	 @FXML
	 private TableView<HiddenCheck_JoinProperty> hiddenCheckTable;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,Integer> C21;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C22;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C23;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C24;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C25;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C26;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C27;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C28;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C29;
	 
	 @FXML
	 private TableColumn<HiddenCheck_JoinProperty,String> C210;
	 
	 @FXML
	 private Pagination pagination2;
	 
	 private Integer offset2;
	 
	 private Integer limit2;
	 
	 private Stage dialogStage;
	 
	 private int i=0;
	 
	 private Integer offset=0;
	 
	 private Integer limit=10;
	 
	 private Map<String,String> searchMap=new HashMap<>();
	 
	 Assets assets= new Connect().get();	 		 
	 
	 public CheckAssetsQueryController() {
		// TODO Auto-generated constructor stub
	 }
	 
	 public void setTableView(TableView<HiddenCheck_JoinProperty> hiddenCheckTable,Integer limit2,
			 Integer offset2,Pagination pagination2,TableColumn<HiddenCheck_JoinProperty,Integer> C21,TableColumn<HiddenCheck_JoinProperty,String> C22,
			 TableColumn<HiddenCheck_JoinProperty,String> C23,TableColumn<HiddenCheck_JoinProperty,String> C24,
			 TableColumn<HiddenCheck_JoinProperty,String> C25,TableColumn<HiddenCheck_JoinProperty,String> C26,
			 TableColumn<HiddenCheck_JoinProperty,String> C27,TableColumn<HiddenCheck_JoinProperty,String> C28,
			 TableColumn<HiddenCheck_JoinProperty,String> C29,TableColumn<HiddenCheck_JoinProperty,String> C210){
		 		
		 	 this.hiddenCheckTable=hiddenCheckTable;
		 	 this.limit2=limit2;
		 	 this.offset2=offset2;
		 	 this.pagination2=pagination2;
		 	 this.C21=C21;
		 	 this.C22=C22;
		 	 this.C23=C23;
		 	 this.C24=C24;
		 	 this.C25=C25;
		 	 this.C26=C26;
		 	 this.C27=C27;
		 	 this.C28=C28;
		 	 this.C29=C29;
		 	 this.C210=C210;
		 
	 	}
	 
	 @FXML
     public void initialize() {
	    
	    //搜索
	    
	     search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				 String search="%"+keyWord.getText()+"%";
				 
				 System.out.println("keyWord="+keyWord.getText());
				 
				 if(!search.equals("")){
				   searchMap.put("[RoomInfo].Address like ", search);
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
	    
	   
 
	    roomTable.setRowFactory( tv -> {
	        TableRow<RoomInfo_PositionProperty > row = new TableRow<>();
	        row.setOnMouseClicked(event -> {
	            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
	            	RoomInfo_PositionProperty  rowData = row.getItem();
	            	table(rowData);
	            }
	        });
	        return row ;
	    });
	    
	 }
	 
	 private void table(RoomInfo_PositionProperty newValue){
		 MyTestUtil.print(newValue);
		 try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(AddCheckInfoDetailController.class.getResource("AddCheckInfoDetail.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("添加资产位置");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            AddCheckInfoDetailController controller = loader.getController();
	       /*
	            Map map=assets.findAllRoomInfo_Position(limit, offset, null, null, searchMap);
	            
	            roomInfos= (List<RoomInfo_Position>) map.get("rows");
	            */
	            String Address=newValue.getAddress().getValue();
	            String ManageRegion=newValue.getManageRegion().getValue();
	            String GUID=newValue.getGUID().getValue();
	            
	            RoomInfo roomInfo=new RoomInfo();
	            
	            roomInfo.setGUID(GUID);
	            roomInfo.setAddress(Address);
	            roomInfo.setManageRegion(ManageRegion);
	            
	            controller.setHiddenCheckInfo(roomInfo);
	            controller.setTableView(hiddenCheckTable, offset2, limit2, searchMap, pagination2, C21, C22, C23, C24, C25, C26, C27, C28,C29,C210);
	            controller.setDialogStage(dialogStage);
	            // Show the dialog and wait until the user closes it
	            dialogStage.show();
	            handleCancel();
	        } catch (IOException e) {
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
	  //   C2.setCellValueFactory(
	   	//	    cellData->cellData.getValue().getGUID());
	     C3.setCellValueFactory(
	    		    cellData->cellData.getValue().getRegion());
	     C4.setCellValueFactory(
	    		    cellData->cellData.getValue().getNum());
	     C5.setCellValueFactory(
	    		    cellData->cellData.getValue().getManageRegion());
	   //  C6.setCellValueFactory(
	    //		 cellData->cellData.getValue().getLat().asObject());
	    // C7.setCellValueFactory(
	    //		 cellData->cellData.getValue().getLng().asObject());
	    
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
	 
	 @FXML
	 private void handleCancel() {
	       dialogStage.close();
	 }
}
