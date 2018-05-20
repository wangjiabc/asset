package com.asset.view.hidden;

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
import java.util.Optional;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;

import org.apache.poi.ss.formula.functions.T;

import com.asset.Singleton;
import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenProperty;
import com.asset.property.RoomInfo_PositionProperty;
import com.asset.property.join.HiddenCheck_JoinProperty;
import com.asset.property.join.HiddenNeaten_JoinProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.FileConvect;
import com.asset.tool.FileType;
import com.asset.tool.MenuType;
import com.asset.tool.MyTestUtil;
import com.asset.view.AssetOverviewController;
import com.asset.view.check.SelectCheckInfoDetailController;
import com.asset.view.hiddenAndAsset.AppendAssetsQueryController;
import com.asset.view.neaten.AugmentNeatenDetailController;
import com.asset.view.neaten.NeatenDetailController;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Assets;
import com.voucher.manage.daoModel.Assets.Hidden_Check;
import com.voucher.manage.daoModel.Assets.Hidden_Data;
import com.voucher.manage.daoModel.Assets.Hidden_Level;
import com.voucher.manage.daoModel.Assets.Hidden_Neaten;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.daoModel.Assets.Hidden_User;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Assets_Join;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Check_Join;
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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
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

public class HiddenDetailController {
	
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
	
	private List manageRegions=new ArrayList<>();
	
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
	 
	 private Map<String,String> searchMap0;
	 
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
	 
	 @FXML
	 private TextField keyWord;
	 
	 /*
	  * 隐患关联资产
	  */
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
		 
	//	 @FXML
	//	 private TableColumn<RoomInfo_PositionProperty,String> C02;
		 
		 @FXML
		 private TableColumn<RoomInfo_PositionProperty,String> C03;
		 
		 @FXML
		 private TableColumn<RoomInfo_PositionProperty,String> C04;
		 
		 @FXML
		 private TableColumn<RoomInfo_PositionProperty,String> C05;
		 
	//	 @FXML
	//	 private TableColumn<RoomInfo_PositionProperty,Double> C06;
		 
	//	 @FXML
	//	 private TableColumn<RoomInfo_PositionProperty,Double> C07;
		 
		 
		 @FXML
		 private ContextMenu contextMenu;
		 	 
		 private List<Hidden_Assets_Join> roomInfos;
		 
		 private int i=0;
		 
		 private Integer offset0=0;
		 
		 private Integer limit0=10;
	 
		 
		 /*
		  * 检查记录
		  */
		/* 
		 @FXML
		 private Tab CheckTab;
		 
		 private ObservableList<HiddenCheck_JoinProperty> hiddenCheckList;
		 
		 @FXML
		 private TableView<HiddenCheck_JoinProperty> hiddenCheckTable;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,Integer> C21;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,String> C22;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,String> C23;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,String> C24;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,String> C25;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,String> C26;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,ProgressBar> C27;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,String> C28;
		 
		 @FXML
		 private TableColumn<HiddenCheck_JoinProperty,String> C29;
		 
		 @FXML
		 private Pagination pagination2;
		 
		 private List<Hidden> hidden;
		 private Integer hiddenValue;
		 
		 //查询按钮
		 @FXML
		 private Button search2;
		 
		 private Map<String,String> searchMap2;
		 
		 private List<Hidden_Check_Join> hidden_Checks;
		 
		 private Integer offset2=0;
		 
		 private Integer limit2=10;
		 
		 */
	/*
	 * 整改记录
	 */
	
		 @FXML
		 private Tab NeatenTab;
		 
		 private ObservableList<HiddenNeaten_JoinProperty> hiddenNeatenList;
		 
		 @FXML
		 private TableView<HiddenNeaten_JoinProperty> hiddenNeatenTable;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,Integer> C31;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,String> C32;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,String> C33;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,String> C34;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,String> C35;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,String> C36;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,ProgressBar> C37;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,String> C38;
		 
		 @FXML
		 private TableColumn<HiddenNeaten_JoinProperty,String> C39;
		 
