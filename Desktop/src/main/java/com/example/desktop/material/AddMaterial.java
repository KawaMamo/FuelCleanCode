package com.example.desktop.material;

import com.example.model.TableController;
import com.example.model.material.MaterialService;
import com.example.model.modal.Modal;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreateMaterialRequest;
import org.example.contract.request.update.UpdateMaterialRequest;
import org.example.model.Material;

import java.util.Objects;

public class AddMaterial {

    public static TableController controller;
    public static Boolean isEditingForm = false;

    private final MaterialService materialService = MaterialService.getInstance();

    @FXML
    private void initialize(){
        if(isEditingForm){
            final Material material = materialService.getItem(Materials.selectedMaterial.getId());
            nameTF.setText(material.getName());
        }
    }
    @FXML
    private TextField nameTF;

    @FXML
    private Button submitBtn;

    @FXML
    void submit() {
        final Material material;
        if(isEditingForm){
            material = materialService.editItem(new UpdateMaterialRequest(Materials.selectedMaterial.getId(), nameTF.getText()));
        }else {
            material = materialService.addItem(new CreateMaterialRequest(nameTF.getText()));
        }
        isEditingForm = false;
        notify(material);
    }

    private static void notify(Material material) {
        String message;
        if(Objects.nonNull(material.getId())){
            message = "material added";
        }else {
            message = "something went wrong";
        }
        controller.addData(material);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
