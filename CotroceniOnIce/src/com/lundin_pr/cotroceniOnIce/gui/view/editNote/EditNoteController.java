package com.lundin_pr.cotroceniOnIce.gui.view.editNote;

import com.lundin_pr.cotroceniOnIce.data.constants.Constants;
import com.lundin_pr.cotroceniOnIce.data.entity.Notification;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EditNoteController {

    @FXML
    private TextArea noteField;
	
    private Stage dialogStage;
    private Notification notification;
    private boolean okClicked = false;
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        this.dialogStage.getIcons().add(new Image(Constants.ICON));
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }
    
    public void setNote(Notification notification){
    	this.notification = notification;
    	
    	noteField.setText(notification.getNote());
    }
    
    @FXML
    private void handleOK(){
    	notification.setNote(noteField.getText());
        okClicked = true;
        dialogStage.close();
    }
    
    @FXML
    private void handleCancel(){
    	dialogStage.close();
    }
}
