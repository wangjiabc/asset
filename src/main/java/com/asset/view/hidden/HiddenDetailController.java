package com.asset.view.hidden;

import java.awt.Desktop;
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
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;

import org.apache.poi.ss.formula.functions.T;

import com.asset.database.Connect;
import com.asset.propert.RowData;
import com.asset.property.HiddenProperty;
import com.asset.property.join.Hidden_JoinProperty;
import com.asset.tool.FileConvect;
import com.asset.tool.FileType;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.Assets.Hidden;
import com.voucher.manage.daoModel.Assets.Hidden_Level;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Jion;

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
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class HiddenDetailController {
	@FXML
	private TextField hiddenName;//隐患名称
	//@FXML
	//private TextField progress;//整改进度
	@FXML
	private TextField hiddenDetail;//隐患情况

	@FXML
	private TextField hiddenState;//隐患状态
		
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
	@FXML
	private DatePicker happenTime;//发生时间
	
	@FXML
	private HBox hBox;
	
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
	private Button delete;
		
	private Hidden_Jion hidden_Jion;
	
	private Stage dialogStage;
	
	private ObservableList<Hidden_JoinProperty> hiddenList;

	@FXML
	private TableView<Hidden_JoinProperty> hiddenTable;
	
	@FXML
	private Slider slider;
	
	@FXML
	private ProgressIndicator pi;
	
	private Double progress;
	
	private List<Hidden_Jion> hidden_Jions;
	
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
	 private TableColumn<Hidden_JoinProperty,Integer> C7;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C8;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C9;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C10;
	 
	 @FXML
	 private TableColumn<Hidden_JoinProperty,String> C11;
	
	 private final Desktop desktop = Desktop.getDesktop();
	 
	 private Stage stage;
	 
	 private File file;
	 
	 private List<String> names=new ArrayList<String>();
	 
	 private List<byte[]> fileBytes=new ArrayList<byte[]>();
	 
	 Assets assets= new Connect().get();
	 
	 public HiddenDetailController() {
		// TODO Auto-generated constructor stub
	 }
	
	 public void setTableView(TableView<Hidden_JoinProperty> hiddenTable,Integer offset,Integer limit,
			Map<String,String> searchMap,Pagination pagination,TableColumn<Hidden_JoinProperty,Integer> C1,
			TableColumn<Hidden_JoinProperty,String> C2,TableColumn<Hidden_JoinProperty,String> C3,TableColumn<Hidden_JoinProperty,String> C4,
			TableColumn<Hidden_JoinProperty,String> C5,TableColumn<Hidden_JoinProperty,ProgressBar> C6,
			TableColumn<Hidden_JoinProperty,Integer> C7,TableColumn<Hidden_JoinProperty,String> C8,
			TableColumn<Hidden_JoinProperty,String> C9,TableColumn<Hidden_JoinProperty,String> C10,TableColumn<Hidden_JoinProperty,String> C11) {
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
	 }
	
	 @FXML
	 private void initialize() {
		 
		 Assets assets=new Connect().getAssets();

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
                    hBox.getChildren().add(vBox);
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
	                    hBox.getChildren().add(vBox);
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
	                    hBox.getChildren().add(vBox);
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
	                    hBox.getChildren().add(vBox);
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
				String[] where={"id=",String.valueOf(hidden_Jion.getId())};
				hidden2.setWhere(where);
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
	                if(happenTime.getValue()!=null){
						LocalDate localDate=happenTime.getValue();
						Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
						Date date2 = Date.from(instant);
						hidden2.setHappen_time(date2);
					}
	                if(hiddenState!=null){
	                	hidden2.setState(hiddenState.getText());
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
					hidden2.setDate(date);
	
		     	int i=assets.updateHidden(hidden2);
				
			    if(names!=null&&fileBytes!=null){
				   int j=assets.uploadImageFile(hidden_Jion.getGUID(), names, fileBytes);
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
					hiddenTable.setItems(null);
					setRoomInfoList(offset, limit,searchMap);
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
		 
		 delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			        alert.setTitle("安全信息");
			        alert.setHeaderText("删除");
			        alert.setContentText("是否删除"+hidden_Jion.getId()+"信息");

			        ButtonType btnType1 = new ButtonType("确定");
			        ButtonType btnType2 = new ButtonType("取消");
			     

			        alert.getButtonTypes().setAll(btnType1, btnType2);

			        Optional<ButtonType> result = alert.showAndWait();
			        result.ifPresent(buttonType -> {
			            if (buttonType == btnType1) {
			                try{
			                String[] where={"id=",String.valueOf(hidden_Jion.getId())};
			                Hidden hidden =new Hidden();
			                hidden.setId(null);
			            	hidden.setWhere(where);
			                int i=assets.deleteHidden(hidden);
			                if(i==1){
			                	alert.setTitle("安全信息");
								alert.setHeaderText("操作");
								alert.setContentText("删除"+hidden_Jion.getId()+"成功");
								alert.showAndWait();
								hiddenTable.setItems(null);
								setRoomInfoList(offset, limit,searchMap);
								handleCancel();
			                }else{
			                	Alert alert2 = new Alert(AlertType.ERROR);
								alert2.setTitle("异常堆栈对话框");
								alert2.setHeaderText("错误");
								alert2.setContentText("删除"+hidden_Jion.getId()+"失败");
								alert2.showAndWait();
			                }
			                }catch (Exception e) {
								// TODO: handle exception
			                	Alert alert2 = new Alert(AlertType.ERROR);
								alert2.setTitle("异常堆栈对话框");
								alert2.setHeaderText("错误");
								alert2.setContentText("删除"+hidden_Jion.getId()+"失败");
								alert2.showAndWait();
								e.printStackTrace();
							}
			            } else if (buttonType == btnType2) {
			            	System.out.println("点击了取消");
			            } 
			        });
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
	    
	 public void setHidden(Hidden_Jion hidden_Jion){
		 this.hidden_Jion=hidden_Jion;
		 System.out.println("guid="+hidden_Jion.getGUID());
		 hiddenName.setText(String.valueOf(hidden_Jion.getName()));
		 hiddenDetail.setText(hidden_Jion.getDetail());
		 hiddenState.setText(hidden_Jion.getState());
		 
		 slider.setValue(hidden_Jion.getProgress()*50); 
	     pi.setProgress(hidden_Jion.getProgress());
		
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
                hBox.getChildren().add(vBox);

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
	 
	 void setRoomInfoList(Integer offset,Integer limit,Map search){

	      String sort=null;
	      String order=null;
	     
		  Map map=new HashMap<>();
		  
		  
		//  map=assets.selectAllHidden(limit, offset, sort, order, search);

		//  map=assets.selectAllHidden_Jion(limit, offset, sort, order, search);
		  
		  map=assets.selectAllHidden_Jion(limit, offset, sort, order, search);
		  
	     hidden_Jions= (List<Hidden_Jion>) map.get("rows");
	     
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
    		    cellData->cellData.getValue().getPrincipal().asObject());
     C8.setCellValueFactory(
    		 cellData->cellData.getValue().getHidden_type());
     C9.setCellValueFactory(
    		 cellData->cellData.getValue().getState());
     C10.setCellValueFactory(
    		 cellData->cellData.getValue().getRemark());
     
     C11.setCellValueFactory(
    		 cellData->cellData.getValue().getDate());
	     
	     int total=(int) map.get("total");
	     int page=total/10;
	     
	     if(total-page*10>0)
          page++;	     
	     
	     pagination.setPageCount(page);
	     	     
	 }
	 
}
