package com.example.desktop.vehicles;

import com.example.model.person.PersonService;
import com.example.model.trafficCenter.TrafficCenterService;
import com.example.model.vehicle.VehicleService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import org.example.contract.request.create.CreateVehicleRequest;
import org.example.model.Person;
import org.example.model.Vehicle;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class AddVehicle {

    @FXML
    private TextField driverTF;

    @FXML
    private TextField officeTF;

    @FXML
    private TextField plateNumberTF;

    @FXML
    private TextField sizeTF;

    @FXML
    private Button submitBtn;

    @FXML
    private TextField trafficCenterTF;
    VehicleService vehicleService = VehicleService.getInstance();
    PersonService personService = PersonService.getInstance();
    private Long selectedDriverId;
    TrafficCenterService trafficCenterService = TrafficCenterService.getInstance();

    @FXML
    void initialize() {
        final List<Person> personList = personService.getPersonList(0, 2000);
        final List<String> stringList = personList.stream().map(person -> person.getName()+" "+person.getNationalId()).toList();
        TextFields.bindAutoCompletion(driverTF, stringList);

        driverTF.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(driverTF.getText().length() > 0) {
                if(stringList.contains(driverTF.getText())){
                    final Person person = personList.get(stringList.indexOf(driverTF.getText()));
                    if(Objects.nonNull(person))
                        selectedDriverId = person.getId();
                }
            }
        });
        trafficCenterService.getItems(null, null);
    }

    @FXML
    void submit() {
        final Vehicle vehicle = vehicleService.addVehicle(new CreateVehicleRequest(plateNumberTF.getText(),
                Long.parseLong(trafficCenterTF.getText()),
                Integer.parseInt(sizeTF.getText()),
                Long.parseLong(officeTF.getText()),
                selectedDriverId));
        String message;
        if(Objects.nonNull(vehicle.getId())){
            message = "Vehicle added";
        }else {
            message = "something went wrong";
        }
        Notifications.create().title("Info").text(message).showInformation();
    }
}
