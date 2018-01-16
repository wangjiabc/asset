package com.asset.view.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden_Level;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.daoModel.Assets.Position;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AppendHiddenQueryController {

	 /*
     * 查询条件菜单
     */
	 @FXML
	 private TextField keyWord;
	 @FXML
	 private ChoiceBox hiddenLevel;	 
	 
	 private Integer hiddenLevelValue;
	 
	 @FXML
	 private ChoiceBox hiddenProgress;	 
	 private String progress;
	 
	 @FXML
	 private ChoiceBox hiddenType;	 
	 private List<Hidden_Type> hidden_Types;
	 private Integer hiddenTypeValue;
	 
	 //查询按钮
	 @FXML
	 private Button search;
	 
	 @FXML
	 private ImageView searchImage;
	 
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
	 
	 @FXML
	 private Pagination pagination;
	 
	// private List<Hidden> hiddens;
	 
	 private List<Hidden_Join> hidden_Jions;
	 
	 private Stage dialogStage;
	 
	 private Integer offset=0;
	 
	 private Integer limit=10;
	 
	 private Map<String,String> searchMap=new HashMap<>();
	 
	 private List<String> names=new ArrayList<String>();
	 
	 private List<byte[]> fileBytes=new ArrayList<byte[]>();
	 
	 private Position position;
	 
	 @FXML
	 private WebView mapview2;
	 
	 Assets assets= new Connect().get();
	
	 public void setPosition(Position position,WebView mapview2) {
			this.position=position;
			this.mapview2=mapview2;
		 }
	 
	 @FXML
	 protected void initialize() {
		 
  	     
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
	     
     
			//整改进度 :
		    List hidden_progress=new ArrayList<>();
		    hidden_progress.add("未整改");
		    hidden_progress.add("整改中");
		    hidden_progress.add("已完成");
		    hiddenProgress.setItems(FXCollections.observableArrayList(hidden_progress));
		    
		    hiddenProgress.getSelectionModel().selectedIndexProperty().addListener(new 
		    		ChangeListener<Number>() {

						@Override
						public void changed(ObservableValue<? extends Number> observable, Number oldValue,
								Number newValue) {
							// TODO Auto-generated method stub
							int i=(int) newValue;
							progress=hidden_progress.get(i).toString();
							searchMap=new HashMap<>();  //清除查询条件
						}
				});
	     
	    //隐患情况 :
		hidden_Types=assets.selectAllHiddenType();
		Iterator<Hidden_Type> iterator3=hidden_Types.iterator();
		List hidden_types=new ArrayList<>();
		while(iterator3.hasNext()){
			    String type_text=iterator3.next().getHidden_type();
				 hidden_types.add(type_text);

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
	    
	    //搜索	    
	     search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(hiddenLevelValue!=null){
				   String search=String.valueOf(hiddenLevelValue);
				   searchMap.put("[Hidden].Hidden_Level=", search);
				 }
				
				 if(keyWord.getText()!=null){
					 searchMap.put("[Hidden].name like ", "%"+keyWord.getText()+"%");
				 }
				 
				 if(hiddenTypeValue!=null){
					 searchMap.put("[Hidden].type=", String.valueOf(hiddenTypeValue));
				 }
				 
				 if(progress!=null){
					 if(progress.equals("未整改")){
						 searchMap.put("[Hidden].progress=","0");
					 }else if(progress.equals("整改中")){
						 searchMap.put("[Hidden].progress>", "0");
						 searchMap.put("[Hidden].progress<", "1");
					 }else{
						 searchMap.put("[Hidden].progress=", "1");
					 }
				 }
				 
				 setRoomInfoList(0,10,searchMap);
			}
		  });
	     
	     
	 //   setRoomInfoList(0,10,searchMap);
	     
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
	    
		 
	    hiddenTable.setRowFactory( tv -> {
	        TableRow<Hidden_JoinProperty> row = new TableRow<>();
	        row.setOnMouseClicked(event -> {
	            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
	            	Hidden_JoinProperty rowData = row.getItem();
	            	table(rowData,mapview2);
	            }
	        });
	        return row ;
	    });
	    
	 }
	 
	 
	 private void table(Hidden_JoinProperty newValue,WebView mapview2){
		 try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(PositionDetailController.class.getResource("PositionDetail.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("添加资产位置");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            PositionDetailController controller = loader.getController();
	            
               Map map=assets.selectAllHidden_Jion(limit, offset, "date", "desc", searchMap);
	            
	   	        hidden_Jions= (List<Hidden_Join>) map.get("rows");
	            MyTestUtil.print(hidden_Jions);
	            Iterator<Hidden_Join> iterator=hidden_Jions.iterator();
	            
	            Hidden_Join hidden_Jion=null;
	            
	            while(iterator.hasNext()){
	            	Hidden_Join h=iterator.next();
	            	try{
	            	 if(newValue.getId().get()==h.getId()){
	            		hidden_Jion=h;
	            		break;
	            	  }
	            	}catch (NullPointerException e) {
						// TODO: handle exception
					}
	            }
	            System.out.println("guid="+hidden_Jion.getGUID());
	            String Address=hidden_Jion.getName();
	            String GUID=hidden_Jion.getGUID();
	            
	            position.setGUID(GUID);
	            
	            controller.setPosition(position,Address,GUID,mapview2);
	            controller.setDialogStage(dialogStage);
	            // Show the dialog and wait until the user closes it
	            dialogStage.show();
	            handleCancel();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 
	void setRoomInfoList(Integer offset,Integer limit,Map search){

		 String sort="date";
	      String order="desc";
	     
		  Map map=new HashMap<>();
		  
		  
		//  map=assets.selectAllHidden(limit, offset, sort, order, search);

		//  map=assets.selectAllHidden_Jion(limit, offset, sort, order, search);
		  
		  map=assets.selectAllHidden_Jion(limit, offset, sort, order, search);
		  
	     hidden_Jions= (List<Hidden_Join>) map.get("rows");
	     
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
