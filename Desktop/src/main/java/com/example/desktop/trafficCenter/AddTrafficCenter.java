package com.example.desktop.trafficCenter;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.trafficCenter.TrafficCenterService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreateTrafficCenterRequest;
import org.example.model.TrafficCenter;

import java.util.Objects;

public class AddTrafficCenter {

    @FXML
    private TextField nameTF;

    @FXML
    private Button submitBtn;

    public static TableController controller;
    private final TrafficCenterService service = TrafficCenterService.getInstance();

    @FXML
    void submit() {
        final TrafficCenter trafficCenter = service.addItem(new CreateTrafficCenterRequest(nameTF.getText()));
        notify(trafficCenter);
    }

    private static void notify(TrafficCenter trafficCenter) {
        String message;
        if(Objects.nonNull(trafficCenter.getId())){
            message = "trafficCenter added";
        }else {
            message = "something went wrong";
        }
        controller.addData(trafficCenter);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
