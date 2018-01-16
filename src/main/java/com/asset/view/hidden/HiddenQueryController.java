package com.asset.view.hidden;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.controlsfx.dialog.Dialogs;

import com.asset.MainApp;
import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.MyTestUtil;
import com.gargoylesoftware.htmlunit.javascript.host.event.MouseEvent;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Join;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class HiddenQueryController {

	/*
	 * 查询条件菜单
	 */
	@FXML
	private TextField keyWord;
	@FXML
	private SplitMenuButton level;
	@FXML
	private SplitMenuButton plan;
	@FXML
	private SplitMenuButton instance;
	// 查询按钮
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
	 private TableColumn<Hidden_JoinProperty,String> C12;
	 
	@FXML
	private Pagination pagination;

	private List<Hidden_Join> hidden_Jions;

	private Stage dialogStage;

	private int i = 0;

	private Integer offset = 0;

	private Integer limit = 10;

	private Map<String, String> searchMap = new HashMap<>();

	Assets assets = new Connect().get();

	public HiddenQueryController() {
		// TODO Auto-generated constructor stub
	}

	@FXML
	private void initialize() {
		URL url = getClass().getResource("");
		String filePath = url.toString() + "Image";

		Image image = new Image(filePath + "/search.png");
		searchImage.setImage(image);

		/*
		 * 查询条件
		 */

		// 隐患级别 :
		/*
		 * MenuItem level1=new MenuItem("一类"); MenuItem level2=new MenuItem("二类");
		 * MenuItem level3=new MenuItem("三类");
		 * 
		 * level.getItems().addAll(level1,level2,level3);
		 * 
		 * level1.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent event) { // TODO Auto-generated
		 * method stub level.setText("一类"); } });
		 * 
		 * level2.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent event) { // TODO Auto-generated
		 * method stub level.setText("二类"); } });
		 * 
		 * level3.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent event) { // TODO Auto-generated
		 * method stub level.setText("三类"); } });
		 */

		List<MenuItem> menuItems = new ArrayList<>();
		String a[] = { "1", "2", "3" };
		for (int i = 0; i < 3; i++) {
			menuItems.add(new MenuItem(String.valueOf(i)));
			String aa = a[i];
			menuItems.get(i).setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					level.setText(aa);
				}
			});
			level.getItems().add(menuItems.get(i));
		}

		// 整改进度 :
		MenuItem exhale = new MenuItem("已发整改通知");

		plan.getItems().addAll(exhale);

		exhale.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				plan.setText("已发整改通知");
			}
		});

		// 隐患情况 :
		MenuItem bigness = new MenuItem("具有重大消防隐患");

		instance.getItems().addAll(bigness);

		bigness.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				instance.setText("具有重大消防隐患");
			}
		});

		// 搜索

		search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("search");
				alert.setHeaderText(level.getText());
				alert.setContentText(keyWord.getText());
				alert.showAndWait();
				String search = "%" + keyWord.getText() + "%";

				if (!search.equals("")) {
					searchMap.put("HiddenInstance like ", search);
				} else {
					searchMap.clear();
				}
				setRoomInfoList(0, 10, searchMap);
			}
		});

		// setRoomInfoList(0,10,searchMap);

		pagination.setPageFactory((Integer pageIndex) -> {
			if (pageIndex >= 0) {
				offset = pageIndex * 10;
				limit = 10;
				setRoomInfoList(offset, limit, searchMap);
				Label mLabel = new Label();
				mLabel.setText("这是第" + (pageIndex + 1) + "页");
				return mLabel;
			} else {
				return null;
			}
		});

		hiddenTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> table(newValue));

		hiddenTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Hidden_JoinProperty>() {

			@Override
			public void changed(ObservableValue<? extends Hidden_JoinProperty> observable, Hidden_JoinProperty oldValue,
					Hidden_JoinProperty newValue) {
				// TODO Auto-generated method stub
				if (i >= 1) {
					if (newValue != null)
						table(newValue);
				} else {
					i++;
				}
			}
		});

	}

	private void table(Hidden_JoinProperty newValue) {
		try {
			String GUID=newValue.getGUID().get();
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HiddenQueryController.class.getResource("Hiddendetail.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("隐患");
			dialogStage.initModality(Modality.APPLICATION_MODAL);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			HiddenDetailController controller = loader.getController();
			controller.setDialogStage(dialogStage);
		//	controller.setTableView(hiddenTable, offset, limit, searchMap, pagination, C1, C2, C3, C4, C5, C6, C7, C8);
			
			Map searchMap0=new HashMap<>();
	  		  
	  		searchMap0.put("[Hidden_Assets].hidden_GUID=", GUID);
	  		Map searchMap2=new HashMap<>();
  		    searchMap2.put("[Hidden_Check].GUID=", GUID);
  		    Map searchMap3=new HashMap<>();
		    searchMap3.put("[Hidden_Neaten].GUID=", GUID);
		    
            controller.setTableView(hiddenTable, offset, limit, searchMap, searchMap0,searchMap2,searchMap3,pagination, C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11,C12);
			Map map = assets.selectAllHidden_Jion(limit, offset, null, null, searchMap);

			hidden_Jions =(List<Hidden_Join>) map.get("rows");

			Iterator<Hidden_Join> iterator = hidden_Jions.iterator();

			Hidden_Join hidden_Join = null;

			while (iterator.hasNext()) {
				Hidden_Join h = iterator.next();
				if (newValue.getId().get() == h.getId()) {
					hidden_Join = h;
					break;
				}
			}

			controller.setHidden(hidden_Join);

			// Show the dialog and wait until the user closes it
			dialogStage.show();

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
	
   C12.setCellValueFactory(
  		 cellData->cellData.getValue().getManageRegion());
   
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
}
