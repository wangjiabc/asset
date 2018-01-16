package com.asset.view.infowrite;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import java.util.Optional;
import java.util.UUID;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;

import org.apache.poi.ss.formula.functions.EDate;
import org.apache.poi.ss.formula.functions.T;

import com.asset.Singleton;
import com.asset.database.Connect;
import com.asset.model.roomInfoData;
import com.asset.propert.RowData;
import com.asset.property.HiddenProperty;
import com.asset.property.RoomInfo_PositionProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.FileConvect;
import com.asset.tool.FileType;
import com.asset.tool.MenuType;
import com.asset.tool.MyTestUtil;
import com.asset.view.AssetOverviewController;
import com.asset.view.check.AugmentCheckInfoDetailController;
import com.asset.view.hiddenAndAsset.AppendAssetsQueryController;
import com.asset.view.neaten.AugmentNeatenDetailController;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Assets;
import com.voucher.manage.daoModel.Assets.Hidden_Data;
import com.voucher.manage.daoModel.Assets.Hidden_Level;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.daoModel.Assets.Hidden_User;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Assets_Join;
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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class InfoWriteController2 {
	@FXML
	private TextField hiddenName;//隐患名称


	@FXML
	private Label note;//录入人
		
	@FXML
	private ChoiceBox<T> hiddenLevel;//隐患类别
	private List<Hidden_Level> hidden_Levels;
	private Integer hiddenLevelValue;
	
	@FXML
	private TextField hiddenRemark;//备注
	
	@FXML
	private ChoiceBox<T> hiddenType;//隐患类型
	private List<Hidden_Type> hidden_Types;
	private Integer hiddenTypeValue;
	
	@FXML
	private ChoiceBox<T> hiddenPrincipal;//负责人
	private List<Hidden_User> hidden_Principals;
	private Integer hiddenPrincipalValue;
	
	@FXML
	private ChoiceBox<T> hiddenManageRegion;
	private List<RoomInfo> roomInfoLists;
	private String manageRegion;
	
	@FXML
	private DatePicker happenTime;//发生时间
	
	@FXML
	private TextArea hiddenDetail;
	
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
	
	@FXML
	private Button update;
	
	@FXML
	private Button exit;
		
	private Hidden_Join hidden_Jion;
	
	private Stage dialogStage;
	
	private ObservableList<Hidden_JoinProperty> hiddenList;

	@FXML
	private TableView<Hidden_JoinProperty> hiddenTable;
	
	@FXML
	private Slider slider;
	
	@FXML
	private ProgressIndicator pi;
	
	private Double progress;
	
	private List<Hidden_Join> hidden_Jions;
	
	private Pagination pagination;
	
	private Integer offset;
	 
	 private Integer limit;
	
	 private Map<String,String> searchMap;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,Integer> C1;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C2;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C3;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C4;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C5;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,ProgressBar> C6;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C7;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C8;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C9;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C10;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C11;
	
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C12;
	 
	 private final Desktop desktop = Desktop.getDesktop();
	 
	 private Stage stage;
	 
	 private File file;
	 
	 private List<String> names=new ArrayList<String>();
	 
	 private List<byte[]> fileBytes=new ArrayList<byte[]>();
	 
	 /*
      * 查询条件菜单
      */
	 @FXML
	 private TextField keyWord;

	 //查询按钮
	 @FXML
	 private Button search;

	 @FXML
	 private Button addAssets;
	 
	 @FXML
	 private Pagination pagination0;
	 
	 private ObservableList<RoomInfo_PositionProperty> roomList;
	 
	 @FXML
	 private TableView<RoomInfo_PositionProperty> roomTable;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty,String> C01;
	 
	// @FXML
	// private TableColumn<RoomInfo_PositionProperty,String> C02;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty,String> C03;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty,String> C04;
	 
	 @FXML
	 private TableColumn<RoomInfo_PositionProperty,String> C05;
	 
	// @FXML
	// private TableColumn<RoomInfo_PositionProperty,Double> C06;
	 
	// @FXML
	// private TableColumn<RoomInfo_PositionProperty,Double> C07;
	 
	 
	 @FXML
	 private ContextMenu contextMenu;
	 	 
	 private List<Hidden_Assets_Join> roomInfos;
	 
	 private int i=0;
	 
	 private Integer offset0=0;
	 
	 private Integer limit0=10;
	 
	 private Map<String,String> searchMap0=new HashMap<>();
      
	 private Hidden hidden;
	 	
	 
	 Assets assets= new Connect().get();
	 
	 UUID uuid=UUID.randomUUID();
	 
	 public InfoWriteController2() {
		// TODO Auto-generated constructor stub
	 }
	
	 public void setTableView(TableView<Hidden_JoinProperty> hiddenTable,Integer offset,Integer limit,
			Map<String,String> searchMap,Pagination pagination,TableColumn<Hidden_JoinProperty,Integer> C1,
			TableColumn<Hidden_JoinProperty,String> C2,TableColumn<Hidden_JoinProperty,String> C3,TableColumn<Hidden_JoinProperty,String> C4,
			TableColumn<Hidden_JoinProperty,String> C5,TableColumn<Hidden_JoinProperty,ProgressBar> C6,
			TableColumn<Hidden_JoinProperty,String> C7,TableColumn<Hidden_JoinProperty,String> C8,
			TableColumn<Hidden_JoinProperty,String> C9,TableColumn<Hidden_JoinProperty,String> C10,
			TableColumn<Hidden_JoinProperty,String> C11,TableColumn<Hidden_JoinProperty,String> C12) {
		this.hiddenTable=hiddenTable;
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
		this.C11=C11;
		this.C12=C12;
	 }
	
	 @FXML
	 private void initialize() {		 
		 
		 Assets assets=new Connect().get();

		 pagination0.setPageCount(1);
		 
		 note.setText(Singleton.getInstance().getHidden_User().getCampusAdmin());
		 
		 searchMap0.put("[Hidden_Assets].hidden_GUID=", uuid.toString());
		 
		 List<Hidden_Level> hidden_levels=assets.setctAllHiddenLevel();
			Iterator<Hidden_Level> iterator=hidden_levels.iterator();
			List levels = new ArrayList<>();
			while (iterator.hasNext()) {
				levels.add(iterator.next().getLevel_text());
			}

			hiddenLevel.setItems(FXCollections.observableArrayList(levels));
		 
			 hidden_Types=assets.selectAllHiddenType();
			 Iterator<Hidden_Type> iterator3=hidden_Types.iterator();
			 List hidden_types=new ArrayList<>();	
			 while(iterator3.hasNext()){
				 hidden_types.add(iterator3.next().getHidden_type());
			 }
			 hiddenType.setItems(FXCollections.observableArrayList(hidden_types));
			 
			 hidden_Principals=(List) assets.selectAllHiddenUser(100, 0, null, null, new HashMap<>()).get("rows");
				
			 Iterator<Hidden_User> iterator4=hidden_Principals.iterator();
			 List hidden_principals=new ArrayList<>();
			 while(iterator4.hasNext()){
				 String principals_text=iterator4.next().getPrincipal_name();
				 hidden_principals.add(principals_text);
			 }
			 
			 hiddenPrincipal.setItems(FXCollections.observableArrayList(hidden_principals));
			
		 FileChooser fileChooser = new FileChooser();
		 
	     slider.setMin(0);  
	     slider.setMax(50);  
	     
	     slider.valueProperty().addListener(  
	          (ObservableValue<? extends Number> ov, Number old_val,   
	            Number new_val) -> {  
	                pi.setProgress(new_val.doubleValue()/50);  	
	                progress=new_val.doubleValue()/50;
	       });  
		 
	     
		 hiddenLevel.getSelectionModel().selectedIndexProperty().addListener(new
				 ChangeListener<Number>() {

					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue,
							Number newValue) {
						// TODO Auto-generated method stub
						int i=(int) newValue;
                        hiddenLevelValue=hidden_levels.get(i).getHidden_level();						
						System.out.println(hiddenLevelValue);
					}
			        
				});
		 
		 hiddenType.getSelectionModel().selectedIndexProperty().addListener(new 
				 ChangeListener<Number>() {
					
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						// TODO Auto-generated method stub
						int i=(int) newValue;
						hiddenTypeValue=hidden_Types.get(i).getType();
					}
				});
		 
		 hiddenPrincipal.getSelectionModel().selectedIndexProperty().addListener(new 
				 ChangeListener<Number>() {
					
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						// TODO Auto-generated method stub
						int i=(int) newValue;
						hiddenPrincipalValue=hidden_Principals.get(i).getPrincipal();
					}
				});
		 
		 
		 roomInfoLists=assets.selectManageRegion();
		 
		 Iterator<RoomInfo> iterator5=roomInfoLists.iterator();
		 
		 List manageRegions=new ArrayList<>();
		 
		 while (iterator5.hasNext()) {
			String manage=iterator5.next().getManageRegion();
			if(!manage.equals(""))
			  manageRegions.add(manage);
		 }
		 
		 hiddenManageRegion.setItems(FXCollections.observableArrayList(manageRegions));
		 
		 hiddenManageRegion.getSelectionModel().selectedIndexProperty().addListener(new 
				 ChangeListener<Number>() {
					
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						// TODO Auto-generated method stub
						int i=(int) newValue;
						manageRegion=(String) manageRegions.get(i);
						System.out.println("i="+i+"  "+manageRegion);
					}
				});
		 
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
		
		 
		 update.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Date date=new Date();
				Hidden hidden2=new Hidden();
				hidden2.setId(null);
				try{					
					if(hiddenName!=null){
					 hidden2.setName(hiddenName.getText());
					}
	                if(hiddenLevelValue!=null){
	                 hidden2.setHidden_level(hiddenLevelValue);
	                }
	                if(hiddenTypeValue!=null){
	                 hidden2.setType(hiddenTypeValue);
	                }
	                if(hiddenPrincipalValue!=null){
	                	hidden2.setPrincipal(hiddenPrincipalValue);
	                }
	                if(happenTime.getValue()!=null){
						LocalDate localDate=happenTime.getValue();
						Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
						Date date2 = Date.from(instant);
						hidden2.setHappen_time(date2);
					}

	                if(progress!=null){
	                	hidden2.setProgress(progress);
	                }
	                if(hiddenDetail!=null){
	                	hidden2.setDetail(hiddenDetail.getText());
	                }
	                if(hiddenRemark!=null){
	                	hidden2.setRemark(hiddenRemark.getText());
	                }
	                if(manageRegion!=null){
	                	hidden2.setManageRegion(manageRegion);
	                }
					hidden2.setDate(date);
	
			     
			    hidden2.setGUID(uuid.toString());
				System.out.println("campusadmin="+Singleton.getInstance().getHidden_User().getCampusAdmin());
			    hidden2.setCampusAdmin(Singleton.getInstance().getHidden_User().getCampusAdmin());
			    hidden2.setTerminal("PC");
			    
		     	int i=assets.insertIntoHidden(hidden2);
				
			    if(names!=null&&fileBytes!=null){
				   int j=assets.uploadImageFile(Hidden_Data.class,uuid.toString(), names, fileBytes);
				   if(j==0){
					 Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("异常堆栈对话框0");
						alert.setHeaderText("错误");
						alert.setContentText("上传图片失败");
						alert.showAndWait();
				  }
							 				
				 if(i==0){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框0");
					alert.setHeaderText("错误");
					alert.setContentText("写入失败");
					alert.showAndWait();
				}else if(i==1){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("对话框");
					alert.setHeaderText("更新数据");
					alert.setContentText("写入成功");
					alert.showAndWait();
					try{
						hiddenTable.setItems(null);
						setRoomInfoList(offset, limit,searchMap);
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					handleCancel();
				 }
	 
				}
			 }catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("异常堆栈对话框2");
					alert.setHeaderText("错误");
					alert.setContentText("写入失败");
					alert.showAndWait();
				}
			}		
		 });
		 
		 exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String[] where={"[Hidden_Assets].hidden_GUID =",uuid.toString()};
                Hidden_Assets hidden_Assets=new Hidden_Assets();
                hidden_Assets.setWhere(where);
                
                int i=assets.deleteHidden_Assets(hidden_Assets);
			    handleCancel();
			}
		});
		 
		 
		 addAssets.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				 try {
			            // Load the fxml file and create a new stage for the popup dialog.
			            FXMLLoader loader = new FXMLLoader();
			            loader.setLocation(getClass().getResource("hiddenAndAsset/AppendAssetsQuery.fxml"));
			            AnchorPane page = (AnchorPane) loader.load();
			            // Create the dialog Stage.
			            Stage dialogStage = new Stage();
			            dialogStage.setTitle("选择要添加此隐患的资产");
			            dialogStage.initModality(Modality.WINDOW_MODAL);
			            Scene scene = new Scene(page);
			            dialogStage.setScene(scene);

			            // Set the person into the controller.
			            AppendAssetsQueryController controller = loader.getController();
			            
			            Hidden hidden=new Hidden();
			            hidden.setGUID(uuid.toString());
			            
			            
			            controller.setHidden(hidden);
			            controller.setTableView(roomTable, offset, limit, searchMap0, pagination0, C01, null, C03, C04, C05);
			            
			            
			            controller.setDialogStage(dialogStage);
			            
			            // Show the dialog and wait until the user closes it
			            dialogStage.show();

			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			}
			
		});
		 
		 roomTable.setRowFactory( tv -> {
		        TableRow<RoomInfo_PositionProperty> row = new TableRow<>();
		        row.setOnMouseClicked(event -> {
		            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            	RoomInfo_PositionProperty rowData = row.getItem();

		            }
		        });
		      
		        row.setOnContextMenuRequested(event->{
		        	
	        	    contextMenu.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							// TODO Auto-generated method stub
							try{
								
							  String GUID=row.getItem().getGUID().get();
							  String Address=row.getItem().getAddress().get();
							  String menuType=MenuType.get(event.getTarget().toString());
							  System.out.println(menuType);
							  
							  
							  if(menuType.equals("m1")){
								  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
							        alert.setTitle("安全信息");
							        alert.setHeaderText("删除");
							        alert.setContentText("是否删除"+Address+"信息");

							        ButtonType btnType1 = new ButtonType("确定");
							        ButtonType btnType2 = new ButtonType("取消");
							     

							        alert.getButtonTypes().setAll(btnType1, btnType2);

							        Optional<ButtonType> result = alert.showAndWait();
							        result.ifPresent(buttonType -> {
							            if (buttonType == btnType1) {
							                try{
							                String[] where={"[Hidden_Assets].asset_GUID=",GUID};
				                            Hidden_Assets hidden_Assets=new Hidden_Assets();
				                            hidden_Assets.setWhere(where);
				                            
							                int i=assets.deleteHidden_Assets(hidden_Assets);
							                if(i==1){
							                	alert.setTitle("安全信息");
												alert.setHeaderText("操作");
												alert.setContentText("删除"+Address+"成功");
												alert.showAndWait();
												roomTable.setItems(null);
												Map searchMap2=new HashMap<>();
												searchMap2.put("[Hidden_Assets].hidden_GUID=", uuid.toString());
												setRoomInfoList0(offset, limit,searchMap2);
							                }else{
							                	Alert alert2 = new Alert(AlertType.ERROR);
												alert2.setTitle("异常堆栈对话框");
												alert2.setHeaderText("错误");
												alert2.setContentText("删除"+Address+"失败");
												alert2.showAndWait();
							                }
							                }catch (Exception e) {
												// TODO: handle exception
							                	Alert alert2 = new Alert(AlertType.ERROR);
												alert2.setTitle("异常堆栈对话框");
												alert2.setHeaderText("错误");
												alert2.setContentText("删除"+Address+"失败");
												alert2.showAndWait();
												e.printStackTrace();
											}
							            } else if (buttonType == btnType2) {
							            	System.out.println("点击了取消");
							            } 
							        });
							  }
							}catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
						}
	        	    });
		        });
		       
		      return row;
		   });
		 
		 
		 
		 
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
	    
	 public void setHidden(Hidden_Join hidden_Jion){
		 this.hidden_Jion=hidden_Jion;
		 System.out.println("guid="+hidden_Jion.getGUID());
		 hiddenName.setText(String.valueOf(hidden_Jion.getName()));

		 note.setText(hidden_Jion.getCampusAdmin());
		 
		 slider.setValue(hidden_Jion.getProgress()*50); 
	     pi.setProgress(hidden_Jion.getProgress());
		 hiddenDetail.setText(hidden_Jion.getDetail());
	     hidden_Levels=assets.setctAllHiddenLevel();		 
		 Integer level=null;
		 int i=0;
		 Iterator<Hidden_Level> iterator=hidden_Levels.iterator();
		 List levels = new ArrayList<>();
		 while (iterator.hasNext()) {
				String level_text=iterator.next().getLevel_text();
				levels.add(level_text);
				if(level_text.equals(hidden_Jion.getLevel_text())){
					level=i;
				}
				i++;
			}
		 hiddenLevel.setItems(FXCollections.observableArrayList(levels));
		 if(level!=null){
			  hiddenLevel.getSelectionModel().select(level);
		 }
		 
		 Integer type=null;
		 i=0;
		 hidden_Types=assets.selectAllHiddenType();
		 Iterator<Hidden_Type> iterator3=hidden_Types.iterator();
		 List hidden_types=new ArrayList<>();
		 while(iterator3.hasNext()){
			 String type_text=iterator3.next().getHidden_type();
			 hidden_types.add(type_text);
			 if(type_text.equals(hidden_Jion.getHidden_type())){
				 type=i;
			 }
			 i++;
		 }
		 hiddenType.setItems(FXCollections.observableArrayList(hidden_types));
		 if(type!=null){
			 hiddenType.getSelectionModel().select(type);
		 }

		 
		 Integer principal=null;
		 i=0;
		 hidden_Principals=(List) assets.selectAllHiddenUser(100, 0, null, null, new HashMap<>()).get("rows");
		 
		 Iterator<Hidden_User> iterator4=hidden_Principals.iterator();
		 List hidden_principals=new ArrayList<>();
		 while(iterator4.hasNext()){
			 String principals_text=iterator4.next().getPrincipal_name();
			 hidden_principals.add(principals_text);
			 if(principals_text.equals(hidden_Jion.getPrincipal_name())){
				 principal=i;
			 }
			 i++;
		 }
		 
		 hiddenPrincipal.setItems(FXCollections.observableArrayList(hidden_principals));
		 if(principal!=null){
			 hiddenPrincipal.getSelectionModel().select(principal);
		 }
		 
		 hiddenRemark.setText(String.valueOf(hidden_Jion.getRemark()));
		// hiddenPrincipal.setText(String.valueOf(hidden_Jion.getPrincipal()));
		 
         Map<String, Object> map=assets.selectAllHiddenDate(hidden_Jion.getGUID().toString());
		 
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
				String filePath=path+hidden_Jion.getGUID()+"\\"+fileName;
				File file = new File(filePath);
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
	 
	 private void openFile(File file) {
	        EventQueue.invokeLater(() -> {
	            try {
	                desktop.open(file);
	            } catch (IOException ex) {
                 ex.printStackTrace();
	            }
	        });
	    }
	 
	 void setRoomInfoList(Integer offset,Integer limit,Map search){

		 String sort="date";
	      String order="desc";
	     
		  Map map=new HashMap<>();
		  
		  
		//  map=assets.selectAllHidden(limit, offset, sort, order, search);

		//  map=assets.selectAllHidden_Jion(limit, offset, sort, order, search);
		  
		  map=assets.selectAllHidden_Jion(limit, offset, sort, order, search);
		  
	     hidden_Jions= (List<Hidden_Join>) map.get("rows");
	     
	     MyTestUtil.print(hidden_Jions);
	     
	     hiddenList= (ObservableList<Hidden_JoinProperty>) new RowData(hidden_Jions,Hidden_JoinProperty.class).get();
	     java.util.Iterator<Hidden_JoinProperty> iterator=hiddenList.iterator();
	    
	     while (iterator.hasNext()) {
			System.out.println("hiddenlist="+iterator.next().getDate());
		}
	     
	    hiddenTable.setItems(hiddenList);
       
	    C1.setCellValueFactory(
                cellData -> cellData.getValue().getId().asObject());
     C2.setCellValueFactory(
   		    cellData->cellData.getValue().getName());
     C3.setCellValueFactory(
    		    cellData->cellData.getValue().getLevel_text());
     C4.setCellValueFactory(
    		    cellData->cellData.getValue().getDetail());
     C5.setCellValueFactory(
    		    cellData->cellData.getValue().getHappen_time());
     C6.setCellValueFactory(
 		    new Callback<TableColumn.CellDataFeatures<Hidden_JoinProperty,ProgressBar>, ObservableValue<ProgressBar>>() {
					
					@Override
					public ObservableValue<ProgressBar> call(CellDataFeatures<Hidden_JoinProperty, ProgressBar> param) {
						// TODO Auto-generated method stub
						DoubleProperty d=param.getValue().getProgress();
						Double dd=d.doubleValue();
						ProgressBar progressBar=new ProgressBar();
						progressBar.setProgress(dd);
						return new SimpleObjectProperty<ProgressBar>(progressBar);
					}
				});
     C7.setCellValueFactory(
    		    cellData->cellData.getValue().getPrincipal_name());
     C8.setCellValueFactory(
    		 cellData->cellData.getValue().getHidden_type());
     C9.setCellValueFactory(
    		 cellData->cellData.getValue().getCampusAdmin());
     C10.setCellValueFactory(
    		 cellData->cellData.getValue().getRemark());
     
     C11.setCellValueFactory(
    		 cellData->cellData.getValue().getDate());
	
     C12.setCellValueFactory(
    		 cellData->cellData.getValue().getManageRegion());
     
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
	 
	 
	 void setRoomInfoList0(Integer offset,Integer limit,Map search){

		 String sort="date";
	      String order="desc";
	     
		  Map map=new HashMap<>();
		  
		  
		  map=assets.findAssetByHideen(limit, offset, sort, order, search);

	     roomInfos= (List<Hidden_Assets_Join>) map.get("rows");
	     
	     MyTestUtil.print(roomInfos);
	     
	     roomList= (ObservableList<RoomInfo_PositionProperty>) new RowData(roomInfos,RoomInfo_PositionProperty.class).get();
	     Iterator<RoomInfo_PositionProperty> iterator=roomList.iterator();
	    
	     while (iterator.hasNext()) {
			System.out.println("roominfos="+iterator.next().getAddress());
		}
	     
	    roomTable.setItems(roomList);

	     C01.setCellValueFactory(
	                cellData -> cellData.getValue().getAddress());
	 //    C02.setCellValueFactory(
	 //  		    cellData->cellData.getValue().getGUID());
	     C03.setCellValueFactory(
	    		    cellData->cellData.getValue().getRegion());
	     C04.setCellValueFactory(
	    		    cellData->cellData.getValue().getNum());
	     C05.setCellValueFactory(
	    		    cellData->cellData.getValue().getManageRegion());
	 //    C06.setCellValueFactory(
	 //   		 cellData->cellData.getValue().getLat().asObject());
	 //    C07.setCellValueFactory(
	 //   		 cellData->cellData.getValue().getLng().asObject());
	    
	     int total=(int) map.get("total");
	     int page=total/10;
	     
	     if(total-page*10>0)
          page++;	     
	     
	     if(total>0){
	     pagination0.setPageCount(page);
	     }else {
			pagination0.setPageCount(1);
		}
	 }
	 
}
