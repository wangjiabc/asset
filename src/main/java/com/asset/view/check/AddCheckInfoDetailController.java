package com.asset.view.check;

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
import com.asset.property.join.HiddenCheck_JoinProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.FileConvect;
import com.asset.tool.FileType;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Check;
import com.voucher.manage.daoModel.Assets.Hidden_Check_Date;
import com.voucher.manage.daoModel.Assets.Hidden_Level;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.daoModel.Assets.Hidden_User;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Check_Join;
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

public class AddCheckInfoDetailController {
	@FXML
	 private Label rightTitleLabel;
	
	@FXML
	 private AnchorPane anchorPane;
	
	private RoomInfo roomInfo;
	
	@FXML
	private Label address;
	
	@FXML
	private Label manageRegion;
	
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
		
		 private ObservableList<HiddenCheck_JoinProperty> hiddenCheckList;
		 
		 @FXML
		 private TableView<HiddenCheck_JoinProperty> hiddenCheckTable;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,Integer> C1;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,String> C2;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,String> C3;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,String> C4;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,String> C5;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,String> C6;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,String> C7;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,String> C8;
		
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,String> C9;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,String> C10;
		 
		 @FXML
		 private Pagination pagination;
		 
	 private Integer offset=10;
	 
	 private Integer limit=0;	 	
	 
	 private Map<String,String> searchMap=new HashMap<>();
	 
	 private Stage dialogStage;
	 
	 private List<Hidden_Check_Join> hidden_Checks;
	 
	//安全巡查的资产名称
	private String checkAddress;
	 
	Assets assets= new Connect().get();
	
	private final Desktop desktop = Desktop.getDesktop();
	 
	public void setHiddenCheckInfo(RoomInfo roomInfo){
       
	       address.setText(roomInfo.getAddress());
	    
	       manageRegion.setText(roomInfo.getManageRegion());
	       
	       this.roomInfo=roomInfo;
	       
	}
	
	 private void openFile(File file) {
	        EventQueue.invokeLater(() -> {
	            try {
	            	Desktop.getDesktop().open(file);
	            } catch (IOException ex) {
                 ex.printStackTrace();
	            }
	        });
	    }
	
