package com.asset.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenUserProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.view.detail.AddUserDetailController;
import com.asset.view.detail.HiddenLevelDetailController;
import com.asset.view.detail.HiddenTypeDetailController;
import com.asset.view.hidden.HiddenDetailController;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Level;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.daoModel.Assets.Hidden_User;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Jion;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AssetWorkController extends AssetAsSwitch{
	
	@FXML
	private Label rightTitleLabel;
	
	@FXML
	private VBox hiddenLevel;
	
	@FXML
	private VBox levelText;
	
	@FXML
	private VBox levelButton;
	
	@FXML
	private VBox hiddenType;
		
	@FXML
	private VBox hiddenTypeButton;
	
	@FXML
	private Button addHiddenTypeButton;
	
	@FXML
	private Button addHiddenLevelButton;
	
	@FXML
	private Button addHiddenUserButton;
	
	private ObservableList<HiddenUserProperty> hiddenList;
	 
	 @FXML
	 private TableView<HiddenUserProperty> hiddenTable;
	 
	 @FXML
	 private TableColumn<HiddenUserProperty,Integer> C1;
	 
	 @FXML
	 private TableColumn<HiddenUserProperty,String> C2;
	 
	 @FXML
	 private TableColumn<HiddenUserProperty,String> C3;
	
	Assets assets= new Connect().get();
	
	 public AssetWorkController() {
		// TODO Auto-generated constructor stub
		 super();
	}
	 
	 @Override
	 protected void initCurrent() {
		 URL url = getClass().getResource("");
		 String filePath=url.toString()+"Image";
		 Image image = new Image(filePath+"/work.jpg");
	     homepage.setImage(image);
	     
	     rightTitleLabel.setText("设置");
	     
	     Insets insets=new Insets(10, 10, 10, 10);
	     hiddenLevel.setPadding(insets);
	     hiddenLevel.setSpacing(20);
	     levelText.setPadding(insets);
	     levelText.setSpacing(20);
	     levelButton.setSpacing(10);

	     List<Hidden_Level> hidden_levels=assets.setctAllHiddenLevel();
	     
	     Iterator<Hidden_Level> iterator=hidden_levels.iterator();
	     
	     while(iterator.hasNext()){
	    	 Hidden_Level hidden_level=iterator.next();
	    	 Label label=new Label();
	    	 label.setText(hidden_level.getHidden_level().toString());
	    	 hiddenLevel.getChildren().add(label);
	    	 Label label2=new Label();
	    	 label2.setText(hidden_level.getLevel_text());
	    	 levelText.getChildren().add(label2);
	    	 Insets insets2=new Insets(5, 5, 5, 5);
	    	 Button button=new Button();	     
		     button.setText("删除");
		     levelButton.getChildren().add(button);
		     button.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					setFlowPane(assets);
				}
			});
	     }
	     
	     addHiddenLevelButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					  FXMLLoader loader = new FXMLLoader();
			            loader.setLocation(AssetWorkController.class.getResource("detail/HiddenLevelDetail.fxml"));
			            AnchorPane page = null;
						try {
							page = (AnchorPane) loader.load();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

			            // Create the dialog Stage.
			            Stage dialogStage = new Stage();
			            dialogStage.setTitle("隐患");
			            dialogStage.initModality(Modality.WINDOW_MODAL);
			            Scene scene = new Scene(page);
			            dialogStage.setScene(scene);

			            // Set the person into the controller.
			            HiddenLevelDetailController controller = loader.getController();
			            controller.setDialogStage(dialogStage);
	                    controller.setHiddenLevel(hiddenLevel, levelText, levelButton);
			            
			            // Show the dialog and wait until the user closes it
			            dialogStage.show();
				}
			});
	     
	     List<Hidden_Type> hidden_Types=assets.selectAllHiddenType();
	     
	     Iterator<Hidden_Type> iterator2=hidden_Types.iterator();
	     
	     while(iterator2.hasNext()){
	    	 Hidden_Type hidden_Type=iterator2.next();
	    	 Label label=new Label();
	    	 label.setText(hidden_Type.getHidden_type());
	    	 hiddenType.getChildren().add(label);
	    	 Button button=new Button();
	    	 button.setText("删除");
	    	 hiddenTypeButton.getChildren().add(button);
	    	 button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					setFlowPane2(assets);
				}
			});
	     }
	     
	     addHiddenTypeButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					  FXMLLoader loader = new FXMLLoader();
			            loader.setLocation(AssetWorkController.class.getResource("detail/HiddenTypeDetail.fxml"));
			            AnchorPane page = null;
						try {
							page = (AnchorPane) loader.load();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

			            // Create the dialog Stage.
			            Stage dialogStage = new Stage();
			            dialogStage.setTitle("隐患");
			            dialogStage.initModality(Modality.WINDOW_MODAL);
			            Scene scene = new Scene(page);
			            dialogStage.setScene(scene);

			            // Set the person into the controller.
			            HiddenTypeDetailController controller = loader.getController();
			            controller.setDialogStage(dialogStage);
	                    controller.setHiddenType(hiddenType, hiddenTypeButton);
			            
			            // Show the dialog and wait until the user closes it
			            dialogStage.show();
				}
			});
	    
	     
	     
	     addHiddenUserButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					 FXMLLoader loader = new FXMLLoader();
			            loader.setLocation(AssetWorkController.class.getResource("detail/AddUserDetail.fxml"));
			            AnchorPane page = null;
						try {
							page = (AnchorPane) loader.load();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

			            // Create the dialog Stage.
			            Stage dialogStage = new Stage();
			            dialogStage.setTitle("新建员工");
			            dialogStage.initModality(Modality.WINDOW_MODAL);
			            Scene scene = new Scene(page);
			            dialogStage.setScene(scene);

			            // Set the person into the controller.
			            AddUserDetailController controller = loader.getController();
			            controller.setDialogStage(dialogStage);
			            controller.setAddUser(hiddenTable, C1, C2, C3);
			            
			            // Show the dialog and wait until the user closes it
			            dialogStage.show();
				}
			});
		     
		     setUser();
		     
		     hiddenTable.setRowFactory( tv -> {
			        TableRow<HiddenUserProperty> row = new TableRow<>();
			        row.setOnMouseClicked(event -> {
			            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
			            	HiddenUserProperty rowData = row.getItem();
			            	table(rowData);
			            }
			        });
			        return row ;
			    });
	     
	     
	 }
	 
	 private void setFlowPane(Assets assets){
		 hiddenLevel.getChildren().clear();
			levelText.getChildren().clear();
			levelButton.getChildren().clear();
			List<Hidden_Level> hidden_levels=assets.setctAllHiddenLevel();
		     
		     Iterator<Hidden_Level> iterator=hidden_levels.iterator();
		     
		     while(iterator.hasNext()){
		    	 Hidden_Level hidden_level2=iterator.next();
		    	 Label label=new Label();
		    	 label.setText(hidden_level2.getHidden_level().toString());
		    	 hiddenLevel.getChildren().add(label);
		    	 Label label2=new Label();
		    	 label2.setText(hidden_level2.getLevel_text());
		    	 levelText.getChildren().add(label2);
		    	 Insets insets2=new Insets(5, 5, 5, 5);
		    	 Button button=new Button();	     
			     button.setText("删除");
			     levelButton.getChildren().add(button);
			     button.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				        alert.setTitle("安全信息");
				        alert.setHeaderText("删除");
				        alert.setContentText("是否删除"+hidden_level2.getId()+"信息");

				        ButtonType btnType1 = new ButtonType("确定");
				        ButtonType btnType2 = new ButtonType("取消");
				     

				        alert.getButtonTypes().setAll(btnType1, btnType2);

				        Optional<ButtonType> result = alert.showAndWait();
				        result.ifPresent(buttonType -> {
				            if (buttonType == btnType1) {
				                try{
				                String[] where={"id=",String.valueOf(hidden_level2.getId())};
				            	hidden_level2.setWhere(where);
				                int i=assets.deleteHiddenLevel(hidden_level2);
				                if(i==1){
				                	alert.setTitle("安全信息");
									alert.setHeaderText("操作");
									alert.setContentText("删除"+hidden_level2.getId()+"成功");
									setFlowPane(assets);
									alert.showAndWait();
				                }else{
				                	Alert alert2 = new Alert(AlertType.ERROR);
									alert2.setTitle("异常堆栈对话框");
									alert2.setHeaderText("错误");
									alert2.setContentText("删除"+hidden_level2.getId()+"失败");
									alert2.showAndWait();
				                }
				                }catch (Exception e) {
									// TODO: handle exception
				                	Alert alert2 = new Alert(AlertType.ERROR);
									alert2.setTitle("异常堆栈对话框");
									alert2.setHeaderText("错误");
									alert2.setContentText("删除"+hidden_level2.getId()+"失败");
									alert2.showAndWait();
								}
				            } else if (buttonType == btnType2) {
				            	System.out.println("点击了取消");
				            } 
				        });
					}
				});
		     }
	 }
	 
	 
	 private void setFlowPane2(Assets assets){
		   hiddenType.getChildren().clear();
		   hiddenTypeButton.getChildren().clear();
		   List<Hidden_Type> hidden_Types=assets.selectAllHiddenType();
		     
		     Iterator<Hidden_Type> iterator=hidden_Types.iterator();
		     
		     while(iterator.hasNext()){
		    	 Hidden_Type hidden_Type=iterator.next();
		    	 Label label=new Label();
		    	 label.setText(hidden_Type.getHidden_type());
		    	 hiddenType.getChildren().add(label);
		    	 Button button=new Button();
		    	 button.setText("删除");
		    	 hiddenTypeButton.getChildren().add(button);
			     button.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				        alert.setTitle("安全信息");
				        alert.setHeaderText("删除");
				        alert.setContentText("是否删除"+hidden_Type.getHidden_type()+"信息");

				        ButtonType btnType1 = new ButtonType("确定");
				        ButtonType btnType2 = new ButtonType("取消");
				     

				        alert.getButtonTypes().setAll(btnType1, btnType2);

				        Optional<ButtonType> result = alert.showAndWait();
				        result.ifPresent(buttonType -> {
				            if (buttonType == btnType1) {
				                try{
				                String[] where={"id=",String.valueOf(hidden_Type.getId())};
				            	hidden_Type.setWhere(where);
				                int i=assets.deleteHiddenType(hidden_Type);
				                if(i==1){
				                	alert.setTitle("安全信息");
									alert.setHeaderText("操作");
									alert.setContentText("删除"+hidden_Type.getId()+"成功");
									setFlowPane2(assets);
									alert.showAndWait();
				                }else{
				                	Alert alert2 = new Alert(AlertType.ERROR);
									alert2.setTitle("异常堆栈对话框");
									alert2.setHeaderText("错误");
									alert2.setContentText("删除"+hidden_Type.getId()+"失败");
									alert2.showAndWait();
				                }
				                }catch (Exception e) {
									// TODO: handle exception
				                	Alert alert2 = new Alert(AlertType.ERROR);
									alert2.setTitle("异常堆栈对话框");
									alert2.setHeaderText("错误");
									alert2.setContentText("删除"+hidden_Type.getId()+"失败");
									alert2.showAndWait();
								}
				            } else if (buttonType == btnType2) {
				            	System.out.println("点击了取消");
				            } 
				        });
					}
				});
		     }
     
	 }
	 
	 
	 private void table(HiddenUserProperty newValue){
		 Integer principal=newValue.getPrincipal().get();
		 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("员工信息");
	        alert.setHeaderText("删除");
	        alert.setContentText("是否删除"+newValue.getPrincipal_name().getValue());

	        ButtonType btnType1 = new ButtonType("确定");
	        ButtonType btnType2 = new ButtonType("取消");
	        
	        alert.getButtonTypes().setAll(btnType1, btnType2);
	        
	        Optional<ButtonType> result = alert.showAndWait();
	        result.ifPresent(buttonType -> {
	        if (buttonType == btnType1) {  
		     try{
			  Hidden_User hidden_User=new Hidden_User();
              String[] where={"principal=",String.valueOf(principal)};
         	  hidden_User.setWhere(where);
              int i=assets.deleteHiddenUser(hidden_User);
              if(i==1){
             	alert.setTitle("安全信息");
					alert.setHeaderText("操作");
					alert.setContentText("删除"+newValue.getPrincipal_name().getValue()+"成功");
					setFlowPane2(assets);
					alert.showAndWait();
					setUser();
              }else{
             	Alert alert2 = new Alert(AlertType.ERROR);
					alert2.setTitle("异常堆栈对话框");
					alert2.setHeaderText("错误");
					alert2.setContentText("删除"+newValue.getPrincipal_name().getValue()+"失败");
					alert2.showAndWait();
             }
             }catch (Exception e) {
					// TODO: handle exception
             	Alert alert2 = new Alert(AlertType.ERROR);
					alert2.setTitle("异常堆栈对话框");
					alert2.setHeaderText("错误");
					alert2.setContentText("删除"+newValue.getPrincipal_name()+"失败");
					alert2.showAndWait();
				 }
	           } else if (buttonType == btnType2) {
	            	System.out.println("点击了取消");
	          } 
	        });
	 }
	 
	/* 
	 private void table(HiddenUserProperty newValue){
		 try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(AssetOverviewController.class.getResource("detail/AddUserDetail.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("新建员工");
	            dialogStage.initModality(Modality.APPLICATION_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            AddUserDetailController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	         

	            dialogStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	 */
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
	 
}
