package com.asset.model;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Utils {
	private void OpenFile(Stage stage,String title) {
		FileChooser file = new FileChooser();
		file.setTitle(title);
		file.showOpenDialog(stage);
		file.setInitialDirectory(new File(System.getProperty("user.home")));
		file.getExtensionFilters().addAll(
		new FileChooser.ExtensionFilter("All Images", "*.*"),
		new FileChooser.ExtensionFilter("JPG", "*.jpg"),
		new FileChooser.ExtensionFilter("GIF", "*.gif"),
		new FileChooser.ExtensionFilter("PNG", "*.png")
				);
		
	}
}