		 @FXML
		 private Pagination pagination3;
		 
		 private Map<String,String> searchMap3=new HashMap<>();
		 
		 private List<Hidden_Neaten_Join> hidden_neatens;
		 
		 private Integer offset3=0;
		 
		 private Integer limit3=10;
		
		 @FXML
		 private ContextMenu contextMenuCheck;	 
		 
		 @FXML
		 private ContextMenu contextMenuNeaten;
		 
	 Assets assets= new Connect().get();
	 
	 public HiddenDetailController() {
		// TODO Auto-generated constructor stub
	 }
	
	 public void setTableView(TableView<Hidden_JoinProperty> hiddenTable,Integer offset,Integer limit,
			Map<String,String> searchMap,Map<String,String> searchMap0,Map<String,String> searchMap2,Map<String,String> searchMap3,
			Pagination pagination,TableColumn<Hidden_JoinProperty,Integer> C1,
			TableColumn<Hidden_JoinProperty,String> C2,TableColumn<Hidden_JoinProperty,String> C3,TableColumn<Hidden_JoinProperty,String> C4,
			TableColumn<Hidden_JoinProperty,String> C5,TableColumn<Hidden_JoinProperty,ProgressBar> C6,
			TableColumn<Hidden_JoinProperty,String> C7,TableColumn<Hidden_JoinProperty,String> C8,
			TableColumn<Hidden_JoinProperty,String> C9,TableColumn<Hidden_JoinProperty,String> C10,
			TableColumn<Hidden_JoinProperty,String> C11,TableColumn<Hidden_JoinProperty,String> C12) {
		this.hiddenTable=hiddenTable;		
		this.offset=offset;
		this.limit=limit;
		this.searchMap=searchMap;
		this.searchMap0=searchMap0;
	//	this.searchMap2=searchMap2;
		this.searchMap3=searchMap3;
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
                        hiddenLevelValue=hidden_Levels.get(i).getHidden_level();						
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
		 
		 hiddenManageRegion.getSelectionModel().selectedIndexProperty().addListener(new 
				 ChangeListener<Number>() {
					
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						// TODO Auto-generated method stub
						int i=(int) newValue;
						String manage=(String) manageRegions.get(i);
						manageRegion=manage;
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
				System.out.println(hidden_Jion);
				String[] where={"id=",String.valueOf(hidden_Jion.getId())};
				hidden2.setWhere(where);
				try{	
					
					if(Singleton.getInstance().getHidden_User().getPurview()>2){
						Alert alert2 = new Alert(AlertType.WARNING);
						alert2.setTitle("警告对话框");
						alert2.setHeaderText("警告");
						alert2.setContentText("你没有修改隐患的权限");
						alert2.showAndWait();
						return ;
					}
					
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
					hidden2.setUpdate_time(date);
	
		     	int i=assets.updateHidden(hidden2);
				
			    if(names!=null&&fileBytes!=null){
				   int j=assets.uploadImageFile(Hidden_Data.class,hidden_Jion.getGUID(), names, fileBytes);
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
						setRoomInfoList(searchMap);
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
		 
		 
		 addAssets.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					 try {
						 if(Singleton.getInstance().getHidden_User().getPurview()>2){
								Alert alert2 = new Alert(AlertType.WARNING);
								alert2.setTitle("警告对话框");
								alert2.setHeaderText("警告");
								alert2.setContentText("你没有添加关联资产的权限");
								alert2.showAndWait();
								return ;
							}
				            // Load the fxml file and create a new stage for the popup dialog.
				            FXMLLoader loader = new FXMLLoader();
				            loader.setLocation(getClass().getResource("hiddenAndAsset/AppendAssetsQuery.fxml"));
				            AnchorPane page = (AnchorPane) loader.load();
				            // Create the dialog Stage.
				            Stage dialogStage = new Stage();
				            dialogStage.setTitle("选择要添加此隐患的资产");
				            dialogStage.initModality(Modality.APPLICATION_MODAL);
				            Scene scene = new Scene(page);
				            dialogStage.setScene(scene);

				            // Set the person into the controller.
				            AppendAssetsQueryController controller = loader.getController();
				            
				            Hidden hidden=new Hidden();
				            hidden.setGUID(hidden_Jion.getGUID());
				            
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
			 
		 pagination0.setPageFactory((Integer pageIndex)->{
		    	if (pageIndex >= 0) {
		    		offset0=pageIndex*10;
		    		limit0=10;
		    		setRoomInfoList0(offset0, limit0, searchMap0);
		    		 Label mLabel = new Label();  
		                mLabel.setText("这是第" + (pageIndex+1) + "页");  
		                return mLabel;  
	            } else {
	                return null;
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
									  
									  if(Singleton.getInstance().getHidden_User().getPurview()>1){
											Alert alert2 = new Alert(AlertType.WARNING);
											alert2.setTitle("警告对话框");
											alert2.setHeaderText("警告");
											alert2.setContentText("你没有删除关联资产的权限");
											alert2.showAndWait();
											return ;
										}
									  
									  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
								        alert.setTitle("删除");
								        alert.setHeaderText("删除隐患关联资产");
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
								                	alert.setTitle("删除隐患关联资产");
													alert.setHeaderText("操作");
													alert.setContentText("删除"+Address+"成功");
													alert.showAndWait();
													roomTable.setItems(null);
													setRoomInfoList0(offset0, limit0,searchMap0);
								                }else{
								                	Alert alert2 = new Alert(AlertType.ERROR);
													alert2.setTitle("异常堆栈对话框1");
													alert2.setHeaderText("错误");
													alert2.setContentText("删除"+Address+"失败");
													alert2.showAndWait();
								                }
								                }catch (Exception e) {
													// TODO: handle exception
								                	e.printStackTrace();
								                	Alert alert2 = new Alert(AlertType.ERROR);
													alert2.setTitle("异常堆栈对话框2");
													alert2.setHeaderText("错误");
													alert2.setContentText("删除"+Address+"失败");
													alert2.showAndWait();													
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
		 
	     search.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				 String searchValue="%"+keyWord.getText()+"%";
				 searchMap0.put(Singleton.ROOMDATABASE+".[dbo].[RoomInfo].Address like ", searchValue);
				 setRoomInfoList0(offset0, limit0, searchMap0);
			}

		});
		

		 /*
		  * 检查记录
		  */
	  /*   CheckTab.setOnSelectionChanged(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				   pagination2.setPageFactory((Integer pageIndex)->{
				    	if (pageIndex >= 0) {
				    		offset2=pageIndex*10;
				    		limit2=10;
				    		System.out.println("pagination="+offset2+" ______"+limit2);
				    		setHiddenCheckList(offset2, limit2, searchMap2);
				    		 Label mLabel = new Label();  
				                mLabel.setText("这是第" + (pageIndex+1) + "页");  
				                return mLabel;  
			            } else {
			                return null;
			            }
				    });
			}
			
			
		});
	     

			 hiddenCheckTable.setRowFactory( tv -> {
			        TableRow<HiddenCheck_JoinProperty> row = new TableRow<>();
			        row.setOnMouseClicked(event -> {
			            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
			            	HiddenCheck_JoinProperty rowData = row.getItem();
			            	table(rowData);
			            }
			        });
			        
			        row.setOnContextMenuRequested(event->{
			        	
		        	    contextMenuCheck.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								try{
									
								  String check_id=row.getItem().getCheck_id().get();
								  String name=row.getItem().getCheck_name().get();
								  String menuType=MenuType.get(event.getTarget().toString());
								  System.out.println(menuType);								  
								  
								  if(menuType.equals("m1")){
									  if(Singleton.getInstance().getHidden_User().getPurview()>1){
											Alert alert2 = new Alert(AlertType.WARNING);
											alert2.setTitle("警告对话框");
											alert2.setHeaderText("警告");
											alert2.setContentText("你没有删除安全检查记录的权限");
											alert2.showAndWait();
											return ;
										}
									  
									  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
								        alert.setTitle("删除");
								        alert.setHeaderText("安全检查记录");
								        alert.setContentText("是否删除"+name+"信息");

								        ButtonType btnType1 = new ButtonType("确定");
								        ButtonType btnType2 = new ButtonType("取消");
								     

								        alert.getButtonTypes().setAll(btnType1, btnType2);

								        Optional<ButtonType> result = alert.showAndWait();
								        result.ifPresent(buttonType -> {
								            if (buttonType == btnType1) {
								                try{
								                String[] where={"[Hidden_Check].check_id=",check_id};
					                            Hidden_Check hidden_Check=new Hidden_Check();
					                            hidden_Check.setWhere(where);
					                            
								                int i=assets.deleteHiddenCheck(hidden_Check);
								                if(i==1){
								                	alert.setTitle("安全检查记录");
													alert.setHeaderText("操作");
													alert.setContentText("删除"+name+"成功");
													alert.showAndWait();
													roomTable.setItems(null);
													setHiddenCheckList(offset2, limit2,searchMap2);
								                }else{
								                	Alert alert2 = new Alert(AlertType.ERROR);
													alert2.setTitle("异常堆栈对话框1");
													alert2.setHeaderText("错误");
													alert2.setContentText("删除"+name+"失败");
													alert2.showAndWait();
								                }
								                }catch (Exception e) {
													// TODO: handle exception
								                	e.printStackTrace();
								                	Alert alert2 = new Alert(AlertType.ERROR);
													alert2.setTitle("异常堆栈对话框2");
													alert2.setHeaderText("错误");
													alert2.setContentText("删除"+name+"失败");
													alert2.showAndWait();													
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
			        
			        return row ;
			    });
	     
	     */
	     
	     /*
	      * 整改记录
	      */
	
		NeatenTab.setOnSelectionChanged(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				pagination3.setPageFactory((Integer pageIndex)->{
			    	if (pageIndex >= 0) {
			    		offset3=pageIndex*10;
			    		limit3=10;
			    		System.out.println("pagination3="+offset3+" ______"+limit3);
			    		setHiddenNeatenList(offset3, limit3, searchMap3);
			    		 Label mLabel = new Label();  
			                mLabel.setText("这是第" + (pageIndex+1) + "页");  
			                return mLabel;  
		            } else {
		                return null;
		            }
			    });		     
			}
						
		});
			 

			 hiddenNeatenTable.setRowFactory( tv -> {
				        TableRow<HiddenNeaten_JoinProperty> row = new TableRow<>();
				        row.setOnMouseClicked(event -> {
				            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
				            	HiddenNeaten_JoinProperty rowData = row.getItem();
				            	table2(rowData);
				            }
				        });
				        
				        row.setOnContextMenuRequested(event->{
				        	
			        	    contextMenuNeaten.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									// TODO Auto-generated method stub
									try{
										
									  String neaten_id=row.getItem().getNeaten_id().get();
									  String name=row.getItem().getNeaten_name().get();
									  String menuType=MenuType.get(event.getTarget().toString());
									  System.out.println(menuType);								  
									  
									  if(menuType.equals("m1")){
										  
											if(Singleton.getInstance().getHidden_User().getPurview()>1){
												Alert alert2 = new Alert(AlertType.WARNING);
												alert2.setTitle("警告对话框");
												alert2.setHeaderText("警告");
												alert2.setContentText("你没有删除隐患整改记录的权限");
												alert2.showAndWait();
												return ;
											}
										  
										  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
									        alert.setTitle("删除");
									        alert.setHeaderText("安全整改记录");
									        alert.setContentText("是否删除"+name+"信息");

									        ButtonType btnType1 = new ButtonType("确定");
									        ButtonType btnType2 = new ButtonType("取消");
									     

									        alert.getButtonTypes().setAll(btnType1, btnType2);

									        Optional<ButtonType> result = alert.showAndWait();
									        result.ifPresent(buttonType -> {
									            if (buttonType == btnType1) {
									                try{
									                String[] where={"[Hidden_Neaten].neaten_id=",neaten_id};
						                            Hidden_Neaten hidden_Neaten=new Hidden_Neaten();
						                            hidden_Neaten.setWhere(where);
						                            
									                int i=assets.deleteHiddenNeaten(hidden_Neaten);
									                if(i==1){
									                	alert.setTitle("安全整改记录");
														alert.setHeaderText("操作");
														alert.setContentText("删除"+name+"成功");
														alert.showAndWait();
														roomTable.setItems(null);
														setHiddenNeatenList(offset3, limit3,searchMap3);
									                }else{
									                	Alert alert2 = new Alert(AlertType.ERROR);
														alert2.setTitle("异常堆栈对话框1");
														alert2.setHeaderText("错误");
														alert2.setContentText("删除"+name+"失败");
														alert2.showAndWait();
									                }
									                }catch (Exception e) {
														// TODO: handle exception
									                	e.printStackTrace();
									                	Alert alert2 = new Alert(AlertType.ERROR);
														alert2.setTitle("异常堆栈对话框2");
														alert2.setHeaderText("错误");
														alert2.setContentText("删除"+name+"失败");
														alert2.showAndWait();													
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
				        
				        return row ;
				    });
			 
		 exit.setOnAction(new EventHandler<ActionEvent>() {

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

		 note.setText(hidden_Jion.getUser_name());

		 try{
		 Date date=hidden_Jion.getHappen_time();
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
	
		 List userList=(List)assets.selectAllHiddenUser(100, 0, null, null, new HashMap<>()).get("rows");
		 
		 hidden_Principals=userList;
		 
		 System.out.println("hidden_Principals=");
		 MyTestUtil.print(hidden_Principals);
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
		 
		 
		 Integer currentManageRegion=null;
		 
         roomInfoLists=assets.selectManageRegion();
		 
		 Iterator<RoomInfo> iterator5=roomInfoLists.iterator();
		 
		 i=0;

		 while (iterator5.hasNext()) {
			String manage=iterator5.next().getManageRegion();
			if(manage!=null&&!manage.equals("")){
			  manageRegions.add(manage);
			  try{
			   if(hidden_Jion.getManageRegion()!=null&&hidden_Jion.getManageRegion().equals(manage)){
				  currentManageRegion=i;
			    }
			   }catch (Exception e) {
				// TODO: handle exception
				  e.printStackTrace();
			   }
			  System.out.println("i="+i);
			  i++;
			}			
		 }
		 System.out.println("manageRegions=");
		 MyTestUtil.print(manageRegions);
		 hiddenManageRegion.setItems(FXCollections.observableArrayList(manageRegions));
		 if(currentManageRegion!=null){
			 hiddenManageRegion.getSelectionModel().select(currentManageRegion);
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
			        }else{
			        	System.out.println(filePath+"创建成功"); 
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
	 
	 void setRoomInfoList(Map search){

	      String sort="date";
	      String order="desc";
	     
		  Map map=new HashMap<>();
		  
		  
		//  map=assets.selectAllHidden(limit, offset, sort, order, search);

		//  map=assets.selectAllHidden_Jion(limit, offset, sort, order, search);
		  
		  
		  System.out.println("1offset= "+this.offset+"    limit= "+limit);
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

	      String sort=null;
	      String order=null;
	     
		  Map map=new HashMap<>();		  
          
		  map=assets.findAssetByHideen(limit, offset, sort, order, search);

	     roomInfos= (List<Hidden_Assets_Join>) map.get("rows");
	     
	     //MyTestUtil.print(roomInfos);
	     
	     roomList= (ObservableList<RoomInfo_PositionProperty>) new RowData(roomInfos,RoomInfo_PositionProperty.class).get();
	     Iterator<RoomInfo_PositionProperty> iterator=roomList.iterator();
	    
	     while (iterator.hasNext()) {
			System.out.println("roominfos="+iterator.next().getAddress());
		}
	     
	    roomTable.setItems(roomList);

	     C01.setCellValueFactory(
	                cellData -> cellData.getValue().getAddress());
	//     C02.setCellValueFactory(
	//   		    cellData->cellData.getValue().getGUID());
	     C03.setCellValueFactory(
	    		    cellData->cellData.getValue().getRegion());
	     C04.setCellValueFactory(
	    		    cellData->cellData.getValue().getNum());
	     C05.setCellValueFactory(
	    		    cellData->cellData.getValue().getManageRegion());
	 //    C06.setCellValueFactory(
	  //  		 cellData->cellData.getValue().getLat().asObject());
	  //   C07.setCellValueFactory(
	  //  		 cellData->cellData.getValue().getLng().asObject());
	    
	     int total=(int) map.get("total");
	     int page=total/10;
	     
	     if(total-page*10>0)
         page++;	     
	     System.out.println("total="+total);
	     if(total>0){
	    	 pagination0.setPageCount(page);
	     }else {
			pagination0.setPageCount(1);
		}
	 
	 }
	 

	 /*
	  * 检查记录
	  */
	 /*
	 private void table(HiddenCheck_JoinProperty newValue){
		 try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("check/SelectCheckInfoDetail.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle(newValue.getName().getName()+"安全检查记录");
	            dialogStage.initModality(Modality.APPLICATION_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            SelectCheckInfoDetailController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setTableView(hiddenCheckTable,offset2,limit2,searchMap2,pagination,C21, C22, C23, C24, C25, C26, C27, C28);
	            	     
	            System.out.println("check_id="+newValue.getCheck_id());
	            
	            Map searchMap02=new HashMap<>();
	            
	            searchMap02.put("[Hidden_Check].check_id=",newValue.getCheck_id().get());
	            
	            String sort="date";
	  	      String order="desc";
	            
	            Map map=assets.selectAllHiddenCheck(limit2, offset2, sort, order, searchMap02);
	            
	            List<Hidden_Check_Join> hidden_Check_Joins= (List<Hidden_Check_Join>) map.get("rows");
	            MyTestUtil.print(hidden_Check_Joins);
	            try{
	               Hidden_Check_Join hidden_Check_Join=hidden_Check_Joins.get(0);            
	               controller.setHiddenCheckInfo(hidden_Check_Join);
	            }catch (Exception e) {
					// TODO: handle exception
	            	e.printStackTrace();
				}
	            // Show the dialog and wait until the user closes it
	            dialogStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	 */
	/*
	 void setHiddenCheckList(Integer offset,Integer limit,Map search){

		 String sort="date";
	      String order="desc";
	     
		  Map map=new HashMap<>();
		  
		  	
		  map=assets.selectAllHiddenCheck(limit, offset, sort, order, search);
		  
	     hidden_Checks= (List<Hidden_Check_Join>) map.get("rows");
	     MyTestUtil.print(hidden_Checks);
	     
	     hiddenCheckList= (ObservableList<HiddenCheck_JoinProperty>) new RowData(hidden_Checks,HiddenCheck_JoinProperty.class).get();
	     
	     java.util.Iterator<HiddenCheck_JoinProperty> iterator=hiddenCheckList.iterator();
	    
	     while (iterator.hasNext()) {
			System.out.println("hiddenlist="+iterator.next().getDate());
		}
	     
	    hiddenCheckTable.setItems(hiddenCheckList);
   
	     C21.setCellValueFactory(
	                cellData -> cellData.getValue().getId().asObject());
	     C22.setCellValueFactory(
	   		    cellData->cellData.getValue().getName());
	     C23.setCellValueFactory(
	    		    cellData->cellData.getValue().getPrincipal());
	     C24.setCellValueFactory(
	    		    cellData->cellData.getValue().getCheck_name());
	     C25.setCellValueFactory(
	    		    cellData->cellData.getValue().getCheck_circs());
	     C26.setCellValueFactory(
	    		    cellData->cellData.getValue().getHappen_time());	
	     
	     C27.setCellValueFactory(
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
					
	      C28.setCellValueFactory(
	    		    cellData->cellData.getValue().getDate());
	     
	      C29.setCellValueFactory(
	    		    cellData->cellData.getValue().getCampusAdmin());
	      
	     int total=(int) map.get("total");
	     int page=total/10;
	     
	     if(total-page*10>0)
      page++;	     
	     System.out.println("page="+page);
	     if(total>0){
	     pagination2.setPageCount(page);
	     }else {
			pagination2.setPageCount(1);
		}
	     	     
	 }
	 */
	 
	 /*
	  * 整改记录
	  */
	 
	 private void table2(HiddenNeaten_JoinProperty newValue){
		 try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("neaten/NeatenDetail.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle(newValue.getName().getName()+"隐患整改记录");
	            dialogStage.initModality(Modality.APPLICATION_MODAL);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	                 
	           System.out.println("neaten_id="+newValue.getNeaten_id());
	           
	           Map searchMap03=new HashMap<>();
	           searchMap03.put("[Hidden_Neaten].neaten_id=",newValue.getNeaten_id().get());
	            
	           String sort="date";
	 	      String order="desc";
	           
	           Map map=assets.selectAllHiddenNeaten(limit3, offset3, sort, order , searchMap03);
	           
	           NeatenDetailController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	           controller.setTableView(hiddenNeatenTable,offset3,limit3,searchMap3,pagination3,C31, C32, C33, C34, C35, C36, C37, C38);
	            		           
	            List<Hidden_Neaten_Join> hidden_Neaten_Joins=  (List<Hidden_Neaten_Join>) map.get("rows");
	            MyTestUtil.print(hidden_Neaten_Joins);
	            try{
	               Hidden_Neaten_Join hidden_Check_Join=hidden_Neaten_Joins.get(0);            
	               controller.setHiddenNeaten(hidden_Check_Join);
	            }catch (Exception e) {
					// TODO: handle exception
	            	e.printStackTrace();
				}
	            // Show the dialog and wait until the user closes it
	            dialogStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	 
	
	 void setHiddenNeatenList(Integer offset,Integer limit,Map search){

		 String sort="date";
	      String order="desc";
	     
		  Map map=new HashMap<>();
		  
		  	
		  map=assets.selectAllHiddenNeaten(limit, offset, sort, order, search);
		  
	     hidden_neatens= (List<Hidden_Neaten_Join>) map.get("rows");
	     MyTestUtil.print(hidden_neatens);
	     
	     hiddenNeatenList= (ObservableList<HiddenNeaten_JoinProperty>) new RowData(hidden_neatens,HiddenNeaten_JoinProperty.class).get();
	     
	     Iterator<HiddenNeaten_JoinProperty> iterator=hiddenNeatenList.iterator();
	    
	     while (iterator.hasNext()) {
			System.out.println("hiddenlist="+iterator.next().getDate());
		}
	     
	    hiddenNeatenTable.setItems(hiddenNeatenList);
  
	     C31.setCellValueFactory(
	                cellData -> cellData.getValue().getId().asObject());
	     C32.setCellValueFactory(
	   		    cellData->cellData.getValue().getName());
	     C33.setCellValueFactory(
	    		    cellData->cellData.getValue().getPrincipal());
	     C34.setCellValueFactory(
	    		    cellData->cellData.getValue().getNeaten_name());
	     C35.setCellValueFactory(
	    		    cellData->cellData.getValue().getNeaten_instance());
	     C36.setCellValueFactory(
	    		    cellData->cellData.getValue().getHappen_time());	
	     
	     C37.setCellValueFactory(
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
					
	      C38.setCellValueFactory(
	    		    cellData->cellData.getValue().getDate());
	      
	      C39.setCellValueFactory(
	    		    cellData->cellData.getValue().getCampusAdmin());
	     
	     int total=(int) map.get("total");
	     int page=total/10;
	     
	     if(total-page*10>0)
     page++;	     
	     System.out.println("page="+page);
	     if(total>0){
	     pagination3.setPageCount(page);
	     }else {
			pagination3.setPageCount(1);
		}
	     	     
	 }
	 
}
