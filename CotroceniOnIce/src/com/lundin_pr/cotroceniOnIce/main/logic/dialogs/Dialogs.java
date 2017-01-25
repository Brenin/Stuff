package com.lundin_pr.cotroceniOnIce.main.logic.dialogs;

import java.io.IOException;

import com.lundin_pr.cotroceniOnIce.data.constants.Constants;
import com.lundin_pr.cotroceniOnIce.data.entity.Notification;
import com.lundin_pr.cotroceniOnIce.gui.view.editNote.EditNoteController;
import com.lundin_pr.cotroceniOnIce.gui.view.newMessage.NewMessageController;
import com.lundin_pr.cotroceniOnIce.main.Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Dialogs {

    /**
     * Opens a dialog for the specified notification
     * 
     * @param notification
     * @return
     */
    public static boolean showEditNoteDialog(Notification notification, Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(Constants.EDIT_NOTE));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(Constants.TITLE_EDIT_NOTE);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            EditNoteController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setNote(notification);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Opens a dialog for a new notification
     * 
     * @return
     */
    public static boolean showNewMessageDialog(Stage primaryStage, Notification notification, Main main) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(Constants.NEW_MESSAGE));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(Constants.TITLE_NEW_MESSAGE);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            NewMessageController controller = loader.getController();
            controller.setMain(main);
            controller.setDialogStage(dialogStage);
            controller.setNotification(notification);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
