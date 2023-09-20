package com.example.desktop.vehicles;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.office.OfficeService;
import com.example.model.person.PersonService;
import com.example.model.trafficCenter.TrafficCenterService;
import com.example.model.vehicle.VehicleService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import org.example.contract.request.create.CreateVehicleRequest;
import org.example.contract.request.update.UpdateVehicleRequest;
import org.example.model.Office;
import org.example.model.Person;
import org.example.model.TrafficCenter;
import org.example.model.Vehicle;

import java.util.List;
import java.util.Objects;

public class AddVehicle {
    public static TableController controller;
    public static Boolean isEditingForm = false;
    @FXML
    private TextField driverTF;

    @FXML
    private TextField officeTF;

    @FXML
    private TextField plateNumberTF;

    @FXML
    private TextField sizeTF;

    @FXML
    private TextField turnTF;

    @FXML
    private Button submitBtn;

    @FXML
    private TextField trafficCenterTF;
    VehicleService vehicleService = VehicleService.getInstance();
    PersonService personService = PersonService.getInstance();
    TrafficCenterService trafficCenterService = TrafficCenterService.getInstance();
    private final OfficeService officeService = OfficeService.getInstance();
    private Long selectedDriverId;
    private Long selectedOfficeId;
    private Long selectedTrafficCenterId;

    @FXML
    void initialize() {

        final List<Person> personList = personService.getItems(null, null);
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

        final List<TrafficCenter> trafficCenters = trafficCenterService.getItems(null, null);
        final List<String> trafficNames = trafficCenters.stream().map(TrafficCenter::getName).toList();
        TextFields.bindAutoCompletion(trafficCenterTF, trafficNames);

        trafficCenterTF.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(trafficCenterTF.getText().length()>0){
                if(trafficNames.contains(trafficCenterTF.getText())){
                    final TrafficCenter trafficCenter = trafficCenters.get(trafficNames.indexOf(trafficCenterTF.getText()));
                    if(Objects.nonNull(trafficCenter))
                        selectedTrafficCenterId = trafficCenter.getId();
                }else {
                    selectedTrafficCenterId = null;
                }
            }
        });

        final List<Office> officeList = officeService.getItems(null, null);
        final List<String> officeNames = officeList.stream().map(Office::getName).toList();
        TextFields.bindAutoCompletion(officeTF, officeNames);

        officeTF.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(officeTF.getText().length()>0){
                if(officeNames.contains(officeTF.getText())){
                    final Office office = officeList.get(officeNames.indexOf(officeTF.getText()));
                    if(Objects.nonNull(office))
                        selectedOfficeId = office.getId();
                }else {
                    selectedOfficeId = null;
                }
            }
        });

        if(isEditingForm){
            final Vehicle vehicle = vehicleService.getVehicle(Vehicles.selectedVehicle.getId());
            driverTF.setText(vehicle.getDriver().getName());
            officeTF.setText(vehicle.getOffice().getName());
            plateNumberTF.setText(vehicle.getPlateNumber());
            sizeTF.setText(vehicle.getSize().toString());
            trafficCenterTF.setText(vehicle.getTrafficCenter().getName());
            selectedDriverId = vehicle.getDriver().getId();
            selectedOfficeId = vehicle.getOffice().getId();
            selectedTrafficCenterId = vehicle.getTrafficCenter().getId();
        }

    }

    @FXML
    void submit() {

        final Vehicle vehicle;
        if(isEditingForm){
            vehicle = vehicleService.editVehicle(new UpdateVehicleRequest(Vehicles.selectedVehicle.getId(),
                    Long.parseLong(turnTF.getText()),
                    plateNumberTF.getText(),
                    selectedTrafficCenterId,
                    Integer.valueOf(sizeTF.getText()),
                    selectedOfficeId,
                    selectedDriverId));
            controller.loadData();
        }else {
            vehicle = vehicleService.addVehicle(new CreateVehicleRequest(
                    Long.parseLong(turnTF.getText()),
                    plateNumberTF.getText(),
                    selectedTrafficCenterId,
                    Integer.parseInt(sizeTF.getText()),
                    selectedOfficeId,
                    selectedDriverId));
            controller.addData(vehicle);
        }
        isEditingForm = false;
        notifyUser(vehicle);
    }

    private static void notifyUser(Vehicle vehicle) {
        String message;
        if(Objects.nonNull(vehicle.getId())){
            message = "Vehicle added Or Edited";
        }else {
            message = "something went wrong";
        }
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
