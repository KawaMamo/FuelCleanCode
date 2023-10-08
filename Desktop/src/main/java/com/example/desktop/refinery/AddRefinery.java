package com.example.desktop.refinery;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.refinery.RefineryService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreateRefineryRequest;
import org.example.contract.request.update.UpdateRefineryRequest;
import org.example.model.Refinery;

import java.util.Objects;

public class AddRefinery {

    public static TableController controller;
    public static Boolean isEditingForm = false;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField translationTF;

    @FXML
    private Button submitBtn;

    private final RefineryService refineryService = RefineryService.getInstance();

    @FXML
    private void initialize(){
        if(isEditingForm){
            final Refinery refinery = refineryService.getItem(Refineries.selectedRefinery.getId());
            nameTF.setText(refinery.getName());
        }
    }
    @FXML
    void submit() {
        final Refinery refinery;
        if(isEditingForm){
            refinery = refineryService.editItem(new UpdateRefineryRequest(Refineries.selectedRefinery.getId(),
                    nameTF.getText(),
                    translationTF.getText()));
        }else {
            refinery = refineryService.addItem(new CreateRefineryRequest(nameTF.getText(), translationTF.getText()));
        }
        isEditingForm = false;
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
