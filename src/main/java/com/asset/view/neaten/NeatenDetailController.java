package com.asset.view.neaten;

import java.awt.Desktop;
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
import java.text.SimpleDateFormat;
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

import com.asset.Singleton;
import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.join.HiddenNeaten_JoinProperty;
import com.asset.property.join.HiddenNeaten_JoinProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.FileConvect;
import com.asset.tool.FileType;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Level;
import com.voucher.manage.daoModel.Assets.Hidden_Neaten;
import com.voucher.manage.daoModel.Assets.Hidden_Neaten_Date;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.daoModel.Assets.Hidden_User;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Neaten_Join;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Join;

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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class NeatenDetailController {
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
		
		 private ObservableList<HiddenNeaten_JoinProperty> hiddenNeatenList;
		 
		 @FXML
		 private TableView<HiddenNeaten_JoinProperty> hiddenNeatenTable;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,Integer> C1;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,String> C2;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,String> C3;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,String> C4;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,String> C5;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,String> C6;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,ProgressBar> C7;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,String> C8;
		
		 @FXML
		 private Pagination pagination;
		 
	 private Integer offset=10;
	 
	 private Integer limit=0;	 	
	 
	 private Map<String,String> searchMap=new HashMap<>();
	 
	 private Stage dialogStage;
	 
	 private Hidden_Join hidden_Join;
	 
	 private Hidden_Neaten_Join Hidden_Neaten_Join;
	 
	 private List<Hidden_Neaten_Join> hidden_Neatens;
	 
	 private List<Hidden_Neaten_Join> hidden_Neaten_Joins;
	 
	 Assets assets= new Connect().get();
	
	 private final Desktop desktop = Desktop.getDesktop();
	 
	public void setHiddenNeaten(Hidden_Neaten_Join Hidden_Neaten_Join){
       
	    if(Hidden_Neaten_Join!=null){
	    	this.Hidden_Neaten_Join=Hidden_Neaten_Join;
	    	principal.setText(Hidden_Neaten_Join.getPrincipal());
	    	checkName.setText(Hidden_Neaten_Join.getNeaten_name());
	    	checkCrics.setText(Hidden_Neaten_Join.getNeaten_instance());
	    	remark.setText(Hidden_Neaten_Join.getRemark());
	    	
	    	 try{
	    	 Date date=Hidden_Neaten_Join.getHappen_time();
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			 String yyyy=formatter.format(date);
			 formatter = new SimpleDateFormat("MM");
			 String mm=formatter.format(date);
			 formatter = new SimpleDateFormat("dd");
			 String dd=formatter.format(date);
			 happenTime.setValue(LocalDate.of(Integer.parseInt(yyyy), Integer.parseInt(mm), Integer.parseInt(dd)));
	    	 }catch (Exception e) {
	 			// TODO: handle exception
	 			 e.printStackTrace();
	 		}
	    	
	    	System.out.println("Hidden_Neaten_Join.getNeaten_id()="+Hidden_Neaten_Join.getNeaten_id());
	    	
	    	 Map<String, Object> map=assets.selectAllHiddenNeatenDate(Hidden_Neaten_Join.getNeaten_id());
	 		 MyTestUtil.print(map);
	 		 List<byte[]> fileBytes2=(List<byte[]>) map.get("fileBytes");
	 		 List<String> names=(List<String>) map.get("names");
	 		 List<String> types=(List<String>) map.get("types");
	 		 
	 		 Iterator<byte[]> iterator2=fileBytes2.iterator();
	 		 
	 		 int n=0;
	 		 while(iterator2.hasNext()){
	 			 byte[] byt=iterator2.next();
	 			try {
	 				String fileName=names.get(n);
	 				String fileType=types.get(n);
	 				String path=Singleton.getInstance().getPath();
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
	 			    
	 			    
	 			    System.out.println("fileType="+fileType);
	 			    if(FileType.testImage(fileType)){
	 			    	System.out.println(fileName);
	 				     in = new ByteArrayInputStream(byt);    //将b作为输入流；
	 				     bufImg = ImageIO.read(in);     //将in作为输入流，读取图片存入image
	 			    }else if(FileType.testDoc(fileType)){
	 			    	System.out.println(fileName);
	 			    	URL url = getClass().getResource("");	            	    	
	         	    	File image=new File(url.getPath()+"/word.jpg");
	                     bufImg = ImageIO.read(image);
	 			    }else if(FileType.testXls(fileType)){
	 			    	System.out.println(fileName);
	 			    	URL url = getClass().getResource("");	            	    	
	         	    	File image=new File(url.getPath()+"/excel.jpg");
	                     bufImg = ImageIO.read(image);
	 			    }else if(FileType.testPdf(fileType)){
	 			    	System.out.println(fileName);
	 			    	URL url = getClass().getResource("");	            	    	
	         	    	File image=new File(url.getPath()+"/pdf.jpg");
	                     bufImg = ImageIO.read(image);
	 			    }
	 			    
	 			    output.close();
	 			    
	 				VBox vBox=new VBox();            	
	 				vBox.setAlignment(Pos.CENTER);
	             	vBox.setMaxWidth(130);
	             	vBox.setMinWidth(130);
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

	                 if(FileType.testImage(fileType)){
	                      imagePane.getChildren().add(vBox);
	 			    }else if(FileType.testDoc(fileType)){
	                       filePane.getChildren().add(vBox);
	 			    }else if(FileType.testXls(fileType)){
	                       filePane.getChildren().add(vBox);
	 			    }else if(FileType.testPdf(fileType)){
	                       filePane.getChildren().add(vBox);
	 			    }
	                 
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
	
	public void setTableView(TableView<HiddenNeaten_JoinProperty> hiddenNeatenTable,Integer offset,Integer limit,
			Map<String,String> searchMap,Pagination pagination,TableColumn<HiddenNeaten_JoinProperty,Integer> C1,
			TableColumn<HiddenNeaten_JoinProperty,String> C2,TableColumn<HiddenNeaten_JoinProperty,String> C3,TableColumn<HiddenNeaten_JoinProperty,String> C4,
			TableColumn<HiddenNeaten_JoinProperty,String> C5,TableColumn<HiddenNeaten_JoinProperty,String> C6,
			TableColumn<HiddenNeaten_JoinProperty,ProgressBar> C7,TableColumn<HiddenNeaten_JoinProperty,String> C8){
		this.hiddenNeatenTable=hiddenNeatenTable;
		this.offset=offset;
		this.limit=limit;
		this.searchMap=searchMap;
		this.pagination=pagination;
		this.C1=C1;
		this.C2=C2;
		this.C3=C3;
		this.C4=C4;
		this.C5=C5;
		this.C6=C6;
		this.C7=C7;
		this.C8=C8;

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
						if(remark.getText()!=null){
							hidden_Neaten.setRemark(remark.getText());
						}
	                    Date date2=new Date();
	                    hidden_Neaten.setUpdate_time(date2);
                
	                    String[] where={"neaten_id=",String.valueOf(Hidden_Neaten_Join.getNeaten_id())};
	                    hidden_Neaten.setWhere(where);
	                    
				int i=assets.updateHiddenNeaten(hidden_Neaten);
				 				
				if(i==0){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框");
					alert.setHeaderText("错误");
					alert.setContentText("写入失败");
					alert.showAndWait();
				}else if(i==1){
					System.out.println("check_id="+Hidden_Neaten_Join.getNeaten_id());
					if(names!=null&&fileBytes!=null){
						System.out.println("fileBytes="+fileBytes);
					  int j=assets.uploadImageFile(Hidden_Neaten_Date.class,Hidden_Neaten_Join.getNeaten_id(), names, fileBytes);
					     if(j==0){
						    Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("异常堆栈对话框0");
							alert.setHeaderText("错误");
							alert.setContentText("上传图片失败");
							alert.showAndWait();
					      }
					}
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("对话框");
					alert.setHeaderText("插入数据");
					alert.setContentText("写入成功");
					alert.showAndWait();
					setHiddenNeaten(offset, limit, searchMap);
					System.out.println("offset="+offset+"     limit="+limit);
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
	
	
	void setHiddenNeaten(Integer offset,Integer limit,Map search){
	      String sort=null;
	      String order=null;
	      
		  Map map=new HashMap<>();
		  	
		  map=assets.selectAllHiddenNeaten(limit, offset, sort, order, search);
		  
	     hidden_Neaten_Joins=  (List<Hidden_Neaten_Join>) map.get("rows");
	     MyTestUtil.print( hidden_Neaten_Joins);
	     
	     hiddenNeatenList= (ObservableList<HiddenNeaten_JoinProperty>) new RowData(hidden_Neaten_Joins,HiddenNeaten_JoinProperty.class).get();
	     

	    hiddenNeatenTable.setItems(hiddenNeatenList);
     
	     C1.setCellValueFactory(
	                cellData -> cellData.getValue().getId().asObject());
	     C2.setCellValueFactory(
	   		    cellData->cellData.getValue().getName());
	     C3.setCellValueFactory(
	    		    cellData->cellData.getValue().getPrincipal());
	     C4.setCellValueFactory(
	    		    cellData->cellData.getValue().getNeaten_name());
	     C5.setCellValueFactory(
	    		    cellData->cellData.getValue().getNeaten_instance());
	     C6.setCellValueFactory(
	    		    cellData->cellData.getValue().getHappen_time());	
	     
	     C7.setCellValueFactory(
	    		    new Callback<TableColumn.CellDataFeatures<HiddenNeaten_JoinProperty,ProgressBar>, ObservableValue<ProgressBar>>() {
						
						@Override
						public ObservableValue<ProgressBar> call(CellDataFeatures<HiddenNeaten_JoinProperty, ProgressBar> param) {
							// TODO Auto-generated method stub
							DoubleProperty d=param.getValue().getProgress();
							Double dd=d.doubleValue();
							ProgressBar progressBar=new ProgressBar();
							progressBar.setProgress(dd);
							return new SimpleObjectProperty<ProgressBar>(progressBar);
						}
					});
					
	      C8.setCellValueFactory(
	    		    cellData->cellData.getValue().getDate());
	     
	     int total=(int) map.get("total");
	     int page=total/10;
	     
	     if(total-page*10>0)
        page++;	     
	     System.out.println("page="+page);
	     pagination.setPageCount(page);
	     	     
	 }

	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
}

