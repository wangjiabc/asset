package com.asset.view.detail;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenTypeProperty;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden_Type;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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

public class UpHiddenTypeDetailController {

	@FXML
	private TextField hiddenType;
	
	private ObservableList<HiddenTypeProperty> hiddenTypeList;
	
	@FXML
	 private TableView<HiddenTypeProperty> hiddenTypeTable;
	
	@FXML
	 private TableColumn<HiddenTypeProperty,Integer> C11;
	
	@FXML
	 private TableColumn<HiddenTypeProperty,String> C12;
	
	private Hidden_Type hidden_Type;
	
	@FXML
	private Button update;
	
	@FXML
	private Button cancel;
	
	private Stage dialogStage;
	
	Assets assets=new Connect().get();
	
	public void setHiddenType(Hidden_Type hidden_Type,TableView<HiddenTypeProperty> hiddenTypeTable,TableColumn<HiddenTypeProperty,Integer> C11,
			TableColumn<HiddenTypeProperty,String> C12){
		
		  this.hidden_Type=hidden_Type;
		  this.hiddenTypeTable=hiddenTypeTable;
		  this.C11=C11;
		  this.C12=C12;
		  
		  hiddenType.setText(hidden_Type.getHidden_type());
	}
	
	@FXML
	private void initialize() {
				 
		 update.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("hiientypeid="+hidden_Type.getId());
				if(hidden_Type!=null){
					hidden_Type.setHidden_type(hiddenType.getText());
					String[] where={"[Assets].[dbo].[Hidden_Type].id=",String.valueOf(hidden_Type.getId())};
					hidden_Type.setWhere(where);
					hidden_Type.setId(null);
					
				try{
					int i=assets.updateHiddenType(hidden_Type);					
					if(i==1){
						  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		                	alert.setTitle("隐患类型");
							alert.setHeaderText("操作");
							alert.setContentText("新建"+hiddenType.getText()+"成功");								
							alert.showAndWait();
							setHiddenType();
							handleCancel();
					}else{
						Alert alert2 = new Alert(AlertType.ERROR);
						alert2.setTitle("异常堆栈对话框");
						alert2.setHeaderText("错误");
						alert2.setContentText("更新"+hiddenType.getText()+"失败");
						alert2.showAndWait();
					  }
				}catch (Exception e) {
						// TODO: handle exception
					    e.printStackTrace();
					    
						Alert alert2 = new Alert(AlertType.ERROR);
						alert2.setTitle("异常堆栈对话框");
						alert2.setHeaderText("错误");
						alert2.setContentText("更新"+hiddenType.getText()+"失败");
						alert2.showAndWait();
				}
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
	
	public void setHiddenType(){
		
		List<Hidden_Type> hidden_Types=assets.selectAllHiddenType();
	     
	     hiddenTypeList=(ObservableList<HiddenTypeProperty>) new RowData(hidden_Types, HiddenTypeProperty.class).get();
	    
	     hiddenTypeTable.setItems(hiddenTypeList);
	     
	     C11.setCellValueFactory(
	    		 cellData -> cellData.getValue().getId().asObject());
	     
	     C12.setCellValueFactory(
	    		 cellData -> cellData.getValue().getHidden_type());
	     
	}

	public void setDialogStage(Stage dialogStage) {
		// TODO Auto-generated method stub
		this.dialogStage=dialogStage;
	}
	
    /**
     * Called when the user clicks cancel.
     */
     @FXML
     private void handleCancel() {
        dialogStage.close();
     }

	
}
