package com.asset.view.hidden;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenProperty;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;

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

public class HiddenDetailController {
	@FXML
	private TextField id;
	@FXML
	private TextField hiddenlevel;
	@FXML
	private TextField changespeed;
	@FXML
	private TextField hiddneinstance;
	@FXML
	private TextField doubletest;
	@FXML
	private TextField floattest;
	@FXML
	private TextField longtest;
	@FXML
	private TextField time;
	
	@FXML
	private Button update;
	
	@FXML
	private Button delete;
	
	private Hidden hidden;
	
	private Stage dialogStage;
	
	private ObservableList<HiddenProperty> hiddenList;

	private TableView<HiddenProperty> hiddenTable;
	
	private List<Hidden> hiddens;
	
	private Pagination pagination;
	
	private Integer offset;
	 
	 private Integer limit;
	
	 private Map<String,String> searchMap;
	 
	 @FXML
	 private TableColumn<HiddenProperty,Integer> C1;
	 
	 @FXML
	 private TableColumn<HiddenProperty, String> C2;
	 
	 @FXML
	 private TableColumn<HiddenProperty,String> C3;
	 
	 @FXML
	 private TableColumn<HiddenProperty, Integer> C4;
	 
	 @FXML
	 private TableColumn<HiddenProperty, String> C5;
	 
	 @FXML
	 private TableColumn<HiddenProperty, String> C6;
	 
	 @FXML
	 private TableColumn<HiddenProperty, Integer> C7;
	 
	 @FXML
	 private TableColumn<HiddenProperty, Integer> C8;
	
	public HiddenDetailController() {
		// TODO Auto-generated constructor stub
	}
	
	public void setTableView(TableView<HiddenProperty> hiddenTable,Integer offset,Integer limit,
			Map<String,String> searchMap,Pagination pagination,TableColumn<HiddenProperty,Integer> C1,
			TableColumn<HiddenProperty,String> C2,TableColumn<HiddenProperty,String> C3,TableColumn<HiddenProperty,Integer> C4,
			TableColumn<HiddenProperty,String> C5,TableColumn<HiddenProperty,String> C6,
			TableColumn<HiddenProperty,Integer> C7,TableColumn<HiddenProperty,Integer> C8) {
		this.hiddenTable=hiddenTable;
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
				Hidden hidden2=new Hidden();
				hidden2.setId(null);
				String[] where={"id=",String.valueOf(hidden.getId())};
				hidden2.setWhere(where);
				try{
					if(hiddenlevel.getText()!=null)
					hidden2.setHidden_level(Integer.parseInt(hiddenlevel.getText()));
					if(changespeed.getText()!=null)
					hidden2.setDetail(changespeed.getText());
					if(hiddneinstance.getText()!=null)
					hidden2.setName(hiddneinstance.getText());
	
					hidden2.setDate(date);
				
				
				
				
				int i=assets.updateHidden(hidden2);
				
				
							 				
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
					hiddenTable.setItems(null);
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
			        alert.setContentText("是否删除"+hidden.getId()+"信息");

			        ButtonType btnType1 = new ButtonType("确定");
			        ButtonType btnType2 = new ButtonType("取消");
			     

			        alert.getButtonTypes().setAll(btnType1, btnType2);

			        Optional<ButtonType> result = alert.showAndWait();
			        result.ifPresent(buttonType -> {
			            if (buttonType == btnType1) {
			                try{
			                String[] where={"id=",String.valueOf(hidden.getId())};
			            	hidden.setWhere(where);
			                int i=assets.deleteHidden(hidden);
			                if(i==1){
			                	alert.setTitle("安全信息");
								alert.setHeaderText("操作");
								alert.setContentText("删除"+hidden.getId()+"成功");
								alert.showAndWait();
								hiddenTable.setItems(null);
								setRoomInfoList(offset, limit,searchMap);
								handleCancel();
			                }else{
			                	Alert alert2 = new Alert(AlertType.ERROR);
								alert2.setTitle("异常堆栈对话框");
								alert2.setHeaderText("错误");
								alert2.setContentText("删除"+hidden.getId()+"失败");
								alert2.showAndWait();
			                }
			                }catch (Exception e) {
								// TODO: handle exception
			                	Alert alert2 = new Alert(AlertType.ERROR);
								alert2.setTitle("异常堆栈对话框");
								alert2.setHeaderText("错误");
								alert2.setContentText("删除"+hidden.getId()+"失败");
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
	    
	 public void setHidden(Hidden hidden){
		 this.hidden=hidden;
		 id.setText(String.valueOf(hidden.getId()));
		 hiddenlevel.setText(String.valueOf(hidden.getDetail()));
		 hiddneinstance.setText(hidden.getGUID());
		 changespeed.setText(hidden.getName());
		 doubletest.setText(String.valueOf(hidden.getHappen_time()));
		 floattest.setText(String.valueOf(hidden.getHidden_level()));
		 longtest.setText(String.valueOf(hidden.getType()));
		 time.setText(String.valueOf(hidden.getDate()));
	 }
	 
	 void setRoomInfoList(Integer offset,Integer limit,Map search){

	      String sort=null;
	      String order=null;
	     
		  Map map=new HashMap<>();
		  
		  Assets assets= new Connect().get();
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
	     
	     int total=(int) map.get("total");
	     int page=total/10;
	     
	     if(total-page*10>0)
          page++;	     
	     
	     pagination.setPageCount(page);
	 
	 }
	 
}
