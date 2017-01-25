package com.lundin_pr.cotroceniOnIce.main.logic.fileHandling;

import java.io.File;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.lundin_pr.cotroceniOnIce.data.constants.Constants;
import com.lundin_pr.cotroceniOnIce.data.entity.Notification;
import com.lundin_pr.cotroceniOnIce.data.wrapper.NotificationWrapper;
import com.lundin_pr.cotroceniOnIce.main.Main;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Files {

    /**
     * Saves the current notification data to the specified file.
     * 
     * @param file
     */
    public static void saveDataToFile(File file, ObservableList<Notification> notifications, Stage primaryStage) {
        try {
            JAXBContext context = JAXBContext.newInstance(NotificationWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            NotificationWrapper wrapper = new NotificationWrapper();
            wrapper.setNotifications(notifications);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setFilePath(file, primaryStage);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(Constants.ERROR_TITLE);
            alert.setHeaderText(Constants.ERROR_SAVE_DATA);
            alert.setContentText(Constants.ERROR_SAVE_DATA_MSG + file.getPath());

            alert.showAndWait();
        }
    }
    
    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * 
     * @param file the file or null to remove the path
     * @param primaryStage 
     */
    public static void setFilePath(File file, Stage primaryStage) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle(Constants.FILE_TITLE + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle(Constants.APPLICATION_TITLE);
        }
    }
    
    /**
     * Loads notification data from the specified file. The current person data will
     * be replaced.
     * 
     * @param file
     */
    public static void loadDataFromFile(File file, ObservableList<Notification> notifications, Stage primaryStage) {
        try {
            JAXBContext context = JAXBContext.newInstance(NotificationWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling
            NotificationWrapper wrapper = (NotificationWrapper) um.unmarshal(file);

            notifications.clear();
            notifications.addAll(wrapper.getNotifications());

            // Save the file path to the registry.
            setFilePath(file, primaryStage);

        } catch (Exception e) { 
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(Constants.ERROR_TITLE);
            alert.setHeaderText(Constants.ERROR_LOAD_DATA);
            alert.setContentText(Constants.ERROR_LOAD_DATA_MSG + file.getPath());

            alert.showAndWait();
        }
    }
    
    /**
     * Returns the notification file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * 
     * @return
     */
    public static File getFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }
}
