package com.example.desktop.trafficCenter;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.tools.FormType;
import com.example.model.tools.QueryBuilder;
import com.example.model.trafficCenter.TrafficCenterService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreateTrafficCenterRequest;
import org.example.contract.request.update.UpdateTrafficCenterRequest;
import org.example.model.TrafficCenter;

import java.util.Objects;

public class AddTrafficCenter {

    public static FormType formType = FormType.CREATE;
    @FXML
    private TextField nameTF;

    @FXML
    private Button submitBtn;

    public static TableController controller;
    private final TrafficCenterService service = TrafficCenterService.getInstance();

    @FXML
    private void initialize(){
        if(formType.equals(FormType.UPDATE)){
            final TrafficCenter trafficCenter = service.getItem(TrafficCenters.selectedTrafficCenter.getId());
            nameTF.setText(trafficCenter.getName());
        }
    }
    @FXML
    void submit() {
        final TrafficCenter trafficCenter;
        if(formType.equals(FormType.UPDATE)){
            trafficCenter = service.editItem(new UpdateTrafficCenterRequest(TrafficCenters.selectedTrafficCenter.getId(), nameTF.getText()));
        }else if(formType.equals(FormType.CREATE)){
            trafficCenter = service.addItem(new CreateTrafficCenterRequest(nameTF.getText()));
        }else {
            trafficCenter = new TrafficCenter();
            final QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.addQueryParameter("name", nameTF.getText());
            queryBuilder.sort();
            controller.setQuery(queryBuilder.getQuery());
        }

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
