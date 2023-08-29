package com.example.desktop.delete;

import com.example.model.delete.DeleteService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.controlsfx.control.Notifications;

public class DeleteConfirmation {
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
        System.out.println(deleteService.delete(deleteUrl, selected));
        Notifications.create().text("Deleted").showInformation();

    }

}
