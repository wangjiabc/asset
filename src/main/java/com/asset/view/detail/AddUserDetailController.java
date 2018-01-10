package com.asset.view.detail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenUserProperty;
import com.asset.tool.Md5;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden_User;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Toggle;

public class AddUserDetailController {

	@FXML
	private TextField id;
	
	@FXML
	private TextField campusAdmin;
	
	@FXML
	private TextField PrincipalName;
	
	@FXML
	private TextField phone;
	
	@FXML
	private Button update;
	
	@FXML
	private Button cancel;
	
	private ObservableList<HiddenUserProperty> hiddenUserList;
	 
	 @FXML
	 private TableView<HiddenUserProperty> hiddenUserTable;
	 
	 @FXML
	 private TableColumn<HiddenUserProperty,Integer> C31;
	 
	 @FXML
	 private TableColumn<HiddenUserProperty,String> C32;
	 
	 @FXML
	 private TableColumn<HiddenUserProperty,String> C33;
	
	 @FXML
	 private TableColumn<HiddenUserProperty,String> C34;
	 
	 @FXML
	 private TableColumn<HiddenUserProperty,String> C35;
	 
	 @FXML
	 private TableColumn<HiddenUserProperty,String> C36;
	 
	 @FXML
	 private TableColumn<HiddenUserProperty,String> C37;
	
	 @FXML
	 private Pagination pagination;
	 
	 private Stage dialogStage;
	
	 private Integer offset;
	 
	 private Integer limit;
	 
	 private Integer purview=3;
	 
	 private String business;
	 
	 @FXML
	 private RadioButton rb1;
	 
	 @FXML
	 private RadioButton rb2;
	 
	 @FXML
	 private RadioButton rb3;
	 
	 Assets assets= new Connect().get();
	
	 public void setAddUser(Integer offset,Integer limit,TableView<HiddenUserProperty> hiddenUserTable,TableColumn<HiddenUserProperty,Integer> C31,
			 TableColumn<HiddenUserProperty,String> C32,TableColumn<HiddenUserProperty,String> C33,
			 TableColumn<HiddenUserProperty,String> C34,TableColumn<HiddenUserProperty,String> C35,
			 TableColumn<HiddenUserProperty,String> C36,TableColumn<HiddenUserProperty,String> C37,
			 Pagination pagination){
		    this.offset=offset;
		    this.limit=limit;
		    this.hiddenUserTable=hiddenUserTable;
		    this.C31=C31;
		    this.C32=C32;
		    this.C33=C33;
		    this.C34=C34;
		    this.C35=C35;
		    this.C36=C36;
		    this.C37=C37;
		    this.pagination=pagination;
	 }
	
	@FXML
	private void initialize() {
		Assets assets=new Connect().get();
		
		Hidden_User hidden_User=new Hidden_User();

		ToggleGroup group = new ToggleGroup();
		
		rb1.setToggleGroup(group);
		rb1.setUserData(1);
		rb2.setToggleGroup(group);
		rb2.setUserData(2);
		rb3.setToggleGroup(group);
		rb3.setUserData(3);
		
		rb3.setSelected(true);
		
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		      public void changed(ObservableValue<? extends Toggle> ov,
		          Toggle old_toggle, Toggle new_toggle) {
		        if (group.getSelectedToggle() != null) {
		           purview=(Integer) group.getSelectedToggle().getUserData();
		           System.out.println("purview="+purview);
		        }
		      }
		    });
		
		update.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			
			  if(hidden_User!=null){
				 Random rand = new Random();
			     int r=Math.abs(rand.nextInt());
			     hidden_User.setPrincipal(r);
			    try{
			    
			    if(id!=null)
					hidden_User.setId(Integer.parseInt(id.getText()));
			    if(campusAdmin!=null)
			    	hidden_User.setCampusAdmin(campusAdmin.getText());
				if(PrincipalName!=null)
					hidden_User.setPrincipal_name(PrincipalName.getText());
				if(phone!=null)
					hidden_User.setPhone(phone.getText());
				 System.out.println("purview="+purview);
				    
				
				    if(purview==1){
				    	business="管理员";
				    }else if(purview==2){
				    	business="录入员";
				    }else if(purview==3){
				    	business="观察员";
				    }
					
				  hidden_User.setBusiness(business); 	
			   	  
				 hidden_User.setPassword(Md5.GetMD5Code("xl123"));	
				 hidden_User.setPurview(purview);
				 
				 Date date=new Date();
				 hidden_User.setRegister_time(date);
				 
				 int i=assets.insertHiddenUser(hidden_User);
				  
				  if(i==1){
					  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	                	alert.setTitle("安全信息");
						alert.setHeaderText("操作");
						alert.setContentText("新建"+PrincipalName.getText()+"成功");								
						alert.showAndWait();
						setUser(offset,limit);
						handleCancel();
	                }else if(i==2){
	                	Alert alert2 = new Alert(AlertType.ERROR);
						alert2.setTitle("异常堆栈对话框");
						alert2.setHeaderText("新建"+PrincipalName.getText()+"失败");
						alert2.setContentText("帐号不能重复");
						alert2.showAndWait();
	                }else if(i==3){
	                	Alert alert3 = new Alert(AlertType.ERROR);
						alert3.setTitle("异常堆栈对话框");
						alert3.setHeaderText("新建"+PrincipalName.getText()+"失败");
						alert3.setContentText("工号不能重复");
						alert3.showAndWait();
	                }else{
	                	Alert alert4 = new Alert(AlertType.ERROR);
						alert4.setTitle("异常堆栈对话框");
						alert4.setHeaderText("错误");
						alert4.setContentText("新建"+PrincipalName.getText()+"失败");
						alert4.showAndWait();
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
		
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				handleCancel();
			}
			
		});
		
	}
	
	
	public void setUser(Integer offset,Integer limit){

		String sort=null;
	     String order=null;
		
		 Map searchMap=new HashMap<>();
		
		 Map map=assets.selectAllHiddenUser(limit, offset, sort, order, searchMap);
		 
		 List userList=(List) map.get("rows");
		 
		 MyTestUtil.print(userList);
		 
		 hiddenUserList= (ObservableList<HiddenUserProperty>) new RowData(userList,HiddenUserProperty.class).get();
		 
		 hiddenUserTable.setItems(hiddenUserList);
		 
		 C31.setCellValueFactory(
	                cellData -> cellData.getValue().getId().asObject());
	     C32.setCellValueFactory(
	   		    cellData->cellData.getValue().getCampusAdmin());
	     C33.setCellValueFactory(
		   		    cellData->cellData.getValue().getPrincipal_name());
	     C34.setCellValueFactory(
		   		    cellData->cellData.getValue().getPhone());
	     C35.setCellValueFactory(
	    		    cellData->cellData.getValue().getBusiness());	     
	     C36.setCellValueFactory(
		   		    cellData->cellData.getValue().getEnter_time());
	     C37.setCellValueFactory(
	    		    cellData->cellData.getValue().getRegister_time());
	     
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
	 
	     /**
	     * Called when the user clicks cancel.
	     */
	     @FXML
	     private void handleCancel() {
	        dialogStage.close();
	     }
	
}
