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

import org.apache.poi.ss.formula.functions.T;
import org.controlsfx.dialog.Dialogs;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Level;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Jion;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;

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
	private ChoiceBox<T> hiddenType;//隐患类型
	private List<Hidden_Type> hidden_Types;
	private Integer hiddenTypeValue;
	
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
	
	private ObservableList<Hidden_JoinProperty> hiddenList;
	 
	 @FXML
	 private TableView<Hidden_JoinProperty> hiddenTable;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,Integer> C1;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C2;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C3;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C4;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C5;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,ProgressBar> C6;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C7;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C8;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C9;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C10;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C11;
	
	// private List<Hidden> hiddens;
	 
	 private List<Hidden_Jion> hidden_Jions;
	 
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
		
		List<Hidden_Level> hidden_levels=assets.setctAllHiddenLevel();
		Iterator<Hidden_Level> iterator=hidden_levels.iterator();
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
		
		
		 hidden_Types=assets.selectAllHiddenType();
		 Iterator<Hidden_Type> iterator3=hidden_Types.iterator();
		 List hidden_types=new ArrayList<>();	
		 while(iterator3.hasNext()){
			 hidden_types.add(iterator3.next().getHidden_type());
		 }
		 hiddenType.setItems(FXCollections.observableArrayList(hidden_types));
		 hiddenType.getSelectionModel().selectedIndexProperty().addListener(new 
				 ChangeListener<Number>() {
					
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						// TODO Auto-generated method stub
						int i=(int) newValue;
						hiddenTypeValue=hidden_Types.get(i).getType();
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
							hidden.setName(hiddenName.getText());
						if(hiddenDetail.getText()!=null)
							hidden.setDetail(hiddenDetail.getText());
						if(hiddenPrincipal.getText()!=null)
							hidden.setDetail(hiddenPrincipal.getText());
						if(hiddenTypeValue!=null)
							hidden.setType(hiddenTypeValue);
						if(hiddenState.getText()!=null)
							hidden.setName(hiddenState.getText());

						if(hiddenRemark.getText()!=null)
							hidden.setRemark(hiddenRemark.getText());
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
	
	
	public void setTableView(TableView<Hidden_JoinProperty> hiddenTable,Integer offset,Integer limit,
			Map<String,String> searchMap,Pagination pagination,TableColumn<Hidden_JoinProperty,Integer> C1,
			TableColumn<Hidden_JoinProperty,String> C2,TableColumn<Hidden_JoinProperty,String> C3,TableColumn<Hidden_JoinProperty,String> C4,
			TableColumn<Hidden_JoinProperty,String> C5,TableColumn<Hidden_JoinProperty,ProgressBar> C6,
			TableColumn<Hidden_JoinProperty,String> C7,TableColumn<Hidden_JoinProperty,String> C8,
			TableColumn<Hidden_JoinProperty,String> C9,TableColumn<Hidden_JoinProperty,String> C10,TableColumn<Hidden_JoinProperty,String> C11) {
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
		this.C9=C9;
		this.C10=C10;
		this.C11=C11;
	}
	
	void setRoomInfoList(Integer offset,Integer limit,Map search){

	      String sort=null;
	      String order=null;
	     
		  Map map=new HashMap<>();
		  
		  
		//  map=assets.selectAllHidden(limit, offset, sort, order, search);

		//  map=assets.selectAllHidden_Jion(limit, offset, sort, order, search);
		  
		  map=assets.selectAllHidden_Jion(limit, offset, sort, order, search);
		  
	     hidden_Jions= (List<Hidden_Jion>) map.get("rows");
	     
	     MyTestUtil.print(hidden_Jions);
	     
	     hiddenList= (ObservableList<Hidden_JoinProperty>) new RowData(hidden_Jions,Hidden_JoinProperty.class).get();
	     java.util.Iterator<Hidden_JoinProperty> iterator=hiddenList.iterator();
	    
	     while (iterator.hasNext()) {
			System.out.println("hiddenlist="+iterator.next().getDate());
		}
	     
	    hiddenTable.setItems(hiddenList);
     
	    C1.setCellValueFactory(
                cellData -> cellData.getValue().getId().asObject());
     C2.setCellValueFactory(
   		    cellData->cellData.getValue().getName());
     C3.setCellValueFactory(
    		    cellData->cellData.getValue().getLevel_text());
     C4.setCellValueFactory(
    		    cellData->cellData.getValue().getDetail());
     C5.setCellValueFactory(
    		    cellData->cellData.getValue().getHappen_time());
     C6.setCellValueFactory(
 		    new Callback<TableColumn.CellDataFeatures<Hidden_JoinProperty,ProgressBar>, ObservableValue<ProgressBar>>() {
					
					@Override
					public ObservableValue<ProgressBar> call(CellDataFeatures<Hidden_JoinProperty, ProgressBar> param) {
						// TODO Auto-generated method stub
						DoubleProperty d=param.getValue().getProgress();
						Double dd=d.doubleValue();
						ProgressBar progressBar=new ProgressBar();
						progressBar.setProgress(dd);
						return new SimpleObjectProperty<ProgressBar>(progressBar);
					}
				});
     C7.setCellValueFactory(
    		    cellData->cellData.getValue().getPrincipal_name());
     C8.setCellValueFactory(
    		 cellData->cellData.getValue().getHidden_type());
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
	
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
	
	@FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
