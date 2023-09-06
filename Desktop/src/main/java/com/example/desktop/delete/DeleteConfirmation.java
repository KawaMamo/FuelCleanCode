package com.example.desktop.delete;

import com.example.model.TableController;
import com.example.model.delete.DeleteService;
import com.example.model.modal.Modal;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.controlsfx.control.Notifications;
import org.example.model.Place;

public class DeleteConfirmation {

    public static TableController controller;
    public static Long selected;
    public static String deleteUrl;
    private final DeleteService deleteService = DeleteService.getInstance();
    @FXML
    private Label selectedItemLbl;

    @FXML
    public void initialize(){
        selectedItemLbl.setText(String.valueOf(selected));
    }
    @FXML
    public void confirm(){
        final Integer delete = deleteService.delete(deleteUrl, selected);
        String message;
        if(delete != 200){
            message = "something went wrong . status code "+delete;
        }else {
            message = "deleted";
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notifications.create().text(message).showInformation();
            }
        });
        controller.removeData();
        Modal.close();
    }

}
