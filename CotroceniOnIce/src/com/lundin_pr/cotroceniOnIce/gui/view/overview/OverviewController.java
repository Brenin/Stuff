package com.lundin_pr.cotroceniOnIce.gui.view.overview;

import com.lundin_pr.cotroceniOnIce.data.entity.Notification;
import com.lundin_pr.cotroceniOnIce.main.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class OverviewController {

	private Main main;
	
    @FXML
    private TableView<Notification> notificationTable;
    @FXML
    private TableColumn<Notification, String> titleColumn;
    @FXML
    private TableColumn<Notification, String> headerColumn;
	
	@FXML
	private Label title;
	@FXML
	private Label header;
	@FXML
	private Label link;
	@FXML
	private Label date;
	@FXML
	private TextArea message;
	@FXML
	private TextArea note;
	
	public void setMain(Main main){
		this.main = main;
		
		notificationTable.setItems(main.getNotificationData());
	}
	
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	note.setWrapText(true);
    	message.setWrapText(true);
    	
    	titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());
    	headerColumn.setCellValueFactory(cellData -> cellData.getValue().getHeaderProperty());
        
        showNotificationDetails(null);
        
        notificationTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showNotificationDetails(newValue));
    }
	
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showNotificationDetails(Notification notification) {
        if (notification != null) {
            title.setText(notification.getTitle());
            header.setText(notification.getHeader());
            link.setText(notification.getLink());
            date.setText(notification.getDate());
            message.setText(notification.getMsg());
            note.setText(notification.getNote());
        } else {
            title.setText("");
            header.setText("");
            link.setText("");
            date.setText("");
            message.setText("");
            note.setText("");
        }
    }
	
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected notification.
     */
	@FXML
	private void editNote(){
		Notification notification = notificationTable.getSelectionModel().getSelectedItem();
		if(notification != null){
			boolean okClicked = main.showEditNoteDialog(notification);
			if(okClicked){
				showNotificationDetails(notification);
			}
		} else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No notification Selected");
            alert.setContentText("Please select a notification in the table.");

            alert.showAndWait();
        }
	}
	
	@FXML
	private void newMessage(){
		Notification notification = new Notification();
		boolean okClicked = main.showNewMessageDialog(notification);
		if(okClicked){
			main.getNotificationData().add(notification);
		}
	}
}
