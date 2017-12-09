package com.asset.view.infowrite;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.formula.functions.T;
import org.controlsfx.dialog.Dialogs;

import com.asset.database.Connect;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Level;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.daoModel.Assets.Hidden_User;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.Alert.AlertType;

public class InfoWriteController {
	@FXML
	private TextField hiddenName;
	@FXML
	private ChoiceBox hiddenLevel;
	
	private Integer hiddenLevelValue;
	
	@FXML
	private TextField hiddenDetail;
	
	@FXML
	private ChoiceBox<T> hiddenPrincipal;//负责人
	private List<Hidden_User> hidden_Principals;
	private Integer hiddenPrincipalValue;
	
	@FXML
	private ChoiceBox<T> hiddenType;//隐患类型
	private List<Hidden_Type> hidden_Types;
	private Integer hiddenTypeValue;
	
	@FXML
	private TextField hiddenState;
	@FXML
	private DatePicker happenTime;
	@FXML
	private TextField hiddenRemark;
	@FXML
	private Button post;
	@FXML
	private Button cancel;
	
	private Stage dialogStage;
	
	Assets assets= new Connect().get();
	
	public InfoWriteController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
    private void initialize() {
		
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
		
		

		 hidden_Types=assets.selectAllHiddenType();
		 Iterator<Hidden_Type> iterator3=hidden_Types.iterator();
		 List hidden_types=new ArrayList<>();	
		 while(iterator3.hasNext()){
			 hidden_types.add(iterator3.next().getHidden_type());
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
		
		 
		 hidden_Principals=assets.selectAllHiddenUser();
		 Iterator<Hidden_User> iterator4=hidden_Principals.iterator();
		 List hidden_principals=new ArrayList<>();
		 while(iterator4.hasNext()){
			 String principals_text=iterator4.next().getPrincipal_name();
			 hidden_principals.add(principals_text);
		 }
		 
		 hiddenPrincipal.setItems(FXCollections.observableArrayList(hidden_principals));		 
		 hiddenPrincipal.getSelectionModel().selectedIndexProperty().addListener(new 
				 ChangeListener<Number>() {
					
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						// TODO Auto-generated method stub
						int i=(int) newValue;
						hiddenPrincipalValue=hidden_Principals.get(i).getPrincipal();
					}
				});
		 
		//限制输入为数字
		/* hiddenlevel.textProperty().addListener(new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		            if (!newValue.matches("\\d*")) {
		                hiddenlevel.setText(newValue.replaceAll("[^\\d]", ""));
		            }
		        }
		    });
		
		
		//限制输入为浮点数
		 doubletest.textProperty().addListener(new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		            if (!newValue.matches("[0-9]*(\\.?)[0-9]*")) {
		                doubletest.setText(newValue.replaceAll("[^\\d*(\\.?)]", ""));
		            }
		        }
		    });
		 
		 
		//限制输入为浮点数
		 floattest.textProperty().addListener(new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		            if (!newValue.matches("[0-9]*(\\.?)[0-9]*")) {
		                floattest.setText(newValue.replaceAll("[^\\d*(\\.?)]", ""));
		            }
		        }
		    });
		 
		//限制输入为数字
		 longtest.textProperty().addListener(new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		            if (!newValue.matches("\\d*")) {
		                longtest.setText(newValue.replaceAll("[^\\d]", ""));
		            }
		        }
		    });
		 */
		 
		post.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Hidden hidden=new Hidden();
				Date date=new Date();
				try{
                    if(hiddenLevelValue!=null)
                    	hidden.setHidden_level(hiddenLevelValue);
					if(hiddenName.getText()!=null)
						hidden.setName(hiddenName.getText());
					if(hiddenDetail.getText()!=null)
						hidden.setDetail(hiddenDetail.getText());
					  if(hiddenPrincipalValue!=null){
		                	hidden.setPrincipal(hiddenPrincipalValue);
		                }
					if(hiddenTypeValue!=null)
						hidden.setType(hiddenTypeValue);
					if(hiddenState.getText()!=null)
						hidden.setState(hiddenState.getText());

					if(hiddenRemark.getText()!=null)
						hidden.setRemark(hiddenRemark.getText());
					if(happenTime.getValue()!=null){
						LocalDate localDate=happenTime.getValue();
						Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
						Date date2 = Date.from(instant);
						hidden.setHappen_time(date2);
					}
					UUID uuid=UUID.randomUUID();
					hidden.setGUID(String.valueOf(uuid));
					hidden.setDate(date);
				
				Assets assets=new Connect().getAssets();
				
				int i=assets.insertIntoHidden(hidden);
				
				
							 				
				if(i==0){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框");
					alert.setHeaderText("错误");
					alert.setContentText("写入失败");
					alert.showAndWait();
				}else if(i==1){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("对话框");
					alert.setHeaderText("插入数据");
					alert.setContentText("写入成功");
					alert.showAndWait();
					handleCancel();
				 }
				}catch (Exception e) {
					// TODO: handle exception
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框");
					alert.setHeaderText("错误");
					alert.setContentText("写入失败");
					alert.showAndWait();
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
	
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
	
	@FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
