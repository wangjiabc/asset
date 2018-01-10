package com.asset.view.detail;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenLevelProperty;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden_Level;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HiddenLevelDetailController {
	@FXML
	private TextField hiddenlevel;
	@FXML
	private TextField levelText;
	
	private ObservableList<HiddenLevelProperty> hiddenLevelList;
	
	@FXML
	 private TableView<HiddenLevelProperty> hiddenLevelTable;
	
	@FXML
	 private TableColumn<HiddenLevelProperty,Integer> C21;
	
	@FXML
	 private TableColumn<HiddenLevelProperty,String> C22;
	
	@FXML
	private Button update;
	
	@FXML
	private Button cancel;
	
	@FXML
	private Label rightTitleLabel;
		
	private Stage dialogStage;
	
	Assets assets= new Connect().get();
	
	public void setHiddenLevel(TableView<HiddenLevelProperty> hiddenLevelTable,TableColumn<HiddenLevelProperty,Integer> C21,
			TableColumn<HiddenLevelProperty,String> C22) {
			
		   this.hiddenLevelTable=hiddenLevelTable;
		   this.C21=C21;
		   this.C22=C22;
		
	}
	
	@FXML
	private void initialize() {
		 Assets assets=new Connect().get();
		 
		 update.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Hidden_Level hidden_level=new Hidden_Level();
				
			try{
				if(hiddenlevel.getText()!=null)
					hidden_level.setHidden_level(Integer.parseInt(hiddenlevel.getText()));
				if(levelText.getText()!=null)
					hidden_level.setLevel_text(levelText.getText());
				
				int i=assets.insertHiddenLevel(hidden_level);
				
				if(i==1){
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                	alert.setTitle("隐患级别");
					alert.setHeaderText("操作");
					alert.setContentText("新建"+hiddenlevel.getText()+"成功");						
					alert.showAndWait();
					setHiddenLevel();
					handleCancel();
				}else if(i==2){
					Alert alert2 = new Alert(AlertType.ERROR);
					alert2.setTitle("异常堆栈对话框");
					alert2.setHeaderText("错误");
					alert2.setContentText(hiddenlevel.getText()+"已存在,新建失败");
					alert2.showAndWait();
				 }else{
					Alert alert2 = new Alert(AlertType.ERROR);
					alert2.setTitle("异常堆栈对话框");
					alert2.setHeaderText("错误");
					alert2.setContentText("新建"+hiddenlevel.getText()+"失败");
					alert2.showAndWait();
				  }
			}catch (Exception e) {
					// TODO: handle exception
				    e.printStackTrace();
				    
					Alert alert2 = new Alert(AlertType.ERROR);
					alert2.setTitle("异常堆栈对话框");
					alert2.setHeaderText("错误");
					alert2.setContentText("新建"+hiddenlevel.getText()+"失败");
					alert2.showAndWait();
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
	 
	 public void setHiddenLevel(){
			
			List<Hidden_Level> hidden_levels=assets.setctAllHiddenLevel();
			
			hiddenLevelList=(ObservableList<HiddenLevelProperty>) new RowData(hidden_levels, HiddenLevelProperty.class).get();
			
			hiddenLevelTable.setItems(hiddenLevelList);
			
			C21.setCellValueFactory(
		    		 cellData -> cellData.getValue().getHidden_level().asObject());
			
			C22.setCellValueFactory(
		    		 cellData -> cellData.getValue().getLevel_text());
			
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
	
}
