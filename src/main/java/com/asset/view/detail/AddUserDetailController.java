package com.asset.view.detail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenUserProperty;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden_User;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddUserDetailController {

	@FXML
	private TextField id;
	
	@FXML
	private TextField PrincipalName;
	
	@FXML
	private TextField Business;
	
	@FXML
	private Button update;
	
	private ObservableList<HiddenUserProperty> hiddenList;
	
	@FXML
	 private TableView<HiddenUserProperty> hiddenTable;
	
	 @FXML
	 private TableColumn<HiddenUserProperty,Integer> C1;
	 
	 @FXML
	 private TableColumn<HiddenUserProperty,String> C2;
	 
	 @FXML
	 private TableColumn<HiddenUserProperty,String> C3;
	
	 private Stage dialogStage;
	
	 Assets assets= new Connect().get();
	
	 public void setAddUser(TableView<HiddenUserProperty> hiddenTable,TableColumn<HiddenUserProperty,Integer> C1,
			 TableColumn<HiddenUserProperty,String> C2,
			TableColumn<HiddenUserProperty,String> C3){
		    this.hiddenTable=hiddenTable;
		    this.C1=C1;
		    this.C2=C2;
		    this.C3=C3;
	 }
	
	@FXML
	private void initialize() {
		Assets assets=new Connect().getAssets();
		
		Hidden_User hidden_User=new Hidden_User();

		update.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				if(id!=null)
					hidden_User.setId(Integer.parseInt(id.getText()));
				if(PrincipalName!=null)
					hidden_User.setPrincipal_name(PrincipalName.getText());
				if(Business!=null)
					hidden_User.setBusiness(Business.getText());
				
			  if(hidden_User!=null){
				 Random rand = new Random();
			     int r=Math.abs(rand.nextInt());
			     hidden_User.setPrincipal(r);
			    try{
			    	 
				  int i=assets.insertHiddenUser(hidden_User);
				  
				  if(i==1){
					  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	                	alert.setTitle("安全信息");
						alert.setHeaderText("操作");
						alert.setContentText("新建"+PrincipalName.getText()+"成功");								
						alert.showAndWait();
						setUser();
						handleCancel();
	                }else{
	                	Alert alert2 = new Alert(AlertType.ERROR);
						alert2.setTitle("异常堆栈对话框");
						alert2.setHeaderText("错误");
						alert2.setContentText("新建"+PrincipalName.getText()+"失败");
						alert2.showAndWait();
	                }
	                }catch (Exception e) {
						// TODO: handle exception
	                	Alert alert2 = new Alert(AlertType.ERROR);
						alert2.setTitle("异常堆栈对话框");
						alert2.setHeaderText("错误");
						alert2.setContentText("新建"+PrincipalName.getText()+"失败");
						alert2.showAndWait();
						e.printStackTrace();
					}
				  
				}
				
			}
		});
		
		
	}
	
	
	public void setUser(){
		 List userList=new ArrayList<>();
		 userList=assets.selectAllHiddenUser();
		 
		 hiddenList= (ObservableList<HiddenUserProperty>) new RowData(userList,HiddenUserProperty.class).get();
		 
		 hiddenTable.setItems(hiddenList);
		 
		 C1.setCellValueFactory(
	                cellData -> cellData.getValue().getId().asObject());
	     C2.setCellValueFactory(
	   		    cellData->cellData.getValue().getPrincipal_name());
	     C3.setCellValueFactory(
	    		    cellData->cellData.getValue().getBusiness());
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
