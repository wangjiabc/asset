package com.asset.view;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.bcel.generic.NEW;

import com.asset.database.Connect;
import com.rmi.server.Assets;
import com.voucher.manage.daoModel.HiddenAssetByMonthAmount;
import com.voucher.manage.daoModel.HiddenByMonthAmount;
import com.voucher.manage.daoModel.Assets.Hidden_Type;
import com.voucher.manage.tools.MyTestUtil;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

public class SafeInspectionController extends AssetAsSwitch{
	@FXML
	 private Label rightTitleLabel;

	@FXML
	 private PieChart hiddenChart;
	
	@FXML
	 private PieChart assetChart;
	
	@FXML
	 private BarChart< Number, String> barChart;
	
	@FXML
	 private AreaChart<Number, String> areaChart;
	
	@FXML
	 private ChoiceBox hiddenYears1;	 
	 private List<String> hidden_years1;
	 private String hiddenYear1;
	
	 @FXML
	 private ChoiceBox hiddenYears2;	 
	 private List<String> hidden_years2;
	 private String hiddenYear2;
	 
	 Assets assets= new Connect().get();
	
	 public SafeInspectionController() {
		// TODO Auto-generated constructor stub
		 super();
	}
	 
	 @Override
	 protected void initCurrent() {
		 
		 URL url = getClass().getResource("");
		 String filePath=url.toString()+"Image";
		 Image image = new Image(filePath+"/inform.jpg");
	     homepage.setImage(image);
	     
	     rightTitleLabel.setText("安全数据统计");
	 
	     int in=assets.findInHidden();	     
	     int not=assets.findNotHidden();	     
	     int success=assets.findSuccessHidden();
	     
	     hiddenChart.setData(getChartData(not,in,success));
	     hiddenChart.setTitle("全部隐患数量");

	     int successAssets=assets.findAllAssets();
	     int hiddenAssets=assets.findAllAssetsHidden();
	     
	     assetChart.setData(getChartData2(successAssets, hiddenAssets));
	     assetChart.setTitle("全部隐患资产数量");

	     assetChart.getStylesheets().add("com/asset/view/chart2.css");
	     
	     hidden_years1=assets.findHiddenByYear();
	     
	     hiddenYears1.setItems(FXCollections.observableArrayList(hidden_years1));
	     
	     hiddenYears1.getSelectionModel().selectedIndexProperty().addListener(new
				 ChangeListener<Number>() {

					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue,
							Number newValue) {
						// TODO Auto-generated method stub
						int i=(int) newValue;
                        hiddenYear1=hidden_years1.get(i);                        
                        hiddenAmountByMonth(barChart, hiddenYear1); 
						System.out.println(hiddenYear1);
					}
			        
				});
	     
	     hiddenYears1.getSelectionModel().select(0);
	     	     	     	     
	     hidden_years2=assets.findAssetByYear();
	     
	     hiddenYears2.setItems(FXCollections.observableArrayList(hidden_years2));
	     
	     hiddenYears2.getSelectionModel().selectedIndexProperty().addListener(new
				 ChangeListener<Number>() {

					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue,
							Number newValue) {
						// TODO Auto-generated method stub
						int i=(int) newValue;
						hiddenYear2=hidden_years2.get(i);
						hiddenAssetAmountByMonth(areaChart, hiddenYear2);
						System.out.println(hiddenYear2);
					}
			        
				});
	     
	     hiddenYears2.getSelectionModel().select(0);
	     
	     
	 }
	 
	 private ObservableList<Data> getChartData(Integer d1,Integer d2,Integer d3) {
	        ObservableList<Data> answer = FXCollections.observableArrayList();
	        answer.addAll(new PieChart.Data("未整改的隐患"+d1+"处("+(d1*100/(d1+d2+d3)*100)/100+"%)", d1),
	                new PieChart.Data("整改中的隐患"+d2+"处("+(d2*100/(d1+d2+d3)*100)/100+"%)", d2),
	                new PieChart.Data("已整改的隐患"+d3+"处("+(d3*100/(d1+d2+d3)*100)/100+"%)", d3));
	        return answer;
	    }
	 
	 private ObservableList<Data> getChartData2(Integer d1,Integer d2) {
	        ObservableList<Data> answer = FXCollections.observableArrayList();
	        answer.addAll(new PieChart.Data("正常资产数量"+d1+"处("+(d1*100/(d1+d2)*100)/100+"%)", d1),
	                new PieChart.Data("隐患资产数量"+d2+"处("+(d2*100/(d1+d2)*100)/100+"%)", d2));
	        return answer;
	    }
	 
	 private void hiddenAmountByMonth(BarChart barChart,String year){
		 
		 barChart.setTitle("每月新增隐患数量");

	     XYChart.Series series = new XYChart.Series();
	     series.setName("隐患数量");

	     List list=assets.findHiddenByMonthOfYear(year);
	     
	     Iterator<HiddenByMonthAmount> iterator=list.iterator();
	     
	     int size=0;
	     
	     while (iterator.hasNext()) {
	    	 HiddenByMonthAmount hiddenByMonthAmount=iterator.next();
	    	 series.getData().add(new XYChart.Data(hiddenByMonthAmount.getYear()+"  隐患:"
	    			 				+hiddenByMonthAmount.getAmount(),
	    			                hiddenByMonthAmount.getAmount()));
	    	 size++;
		 }
	     
	     barChart.getData().add(series);	     
	     barChart.getData().clear();	     
	     barChart.getData().add(series);	     
	     barChart.setMaxWidth(size*100);

	 }
	 
	 
	 private void hiddenAssetAmountByMonth(AreaChart areaChart,String year){
		 
		 XYChart.Series seriesAsset= new XYChart.Series();
		 seriesAsset.setName("正常资产");
		 
		 XYChart.Series seriesHidden = new XYChart.Series();
		 seriesHidden.setName("有隐患资产");
		 
		 List list=assets.findHiddenAssetsByMonthOfYear(year);
		 
		 MyTestUtil.print(list);
		 
		 Iterator<HiddenAssetByMonthAmount> iterator=list.iterator();
		 
		 while (iterator.hasNext()) {
			HiddenAssetByMonthAmount hiddenAssetByMonthAmount=iterator.next();
			
			seriesAsset.getData().add(new XYChart.Data(hiddenAssetByMonthAmount.getYear()+
					" 资产:"+hiddenAssetByMonthAmount.getAmount()+
					" 有隐患资产:"+hiddenAssetByMonthAmount.getHiddenAmount(), 
					hiddenAssetByMonthAmount.getAmount()));
			
			seriesHidden.getData().add(new XYChart.Data(hiddenAssetByMonthAmount.getYear()+
					" 资产:"+hiddenAssetByMonthAmount.getAmount()+
					" 有隐患资产:"+hiddenAssetByMonthAmount.getHiddenAmount(), 
					hiddenAssetByMonthAmount.getHiddenAmount()));
			
		 }
 
	     areaChart.setTitle("每月新增隐患资产数量");
	     
	     areaChart.getData().clear();
	     
	     areaChart.getData().addAll(seriesAsset,seriesHidden);
	     
	     areaChart.getStylesheets().add("com/asset/view/chart3.css");
		 
	 }
	 
}
