package com.example.desktop.transportation;

import com.example.model.TableController;
import com.example.model.category.CategoryService;
import com.example.model.gasStation.GasStationService;
import com.example.model.refinery.RefineryService;
import com.example.model.transportation.TransportationService;
import com.example.model.vehicle.VehicleService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import org.example.contract.request.create.CreateTransRequest;
import org.example.model.*;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class AddTransportation {

    public static TableController controller;
    public static Boolean isEditingForm = false;

    @FXML
    private TextField amountTF;

    @FXML
    private TextField vehicleTF;
    @FXML
    private TextField currencyTF;

    @FXML
    private DatePicker dateDP;

    @FXML
    private Button deliveryBtn;

    @FXML
    private TextField destinationTF;

    @FXML
    private TextField driverTF;

    @FXML
    private Button endBtn;

    @FXML
    private Button forfeitBtn;

    @FXML
    private TextField materialTF;

    @FXML
    private TextField notesTF;

    @FXML
    private TextField partAmountTF;

    @FXML
    private TableView<Partition> partitionsTbl;

    @FXML
    private TextField priceCateTF;

    @FXML
    private Label priceLbl;

    @FXML
    private TextField reasonTF;

    @FXML
    private TextField refineyTF;

    @FXML
    private TextField sizeTF;

    @FXML
    private TextField sourceTF;

    @FXML
    private TextField stationTF;

    @FXML
    private Button transBtn;

    private final RefineryService refineryService = RefineryService.getInstance();
    private final VehicleService vehicleService = VehicleService.getInstance();
    private final TransportationService transportationService = TransportationService.getInstance();
    private final GasStationService gasStationService = GasStationService.getInstance();
    private final CategoryService categoryService = CategoryService.getInstance();

    private Long selectedRefineryId;
    private Long selectedVehicleId;
    private Long selectedGasStationId;
    private Long selectedRegionId;

    @FXML
    private void initialize(){

        dateDP.setValue(LocalDate.now());

        final List<Refinery> items = refineryService.getItems(null, null);
        final List<String> refineryNames = items.stream().map(Place::getName).toList();
        TextFields.bindAutoCompletion(refineyTF, refineryNames);


        final List<Vehicle> vehicles = vehicleService.getVehicles(null, null);
        final List<String> vehiclesNames = vehicles.stream().map(Vehicle::getPlateNumber).toList();
        TextFields.bindAutoCompletion(vehicleTF, vehiclesNames);

        refineyTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(refineyTF.getText().length()>0){
                    final Refinery refinery = items.get(refineryNames.indexOf(refineyTF.getText()));
                    selectedRefineryId = refinery.getId();
                    sourceTF.setText(refinery.getName());
                }
            }
        });

        vehicleTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(vehicleTF.getText().length()>0){
                    final Vehicle vehicle = vehicles.get(vehiclesNames.indexOf(vehicleTF.getText()));
                    driverTF.setText(vehicle.getDriver().getName());
                    sizeTF.setText(NumberFormat.getInstance().format(vehicle.getSize()));
                    selectedVehicleId = vehicle.getId();
                }
            }
        });

        final List<GasStation> gasStations = gasStationService.getItems(null, null);
        final List<String> stationList = gasStations.stream().map(GasStation::getName).toList();
        TextFields.bindAutoCompletion(stationTF, stationList);

        stationTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(stationTF.getText().length()>0){
                    final GasStation gasStation = gasStations.get(stationList.indexOf(stationTF.getText()));
                    selectedGasStationId = gasStation.getId();
                    destinationTF.setText(gasStation.getRegion().getName());
                    selectedRegionId = gasStation.getRegion().getId();
                    priceCateTF.setText(gasStation.getPriceCategory().getName());
                    materialTF.setText(gasStation.getMaterial().getName());
                    partAmountTF.setText(sizeTF.getText());
                    final List<Category> categories = categoryService.getItems(0,
                            1,
                            "key=priceCategory&value=" + gasStation.getPriceCategory().getId() + "&operation=%3A&key=material&value=" + gasStation.getMaterial().getId() + "&operation=%3A&sort=id,desc");
                    priceLbl.setText(categories.get(0).getPrice().getAmount()+" "+categories.get(0).getPrice().getCurrency());
                }
            }
        });
    }

    @FXML
    void addTrans() {
        if(Objects.nonNull(selectedRefineryId) && Objects.nonNull(selectedVehicleId)){
            final Transportation transportation = transportationService.addItem(new CreateTransRequest(selectedRefineryId,
                    selectedVehicleId,
                    Long.parseLong(sizeTF.getText().replaceAll(",", "")),
                    TransportationType.NORMAL));
            notify(transportation);
        }
    }

    private void notify(Transportation transportation) {
        String message;
        if(Objects.nonNull(transportation.getId())){
            message = "transportation added";
            transBtn.setDisable(true);
        }else {
            message = "something went wrong";
        }
        controller.addData(transportation);
        Notifications.create().title("Info").text(message).showInformation();
    }

    @FXML
    void submit() {

    }

    @FXML
    void close() {

    }

    @FXML
    void addForfeit() {

    }

    @FXML
    void addLineFee() {

    }


}
