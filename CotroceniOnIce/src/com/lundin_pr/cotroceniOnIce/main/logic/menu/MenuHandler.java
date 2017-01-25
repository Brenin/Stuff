package com.lundin_pr.cotroceniOnIce.main.logic.menu;

import java.io.File;

import com.lundin_pr.cotroceniOnIce.main.Main;

import javafx.stage.FileChooser;

public class MenuHandler {

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    public static void handleSave(Main main) {
        File notificationsFile = main.getFilePath();
        if (notificationsFile != null) {
            main.saveDataToFile(notificationsFile);
        } else {
            handleSaveAs(main);
        }
    }
	
    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    public static void handleSaveAs(Main main) {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(main.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            main.saveDataToFile(file);
        }
    }
    
    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    public static void handleOpen(Main main) {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (file != null) {
            main.loadDataFromFile(file);
        }
    }
    
    /**
     * Closes the application.
     */
    public static void handleExit() {
        System.exit(0);
    }
}
