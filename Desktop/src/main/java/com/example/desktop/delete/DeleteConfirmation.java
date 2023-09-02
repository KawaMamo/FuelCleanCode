package com.example.desktop.delete;

import com.example.model.TableController;
import com.example.model.delete.DeleteService;
import com.example.model.modal.Modal;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.controlsfx.control.Notifications;

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
        deleteService.delete(deleteUrl, selected);
        Notifications.create().text("Deleted").showInformation();
        controller.removeData();
        Modal.close();
    }

}
