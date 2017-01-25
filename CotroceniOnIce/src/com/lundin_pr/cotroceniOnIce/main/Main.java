package com.lundin_pr.cotroceniOnIce.main;

import java.io.File;
import java.io.IOException;

import com.lundin_pr.cotroceniOnIce.data.constants.Constants;
import com.lundin_pr.cotroceniOnIce.data.entity.Notification;
import com.lundin_pr.cotroceniOnIce.gui.view.overview.OverviewController;
import com.lundin_pr.cotroceniOnIce.gui.view.root.RootController;
import com.lundin_pr.cotroceniOnIce.main.logic.dialogs.Dialogs;
import com.lundin_pr.cotroceniOnIce.main.logic.fcm.FCMSender;
import com.lundin_pr.cotroceniOnIce.main.logic.fileHandling.Files;
import com.lundin_pr.cotroceniOnIce.main.logic.menu.MenuHandler;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * The data as an observable list of Notifications.
     */
    private ObservableList<Notification> notifications = FXCollections.observableArrayList();
	
    public ObservableList<Notification> getNotificationData() {
        return notifications;
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(Constants.APPLICATION_TITLE);
        
        this.primaryStage.getIcons().add(new Image(Constants.ICON));

        initRootLayout();
        interceptClose();
        showOverview();
	}

    /**
     * Initializes the root layout and tries to load the last opened file.
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(Constants.ROOT));
            rootLayout = (BorderPane) loader.load(); 

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = getFilePath();
        if (file != null) {
            loadDataFromFile(file);
        }
    }
    
    /**
     * Shows the overview inside the root layout.
     */
    public void showOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(Constants.OVERVIEW));
            AnchorPane personOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(personOverview);

            OverviewController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Intercepts the onCloseEvent that fires when 
     * the user presses the X in the upper right corner
     */
    private void interceptClose(){
    	primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.runLater(new Runnable(){
					@Override
					public void run() {
						MenuHandler.handleSave(Main.this);
						MenuHandler.handleExit();
					}
				});
			}
		});
    }
    
    public boolean showNewMessageDialog(Notification notification) {
    	return Dialogs.showNewMessageDialog(primaryStage, notification, this);
    }
    
    public boolean showEditNoteDialog(Notification notification){
    	return Dialogs.showEditNoteDialog(notification, primaryStage);
    }
    
    public void sendNotification(Notification notification){
    	FCMSender.sendNotification(notification);
    }
    
    public File getFilePath(){
    	return Files.getFilePath();
    }
      
    public void loadDataFromFile(File file){
    	Files.loadDataFromFile(file, notifications, primaryStage);
    }

    public void setFilePath(File file){
    	Files.setFilePath(file, primaryStage);
    }
    
    public void saveDataToFile(File file){
    	Files.saveDataToFile(file, notifications, primaryStage);
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
