package com.asset.view.map;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import com.asset.database.Connect;
import com.asset.tool.HttpTools;
import com.rmi.server.Assets;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class BaiduMapController {

	@FXML
	private WebView mapview;
	
	private WebEngine webEngine;

	@FXML
	private Button appendMap;
	
	@FXML
	private Button queryMap;
	
	@FXML
	private Button button3;

	Assets assets= new Connect().get();
	
	public BaiduMapController() {
		// TODO Auto-generated constructor stub
		
	}
	
	 @FXML
     private void initialize() throws URISyntaxException, ClientProtocolException, IOException {
		 final URI loginUri=new URI("http://220.166.104.133/voucher/seller/toLogin.do");
		 final URI betakeUri=new URI("http://220.166.104.133/voucher/baiduMap/get.do");
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
	     
	     webEngine=mapview.getEngine();
	     
	     button3.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					webEngine.load(betakeUri.toString());
				}
			 });
	
	     new AppendAssetController(mapview,webEngine,appendMap).initCurrent(); 
	     
	     new QueryAssetController(mapview, webEngine, queryMap).initCurrent();
	}

	 
}

