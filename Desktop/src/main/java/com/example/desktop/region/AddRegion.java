package com.example.desktop.region;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.region.RegionService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreateRegionRequest;
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
    void submit() {
        final Region region = regionService.addItem(new CreateRegionRequest(nameTF.getText()));
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
