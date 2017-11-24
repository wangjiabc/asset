package test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.CookieStore;
import org.apache.http.message.BasicNameValuePair;
import com.asset.tool.HttpTools;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
 
 
public class WebViewSample extends Application {
    private Scene scene; 
    @Override public void start(Stage stage) throws URISyntaxException, IOException{

        // create the scene
        stage.setTitle("Web View");
        scene = new Scene(new Browser(),1000,800, Color.web("#666970"));
        stage.setScene(scene);     
        stage.show();
    }
 
    public static void main(String[] args){
        launch(args);
    }
    
  }
class Browser extends Region {
	private CookieStore cookieStore = null; 
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    final URI loginUri=new URI("http://localhost:8080/voucher/seller/toLogin.do");
    final URI betakeUri=new URI("http://localhost:8080/voucher/baiduMap/get.do");
    BasicNameValuePair user;
    BasicNameValuePair password;
    
    public Browser() throws URISyntaxException, IOException {
        String c="";
        user = new BasicNameValuePair("campusAdmin", "1");
        password = new BasicNameValuePair("password", "123");
    	c=HttpTools.getCookie(loginUri, user, password);
    	
    	//apply the styles
        getStyleClass().add("browser");
        Map<String, List<String>> headers = new LinkedHashMap<String, List<String>>();
        headers.put("Set-Cookie", Arrays.asList(c));
        try{
         java.net.CookieHandler.getDefault().put(betakeUri, headers);
        }catch(NullPointerException e) {
			// TODO: handle exception
		}
        // load the web page
        webEngine.load(betakeUri.toString());
        //add the web view to the scene
        getChildren().add(browser);
 
    }
    
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
 
    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }
 
    @Override protected double computePrefWidth(double height) {
        return 750;
    }
 
    @Override protected double computePrefHeight(double width) {
        return 500;
    }
}