package com.asset.view.detail;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.asset.database.Connect;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden_Type;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HiddenTypeDetailController {

	@FXML
	private TextField hiddenType;
	
	@FXML
	private Button update;
	
	@FXML
	private VBox hiddenType1;
		
	@FXML
	private VBox hiddenTypeButton;
	
	private Stage dialogStage;
	
	public void setHiddenType(VBox hiddenType1,VBox hiddenTypeButton){
		this.hiddenType1=hiddenType1;
		this.hiddenTypeButton=hiddenTypeButton;
	}
	
	@FXML
	private void initialize() {
		Assets assets=new Connect().getAssets();
		 
		 update.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Hidden_Type hidden_Type=new Hidden_Type();
				
				if(hidden_Type!=null){
					hidden_Type.setHidden_type(hiddenType.getText());
					Random rand = new Random();
					int r=Math.abs(rand.nextInt());
					hidden_Type.setType(r);
					
					int i=assets.insertHiddenType(hidden_Type);
					
					if(i==1){
						setFlowPane2(assets);
					}
				}
			}
			 
		 });
	}
	
	
	private void setFlowPane2(Assets assets){
		   hiddenType1.getChildren().clear();
		   hiddenTypeButton.getChildren().clear();
		   List<Hidden_Type> hidden_Types=assets.selectAllHiddenType();
		     
		     Iterator<Hidden_Type> iterator=hidden_Types.iterator();
		     
		     while(iterator.hasNext()){
		    	 Hidden_Type hidden_Type=iterator.next();
		    	 Label label=new Label();
		    	 label.setText(hidden_Type.getHidden_type());
		    	 hiddenType1.getChildren().add(label);
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

	public void setDialogStage(Stage dialogStage) {
		// TODO Auto-generated method stub
		this.dialogStage=dialogStage;
	}
	
	
}
