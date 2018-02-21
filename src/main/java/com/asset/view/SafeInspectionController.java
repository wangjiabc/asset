package com.asset.view;

import java.net.URL;
import com.asset.database.Connect;
import com.rmi.server.Assets;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
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
	 
	     hiddenChart.setData(getChartData(20.1, 30.1));
	     hiddenChart.setTitle("全部隐患数量");

	     assetChart.setData(getChartData(40.1, 30.1));
	     assetChart.setTitle("隐患资产数量");

	     assetChart.getStylesheets().add("com/asset/view/chart2.css");
	     	     
	     barChart.setTitle("每月隐患增长数量");	     

	     XYChart.Series series1 = new XYChart.Series();
	     series1.setName("2003");
	     series1.getData().add(new XYChart.Data("A",80));
	     series1.getData().add(new XYChart.Data("B",9));
	     series1.getData().add(new XYChart.Data("C",6));
	     
	     barChart.getData().add(series1);
	      
	     XYChart.Series seriesApril= new XYChart.Series();
	     seriesApril.setName("April");
	     seriesApril.getData().add(new XYChart.Data("1", 4));
	     seriesApril.getData().add(new XYChart.Data("2", 10));
	     seriesApril.getData().add(new XYChart.Data("3", 15));
	     
	     XYChart.Series seriesMay = new XYChart.Series();
	     seriesMay.setName("May");
	     seriesMay.getData().add(new XYChart.Data("1", 20));
	     seriesMay.getData().add(new XYChart.Data("2", 35));
	     seriesMay.getData().add(new XYChart.Data("3", 43));
	     
	     areaChart.setTitle("每月隐患资产数量");
	     
	     areaChart.getData().addAll(seriesMay,seriesApril);
	     
	     areaChart.getStylesheets().add("com/asset/view/chart3.css");
	 }
	 
	 private ObservableList<Data> getChartData(Double d1,Double d2) {
	        ObservableList<Data> answer = FXCollections.observableArrayList();
	        answer.addAll(new PieChart.Data("java", d1),
	                new PieChart.Data("JavaFx", d2),
	                new PieChart.Data("fff", 10));
	        return answer;
	    }
}
