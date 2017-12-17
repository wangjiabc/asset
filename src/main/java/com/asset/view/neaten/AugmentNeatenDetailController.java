package com.asset.view.neaten;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;

import org.apache.poi.ss.formula.functions.T;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.join.HiddenCheck_JoinProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.FileConvect;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Level;
import com.voucher.manage.daoModel.Assets.Hidden_Neaten;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.daoModel.Assets.Hidden_User;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Join;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Neaten_Join;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AugmentNeatenDetailController {
	@FXML
	 private Label rightTitleLabel;
	
	@FXML
	 private AnchorPane anchorPane;
	
	@FXML
	private TextField principal;
	
	@FXML
	private TextField checkName;
	
	@FXML
	private TextField remark;
	
	@FXML
	private DatePicker happenTime;//发生时间
	
	@FXML
	private TextArea checkCrics;
		 
	 @FXML
	 private Button post;
	 
	 @FXML
	 private Button cancel;
	 
	 private Stage stage;
	 
	 private File file;
	 
     private List<String> names=new ArrayList<String>();
	 
	 private List<byte[]> fileBytes=new ArrayList<byte[]>();
	 
	 	@FXML
		private FlowPane imagePane;
	 
	 	@FXML
		private FlowPane filePane;
	 	
		@FXML
		private Button switchImage;
		
		@FXML
		private Button switchDoc;
		
		@FXML
		private Button switchExcel;
		
		@FXML
		private Button switchPdf;
		
	 private Integer offset=10;
	 
	 private Integer limit=0;	 	
	 
	 private Map<String,String> searchMap=new HashMap<>();
	 
	 private Stage dialogStage;
	 
	 private Hidden_Join hidden_Join;
	 
	 private Hidden_Neaten_Join hidden_Neaten_Join;
	 
	 Assets assets= new Connect().get();
	
	public void setHiddenCheckInfo(Hidden_Join hidden_Join){
		
		this.hidden_Join=hidden_Join;
		
		searchMap.put("[Assets].[dbo].[Hidden_Neaten].GUID=", hidden_Join.getGUID());
		
		Map map=assets.selectAllHiddenNeaten(limit, offset, null, null, searchMap);
		  
	    List<Hidden_Neaten_Join> hidden_Neaten_Joins= (List<Hidden_Neaten_Join>) map.get("rows");

	    
	    
	}
	 
	@FXML
    private void initialize() {
	 
		 FileChooser fileChooser = new FileChooser();
		 
		 switchImage.setOnAction(new EventHandler<ActionEvent>() {

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
	                	VBox vBox=new VBox();
	                	vBox.setAlignment(Pos.CENTER);
	                	vBox.setMaxWidth(130);
	                	Image image=SwingFXUtils.toFXImage(bufImg, null);
	                	ImageView imageFile=new ImageView();
	                    imageFile.setImage(image);
	                    imageFile.setFitWidth(50);
	                    imageFile.setFitHeight(50);
	                    vBox.getChildren().add(imageFile);
	                    Label label=new Label();
	                    label.setText(file.getName());
	                    vBox.getChildren().add(label);
	                    vBox.setMargin(imageFile, new Insets(0, 10, 0, 10));
	                    vBox.setMargin(label, new Insets(0, 10, 0, 10));
	                    imagePane.getChildren().add(vBox);
	                    names.add(file.getName());
	                    byte[] fileByte=FileConvect.fileToByte(file);
	                    fileBytes.add(fileByte);
	                }
	                try{
	                	 String filePath=file.getPath();
						 prefs.put("lastPath", filePath.substring(0,filePath.lastIndexOf(File.separator)));
						}catch(Exception e1){
							
						}
				}
			});
			
			 
			 switchDoc.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						Preferences prefs = Preferences.userRoot().node(this.getClass().getName()); 
						String lastPath=prefs.get("lastPath", "");
						System.out.println("lastpath="+lastPath);
						if(!lastPath.equals(""))
						fileChooser.setInitialDirectory(new File(lastPath));
						fileChooser.getExtensionFilters().addAll(
							    new FileChooser.ExtensionFilter("DOC", "*.doc"),
							    new FileChooser.ExtensionFilter("DOCX", "*.docx"),
							    new FileChooser.ExtensionFilter("DOCM", "*.docm"),
							    new FileChooser.ExtensionFilter("DOTX", "*.dotx")
							);
						file = fileChooser.showOpenDialog(stage);
						fileChooser.getExtensionFilters().clear();
		                if (file != null) {
		                	BufferedImage bufImg = null;
		                	try {
		                		URL url = getClass().getResource("");	            	    	
		            	    	File image=new File(url.getPath()+"/word.jpg");
		            	    	System.out.println("image="+image);
		                        bufImg = ImageIO.read(image);
		                    } catch (IOException e) {
		                        e.printStackTrace();
		                    }
		                	VBox vBox=new VBox();
		                	vBox.setAlignment(Pos.CENTER);
		                	vBox.setMaxWidth(130);
		                	Image image=SwingFXUtils.toFXImage(bufImg, null);
		                	ImageView imageFile=new ImageView();
		                    imageFile.setImage(image);
		                    imageFile.setFitWidth(50);
		                    imageFile.setFitHeight(50);
		                    vBox.getChildren().add(imageFile);
		                    Label label=new Label();
		                    label.setText(file.getName());
		                    vBox.getChildren().add(label);
		                    vBox.setMargin(imageFile, new Insets(0, 10, 0, 10));
		                    vBox.setMargin(label, new Insets(0, 10, 0, 10));
		                    filePane.getChildren().add(vBox);
		                    names.add(file.getName());
		                    byte[] fileByte=FileConvect.fileToByte(file);
		                    fileBytes.add(fileByte);
		                }
		                try{
		                	 String filePath=file.getPath();
							 prefs.put("lastPath", filePath.substring(0,filePath.lastIndexOf(File.separator)));
							}catch(Exception e1){
								
							}
					}
				});
			 
			
			 switchExcel.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						Preferences prefs = Preferences.userRoot().node(this.getClass().getName()); 
						String lastPath=prefs.get("lastPath", "");
						System.out.println("lastpath="+lastPath);
						if(!lastPath.equals(""))
						fileChooser.setInitialDirectory(new File(lastPath));
						fileChooser.getExtensionFilters().addAll(
								new FileChooser.ExtensionFilter("XLS", "*.xls"),
								new FileChooser.ExtensionFilter("XLSX", "*.xlsx"),
								new FileChooser.ExtensionFilter("XLSM", "*.xlsm"),
							    new FileChooser.ExtensionFilter("XLTM", "*.xltm"),					    
							    new FileChooser.ExtensionFilter("XLSB", "*.xlsb")
							);
						file = fileChooser.showOpenDialog(stage);
						fileChooser.getExtensionFilters().clear();
		                if (file != null) {
		                	BufferedImage bufImg = null;
		                	try {
		                		URL url = getClass().getResource("");	            	    	
		            	    	File image=new File(url.getPath()+"/excel.jpg");
		            	    	System.out.println("image="+image);
		                        bufImg = ImageIO.read(image);
		                    } catch (IOException e) {
		                        e.printStackTrace();
		                    }
		                	VBox vBox=new VBox();
		                	vBox.setAlignment(Pos.CENTER);
		                	vBox.setMaxWidth(130);
		                	Image image=SwingFXUtils.toFXImage(bufImg, null);
		                	ImageView imageFile=new ImageView();
		                    imageFile.setImage(image);
		                    imageFile.setFitWidth(50);
		                    imageFile.setFitHeight(50);
		                    vBox.getChildren().add(imageFile);
		                    Label label=new Label();
		                    label.setText(file.getName());
		                    vBox.getChildren().add(label);
		                    vBox.setMargin(imageFile, new Insets(0, 10, 0, 10));
		                    vBox.setMargin(label, new Insets(0, 10, 0, 10));
		                    filePane.getChildren().add(vBox);
		                    names.add(file.getName());
		                    byte[] fileByte=FileConvect.fileToByte(file);
		                    fileBytes.add(fileByte);
		                }
		                try{
		                	 String filePath=file.getPath();
							 prefs.put("lastPath", filePath.substring(0,filePath.lastIndexOf(File.separator)));
							}catch(Exception e1){
								
							}
					}
				});
			 
			 switchPdf.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						Preferences prefs = Preferences.userRoot().node(this.getClass().getName()); 
						String lastPath=prefs.get("lastPath", "");
						System.out.println("lastpath="+lastPath);
						if(!lastPath.equals(""))
						fileChooser.setInitialDirectory(new File(lastPath));
						fileChooser.getExtensionFilters().addAll(
								new FileChooser.ExtensionFilter("PDF", "*.pdf")
							);
						file = fileChooser.showOpenDialog(stage);
						fileChooser.getExtensionFilters().clear();
		                if (file != null) {
		                	BufferedImage bufImg = null;
		                	try {
		                		URL url = getClass().getResource("");	            	    	
		            	    	File image=new File(url.getPath()+"/pdf.jpg");
		            	    	System.out.println("image="+image);
		                        bufImg = ImageIO.read(image);
		                    } catch (IOException e) {
		                        e.printStackTrace();
		                    }
		                	VBox vBox=new VBox();
		                	vBox.setAlignment(Pos.CENTER);
		                	vBox.setMaxWidth(130);
		                	Image image=SwingFXUtils.toFXImage(bufImg, null);
		                	ImageView imageFile=new ImageView();
		                    imageFile.setImage(image);
		                    imageFile.setFitWidth(50);
		                    imageFile.setFitHeight(50);
		                    vBox.getChildren().add(imageFile);
		                    Label label=new Label();
		                    label.setText(file.getName());
		                    vBox.getChildren().add(label);
		                    vBox.setMargin(imageFile, new Insets(0, 10, 0, 10));
		                    vBox.setMargin(label, new Insets(0, 10, 0, 10));
		                    filePane.getChildren().add(vBox);
		                    names.add(file.getName());
		                    byte[] fileByte=FileConvect.fileToByte(file);
		                    fileBytes.add(fileByte);
		                }
		                try{
		                	 String filePath=file.getPath();
							 prefs.put("lastPath", filePath.substring(0,filePath.lastIndexOf(File.separator)));
							}catch(Exception e1){
								
							}
					}
				});
		 
		post.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Hidden_Neaten hidden_Neaten=new Hidden_Neaten();
				Date date=new Date();
				try{
					    hidden_Neaten.setGUID(hidden_Join.getGUID());
					    if(principal.getText()!=null)
	                    	hidden_Neaten.setPrincipal(principal.getText());
						if(checkName.getText()!=null)
							hidden_Neaten.setNeaten_name(checkName.getText());
	
						if(happenTime.getValue()!=null){
							LocalDate localDate=happenTime.getValue();
							Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
							Date date2 = Date.from(instant);
							hidden_Neaten.setHappen_time(date2);
						}
						if(checkCrics.getText()!=null)
							hidden_Neaten.setNeaten_instance(checkCrics.getText());
												
						UUID uuid=UUID.randomUUID();
						hidden_Neaten.setNeaten_id(uuid.toString());
	                    Date date2=new Date();
	                    hidden_Neaten.setDate(date2);
	                    System.out.println("hidden_neaten=");
				MyTestUtil.print(hidden_Neaten);
				int i=assets.insertHiddenNeaten(hidden_Neaten);
				 				
				if(i==0){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框");
					alert.setHeaderText("错误");
					alert.setContentText("写入失败");
					alert.showAndWait();
				}else if(i==1){
					int j=assets.uploadImageFile(Hidden_Neaten.class,hidden_Neaten.getNeaten_id(), names, fileBytes);
					   if(j==0){
						 Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("异常堆栈对话框0");
							alert.setHeaderText("错误");
							alert.setContentText("上传图片失败");
							alert.showAndWait();
					  }
					
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
					e.printStackTrace();
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

