package com.example.desktop.refinery;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.refinery.RefineryService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreateRefineryRequest;
import org.example.model.Refinery;

import java.util.Objects;

public class AddRefinery {

    public static TableController controller;
    public static Boolean isEditingForm = false;
    @FXML
    private TextField nameTF;

    @FXML
    private Button submitBtn;

    private final RefineryService refineryService = RefineryService.getInstance();

    @FXML
    void submit() {
        final Refinery refinery = refineryService.addItem(new CreateRefineryRequest(nameTF.getText()));
        notify(refinery);
    }

    private static void notify(Refinery refinery) {
        String message;
        if(Objects.nonNull(refinery.getId())){
            message = "refinery added";
        }else {
            message = "something went wrong";
        }
        controller.addData(refinery);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
