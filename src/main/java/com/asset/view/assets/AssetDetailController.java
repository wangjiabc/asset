package com.asset.view.assets;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenProperty;
import com.asset.property.RoomInfoProperty;
import com.asset.property.RoomInfo_PositionProperty;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.daoModelJoin.RoomInfo_Position;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AssetDetailController {
	@FXML
	private TextField id;
	@FXML
	private TextField hiddenlevel;
	@FXML
	private TextField changespeed;

	@FXML
	private TextField time;
	
	@FXML
	private TextField Num;
	
	@FXML
	private TextField Lat;
	
	@FXML
	private TextField Lng;
	
	@FXML
	private Button update;
	
	@FXML
	private Button delete;
	
	private Stage dialogStage;
	
	private ObservableList<RoomInfo_PositionProperty> roomList;

	private TableView<RoomInfo_PositionProperty> roomTable;
	
	private List<RoomInfo_Position> roomInfos;
	
	private Pagination pagination;
	
	private Integer offset;
	 
	 private Integer limit;
	
	 private Map<String,String> searchMap;
	 
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
	 private TableColumn<RoomInfo_PositionProperty, Double> C6;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty, Double> C7;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty,String> C8;
	
	 private RoomInfo_Position roomInfo;
	 
	public AssetDetailController() {
		// TODO Auto-generated constructor stub
	}
	
	public void setTableView(TableView<RoomInfo_PositionProperty> roomTable,Integer offset,Integer limit,
			Map<String,String> searchMap,Pagination pagination,TableColumn<RoomInfo_PositionProperty, String> C1,
			TableColumn<RoomInfo_PositionProperty, String> C2,TableColumn<RoomInfo_PositionProperty, String> C3,TableColumn<RoomInfo_PositionProperty, String> C4,
			TableColumn<RoomInfo_PositionProperty, String> C5,TableColumn<RoomInfo_PositionProperty, Double> C6,
			TableColumn<RoomInfo_PositionProperty, Double> C7,TableColumn<RoomInfo_PositionProperty, String> C8) {
		this.roomTable=roomTable;
		this.offset=offset;
		this.limit=limit;
		this.searchMap=searchMap;
		this.pagination=pagination;
		this.C1=C1;
		this.C2=C2;
		this.C3=C3;
		this.C4=C4;
		this.C5=C5;
		this.C6=C6;
		this.C7=C7;
		this.C8=C8;
	}
	
	 @FXML
	 private void initialize() {
		 
		 Assets assets=new Connect().getAssets();
		 
		 update.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Date date=new Date();
				RoomInfo roomInfo2=new RoomInfo();
				String[] where={"GUID=",roomInfo.getGUID()};
				roomInfo2.setWhere(where);
				try{
					if(id.getText()!=null)
						roomInfo2.setGUID(id.getText());
					if(hiddenlevel.getText()!=null)
					   roomInfo2.setAddress(hiddenlevel.getText());
					if(changespeed.getText()!=null)
						roomInfo2.setRegion(changespeed.getText());
				MyTestUtil.print(roomInfo2);
					roomInfo2.setInDate(date);

				int i=assets.updateRoomInfo(roomInfo2);						
							 				
				if(i==0){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框0");
					alert.setHeaderText("错误");
					alert.setContentText("写入失败");
					alert.showAndWait();
				}else if(i==1){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("对话框");
					alert.setHeaderText("更新数据");
					alert.setContentText("写入成功");
					alert.showAndWait();
					roomTable.setItems(null);
					setRoomInfoList(offset, limit,searchMap);
					handleCancel();
				 }
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框2");
					alert.setHeaderText("错误");
					alert.setContentText("写入失败");
					alert.showAndWait();
				}
			}		
		 });
		 
		 delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			        alert.setTitle("安全信息");
			        alert.setHeaderText("删除");
			        alert.setContentText("是否删除"+roomInfo.getGUID()+"信息");

			        ButtonType btnType1 = new ButtonType("确定");
			        ButtonType btnType2 = new ButtonType("取消");
			     

			        alert.getButtonTypes().setAll(btnType1, btnType2);

			        Optional<ButtonType> result = alert.showAndWait();
			        result.ifPresent(buttonType -> {
			            if (buttonType == btnType1) {
			              try{
			                String[] where={"GUID=",roomInfo.getGUID()};
			                RoomInfo roomInfo2=new RoomInfo();
			            	roomInfo2.setWhere(where);
			               int i=assets.deleteRoomInfo(roomInfo2);
			                if(i==1){
			                	alert.setTitle("安全信息");
								alert.setHeaderText("操作");
								alert.setContentText("删除"+roomInfo.getAddress()+"成功");
								alert.showAndWait();
			                }else{
			                	Alert alert2 = new Alert(AlertType.ERROR);
								alert2.setTitle("异常堆栈对话框");
								alert2.setHeaderText("错误");
								alert2.setContentText("删除"+roomInfo.getAddress()+"失败");
								alert2.showAndWait();
			                }
			                }catch (Exception e) {
								// TODO: handle exception
			                	Alert alert2 = new Alert(AlertType.ERROR);
								alert2.setTitle("异常堆栈对话框");
								alert2.setHeaderText("错误");
								alert2.setContentText("删除"+roomInfo.getAddress()+"失败");
								alert2.showAndWait();
							}
			            } else if (buttonType == btnType2) {
			            	System.out.println("点击了取消");
			            } 
			        });
			}
		});
		 
	 }
	 
	 
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
	 
	 /**
	     * Called when the user clicks cancel.
	     */
	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }
	    
	 public void setAssets(RoomInfo_Position roomInfo2){
		 this.roomInfo=roomInfo2;
		 id.setText(roomInfo2.getGUID());
		 hiddenlevel.setText(roomInfo2.getAddress());
		 changespeed.setText(roomInfo2.getRegion());
		 time.setText(String.valueOf(roomInfo2.getInDate()));
		 Num.setText(roomInfo2.getNum());
		  Lat.setText(String.valueOf(roomInfo2.getLat()));
		  Lng.setText(String.valueOf(roomInfo2.getLng()));
	 }
	 
	 void setRoomInfoList(Integer offset,Integer limit,Map search){

	      String sort=null;
	      String order=null;
	     
		  Map map=new HashMap<>();
		  
		  Assets assets= new Connect().get();
		  map=assets.selectAllHidden(limit, offset, sort, order, search);

	     roomInfos= (List<RoomInfo_Position>) map.get("rows");
	     
	     MyTestUtil.print(roomInfos);
	     
	     roomList=  (ObservableList<RoomInfo_PositionProperty>) new RowData(roomInfos,RoomInfo_PositionProperty.class).get();
	     Iterator<RoomInfo_PositionProperty> iterator=roomList.iterator();
	    
	     while (iterator.hasNext()) {
			System.out.println("hiddenlist="+iterator.next().getAddress());
		}
	     
	    roomTable.setItems(roomList);

	     C1.setCellValueFactory(
	                cellData -> cellData.getValue().getAddress());
	     C2.setCellValueFactory(
	   		    cellData->cellData.getValue().getGUID());
	     C3.setCellValueFactory(
	    		    cellData->cellData.getValue().getNum());
	     C4.setCellValueFactory(
	    		    cellData->cellData.getValue().getRegion());
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
	 
}

