package com.asset.view;

import java.net.URL;
import java.util.List;

import com.asset.database.Connect;
import com.asset.tool.MyTestUtil;
import com.rmi.server.Assets;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Data_Join;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;

public class HiddenQueryController extends AssetAsSwitch{
	
	@FXML
	 private Label rightTitleLabel;
	
	@FXML
	 private FlowPane flowPane1;
	
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
	     
	     List<Hidden_Data_Join> hidden_Data_Joins=assets.hiddenQuery(1);
	     
	     MyTestUtil.print(hidden_Data_Joins);
	     
	 }
	 
}
