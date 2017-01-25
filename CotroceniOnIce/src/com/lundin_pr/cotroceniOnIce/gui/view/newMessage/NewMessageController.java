package com.lundin_pr.cotroceniOnIce.gui.view.newMessage;

import com.lundin_pr.cotroceniOnIce.data.constants.Constants;
import com.lundin_pr.cotroceniOnIce.data.entity.Notification;
import com.lundin_pr.cotroceniOnIce.main.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class NewMessageController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField headerField;
    @FXML
    private TextField linkField;
    @FXML
    private TextArea messageField;
	
    private Main main;
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
	
    public void setMain(Main main){
    	this.main = main;
    }
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    public void setNotification(Notification notification){
    	this.notification = notification;
    }
    
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	notification.setTitle(titleField.getText());
        	notification.setHeader(headerField.getText());
        	notification.setLink(linkField.getText());
            notification.setMsg(messageField.getText());

			main.sendNotification(notification);
			// And populate note with response
            // TODO change this to actually send it
//			notification.setNote("This is the response from the server\nResponseCode: 200");
            
            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (titleField.getText() == null || titleField.getText().length() == 0) {
            errorMessage += "No valid title!\n"; 
        }
        if (headerField.getText() == null || headerField.getText().length() == 0) {
            errorMessage += "No valid header!\n"; 
        }
        if (linkField.getText() == null || linkField.getText().length() == 0) {
            errorMessage += "No valid link!\n"; 
        }
        if (messageField.getText() == null || messageField.getText().length() == 0) {
            errorMessage += "No valid message!\n"; 
        } 

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle(Constants.ERROR_INVALID_INPUT_TITLE);
            alert.setHeaderText(Constants.ERROR_INVALID_INPUT);
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
	}

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
