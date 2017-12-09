package com.asset.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.join.HiddenCheck_JoinProperty;
import com.asset.property.join.HiddenNeaten_JoinProperty;
import com.asset.tool.MyTestUtil;
import com.asset.view.detail.AddCheckInfoDetailController;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Check_Join;

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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AssetInformController extends AssetAsSwitch{

	@FXML
	 private Label rightTitleLabel;
	
	 private ObservableList<HiddenNeaten_JoinProperty> hiddenNeaten;
	 
	 @FXML
	 private TableView<HiddenNeaten_JoinProperty> hiddenNeatenTable;
	 
	 @FXML
	 private TableColumn<HiddenNeaten_JoinProperty,Integer> C1;
	 
	 @FXML
	 private TableColumn<HiddenNeaten_JoinProperty,String> C2;
	 
	 @FXML
	 private TableColumn<HiddenNeaten_JoinProperty,String> C3;
	 
	 @FXML
	 private TableColumn<HiddenNeaten_JoinProperty,String> C4;
	 
	 @FXML
	 private TableColumn<HiddenNeaten_JoinProperty,String> C5;
	 
	 @FXML
	 private TableColumn<HiddenNeaten_JoinProperty,String> C6;
	 
	 @FXML
	 private TableColumn<HiddenNeaten_JoinProperty,ProgressBar> C7;
	 
	 @FXML
	 private TableColumn<HiddenNeaten_JoinProperty,String> C8;
	 
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
	
	 public AssetInformController() {
		// TODO Auto-generated constructor stub
		 super();
	}
	 
	 @Override
	 protected void initCurrent() {
		 
		 URL url = getClass().getResource("");
		 String filePath=url.toString()+"Image";
		 Image image = new Image(filePath+"/inform.jpg");
	     homepage.setImage(image);
	     
	     rightTitleLabel.setText("整改记录");
	 
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
		     
		   //搜索
			    
		     search.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					
					 if(hiddenValue!=null){
					   String search=hidden.get(hiddenValue).getGUID();
					   searchMap.put("[Assets].[dbo].[Hidden_Check].GUID=", search);
					 }
					 
					 setHiddenNeaten(0,10,searchMap);
				}
			  });
		    
		     
		     pagination.setPageFactory((Integer pageIndex)->{
			    	if (pageIndex >= 0) {
			    		offset=pageIndex*10;
			    		limit=10;
			    		System.out.println("pagination="+offset+" ______"+limit);
			    		setHiddenNeaten(offset, limit, searchMap);
			    		 Label mLabel = new Label();  
			                mLabel.setText("这是第" + (pageIndex+1) + "页");  
			                return mLabel;  
		            } else {
		                return null;
		            }
			    });
		     
		     
		     
		 }
		 
		 
		 void setHiddenNeaten(Integer offset,Integer limit,Map search){

		      String sort=null;
		      String order=null;
		     
			  Map map=new HashMap<>();
			  
			  	
			  map=assets.selectAllHiddenNeaten(limit, offset, sort, order, search);
			  
		     hidden_Checks= (List<Hidden_Check_Join>) map.get("rows");
		     MyTestUtil.print(hidden_Checks);
		     
		     hiddenNeaten= (ObservableList<HiddenNeaten_JoinProperty>) new RowData(hidden_Checks,HiddenNeaten_JoinProperty.class).get();
		     

		    hiddenNeatenTable.setItems(hiddenNeaten);
	       
		     C1.setCellValueFactory(
		                cellData -> cellData.getValue().getId().asObject());
		     C2.setCellValueFactory(
		   		    cellData->cellData.getValue().getName());
		     C3.setCellValueFactory(
		    		    cellData->cellData.getValue().getDetail());
		     C4.setCellValueFactory(
		    		    cellData->cellData.getValue().getHappen_time());
		     C5.setCellValueFactory(
		    		    cellData->cellData.getValue().getDate());
		     C6.setCellValueFactory(
		    		    cellData->cellData.getValue().getInstance());	
		     
		     C7.setCellValueFactory(
		    		    new Callback<TableColumn.CellDataFeatures<HiddenNeaten_JoinProperty,ProgressBar>, ObservableValue<ProgressBar>>() {
							
							@Override
							public ObservableValue<ProgressBar> call(CellDataFeatures<HiddenNeaten_JoinProperty, ProgressBar> param) {
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
		     pagination.setPageCount(page);
		     	     
		 }
	 
}
