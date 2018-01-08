package test;

import java.net.URL;

import javafx.application.Application;  
import javafx.scene.Scene;  
import javafx.scene.layout.StackPane;  
import javafx.scene.image.Image;  
import javafx.stage.Stage;  
  
public class StackoverflowIcon extends Application  
{  
  
    @Override  
    public void start(Stage stage)  
    {  
  
        StackPane root = new StackPane();  
        Scene scene = new Scene(root, 300, 250);  
        URL url = getClass().getResource("");
System.out.println(url);
        // set icon  
        stage.getIcons().add(new Image(url.toString()+"/logo.gif"));  
  
        // set title  
        stage.setTitle("Wow!! Stackoverflow Icon");  
        stage.setScene(scene);  
        stage.show();  
    }  
  
    /** 
     * @param args the command line arguments 
     */  
    public static void main(String[] args)  
    {  
        launch(args);  
    }  
}  
