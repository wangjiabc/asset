package com.asset.view.detail;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.ss.formula.functions.T;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.join.HiddenCheck_JoinProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Check;
import com.voucher.manage.daoModel.Assets.Hidden_Level;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.daoModel.Assets.Hidden_User;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Check_Join;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Join;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AddCheckInfoDetailController {
	@FXML
	 private Label rightTitleLabel;
	
	@FXML
	 private AnchorPane anchorPane;
	
	@FXML
	private TextField principal;
	
	@FXML
	private TextField checkName;
	
	@FXML
	private ChoiceBox<T> hiddenName;//隐患类型
	private List<Hidden> hidden;
	private Integer hiddenValue;
	
	@FXML
	private DatePicker happenTime;//发生时间
	
	@FXML
	private TextArea checkCrics;
	
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
	 
	 //查询按钮
	 @FXML
	 private Button search;
	 
	 @FXML
	 private Button post;
	 
	 @FXML
	 private Button cancel;
	 
	 private Integer offset;
	 
	 private Integer limit;	 	
	 
	 private Map<String,String> searchMap=new HashMap<>();
	 
	 private Stage dialogStage;
	 
	 private List<Hidden_Check_Join> hidden_Checks;
	 
	 Assets assets= new Connect().get();
	
	 public void setTableView(TableView<HiddenCheck_JoinProperty> hiddenCheckTable,Integer offset,Integer limit,
				Map<String,String> searchMap,Pagination pagination,TableColumn<HiddenCheck_JoinProperty,Integer> C1,
				TableColumn<HiddenCheck_JoinProperty,String> C2,TableColumn<HiddenCheck_JoinProperty,String> C3,TableColumn<HiddenCheck_JoinProperty,String> C4,
				TableColumn<HiddenCheck_JoinProperty,String> C5,TableColumn<HiddenCheck_JoinProperty,String> C6,
				TableColumn<HiddenCheck_JoinProperty,ProgressBar> C7,TableColumn<HiddenCheck_JoinProperty,String> C8){
			this.hiddenCheckTable=hiddenCheckTable;
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
		
		
		 
		post.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Hidden_Check hidden_Check=new Hidden_Check();
				Date date=new Date();
				try{
					 if(principal.getText()!=null)
	                    	hidden_Check.setPrincipal(principal.getText());
						if(checkName.getText()!=null)
							hidden_Check.setCheck_name(checkName.getText());
						if(hiddenValue!=null){
							hidden_Check.setGUID(hidden.get(hiddenValue).getGUID());
						}
						if(happenTime.getValue()!=null){
							LocalDate localDate=happenTime.getValue();
							Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
							Date date2 = Date.from(instant);
							hidden_Check.setHappen_time(date2);
						}
						if(checkCrics.getText()!=null)
							hidden_Check.setCheck_circs(checkCrics.getText());
	                    Date date2=new Date();
	                    hidden_Check.setDate(date2);
	                    System.out.println("hidden_Check=");
				MyTestUtil.print(hidden_Check);
				int i=assets.insertHiddenCheck(hidden_Check);
				
				
							 				
				if(i==0){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框");
					alert.setHeaderText("错误");
					alert.setContentText("写入失败");
					alert.showAndWait();
				}else if(i==1){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("对话框");
					alert.setHeaderText("插入数据");
					alert.setContentText("写入成功");
					alert.showAndWait();
					setHiddenCheckList(offset, limit, searchMap);
					handleCancel();
				 }
				}catch (Exception e) {
					// TODO: handle exception
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框");
					alert.setHeaderText("错误");
					alert.setContentText("写入失败");
					alert.showAndWait();
					e.printStackTrace();
				}
			}
		});
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				handleCancel();
			}
		});
		
		
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
