package com.asset.view;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.asset.database.Connect;
import com.asset.view.detail.HiddenLevelDetailController;
import com.asset.view.hidden.HiddenDetailController;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Level;

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
	private Button button;
	
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
	     
	     rightTitleLabel.setText("工作动态");
	     
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
	     
	     button.setOnAction(new EventHandler<ActionEvent>() {

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
}
