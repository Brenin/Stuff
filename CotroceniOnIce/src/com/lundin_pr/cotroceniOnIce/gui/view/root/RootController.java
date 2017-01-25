package com.lundin_pr.cotroceniOnIce.gui.view.root;

import com.lundin_pr.cotroceniOnIce.main.Main;
import com.lundin_pr.cotroceniOnIce.main.logic.menu.MenuHandler;

import javafx.fxml.FXML;

public class RootController {

    private Main main;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * Creates an new file
     */
    @FXML
    private void handleNew() {
    	main.getNotificationData().clear();
    	main.setFilePath(null);
    }

    @FXML
    private void handleOpen() {
        MenuHandler.handleOpen(main);
    }

    @FXML
    private void handleSave(){
    	MenuHandler.handleSave(main);
    }
    
    @FXML
    private void handleSaveAs(){
    	MenuHandler.handleSaveAs(main);
    }

    @FXML
    private void handleExit() {
        MenuHandler.handleExit();
    }
}
