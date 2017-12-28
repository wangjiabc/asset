package com.asset.view;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.awt.Desktop;

import javax.imageio.ImageIO;

import com.asset.database.Connect;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Data_Join;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class HiddenQueryController extends AssetAsSwitch{
	
	@FXML
	 private Label rightTitleLabel;
	
	@FXML
	 private FlowPane flowPane1;
	
	private final Desktop desktop = Desktop.getDesktop();
	
	Assets assets= new Connect().get();
	
	 public HiddenQueryController() {
		// TODO Auto-generated constructor stub
		 super();
	}
	 
	 @Override
	 protected void initCurrent() {
		 URL url = getClass().getResource("");
		 String filePath=url.toString()+"Image";
		 Image image = new Image(filePath+"/search.png");
	     homepage.setImage(image);
	     
	     rightTitleLabel.setText("隐患查询");
	     
	     Map<String, Object> map=assets.hiddenQuery(1);
	     
	     List<byte[]> fileBytes=(List<byte[]>) map.get("fileBytes");
		 List<String> names=(List<String>) map.get("names");
		 
		 Iterator<byte[]> iterator=fileBytes.iterator();
		 
		 int n=0;
		 
		 while (iterator.hasNext()) {
			 byte[] byt=iterator.next();
			 try {
					String fileName=names.get(n);
					String path="C:\\Users\\WangJing\\Desktop\\bb\\doc\\";
					File file = new File(path+fileName);
					if (!file.getParentFile().exists()) {  
				        boolean result = file.getParentFile().mkdirs();  
				        if (!result) {  
				            System.out.println("创建失败");  
				        }  
				    }  								
				    OutputStream output = new FileOutputStream(file);
				    BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
				    bufferedOutput.write(byt);
				    ByteArrayInputStream in;
				    BufferedImage bufImg = null;
				    
				    in = new ByteArrayInputStream(byt);    //将b作为输入流；
				    bufImg = ImageIO.read(in);     //将in作为输入流，读取图片存入image
				     
				    output.close();
				    
				    VBox vBox=new VBox();            	
					vBox.setAlignment(Pos.CENTER);
	            	vBox.setMaxWidth(130);
	            	vBox.setMinWidth(130);
	            	Image hiddenImage=SwingFXUtils.toFXImage(bufImg, null);
	            	ImageView imageFile=new ImageView();
	                imageFile.setImage(hiddenImage);
	                imageFile.setFitWidth(50);
	                imageFile.setFitHeight(50);
	                vBox.getChildren().add(imageFile);
	                Label label=new Label();
	                label.setText(file.getName());
	                vBox.getChildren().add(label);
	                vBox.setMargin(imageFile, new Insets(0, 10, 0, 10));
	                vBox.setMargin(label, new Insets(0, 10, 0, 10));  
	               
	                flowPane1.getChildren().add(vBox);
	                
	                imageFile.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	        			@Override
	        			public void handle(MouseEvent event) {
	        				// TODO Auto-generated method stub
	        				openFile(file);
	        			}
	        		});
	                
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				n++;
			 }

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
