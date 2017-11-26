package com.asset.view.map;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.bcel.generic.NEW;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import javafx.concurrent.Worker.State;

import com.alibaba.fastjson.JSONObject;
import com.asset.database.Connect;
import com.asset.property.HiddenProperty;
import com.asset.tool.HttpTools;
import com.asset.view.hidden.HiddenDetailController;
import com.asset.view.hidden.HiddenQueryController;
import com.rmi.server.Assets;

import controller.model.Position;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class BaiduMapController {

	@FXML
	private WebView mapview;
	
	private WebEngine webEngine;

	@FXML
	private Button button1;
	
	@FXML
	private Button button2;
	
	@FXML
	private Button button3;

	Assets assets= new Connect().get();
	
	public BaiduMapController() {
		// TODO Auto-generated constructor stub
		
	}
	
	 @FXML
     private void initialize() throws URISyntaxException, ClientProtocolException, IOException {
		 final URI loginUri=new URI("http://localhost:8080/voucher/seller/toLogin.do");
		 final URI betakeUri=new URI("http://localhost:8080/voucher/baiduMap/get.do");
		 BasicNameValuePair user;
		 BasicNameValuePair password;
		 String c;
		 user = new BasicNameValuePair("campusAdmin", "1");
	     password = new BasicNameValuePair("password", "123");
	     c=HttpTools.getCookie(loginUri, user, password);
	     Map<String, List<String>> headers = new LinkedHashMap<String, List<String>>();
	     headers.put("Set-Cookie", Arrays.asList(c));
	     try{
	         java.net.CookieHandler.getDefault().put(betakeUri, headers);
	      }catch(NullPointerException e) {
				// TODO: handle exception
		 }
	     
	     webEngine = mapview.getEngine();
		 
		 // load the web page
	     webEngine.load(betakeUri.toString());


	    
	     
	     button2.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					webEngine.load("http://localhost:8080/voucher/test/index.html");
				}
			 });
	     
	     button3.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					webEngine.load(betakeUri.toString());
				}
			 });
	
	     new AppendAssetController(mapview,webEngine,button1).initCurrent(); 
	}

	 
}

