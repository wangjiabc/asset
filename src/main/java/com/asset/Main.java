package com.asset;

import javafx.application.Application;  
import javafx.fxml.FXMLLoader;  
import javafx.scene.Parent;  
import javafx.scene.Scene;  
import javafx.stage.Stage;  
import org.springframework.boot.SpringApplication;  
import org.springframework.boot.autoconfigure.SpringBootApplication;  
import org.springframework.context.ConfigurableApplicationContext;  
  
import java.util.concurrent.CompletableFuture;  
  
@SpringBootApplication  
public class Main extends Application {  
    public static ConfigurableApplicationContext applicationContext;  
  
    @Override  
    public void init() throws Exception {  
        CompletableFuture.supplyAsync(() -> {  
            ConfigurableApplicationContext ctx = SpringApplication.run(this.getClass());  
            return ctx;  
        }).thenAccept(this::launchApplicationView);  
//        Main.applicationContext = SpringApplication.run(this.getClass());  
    }  
  
    @Override  
    public void start(Stage primaryStage) throws Exception {  
        Parent root = FXMLLoader.load(getClass().getResource("/view/PersonOverview.fxml"));  
        primaryStage.setTitle("Hello World");  
        primaryStage.setScene(new Scene(root, 600, 400));  
        primaryStage.show();  
    }  
  
    private void launchApplicationView(ConfigurableApplicationContext ctx) {  
        Main.applicationContext = ctx;  
    }  
  
    public static void main(String[] args) {  
        launch(args);  
    }  
}  
