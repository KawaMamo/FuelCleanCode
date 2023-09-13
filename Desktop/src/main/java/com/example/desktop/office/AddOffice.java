package com.example.desktop.office;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.office.OfficeService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreateOfficeRequest;
import org.example.model.Office;

import java.util.Objects;

public class AddOffice {

    public static Boolean isEditingForm = false;
    @FXML
    private TextField nameTF;

    @FXML
    private Button submitBtn;

    public static TableController controller;
    private final OfficeService officeService = OfficeService.getInstance();

    @FXML
    void submit() {
        final Office office = officeService.addItem(new CreateOfficeRequest(nameTF.getText()));
        notify(office);
    }

    private static void notify(Office office) {
        String message;
        if(Objects.nonNull(office.getId())){
            message = "office added";
        }else {
            message = "something went wrong";
        }
        controller.addData(office);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
