package com.asset.view.detail;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.asset.database.Connect;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden_Level;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HiddenLevelDetailController {
	@FXML
	private TextField hiddenlevel;
	@FXML
	private TextField levelText;
	@FXML
	private Button update;
	
	@FXML
	private Label rightTitleLabel;
	
	@FXML
	private VBox hiddenLevel1;
	
	@FXML
	private VBox levelText1;
	
	@FXML
	private VBox levelButton1;
	
	private Stage dialogStage;
	
	public void setHiddenLevel(VBox hiddenLevel1,VBox levelText1,VBox levelButton1) {
		this.hiddenLevel1=hiddenLevel1;
		this.levelText1=levelText1;
		this.levelButton1=levelButton1;
	}
	
	@FXML
	private void initialize() {
		 Assets assets=new Connect().getAssets();
		 
		 update.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Hidden_Level hidden_level=new Hidden_Level();
				
				if(hiddenlevel.getText()!=null)
					hidden_level.setHidden_level(Integer.parseInt(hiddenlevel.getText()));
				if(levelText.getText()!=null)
					hidden_level.setLevel_text(levelText.getText());
				
				int i=assets.insertHiddenLevel(hidden_level);
				
				if(i==1){
					setFlowPane(assets);
				}
			}
			 
		 });
		 
	 }
	
	 private void setFlowPane(Assets assets){
		 hiddenLevel1.getChildren().clear();
			levelText1.getChildren().clear();
			levelButton1.getChildren().clear();
			List<Hidden_Level> hidden_levels=assets.setctAllHiddenLevel();
		     
		     Iterator<Hidden_Level> iterator=hidden_levels.iterator();
		     
		     while(iterator.hasNext()){
		    	 Hidden_Level hidden_level2=iterator.next();
		    	 Label label=new Label();
		    	 label.setText(hidden_level2.getHidden_level().toString());
		    	 hiddenLevel1.getChildren().add(label);
		    	 Label label2=new Label();
		    	 label2.setText(hidden_level2.getLevel_text());
		    	 levelText1.getChildren().add(label2);
		    	 Insets insets2=new Insets(5, 5, 5, 5);
		    	 Button button=new Button();	     
			     button.setText("删除");
			     levelButton1.getChildren().add(button);
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
