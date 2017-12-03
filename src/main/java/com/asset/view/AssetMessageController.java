package com.asset.view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;

import com.asset.database.Connect;
import com.asset.tool.FileConvect;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
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
	 private Button docButton;
	 
	 @FXML
	 private Button upload2Button;
	 
	 @FXML
	 ImageView imageFile;
	 
	 @FXML
	 Label docLabel;
	 
	 private final Desktop desktop = Desktop.getDesktop();
	 private Stage stage;
	 private File file;
	 private File docFile;
	 
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
	     
	     FileChooser fileChooser = new FileChooser();
     
		
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
						new FileChooser.ExtensionFilter("PNG", "*.png"),
						new FileChooser.ExtensionFilter("GIF", "*.gif"),
						new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
					    new FileChooser.ExtensionFilter("JPG", "*.jpg"),					    
					    new FileChooser.ExtensionFilter("BMP", "*.bmp")
					);
				file = fileChooser.showOpenDialog(stage);
				fileChooser.getExtensionFilters().clear();
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
				byte[] fileByte=FileConvect.fileToByte(file);
				List<String> names=new ArrayList<>();
				List<byte[]> fileBytes=new ArrayList<byte[]>();
				names.add("aaa");
				fileBytes.add(fileByte);
				assets.uploadImageFile("aaaaaa", names, fileBytes);
			}
		});
	
		docButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Preferences prefs = Preferences.userRoot().node(this.getClass().getName()); 
				String lastPath=prefs.get("lastPath", "");
				System.out.println("lastpath="+lastPath);
				if(!lastPath.equals(""))
				fileChooser.setInitialDirectory(new File(lastPath));
				fileChooser.getExtensionFilters().addAll(
					    new FileChooser.ExtensionFilter("DOC", "*.doc")
					);
				docFile = fileChooser.showOpenDialog(stage);
				fileChooser.getExtensionFilters().clear();
                if (docFile != null) {
                  //  openFile(file);
                   docLabel.setText(docFile.getName());
                }
                try{
                	 String filePath=file.getPath();
					 prefs.put("lastPath", filePath.substring(0,filePath.lastIndexOf(File.separator)));
					}catch(Exception e1){
						
					}
			}
		});
		
		upload2Button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Assets assets=new Connect().getAssets();
				byte[] fileByte=FileConvect.fileToByte(docFile);
				List<String> names=new ArrayList<>();
				List<byte[]> fileBytes=new ArrayList<byte[]>();
				names.add(docFile.getName());
				fileBytes.add(fileByte);
				assets.uploadDocFile("bbbb", names, fileBytes);
			}
		});
		
		imageFile.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				openFile(file);
			}
		});
		
		docLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				openFile(docFile);
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
