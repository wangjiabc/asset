package view;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;

import com.asset.tool.DateUtil;

import controller.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RootLayoutController {

	//删除按钮
    @FXML
    private void handleDeletePerson() {

        	Dialogs.create()
            .title("edit")
            .masthead("edit")
            .message("Please select a person in the table.")
            .owner(null)
            .showInformation();
    
    }
    
    @FXML
    private void handleexit(){
    	Action a=Dialogs.create()
        .title("edit")
        .masthead("edit")
        .message("Please select a person in the table.")
        .owner(null)
        .showConfirm();
    	
    	if(a.getText().equals("@@dlg.yes.button")){
    		System.exit(0);
    	}
    	
    	System.out.println(a.getText());
    }
}