	public void setTableView(TableView<HiddenCheck_JoinProperty> hiddenCheckTable,Integer offset,Integer limit,
			Map<String,String> searchMap,Pagination pagination,TableColumn<HiddenCheck_JoinProperty,Integer> C1,
			TableColumn<HiddenCheck_JoinProperty,String> C2,TableColumn<HiddenCheck_JoinProperty,String> C3,TableColumn<HiddenCheck_JoinProperty,String> C4,
			TableColumn<HiddenCheck_JoinProperty,String> C5,TableColumn<HiddenCheck_JoinProperty,String> C6,
			TableColumn<HiddenCheck_JoinProperty,String> C7,TableColumn<HiddenCheck_JoinProperty,String> C8,
			TableColumn<HiddenCheck_JoinProperty,String> C9,TableColumn<HiddenCheck_JoinProperty,String> C10){
		this.hiddenCheckTable=hiddenCheckTable;
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
		this.C9=C9;
		this.C10=C10;

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
				Hidden_Check hidden_Check=new Hidden_Check();
				Date date=new Date();
				try{
					
					if(Singleton.getInstance().getHidden_User().getPurview()>2){
						Alert alert2 = new Alert(AlertType.WARNING);
						alert2.setTitle("警告对话框");
						alert2.setHeaderText("警告");
						alert2.setContentText("你没有更新安全检查记录的的权限");
						alert2.showAndWait();
						return ;
					 }
					
					
					    if(principal.getText()!=null)
	                    	hidden_Check.setPrincipal(principal.getText());
						if(checkName.getText()!=null)
							hidden_Check.setCheck_name(checkName.getText());
	
						if(happenTime.getValue()!=null){
							LocalDate localDate=happenTime.getValue();
							Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
							Date date2 = Date.from(instant);
							hidden_Check.setHappen_time(date2);
						}
						if(checkCrics.getText()!=null)
							hidden_Check.setCheck_circs(checkCrics.getText());
						if(remark.getText()!=null)
							hidden_Check.setRemark(remark.getText());
	                    
						hidden_Check.setCampusAdmin(Singleton.getInstance().getHidden_User().getCampusAdmin());
						hidden_Check.setTerminal("PC");
						
						hidden_Check.setGUID(roomInfo.getGUID());
						UUID uuid=UUID.randomUUID();
						hidden_Check.setCheck_id(uuid.toString());
	                    Date date2=new Date();
	                    hidden_Check.setDate(date2);
	                    System.out.println("hidden_Check=");
                
	                    String[] where={"check_id=",String.valueOf(hidden_Check.getCheck_id())};
	                    hidden_Check.setWhere(where);
	                    
				int i=assets.insertHiddenCheck(hidden_Check);
				 				
				if(i==0){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框");
					alert.setHeaderText("错误");
					alert.setContentText("写入失败");
					alert.showAndWait();
				}else if(i==1){
					System.out.println("check_id="+hidden_Check.getCheck_id());
					if(names!=null&&fileBytes!=null){
						System.out.println("fileBytes="+fileBytes);
					  int j=assets.uploadImageFile(Hidden_Check_Date.class,hidden_Check.getCheck_id(), names, fileBytes);
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
					setHiddenCheckList(offset, limit, searchMap);
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
	
	
	 void setHiddenCheckList(Integer offset,Integer limit,Map search){

	      String sort="date";
	      String order="desc";
	      
		  Map map=new HashMap<>();
		  
		  	
		  map=assets.selectAllHiddenCheck(limit, offset, sort, order,checkAddress, search);
		  
	     hidden_Checks= (List<Hidden_Check_Join>) map.get("rows");
	     MyTestUtil.print(hidden_Checks);
	     
	     hiddenCheckList= (ObservableList<HiddenCheck_JoinProperty>) new RowData(hidden_Checks,HiddenCheck_JoinProperty.class).get();
	     
	     java.util.Iterator<HiddenCheck_JoinProperty> iterator=hiddenCheckList.iterator();
	    
	     while (iterator.hasNext()) {
			System.out.println("hiddenlist="+iterator.next().getDate());
		}
	     
	    hiddenCheckTable.setItems(hiddenCheckList);
     
	     C1.setCellValueFactory(
	                cellData -> cellData.getValue().getId().asObject());
	     C2.setCellValueFactory(
	   		    cellData->cellData.getValue().getAddress());
	     C3.setCellValueFactory(
	    		    cellData->cellData.getValue().getPrincipal());
	     C4.setCellValueFactory(
	    		    cellData->cellData.getValue().getCheck_name());
	     C5.setCellValueFactory(
	    		    cellData->cellData.getValue().getCheck_circs());
	     C6.setCellValueFactory(
	    		    cellData->cellData.getValue().getHappen_time());	
	     /*
	     C7.setCellValueFactory(
	    		    new Callback<TableColumn.CellDataFeatures<HiddenCheck_JoinProperty,ProgressBar>, ObservableValue<ProgressBar>>() {
						
						@Override
						public ObservableValue<ProgressBar> call(CellDataFeatures<HiddenCheck_JoinProperty, ProgressBar> param) {
							// TODO Auto-generated method stub
							DoubleProperty d=param.getValue().getProgress();
							Double dd=d.doubleValue();
							ProgressBar progressBar=new ProgressBar();
							progressBar.setProgress(dd);
							return new SimpleObjectProperty<ProgressBar>(progressBar);
						}
					});
		*/
	     
	     C7.setCellValueFactory(
	    		    cellData->cellData.getValue().getManageRegion());
	     
	      C8.setCellValueFactory(
	    		    cellData->cellData.getValue().getDate());
	     
	      C9.setCellValueFactory(
	    		    cellData->cellData.getValue().getUser_name());
	      	      
	      C10.setCellValueFactory(
	    		  	cellData->cellData.getValue().getDistrict());
	      
	     int total=(int) map.get("total");
	     int page=total/10;
	     
	     if(total-page*10>0)
         page++;	     
	    
         if(total>0){
	     pagination.setPageCount(page);
         }else {
        	 pagination.setPageCount(1);
		}
	     	     
	 }
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
}
