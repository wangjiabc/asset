package com.asset.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.asset.Singleton;
import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenLevelProperty;
import com.asset.property.HiddenProperty;
import com.asset.property.HiddenTypeProperty;
import com.asset.property.HiddenUserProperty;
import com.asset.property.join.HiddenCheck_JoinProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.Md5;
import com.asset.tool.MenuType;
import com.asset.tool.MyTestUtil;
import com.asset.view.detail.AddUserDetailController;
import com.asset.view.detail.HiddenLevelDetailController;
import com.asset.view.detail.HiddenTypeDetailController;
import com.asset.view.detail.UpHiddenLevelDetailController;
import com.asset.view.detail.UpHiddenTypeDetailController;
import com.asset.view.detail.UpUserDetailController;
import com.asset.view.hidden.HiddenDetailController;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Check;
import com.voucher.manage.daoModel.Assets.Hidden_Level;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.daoModel.Assets.Hidden_User;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Join;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AssetWorkController extends AssetAsSwitch{
	
	@FXML
	private Label rightTitleLabel;
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Tab typeTab;
	
	@FXML
	private Tab levelTab;
	
	@FXML
	private Tab userTab;
	
	@FXML
	private Button addHiddenTypeButton;
	
	@FXML
	private Button upHiddenTypeButton;
	
	@FXML
	private Button delHiddenTypeButton;
	
	@FXML
	private Button addHiddenLevelButton;
	
	@FXML
	private Button upHiddenLevelButton;
	
	@FXML
	private Button delHiddenLevelButton;
	
	@FXML
	private Button addHiddenUserButton;
	
	@FXML
	private Button upHiddenUserButton;
	
	@FXML
	private Button delHiddenUserButton;
	
	private ObservableList<HiddenTypeProperty> hiddenTypeList;	
	
	@FXML
	 private TableView<HiddenTypeProperty> hiddenTypeTable;
	
	@FXML
	 private TableColumn<HiddenTypeProperty,Integer> C11;
	
	@FXML
	 private TableColumn<HiddenTypeProperty,String> C12;
	
	private ObservableList<HiddenLevelProperty> hiddenLevelList;
	
	@FXML
	 private TableView<HiddenLevelProperty> hiddenLevelTable;
	
	@FXML
	 private TableColumn<HiddenLevelProperty,Integer> C21;
	
	@FXML
	 private TableColumn<HiddenLevelProperty,String> C22;
	
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
	 private ContextMenu contextMenuUser;
	 
	 @FXML
	 private ContextMenu contextMenuLevel;
	 
	 @FXML
	 private ContextMenu contextMenuType;
	 
	 private Integer offset=0;
	 
	 private Integer limit=10;
	 
	 @FXML
	 private Pagination pagination;
	 
	 @FXML
	 private TextField oldPassword;
	 
	 @FXML
	 private PasswordField newPassword;
	 
	 @FXML
	 private PasswordField affirmPassword;
	 
	 @FXML
	 private Button rework;
	 
	 @FXML
	  Button hiddenWrite;
	 
	 private HiddenUserProperty hiddenUserProperty;
	 
	 private HiddenTypeProperty hiddenTypeProperty;
	 
	 private HiddenLevelProperty hiddenLevelProperty;
	 
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
	     
	   
	     setHiddenType();
	     
	     setHiddenLevel();
     
	     Image delImage=new Image(filePath+"/del.jpg");
	     ImageView imageView0=new ImageView();
	     imageView0.setFitWidth(25);
	     imageView0.setFitHeight(25);
	     imageView0.setImage(delImage);
	     contextMenuLevel.getItems().get(0).setGraphic(imageView0);
	     ImageView imageView1=new ImageView();
	     imageView1.setFitWidth(25);
	     imageView1.setFitHeight(25);
	     imageView1.setImage(delImage);
	     contextMenuType.getItems().get(0).setGraphic(imageView1);
	     ImageView imageView2=new ImageView();
	     imageView2.setFitWidth(25);
	     imageView2.setFitHeight(25);
	     imageView2.setImage(delImage);
	     contextMenuUser.getItems().get(0).setGraphic(imageView2);
	     
	     hiddenWrite.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					main.showAssetOverview();
				}
				
			});
	     
	     pagination.setPageFactory((Integer pageIndex)->{
		    	if (pageIndex >= 0) {
		    		offset=pageIndex*10;
		    		limit=10;
		    		setUser(offset, limit);
		    		 Label mLabel = new Label();  
		                mLabel.setText("这是第" + (pageIndex+1) + "页");  
		                return mLabel;  
	            } else {
	                return null;
	            }
		    });
	     
	     if(Singleton.getInstance().getHidden_User().getPurview()>0){
	    	 typeTab.setDisable(true);
	    	 levelTab.setDisable(true);
	    	 userTab.setDisable(true);
	    	 SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();	     
	    	 selectionModel.select(3);
	     }
	     
	     hiddenTypeTable.getSelectionModel().selectedItemProperty().addListener(new
				 ChangeListener<HiddenTypeProperty>() {

					@Override
					public void changed(ObservableValue<? extends HiddenTypeProperty> observable, HiddenTypeProperty oldValue,
							HiddenTypeProperty newValue) {
						// TODO Auto-generated method stub
						 hiddenTypeProperty=newValue;
					}
			        
				});
	     
	    delHiddenTypeButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				delType(hiddenTypeProperty);
			}
			
		}); 
	     
	    upHiddenTypeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				table2(hiddenTypeProperty);
			}
		});
	     
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
			            dialogStage.setTitle("新建隐患类型");
			            dialogStage.initModality(Modality.APPLICATION_MODAL);
			            Scene scene = new Scene(page);
			            dialogStage.setScene(scene);

			            // Set the person into the controller.
			            HiddenTypeDetailController controller = loader.getController();
			            controller.setHiddenType(hiddenTypeTable, C11, C12);
			            controller.setDialogStage(dialogStage);
	      	            
			            // Show the dialog and wait until the user closes it
			            dialogStage.show();
				}
			});
	     
	     
	     hiddenLevelTable.getSelectionModel().selectedItemProperty().addListener(new
				 ChangeListener<HiddenLevelProperty>() {

					@Override
					public void changed(ObservableValue<? extends HiddenLevelProperty> observable, HiddenLevelProperty oldValue,
							HiddenLevelProperty newValue) {
						// TODO Auto-generated method stub
						 hiddenLevelProperty=newValue;
					}
			        
				});
	   
	  delHiddenLevelButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			delLevel(hiddenLevelProperty);
		}
		
	  });  
	     
	   upHiddenLevelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
			     table3(hiddenLevelProperty);	
			}
			
		});
	     
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
			            dialogStage.setTitle("新建隐患等级");
			            dialogStage.initModality(Modality.APPLICATION_MODAL);
			            Scene scene = new Scene(page);
			            dialogStage.setScene(scene);

			            // Set the person into the controller.
			            HiddenLevelDetailController controller = loader.getController();
			            
			            controller.setHiddenLevel(hiddenLevelTable, C21, C22);
			            
			            controller.setDialogStage(dialogStage);
	    	            
			            // Show the dialog and wait until the user closes it
			            dialogStage.show();
				}
			});
	     	    
	     
	     hiddenUserTable.getSelectionModel().selectedItemProperty().addListener(new
				 ChangeListener<HiddenUserProperty>() {

					@Override
					public void changed(ObservableValue<? extends HiddenUserProperty> observable, HiddenUserProperty oldValue,
							HiddenUserProperty newValue) {
						// TODO Auto-generated method stub
						 hiddenUserProperty=newValue;
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
			            dialogStage.initModality(Modality.APPLICATION_MODAL);
			            Scene scene = new Scene(page);
			            dialogStage.setScene(scene);

			            // Set the person into the controller.
			            AddUserDetailController controller = loader.getController();
			            controller.setDialogStage(dialogStage);
			            controller.setAddUser(offset, limit, hiddenUserTable, C31, C32, C33, C34, C35, C36, C37, pagination);
			            
			            // Show the dialog and wait until the user closes it
			            dialogStage.show();
				}
			});
		     
	     
	      
	     
	       upHiddenUserButton.setOnAction(new EventHandler<ActionEvent>() {

	    	   @Override
	    	   public void handle(ActionEvent event){ 
	    		   // TODO Auto-generated method stub
	    		   try{
				    table(hiddenUserProperty);
	    		   }catch (Exception e) {
					// TODO: handle exception
	    			   e.printStackTrace();
				  }
	    	   }
			
		   });
		     
	   delHiddenUserButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
			    delUser(hiddenUserProperty);	
			}
			
		});
	       
		     hiddenUserTable.setRowFactory( tv -> {
			        TableRow<HiddenUserProperty> row = new TableRow<>();
			        row.setOnMouseClicked(event -> {
			            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
			            	HiddenUserProperty rowData = row.getItem();
			            	table(rowData);
			            }
			        });
			        
			        row.setOnContextMenuRequested(event->{
			        	
		        	    contextMenuUser.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								try{
									
								  
								  String menuType=MenuType.get(event.getTarget().toString());
								  System.out.println(menuType);								  
								  
								  if(menuType.equals("m1")){
									  HiddenUserProperty rowData=row.getItem();
									  delUser(rowData);
									  
								  }
								  
								  
								  if(menuType.equals("m2")){
									  HiddenUserProperty rowData = row.getItem();
						              table(rowData);
								  }
								  
								  
								}catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							}
		        	    });
			        });
			        
			        return row ;
			    });
	     
		     
		     hiddenTypeTable.setRowFactory( tv -> {
			        TableRow<HiddenTypeProperty> row = new TableRow<>();
			        row.setOnMouseClicked(event -> {
			            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
			            	HiddenTypeProperty rowData = row.getItem();
			            	table2(rowData);
			            }
			        });
			        
			        row.setOnContextMenuRequested(event->{
			        	
			        	contextMenuType.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								try{
								  
								  String menuType=MenuType.get(event.getTarget().toString());
								  System.out.println(menuType);								  
								  
								  if(menuType.equals("m1")){
									  
									  delType(row.getItem());
									 
									  }
								  
								  								  
								  if(menuType.equals("m2")){
									  HiddenTypeProperty rowData = row.getItem();
						              table2(rowData);
								  }
								  
								  
								}catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							}
		        	    });
			        });
			        
			       return row;
		     });
		     
		     hiddenLevelTable.setRowFactory( tv -> {
			        TableRow<HiddenLevelProperty> row = new TableRow<>();
			        row.setOnMouseClicked(event -> {
			            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
			            	HiddenLevelProperty rowData = row.getItem();
			            	table3(rowData);
			            }
			        });
			        
			        row.setOnContextMenuRequested(event->{
			        	
		        	    contextMenuLevel.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								try{
								  
								  String menuType=MenuType.get(event.getTarget().toString());
								  
								  System.out.println(menuType);								  
								  
								  if(menuType.equals("m1")){
									  
									  delLevel(row.getItem());
								
								  }
								  
								  
								  if(menuType.equals("m2")){
									  HiddenLevelProperty rowData = row.getItem();
						              table3(rowData);
								  }
								  
								  
								}catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							}
		        	    });
			        });
			        
			        return row;
		     });
	     
		     rework.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
				     String oldPw=oldPassword.getText();
				     String newPw=newPassword.getText();
				     String affirmPw=affirmPassword.getText();
				     
				   try{ 
				     if(!newPw.equals(affirmPw)){
				    	    Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("错误");
							alert.setHeaderText("修改密码错误");
							alert.setContentText("两次输入的密码不一致");
							alert.showAndWait();
				     }else{
				    	 Hidden_User hidden_User=new Hidden_User();
				    	 String campusAdmin=Singleton.getInstance().getHidden_User().getCampusAdmin();
				    	 
				    	 System.out.println("campusAdmin="+campusAdmin);
				    	 hidden_User.setCampusAdmin(campusAdmin);
				    	 hidden_User.setPassword(Md5.GetMD5Code(newPw));
				    	 int i=assets.updateUserPassword(hidden_User,Md5.GetMD5Code(oldPw));
				    	 
				    	 if(i==1){
				    		   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
								alert.setTitle("修改密码");
								alert.setHeaderText("修改密码成功");
								alert.showAndWait();
								oldPassword.setText("");
								newPassword.setText("");
								affirmPassword.setText("");
				    	 }else if(i==3){
				    		   Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("错误");
								alert.setHeaderText("修改密码错误");
								alert.setContentText("原密码不正确");
								alert.showAndWait();
				    	 }else{
				    		 Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("错误");
								alert.setHeaderText("修改密码错误");
								alert.setContentText("其它错误");
								alert.showAndWait();
				    	 }
				    	 
				     }
				   }catch (Exception e) {
					// TODO: handle exception
					   e.printStackTrace();
					   Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("错误");
						alert.setHeaderText("修改密码错误");
						alert.setContentText("其它错误");
						alert.showAndWait();
				  }
				}
				
			});
		     
	 }
	 
	private void delUser(HiddenUserProperty hiddenUserProperty){
		String uesrId=String.valueOf(hiddenUserProperty.getId().get());
		  String userName=hiddenUserProperty.getPrincipal_name().get();
		  Integer principal=hiddenUserProperty.getPrincipal().get();
		  
		  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("删除");
	        alert.setHeaderText("安全员工记录");
	        alert.setContentText("是否删除"+userName+"的信息");

	        ButtonType btnType1 = new ButtonType("确定");
	        ButtonType btnType2 = new ButtonType("取消");
	     

	        alert.getButtonTypes().setAll(btnType1, btnType2);

	        Optional<ButtonType> result = alert.showAndWait();
	        result.ifPresent(buttonType -> {
	            if (buttonType == btnType1) {
	                try{
	                String[] where={"[Assets].[dbo].[Hidden_User].id =",uesrId};
                  Hidden_User hidden_User=new Hidden_User();
                  hidden_User.setWhere(where);
                  hidden_User.setPrincipal(principal);
                  
	                int i=assets.deleteHiddenUser(hidden_User);
	                if(i==1){
	                	alert.setTitle("安全员工记录");
						alert.setHeaderText("操作");
						alert.setContentText("删除"+userName+"成功");
						alert.showAndWait();
						hiddenUserTable.setItems(null);
						setUser(offset,limit);
	                }else if(i==3){
	                	Alert alert3 = new Alert(AlertType.WARNING);
						alert3.setTitle("警告对话框");
						alert3.setHeaderText("警告");
						alert3.setContentText("不能删除隐患负责人"+userName);
						alert3.showAndWait();
	                }else{
	                	Alert alert2 = new Alert(AlertType.ERROR);
						alert2.setTitle("异常堆栈对话框1");
						alert2.setHeaderText("错误");
						alert2.setContentText("删除"+userName+"失败");
						alert2.showAndWait();
	                }
	                }catch (Exception e) {
						// TODO: handle exception
	                	e.printStackTrace();
	                	Alert alert2 = new Alert(AlertType.ERROR);
						alert2.setTitle("异常堆栈对话框2");
						alert2.setHeaderText("错误");
						alert2.setContentText("删除"+userName+"失败");
						alert2.showAndWait();													
					}
	            } else if (buttonType == btnType2) {
	            	System.out.println("点击了取消");
	            } 
	        });
	}
	 
	
	private void delType(HiddenTypeProperty hiddenTypeProperty){
		
		 int id=hiddenTypeProperty.getId().get();
		  String typeId=String.valueOf(id);
		  String typeName=hiddenTypeProperty.getHidden_type().get();
		    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("删除");
	        alert.setHeaderText("隐患等级");
	        alert.setContentText("是否删除"+typeName+"的信息");

	        ButtonType btnType1 = new ButtonType("确定");
	        ButtonType btnType2 = new ButtonType("取消");
	     

	        alert.getButtonTypes().setAll(btnType1, btnType2);

	        Optional<ButtonType> result = alert.showAndWait();
	        result.ifPresent(buttonType -> {
	            if (buttonType == btnType1) {
	                try{
	                String[] where={"[Assets].[dbo].[Hidden_Type].id =", typeId};
                   Hidden_Type hidden_Type=new Hidden_Type();
                   hidden_Type.setWhere(where);
                   hidden_Type.setType(hiddenTypeProperty.getType().get());
                   
	                int i=assets.deleteHiddenType(hidden_Type);
	                if(i==1){
	                	alert.setTitle("安全隐患等级");
						alert.setHeaderText("操作");
						alert.setContentText("删除"+typeName+"成功");
						alert.showAndWait();
						setHiddenType();
	                }else if(i==3){
	                	Alert alert2 = new Alert(AlertType.WARNING);
						alert2.setTitle("警告对话框");
						alert2.setHeaderText("警告");
						alert2.setContentText(typeName+"正在被使用,不能删除!");
						alert2.showAndWait();
	                }else{
	                	Alert alert2 = new Alert(AlertType.ERROR);
						alert2.setTitle("异常堆栈对话框1");
						alert2.setHeaderText("错误");
						alert2.setContentText("删除"+typeName+"失败");
						alert2.showAndWait();
	                }
	                }catch (Exception e) {
						// TODO: handle exception
	                	e.printStackTrace();
	                	Alert alert2 = new Alert(AlertType.ERROR);
						alert2.setTitle("异常堆栈对话框2");
						alert2.setHeaderText("错误");
						alert2.setContentText("删除"+typeName+"失败");
						alert2.showAndWait();													
					}
	            } else if (buttonType == btnType2) {
	            	System.out.println("点击了取消");
	            } 
	       });
	 }
	
	
	private void delLevel(HiddenLevelProperty hiddenLevelProperty){
		
		  int level=hiddenLevelProperty.getHidden_level().get();
		  String levelId=String.valueOf(hiddenLevelProperty.getId().get());
		  String levelName=hiddenLevelProperty.getLevel_text().get();
		  Integer hiddenLevel=hiddenLevelProperty.getHidden_level().get();
		  
		  System.out.println(level);
		 if(level==1||level==2||level==3){
			  Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("错误");
				alert.setHeaderText("删除隐患等级错误");
				alert.setContentText("默认隐患级别不能删除");
				alert.showAndWait();
		  }else{
		    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("删除");
	        alert.setHeaderText("隐患等级");
	        alert.setContentText("是否删除"+levelName+"的信息");

	        ButtonType btnType1 = new ButtonType("确定");
	        ButtonType btnType2 = new ButtonType("取消");
	     

	        alert.getButtonTypes().setAll(btnType1, btnType2);

	        Optional<ButtonType> result = alert.showAndWait();
	        result.ifPresent(buttonType -> {
	            if (buttonType == btnType1) {
	                try{
	                String[] where={"[Assets].[dbo].[Hidden_Level].id =", levelId};
                    Hidden_Level hidden_Level=new Hidden_Level();
                    hidden_Level.setWhere(where);
                    hidden_Level.setHidden_level(hiddenLevel);
                    
	                int i=assets.deleteHiddenLevel(hidden_Level);
	                if(i==1){
	                	alert.setTitle("隐患等级记录");
						alert.setHeaderText("操作");
						alert.setContentText("删除"+levelName+"成功");
						alert.showAndWait();
						setHiddenLevel();
	                }else if(i==3){
	                	Alert alert3 = new Alert(AlertType.WARNING);
	                	alert3.setTitle("隐患等级记录");
						alert3.setHeaderText("操作");
						alert3.setContentText(levelName+"正在被使用,不能删除");
						alert3.showAndWait();
	                }else{
	                	Alert alert2 = new Alert(AlertType.ERROR);
						alert2.setTitle("异常堆栈对话框1");
						alert2.setHeaderText("错误");
						alert2.setContentText("删除"+levelName+"失败");
						alert2.showAndWait();
	                }
	                }catch (Exception e) {
						// TODO: handle exception
	                	e.printStackTrace();
	                	Alert alert2 = new Alert(AlertType.ERROR);
						alert2.setTitle("异常堆栈对话框2");
						alert2.setHeaderText("错误");
						alert2.setContentText("删除"+levelName+"失败");
						alert2.showAndWait();													
					}
	            } else if (buttonType == btnType2) {
	            	System.out.println("点击了取消");
	            } 
	        });
		  }
	}
	
	 private void table(HiddenUserProperty newValue){
		 Integer id=newValue.getId().get();
		 Integer principal=newValue.getPrincipal().get();
		 String campusAdmin=newValue.getCampusAdmin().get();
		 String principalName=newValue.getPrincipal_name().get();		
		 String phone=newValue.getPhone().get();
		 String business=newValue.getBusiness().get();
		 Integer purview=newValue.getPurview().get(); 
		 
		 Hidden_User hidden_User=new Hidden_User();
		 
		 hidden_User.setId(id);
		 hidden_User.setPrincipal(principal);
		 hidden_User.setCampusAdmin(campusAdmin);
		 hidden_User.setPrincipal_name(principalName);
		 hidden_User.setPhone(phone);
		 hidden_User.setBusiness(business);
		 hidden_User.setPurview(purview);
		 
		 FXMLLoader loader = new FXMLLoader();
         loader.setLocation(AssetWorkController.class.getResource("detail/UpUserDetail.fxml"));
         AnchorPane page = null;
			try {
				page = (AnchorPane) loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

         // Create the dialog Stage.
         Stage dialogStage = new Stage();
         dialogStage.setTitle("修改员工信息");
         dialogStage.initModality(Modality.APPLICATION_MODAL);
         Scene scene = new Scene(page);
         dialogStage.setScene(scene);

         // Set the person into the controller.
         UpUserDetailController controller = loader.getController();
         controller.setUpUser(hidden_User, offset, limit, hiddenUserTable, C31, C32, C33, C34, C35, C36, C37, pagination);
         controller.setDialogStage(dialogStage);
         
         // Show the dialog and wait until the user closes it
         dialogStage.show();
	 }
	 
	 private void table2(HiddenTypeProperty newValue){
		 try{
			   Integer id=newValue.getId().get();
			   Integer type=newValue.getType().get();
               String typeName=newValue.getHidden_type().get();
			   Hidden_Type hidden_Type=new Hidden_Type();
			   hidden_Type.setId(id);
			   hidden_Type.setType(type);
			   hidden_Type.setHidden_type(typeName);
			   
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(AssetOverviewController.class.getResource("detail/UpHiddenTypeDetail.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("更新隐患等级");
	            dialogStage.initModality(Modality.APPLICATION_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            UpHiddenTypeDetailController controller = loader.getController();
	            
	            controller.setHiddenType(hidden_Type,hiddenTypeTable, C11, C12);
	            
	            controller.setDialogStage(dialogStage);
	            
	            // Show the dialog and wait until the user closes it
	            dialogStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 private void table3(HiddenLevelProperty newValue){
		 int level=newValue.getHidden_level().get();
		 try {
			 if(level==1||level==2||level==3){
				  Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("错误");
					alert.setHeaderText("更新隐患等级错误");
					alert.setContentText("默认隐患级别不能更新");
					alert.showAndWait();
			  }else{
			    Integer id=newValue.getId().get();
			    Integer hidden_level=newValue.getHidden_level().get();
			    String level_text=newValue.getLevel_text().get();
			    Hidden_Level hidden_Level=new Hidden_Level();
			    hidden_Level.setId(id);
			    hidden_Level.setHidden_level(hidden_level);
			    hidden_Level.setLevel_text(level_text);
			    
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(AssetOverviewController.class.getResource("detail/UpHiddenLevelDetail.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("更新隐患等级");
	            dialogStage.initModality(Modality.APPLICATION_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            UpHiddenLevelDetailController controller = loader.getController();
	            
	            controller.setHiddenLevel(hidden_Level,hiddenLevelTable, C21, C22);
	            
	            controller.setDialogStage(dialogStage);
	            
	            // Show the dialog and wait until the user closes it
	            dialogStage.show();
			  }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
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
	 
	public void setHiddenLevel(){
		
		List<Hidden_Level> hidden_levels=assets.setctAllHiddenLevel();
		
		hiddenLevelList=(ObservableList<HiddenLevelProperty>) new RowData(hidden_levels, HiddenLevelProperty.class).get();
		
		hiddenLevelTable.setItems(hiddenLevelList);
		
		C21.setCellValueFactory(
	    		 cellData -> cellData.getValue().getHidden_level().asObject());
		
		C22.setCellValueFactory(
	    		 cellData -> cellData.getValue().getLevel_text());
		
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
	 
}
