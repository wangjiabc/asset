package com.asset.view.child;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.controlsfx.dialog.Dialogs;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.RoomInfoProperty;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.RoomInfo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RoomInfoQueryController {
	 
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
	 
	 private ObservableList<RoomInfoProperty> roomInfoList;
	 
	 @FXML
	 private TableView<RoomInfoProperty> roomInfoTable;
	 
	 @FXML
	 private TableColumn<RoomInfoProperty,String> C1;
	 
	 @FXML
	 private TableColumn<RoomInfoProperty,String> C2;
	 
	 @FXML
	 private TableColumn<RoomInfoProperty,String> C3;
	 
	 @FXML
	 private TableColumn<RoomInfoProperty,String> C4;
	 
	 @FXML
	 private TableColumn<RoomInfoProperty,String> C5;
	 
	 @FXML
	 private Pagination pagination;
	 
	 public RoomInfoQueryController() {
		// TODO Auto-generated constructor stub
	}
	 
	 @FXML
     private void initialize() {
		 URL url = getClass().getResource("");		 
		 String filePath=url.toString()+"Image";
		 
		 Image image=new Image(filePath+"/search.png");
	     searchImage.setImage(image);
		 
		 /*
	      * 查询条件
	      */
	     Map<String,String> map=new HashMap<>();
	     //隐患级别 :
	     MenuItem level1=new MenuItem("一类");	     
	     MenuItem level2=new MenuItem("二类");
	     MenuItem level3=new MenuItem("三类");
	     
	     level.getItems().addAll(level1,level2,level3);
	     
	     level1.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				level.setText("一类");
			}
		  });
	     
	     level2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				level.setText("二类");
			}
		 });
	     
	     level3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				level.setText("三类");
			}
		 });
	     
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
				 Dialogs.create()
	                .title("search")
	                .masthead(level.getText())
	                .message(keyWord.getText())
	                .owner(null)
	                .showWarning();
				 String search="%"+keyWord.getText()+"%";
				 
				 if(!search.equals("")){
				   map.put("Address like ", search);
				 }else {
					map.clear();
				}
				 setRoomInfoList(0,10,map);
			}
		  });
	     
	     
	    setRoomInfoList(0,10,map);
	     
	    pagination.setPageFactory((Integer pageIndex)->{
	    	if (pageIndex >= 0) {
	    		setRoomInfoList(pageIndex*10,10,map);
	    		 Label mLabel = new Label();  
	                mLabel.setText("这是第" + (pageIndex+1) + "页");  
	                return mLabel;  
            } else {
                return null;
            }
	    });
	    
	   
	    roomInfoTable.getSelectionModel().selectedItemProperty().addListener(
	    		(observable, oldValue, newValue) ->table(newValue));
	    
	 }
	 
	 private void table(RoomInfoProperty newValue){
		 Dialogs.create()
         .title("search")
         .masthead(newValue.toString())
         .message(keyWord.getText())
         .owner(null)
         .showWarning();
	 }
	 
	 void setRoomInfoList(Integer offset,Integer limit,Map search){

	      String sort=null;
	      String order=null;
	     
		  Map map=new HashMap<>();
		  
		  Assets assets= new Connect().get();
		  map=assets.getRoomInfo(limit, offset, sort, order, search);

	     List<RoomInfo> roomInfo=(List<RoomInfo>) map.get("rows");
	     
	     MyTestUtil.print(roomInfo);
	     
	      roomInfoList=(ObservableList<RoomInfoProperty>) new RowData(roomInfo,RoomInfoProperty.class).get();
	     java.util.Iterator<RoomInfoProperty> iterator=roomInfoList.iterator();
	    
	     while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	     
	    roomInfoTable.setItems(roomInfoList);
	     
	     C1.setCellValueFactory(
	                cellData -> cellData.getValue().getNum());
	     C2.setCellValueFactory(
	    		    cellData->cellData.getValue().getAddress());
	     C3.setCellValueFactory(
	    		    cellData->cellData.getValue().getOriginalAddress());
	     C4.setCellValueFactory(
	    		    cellData->cellData.getValue().getGUID());
	     C5.setCellValueFactory(
	    		    cellData->cellData.getValue().getOriginalNum());
	     
	     int total=(int) map.get("total")/10;
	     
	     if(total<1)
	    	 total=1;
	     
	     pagination.setPageCount(total);
	 
	 }
	 
}
