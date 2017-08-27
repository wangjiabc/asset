package com.asset.view;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import com.asset.model.RoomInfoProperty;
import com.asset.model.roomInfoData;
import com.gargoylesoftware.htmlunit.javascript.host.Iterator;
import com.voucher.manage.daoModel.RoomInfo;

import controller.model.Person;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AssetOverviewController extends AssetAsSwitch{
	
	 @FXML
	 private ImageView search;
	 
	 @FXML
	 private Label rightTitleLabel;
	 
	 @FXML
	 private SplitMenuButton level;
	 
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
	     
	     image=new Image(filePath+"/search.png");
	     search.setImage(image);
	     
	     rightTitleLabel.setText("主页");
	     
	     MenuItem logout=new MenuItem("logout");
	     
	     level.getItems().addAll(logout,new MenuItem("sleep"));
	     
	     logout.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				level.setText("logout");
			}
		});
	     
	    setRoomInfoList(0,10);
	     
	    pagination.setPageFactory((Integer pageIndex)->{
	    	if (pageIndex >= 0) {
	    		setRoomInfoList(pageIndex,10);
	    		System.out.println(pageIndex);
	    		 Label mLabel = new Label();  
	                mLabel.setText("这是第" + (pageIndex+1) + "页");  
	                return mLabel;  
            } else {
                return null;
            }
	    });
	 }
	 
	 void setRoomInfoList(Integer offset,Integer limit){

	      String sort=null;
	      String order=null;
		  String search=null;
	     
		  Map map=new HashMap<>();
		 
	      map=new roomInfoData().get(limit, offset, sort, order, search);
	     
	     roomInfoList=(ObservableList<RoomInfoProperty>) map.get("value");
	      
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
	     
	     pagination.setPageCount(total);
	 }
	 
}
