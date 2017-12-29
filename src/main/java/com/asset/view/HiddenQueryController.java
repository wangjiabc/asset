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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.awt.Desktop;

import javax.imageio.ImageIO;

import com.asset.database.Connect;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.MyTestUtil;
import com.asset.view.hidden.HiddenDetailController;
import com.rmi.server.Assets;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Data_Join;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Join;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HiddenQueryController extends AssetAsSwitch{
	
	@FXML
	 private Label rightTitleLabel;
	
	@FXML
	private FlowPane flowPane1;
	
	@FXML
	private FlowPane flowPane2;
	
	@FXML
	private FlowPane flowPane3;
	
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
	     List<String> GUIDs=(List<String>) map.get("GUIDs");
		 List<String> names=(List<String>) map.get("names");
		 List<String> details=(List<String>) map.get("details");
		 List<Double> progress=(List<Double>) map.get("progress");
		 List<Date> dates=(List<Date>) map.get("dates");
		 
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
	            	vBox.setMaxWidth(200);
	            	vBox.setMinWidth(200);
	            	Image hiddenImage=SwingFXUtils.toFXImage(bufImg, null);
	            	ImageView imageFile=new ImageView();
	                imageFile.setImage(hiddenImage);
	                imageFile.setFitWidth(150);
	                imageFile.setFitHeight(150);
	                vBox.getChildren().add(imageFile);
	                Label label=new Label();
	                label.setText(file.getName());
	                vBox.getChildren().add(label);
	                Label label2=new Label();
	                Double progres=progress.get(n);
	                if(progres==0){
	                	label2.setText("(未整改)");
	                	label2.setTextFill(Color.RED);
	                }else if(progres>0&&progres<1){
	                	label2.setText("(整改中)");
	                	label2.setTextFill(Color.BLUE);
	                }else {
	                	label2.setText("(已完成)");
					}
	                vBox.getChildren().add(label2);
	                Label label3=new Label();
	                label3.setText(details.get(n));
	                vBox.getChildren().add(label3);
	                Label label4=new Label();
	                SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");	        		
	        		Date begin=new Date();	        		
	        		int between=0;	        		
	        		try {
	        			Date endDate=df.parse(String.valueOf(dates.get(n)));
	        			between=(int) ((begin.getTime()-endDate.getTime())/(1000*3600*24));
	        		} catch (ParseException e) {
	        			// TODO Auto-generated catch block
	        			e.printStackTrace();
	        		}
	        		label4.setText("录入时间 : "+between+"天前");
	        		vBox.getChildren().add(label4);
	                vBox.setMargin(imageFile, new Insets(0, 10, 0, 10));
	                vBox.setMargin(label, new Insets(0, 10, 0, 10));  
	               
	                flowPane1.getChildren().add(vBox);
	                
	                String GUID=GUIDs.get(n);
	                
	                imageFile.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	        			@Override
	        			public void handle(MouseEvent event) {
	        				// TODO Auto-generated method stub
	        				table(GUID);
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

		 
		 Map<String, Object> map2=assets.hiddenQuery(2);
	     
	     List<byte[]> fileBytes2=(List<byte[]>) map2.get("fileBytes");
	     List<String> GUIDs2=(List<String>) map2.get("GUIDs");
		 List<String> names2=(List<String>) map2.get("names");
		 List<String> details2=(List<String>) map2.get("details");
		 List<Double> progress2=(List<Double>) map2.get("progress");
		 List<Date> dates2=(List<Date>) map2.get("dates");
		 
		 Iterator<byte[]> iterator2=fileBytes2.iterator();
		 
		 n=0;
		 
		 while (iterator2.hasNext()) {
			 byte[] byt=iterator2.next();
			 try {
					String fileName=names2.get(n);
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
	            	vBox.setMaxWidth(200);
	            	vBox.setMinWidth(200);
	            	Image hiddenImage=SwingFXUtils.toFXImage(bufImg, null);
	            	ImageView imageFile=new ImageView();
	                imageFile.setImage(hiddenImage);
	                imageFile.setFitWidth(150);
	                imageFile.setFitHeight(150);
	                vBox.getChildren().add(imageFile);
	                Label label=new Label();
	                label.setText(file.getName());
	                vBox.getChildren().add(label);
	                Label label2=new Label();
	                Double progres=progress2.get(n);
	                if(progres==0){
	                	label2.setText("(未整改)");
	                	label2.setTextFill(Color.RED);
	                }else if(progres>0&&progres<1){
	                	label2.setText("(整改中)");
	                	label2.setTextFill(Color.BLUE);
	                }else {
	                	label2.setText("(已完成)");
					}
	                vBox.getChildren().add(label2);
	                Label label3=new Label();
	                label3.setText(details2.get(n));
	                vBox.getChildren().add(label3);
	                Label label4=new Label();
	                SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");	        		
	        		Date begin=new Date();	        		
	        		int between=0;	        		
	        		try {
	        			Date endDate=df.parse(String.valueOf(dates2.get(n)));
	        			between=(int) ((begin.getTime()-endDate.getTime())/(1000*3600*24));
	        		} catch (ParseException e) {
	        			// TODO Auto-generated catch block
	        			e.printStackTrace();
	        		}
	        		label4.setText("录入时间 : "+between+"天前");
	        		vBox.getChildren().add(label4);
	                vBox.setMargin(imageFile, new Insets(0, 10, 0, 10));
	                vBox.setMargin(label, new Insets(0, 10, 0, 10));  
	               
	                flowPane2.getChildren().add(vBox);
	                
	                String GUID=GUIDs2.get(n);
	                
	                imageFile.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	        			@Override
	        			public void handle(MouseEvent event) {
	        				// TODO Auto-generated method stub
	        				table(GUID);
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
		 
		 
		 Map<String, Object> map3=assets.hiddenQuery(3);
	     
	     List<byte[]> fileBytes3=(List<byte[]>) map3.get("fileBytes");
	     List<String> GUIDs3=(List<String>) map3.get("GUIDs");
		 List<String> names3=(List<String>) map3.get("names");
		 List<String> details3=(List<String>) map3.get("details");
		 List<Double> progress3=(List<Double>) map3.get("progress");
		 List<Date> dates3=(List<Date>) map3.get("dates");
		 
		 Iterator<byte[]> iterator3=fileBytes3.iterator();
		 
		 n=0;
		 
		 while (iterator3.hasNext()) {
			 byte[] byt=iterator3.next();
			 try {
					String fileName=names3.get(n);
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
	            	vBox.setMaxWidth(200);
	            	vBox.setMinWidth(200);
	            	Image hiddenImage=SwingFXUtils.toFXImage(bufImg, null);
	            	ImageView imageFile=new ImageView();
	                imageFile.setImage(hiddenImage);
	                imageFile.setFitWidth(150);
	                imageFile.setFitHeight(150);
	                vBox.getChildren().add(imageFile);
	                Label label=new Label();
	                label.setText(file.getName());
	                vBox.getChildren().add(label);
	                Label label2=new Label();
	                Double progres=progress3.get(n);
	                if(progres==0){
	                	label2.setText("(未整改)");
	                	label2.setTextFill(Color.RED);
	                }else if(progres>0&&progres<1){
	                	label2.setText("(整改中)");
	                	label2.setTextFill(Color.BLUE);
	                }else {
	                	label2.setText("(已完成)");
					}
	                vBox.getChildren().add(label2);
	                Label label3=new Label();
	                label3.setText(details3.get(n));
	                vBox.getChildren().add(label3);
	                Label label4=new Label();
	                SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");	        		
	        		Date begin=new Date();	        		
	        		int between=0;	        		
	        		try {
	        			Date endDate=df.parse(String.valueOf(dates3.get(n)));
	        			between=(int) ((begin.getTime()-endDate.getTime())/(1000*3600*24));
	        		} catch (ParseException e) {
	        			// TODO Auto-generated catch block
	        			e.printStackTrace();
	        		}
	        		label4.setText("录入时间 : "+between+"天前");
	        		vBox.getChildren().add(label4);
	                vBox.setMargin(imageFile, new Insets(0, 10, 0, 10));
	                vBox.setMargin(label, new Insets(0, 10, 0, 10));  
	               
	                flowPane3.getChildren().add(vBox);
	                
	                String GUID=GUIDs3.get(n);
	                
	                imageFile.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	        			@Override
	        			public void handle(MouseEvent event) {
	        				// TODO Auto-generated method stub
	        				table(GUID);
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
		 
		 
		  MyTestUtil.print(map);
		  MyTestUtil.print(map2);
		  MyTestUtil.print(map3);
		}
	   
	 
	 private void table(String GUID){
		 try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(AssetOverviewController.class.getResource("hidden/HiddenDetail.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("隐患");
	            dialogStage.initModality(Modality.APPLICATION_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            HiddenDetailController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            
	            Map searchMap=new HashMap<>();
	            searchMap.put("[Assets].[dbo].[Hidden].GUID=", GUID);
	            Map searchMap0=new HashMap<>();	  		  
	  		    searchMap0.put("[Assets].[dbo].[Hidden_Assets].hidden_GUID=", GUID);
	  		    Map searchMap2=new HashMap<>();
	  		    searchMap2.put("[Assets].[dbo].[Hidden_Check].GUID=", GUID);
	  		    Map searchMap3=new HashMap<>();
	  		    searchMap3.put("[Assets].[dbo].[Hidden_Neaten].GUID=", GUID);
	  		    
	            controller.setTableView(null,null,null,searchMap,searchMap0,searchMap2,searchMap3,null,null, null, null, null, null, null, null, null,null,null,null);
	            
	           // Map map=assets.selectAllHidden(limit, offset, null, null, searchMap);

	            Map map=assets.selectAllHidden_Jion(10,0, null, null, searchMap);
	            
	            List<Hidden_Join> hidden_Jions= (List<Hidden_Join>) map.get("rows");
	            
	            Iterator<Hidden_Join> iterator=hidden_Jions.iterator();
	            
	            Hidden_Join hidden_Jion=null;
	            
	            while(iterator.hasNext()){
	            	Hidden_Join h=iterator.next();
	            	try{
	            	 if(GUID.equals(h.getGUID())){
	            		hidden_Jion=h;
	    	            controller.setHidden(hidden_Jion);
	            		break;
	            	  }
	            	}catch (NullPointerException e) {
						// TODO: handle exception
					}
	            }
	            


	            // Show the dialog and wait until the user closes it
	            dialogStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	 
}
