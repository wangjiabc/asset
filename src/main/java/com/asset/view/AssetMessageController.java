package com.asset.view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;

import org.apache.poi.ss.formula.functions.T;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.join.HiddenCheck_JoinProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.FileConvect;
import com.asset.tool.MyTestUtil;
import com.asset.view.detail.AddCheckInfoDetailController;
import com.asset.view.infowrite.InfoWriteController2;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Check;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Check_Join;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AssetMessageController extends AssetAsSwitch{
	
	@FXML
	 private Label rightTitleLabel;
	
	@FXML
	 private AnchorPane anchorPane;
	
	 @FXML
	 private Button addCheckInfoButton;

	 @FXML
	 ImageView imageFile;
	 
	 @FXML
	 Label docLabel;
	 
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
	 
	 public AssetMessageController() {
		// TODO Auto-generated constructor stub
		 super();	
		 stage=new Stage();
		 stage.setTitle("File Chooser Sample");
	}
	 
	 @Override
	 protected void initCurrent() {
		 
		 URL url = getClass().getResource("");
		 String filePath=url.toString()+"Image";
		 Image image = new Image(filePath+"/message.jpg");
	     homepage.setImage(image);
	     
	     rightTitleLabel.setText("检查记录");
	     
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
				 
				 setHiddenCheckList(0,10,searchMap);
			}
		  });
	    
	     
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
	     
	     
	      addCheckInfoButton.setOnAction(new EventHandler<ActionEvent>() {
	    	   
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					   FXMLLoader loader = new FXMLLoader();
			            loader.setLocation(AssetMessageController.class.getResource("detail/AddCheckInfoDetail.fxml"));
			            AnchorPane page = (AnchorPane) loader.load();

			            // Create the dialog Stage.
			            Stage dialogStage = new Stage();
			            dialogStage.setTitle("添加隐患检查记录");
			            dialogStage.initModality(Modality.APPLICATION_MODAL);
			            Scene scene = new Scene(page);
			            dialogStage.setScene(scene);

			            // Set the person into the controller.
			            AddCheckInfoDetailController controller = loader.getController();
			            controller.setTableView(hiddenCheckTable, offset, limit, searchMap, pagination, C1, C2, C3, C4, C5, C6, C7, C8);
			            controller.setDialogStage(dialogStage);
			            
			            // Show the dialog and wait until the user closes it
			            dialogStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	     pagination.setPageCount(page);
	     	     
	 }
	 
	 private void openFile(File file) {
	        EventQueue.invokeLater(() -> {
	            try {
	                desktop.open(file);
	            } catch (IOException ex) {
                    ex.printStackTrace();
	            }
	        });
	    }
}
