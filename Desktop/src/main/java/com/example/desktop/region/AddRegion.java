package com.example.desktop.region;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.region.RegionService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreateRegionRequest;
import org.example.contract.request.update.UpdateRegionRequest;
import org.example.model.Group;
import org.example.model.Region;

import java.util.Objects;

public class AddRegion {

    public static TableController controller;
    public static Boolean isEditingForm = false;

    @FXML
    private TextField nameTF;

    @FXML
    private Button submitBtn;

    private final RegionService regionService = RegionService.getInstance();

    @FXML
    private void initialize(){
        if(isEditingForm){
            final Region region = regionService.getItem(Regions.selectedRegion.getId());
            nameTF.setText(region.getName());
        }
    }
    @FXML
    void submit() {
        final Region region;
        if(isEditingForm){
            region = regionService.editItem(new UpdateRegionRequest(Regions.selectedRegion.getId(), nameTF.getText()));
        }else {
            region = regionService.addItem(new CreateRegionRequest(nameTF.getText()));
        }
        isEditingForm = false;
        notify(region);
    }

    private static void notify(Region region) {
        String message;
        if(Objects.nonNull(region.getId())){
            message = "region added";
        }else {
            message = "something went wrong";
        }
        controller.addData(region);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
