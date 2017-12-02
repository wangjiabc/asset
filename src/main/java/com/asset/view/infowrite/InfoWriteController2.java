package com.asset.view.infowrite;


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

import org.controlsfx.dialog.Dialogs;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenProperty;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_level;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.Alert.AlertType;

public class InfoWriteController2 {
	@FXML
	private TextField hiddenName;
	@FXML
	private ChoiceBox hiddenLevel;
	
	private Integer hiddenLevelValue;
	
	@FXML
	private TextField hiddenDetail;
	@FXML
	private TextField hiddenPrincipal;
	@FXML
	private TextField hiddenType;
	@FXML
	private TextField hiddenState;
	@FXML
	private DatePicker happenTime;
	@FXML
	private TextField hiddenRemark;
	@FXML
	private Button post;
	@FXML
	private Button cancel;
	
	private Stage dialogStage;
	
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
	
	 private List<Hidden> hiddens;
	 
	 private Pagination pagination;
	 
     private Integer offset;
	 
	 private Integer limit;
	
	 private Map<String,String> searchMap;
	 
	Assets assets= new Connect().get();
	
	public InfoWriteController2() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
    private void initialize() {
		
		List<Hidden_level> hidden_levels=assets.setctAllHiddenLevel();
		Iterator<Hidden_level> iterator=hidden_levels.iterator();
		List levels = new ArrayList<>();
		while (iterator.hasNext()) {
			levels.add(iterator.next().getLevel_text());
		}

		hiddenLevel.setItems(FXCollections.observableArrayList(levels));
		
		hiddenLevel.getSelectionModel().selectedIndexProperty().addListener(new
				 ChangeListener<Number>() {

					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue,
							Number newValue) {
						// TODO Auto-generated method stub
						int i=(int) newValue;
                        hiddenLevelValue=hidden_levels.get(i).getHidden_level();						
						System.out.println(hiddenLevelValue);
					}
			        
				});
		
		//限制输入为数字
		/* hiddenlevel.textProperty().addListener(new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		            if (!newValue.matches("\\d*")) {
		                hiddenlevel.setText(newValue.replaceAll("[^\\d]", ""));
		            }
		        }
		    });
		
		
		//限制输入为浮点数
		 doubletest.textProperty().addListener(new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		            if (!newValue.matches("[0-9]*(\\.?)[0-9]*")) {
		                doubletest.setText(newValue.replaceAll("[^\\d*(\\.?)]", ""));
		            }
		        }
		    });
		 
		 
		//限制输入为浮点数
		 floattest.textProperty().addListener(new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		            if (!newValue.matches("[0-9]*(\\.?)[0-9]*")) {
		                floattest.setText(newValue.replaceAll("[^\\d*(\\.?)]", ""));
		            }
		        }
		    });
		 
		//限制输入为数字
		 longtest.textProperty().addListener(new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		            if (!newValue.matches("\\d*")) {
		                longtest.setText(newValue.replaceAll("[^\\d]", ""));
		            }
		        }
		    });
		 */
		 
		post.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Hidden hidden=new Hidden();
				Date date=new Date();
				try{
                    if(hiddenLevelValue!=null)
                    	hidden.setHidden_level(hiddenLevelValue);
					if(hiddenName.getText()!=null)
						hidden.setDetail(hiddenName.getText());
					if(hiddenDetail.getText()!=null)
						hidden.setName(hiddenDetail.getText());
					if(hiddenPrincipal.getText()!=null)
						hidden.setDetail(hiddenPrincipal.getText());
					if(hiddenType.getText()!=null)
						hidden.setName(hiddenType.getText());
					if(hiddenState.getText()!=null)
						hidden.setName(hiddenState.getText());
					if(hiddenPrincipal.getText()!=null)
						hidden.setDetail(hiddenPrincipal.getText());
					if(hiddenRemark.getText()!=null)
						hidden.setName(hiddenRemark.getText());
					if(happenTime.getValue()!=null){
						LocalDate localDate=happenTime.getValue();
						Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
						Date date2 = Date.from(instant);
						hidden.setHappen_time(date2);
					}
					UUID uuid=UUID.randomUUID();
					hidden.setGUID(String.valueOf(uuid));
					hidden.setDate(date);
				
				Assets assets=new Connect().getAssets();
				
				int i=assets.insertIntoHidden(hidden);
				
				
							 				
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
					setRoomInfoList(offset, limit, searchMap);
					handleCancel();
				 }
				}catch (Exception e) {
					// TODO: handle exception
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框");
					alert.setHeaderText("错误");
					alert.setContentText("写入失败");
					alert.showAndWait();
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
	
	
	public void setTableView(TableView<HiddenProperty> hiddenTable,Integer offset,Integer limit,
			Map<String,String> searchMap,Pagination pagination,TableColumn<HiddenProperty,Integer> C1,
			TableColumn<HiddenProperty,String> C2,TableColumn<HiddenProperty,String> C3,TableColumn<HiddenProperty,Integer> C4,
			TableColumn<HiddenProperty,String> C5,TableColumn<HiddenProperty,String> C6,
			TableColumn<HiddenProperty,Integer> C7,TableColumn<HiddenProperty,Integer> C8,
			Map<String,String> searchMap1) {
		this.hiddenTable=hiddenTable;
		this.offset=offset;
		this.limit=limit;
		this.searchMap=searchMap1;
		this.pagination=pagination;
		this.C1=C1;
		this.C2=C2;
		this.C3=C3;
		this.C4=C4;
		this.C5=C5;
		this.C6=C6;
		this.C7=C7;
		this.C8=C8;
		this.searchMap=searchMap1;
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
	
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
	
	@FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
