package com.asset.view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;

import com.asset.database.Connect;
import com.rmi.server.Assets;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AssetMessageController extends AssetAsSwitch{
	
	@FXML
	 private Label rightTitleLabel;
	
	@FXML
	 private AnchorPane anchorPane;
	
	 @FXML
	 private Button button;
	
	 @FXML
	 private Button button2;
	 
	 @FXML
	 private Button imageButton;
	 
	 @FXML
	 private Button uploadButton;
	 
	 @FXML
	 ImageView imageFile;
	 
	 private final Desktop desktop = Desktop.getDesktop();
	 private Stage stage;
	 private File file;
	 
	 public AssetMessageController() {
		// TODO Auto-generated constructor stub
		 super();	
		 stage=new Stage();
		 stage.setTitle("File Chooser Sample");
	}
	 
	 @Override
	 protected void initCurrent() {
		 URL url = getClass().getResource("");
		 String filePath=url.toString()+"Image";
		 Image image = new Image(filePath+"/message.jpg");
	     homepage.setImage(image);
	     
	     rightTitleLabel.setText("最新消息");
	     
	     Parent root;
	     
	     final FileChooser fileChooser = new FileChooser();
	     
		try {
			root = FXMLLoader.load(getClass().getResource("child/DeviceView.fxml"));
			anchorPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
		
		 button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
				    anchorPane.getChildren().clear();
				    anchorPane.getChildren().add(button);
				    anchorPane.getChildren().add(button2);
				}
			  });

		 button2.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
				    anchorPane.getChildren().clear();
				    anchorPane.getChildren().add(button);
				    anchorPane.getChildren().add(button2);
				    Parent root;
				    try {
						root = FXMLLoader.load(getClass().getResource("child/DeviceView.fxml"));
						anchorPane.getChildren().add(root);
				    } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				 }
			  });
		 
		imageButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Preferences prefs = Preferences.userRoot().node(this.getClass().getName()); 
				String lastPath=prefs.get("lastPath", "");
				System.out.println("lastpath="+lastPath);
				if(!lastPath.equals(""))
				fileChooser.setInitialDirectory(new File(lastPath));
				fileChooser.getExtensionFilters().addAll(
					    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
					    new FileChooser.ExtensionFilter("GIF", "*.gif"),
					    new FileChooser.ExtensionFilter("BMP", "*.bmp"),
					    new FileChooser.ExtensionFilter("PNG", "*.png"),
					    new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
					);
				file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                  //  openFile(file);
                	BufferedImage bufImg = null;
                	try {
                        bufImg = ImageIO.read(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                	Image image=SwingFXUtils.toFXImage(bufImg, null);
                    imageFile.setImage(image);
                }
                try{
                	 String filePath=file.getPath();
					 prefs.put("lastPath", filePath.substring(0,filePath.lastIndexOf(File.separator)));
					}catch(Exception e1){
						
					}
			}
		});
		
		uploadButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Assets assets=new Connect().getAssets();
				assets.uploadFile(file, Assets.type.IMAGE);
			}
		});
		
	 }
	 
	 
	 
	 private void openFile(File file) {
	        EventQueue.invokeLater(() -> {
	            try {
	                desktop.open(file);
	            } catch (IOException ex) {
                    ex.printStackTrace();
	            }
	        });
	    }
}
