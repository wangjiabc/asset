package com.asset.view.check;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.join.HiddenCheck_JoinProperty;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Check_Join;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SelectCheckInfoController {
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
	 
	 @FXML
	 private ChoiceBox<T> hiddenName;//隐患名字
	 private List<Hidden> hidden;
	 private Integer hiddenValue;
	 
	 //查询按钮
	 @FXML
	 private Button search;
	 
	 private Map<String,String> searchMap=new HashMap<>();
	 
	 private List<Hidden_Check_Join> hidden_Checks;
	 
     private Stage dialogStage;
	 
	 private Integer offset=0;
	 
	 private Integer limit=10;
	 
	 private final Desktop desktop = Desktop.getDesktop();
	 private Stage stage;
	 private File file;
	 private File docFile;
	 
	 Assets assets= new Connect().get();
	 
	 public void setSearch(Map searchMap){
		this.searchMap=searchMap;
	}
	 
	 @FXML
	 protected void initialize() {
		 pagination.setPageFactory((Integer pageIndex)->{
		    	if (pageIndex >= 0) {
		    		offset=pageIndex*10;
		    		limit=10;
		    		System.out.println("pagination="+offset+" ______"+limit);
		    		setHiddenCheckList(offset, limit, searchMap);
		    		 Label mLabel = new Label();  
		                mLabel.setText("这是第" + (pageIndex+1) + "页");  
		                return mLabel;  
	            } else {
	                return null;
	            }
		    });
	     

			 hiddenCheckTable.setRowFactory( tv -> {
			        TableRow<HiddenCheck_JoinProperty> row = new TableRow<>();
			        row.setOnMouseClicked(event -> {
			            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
			            	HiddenCheck_JoinProperty rowData = row.getItem();
			            	table(rowData);
			            }
			        });
			        return row ;
			    });

			 
	 }
	 
	 
	 private void table(HiddenCheck_JoinProperty newValue){
		 try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("SelectCheckInfoDetail.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("隐患");
	            dialogStage.initModality(Modality.APPLICATION_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            SelectCheckInfoDetailController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setTableView(hiddenCheckTable,offset,limit,searchMap,pagination,C1, C2, C3, C4, C5, C6, C7, C8);
	            	     
	            System.out.println("check_id="+newValue.getCheck_id());
	            
	            Map searchMap0=new HashMap<>();
	            
	            searchMap0.put("[Assets].[dbo].[Hidden_Check].check_id=",newValue.getCheck_id().get());
	            
	            Map map=assets.selectAllHiddenCheck(limit, offset, null, null, searchMap0);
	            
	            List<Hidden_Check_Join> hidden_Check_Joins= (List<Hidden_Check_Join>) map.get("rows");
	            MyTestUtil.print(hidden_Check_Joins);
	            try{
	               Hidden_Check_Join hidden_Check_Join=hidden_Check_Joins.get(0);            
	               controller.setHiddenCheckInfo(hidden_Check_Join);
	            }catch (Exception e) {
					// TODO: handle exception
	            	e.printStackTrace();
				}
	            // Show the dialog and wait until the user closes it
	            dialogStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
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
