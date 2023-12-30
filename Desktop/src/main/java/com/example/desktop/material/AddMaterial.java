package com.example.desktop.material;

import com.example.model.TableController;
import com.example.model.material.MaterialService;
import com.example.model.modal.Modal;
import com.example.model.tools.FormType;
import com.example.model.tools.QueryBuilder;
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
    public static FormType formType = FormType.CREATE;

    private final MaterialService materialService = MaterialService.getInstance();

    @FXML
    private void initialize(){
        if(formType.equals(FormType.UPDATE)){
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
        if(formType.equals(FormType.UPDATE)){
            material = materialService.editItem(new UpdateMaterialRequest(Materials.selectedMaterial.getId(), nameTF.getText()));
        }else if(formType.equals(FormType.CREATE)){
            material = materialService.addItem(new CreateMaterialRequest(nameTF.getText()));
        }else {
            material = new Material();
            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.addQueryParameter("name", nameTF.getText());
            queryBuilder.sort();
            controller.setQuery(queryBuilder.getQuery());
        }
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
