package com.asset.view;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.controlsfx.dialog.Dialogs;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.RoomInfoProperty;
import com.asset.tool.MyTestUtil;
import com.asset.view.assets.AssetsQueryController;
import com.asset.view.hidden.HiddenQueryController;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.RoomInfo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AssetOverviewController extends AssetAsSwitch{

	 @FXML
	 private Label rightTitleLabel;
 
	 @FXML
	 private Button button;
	 
	 public AssetOverviewController() {
		// TODO Auto-generated constructor stub
		 super();  
	 }
	 
	 @Override
	 protected void initCurrent() {
		 URL url = getClass().getResource("");
		 String filePath=url.toString()+"Image";
		 Image image = new Image(filePath+"/home.jpg");
	     homepage.setImage(image);
	     
	     rightTitleLabel.setText("主页");
	     
	     button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					 // Load the fxml file and create a new stage for the popup dialog.
		            FXMLLoader loader = new FXMLLoader();
		            loader.setLocation(AssetOverviewController.class.getResource("assets/AssetsQuery.fxml"));
		            AnchorPane page = (AnchorPane) loader.load();

		            // Create the dialog Stage.
		            Stage dialogStage = new Stage();
		            dialogStage.setTitle("隐患查询");
		            dialogStage.initModality(Modality.WINDOW_MODAL);
		            Scene scene = new Scene(page);
		            dialogStage.setScene(scene);

		            // Set the person into the controller.
		            AssetsQueryController controller = loader.getController();
		           // controller.setDialogStage(dialogStage);
		            
		            // Show the dialog and wait until the user closes it
		            dialogStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		  });
	     	     
	 }
	 
}
