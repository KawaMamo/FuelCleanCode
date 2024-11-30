package com.example.desktop.transferMaterial;

import com.example.model.TableController;
import com.example.model.gasStation.GasStationService;
import com.example.model.material.MaterialService;
import com.example.model.modal.Modal;
import com.example.model.tools.FormType;
import com.example.model.tools.QueryBuilder;
import com.example.model.transLine.TransLineService;
import com.example.model.transLog.TransLogService;
import com.example.model.transferMaterial.TransferMaterialsService;
import com.example.model.vehicle.VehicleService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import org.example.contract.request.create.CreateTransLogRequest;
import org.example.contract.request.create.CreateTransferMaterialRequest;
import org.example.contract.request.update.UpdateTransferMaterialRequest;
import org.example.model.*;

import java.util.List;
import java.util.Objects;

public class AddTransferMaterial {

    public static TableController controller;
    public static FormType formType = FormType.CREATE;
    private final TransferMaterialsService transferMaterialsService = TransferMaterialsService.getInstance();
    private final GasStationService gasStationService = GasStationService.getInstance();
    private final MaterialService materialService = MaterialService.getInstance();
    private final VehicleService vehicleService = VehicleService.getInstance();
    private final TransLineService transLineService = TransLineService.getInstance();
    private final TransLogService transLogService = TransLogService.getInstance();
    private Long selectedSourceId;
    private Long selectedDestinationId;
    private Long selectedMaterialId;
    public static Long selectedVehicleId;
    @FXML
    private TextField amountTF;

    @FXML
    private ChoiceBox<String> currencyCB;

    @FXML
    private TextField destinationTF;

    @FXML
    private TextField materialTF;

    @FXML
    private TextField priceTF;

    @FXML
    private TextField sourceTF;

    @FXML
    private Button submitBtn;
    @FXML
    private Label infoLabel;
    @FXML
    private TextField driverTF;
    @FXML
    private TextField vehicleTF;
    private GasStation selectedSource;
    private TransLine selectedTransLine;

    @FXML
    private void initialize(){
        submitBtn.setDisable(true);
        currencyCB.getItems().add("USD");
        currencyCB.getItems().add("SP");
        if(formType.equals(FormType.UPDATE)){
            final TransferMaterials item = transferMaterialsService.getItem(TransferMaterialsTblCont.selectedTransferMaterials.getId());
            sourceTF.setText(item.getSource().getName());
            destinationTF.setText(item.getDestination().getName());
            materialTF.setText(item.getMaterial().getName());
            priceTF.setText(item.getPrice().getAmount().toString());
            currencyCB.setValue(item.getPrice().getCurrency());
            selectedSourceId = item.getSource().getId();
            selectedDestinationId = item.getDestination().getId();
            selectedMaterialId = item.getMaterial().getId();
        }

        final List<GasStation> gasStations = gasStationService.getItems(null, null);
        final List<String> gasStationNames = gasStations.stream().map(Place::getName).toList();
        TextFields.bindAutoCompletion(sourceTF, gasStationNames);

        TextFields.bindAutoCompletion(destinationTF, gasStationNames);
        final List<Material> materials = materialService.getItems(null, null);
        final List<String> materialsNames = materials.stream().map(Material::getName).toList();
        TextFields.bindAutoCompletion(materialTF, materialsNames);

        sourceTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(sourceTF.getText().length()>0){
                    if(gasStationNames.contains(sourceTF.getText())){
                        final GasStation gasStation = gasStations.get(gasStationNames.indexOf(sourceTF.getText()));
                        if(Objects.nonNull(gasStation)){
                            selectedSource = gasStation;
                            selectedSourceId = gasStation.getId();
                        }
                    }
                }
            }
        });

        destinationTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(destinationTF.getText().length()>0){
                    if(gasStationNames.contains(destinationTF.getText())){
                        final GasStation gasStation = gasStations.get(gasStationNames.indexOf(destinationTF.getText()));
                        if(Objects.nonNull(gasStation)){
                            selectedDestinationId = gasStation.getId();
                            final List<TransLine> transLineList = transLineService.getItems(0,
                                    1,
                                    "&key=source&value=" + selectedSource.getRegion().getId() + "&operation=%3A" +
                                            "&key=destination&value=" + gasStation.getRegion().getId() + "&operation=%3A&sort=id,desc");
                            if(Objects.nonNull(transLineList.get(0))){
                                selectedTransLine = transLineList.get(0);
                                submitBtn.setDisable(false);
                            }else {
                                infoLabel.setText("No transportation line found");
                            }
                        }
                    }
                }
            }
        });

        materialTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(materialTF.getText().length()>0){
                    if(materialsNames.contains(materialTF.getText())){
                        final Material material = materials.get(materialsNames.indexOf(materialTF.getText()));
                        if(Objects.nonNull(material)){
                            selectedMaterialId = material.getId();
                        }
                    }
                }
            }
        });

        final List<Vehicle> vehicles = vehicleService.getVehicles(null, null);
        final List<String> vehiclesNames = vehicles.stream().map(Vehicle::getPlateNumber).toList();
        TextFields.bindAutoCompletion(vehicleTF, vehiclesNames);

        vehicleTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(vehicleTF.getText().length()>0){
                    final Vehicle vehicle = vehicles.get(vehiclesNames.indexOf(vehicleTF.getText()));
                    driverTF.setText(vehicle.getDriver().getName());
                    selectedVehicleId = vehicle.getId();
                }
            }
        });
    }

    @FXML
    void submit() {
        final TransferMaterials transferMaterials;
        if(formType.equals(FormType.UPDATE)){
            transferMaterials = transferMaterialsService.editItem(new UpdateTransferMaterialRequest(TransferMaterialsTblCont.selectedTransferMaterials.getId(),
                    selectedSourceId,
                    selectedDestinationId,
                    selectedMaterialId,
                    Long.parseLong(amountTF.getText()),
                    new Money(currencyCB.getValue(), Double.parseDouble(priceTF.getText()))));
        } else if (formType.equals(FormType.CREATE)) {
            transferMaterials = transferMaterialsService.addItem(new CreateTransferMaterialRequest(selectedSourceId,
                    selectedDestinationId,
                    selectedMaterialId,
                    Long.parseLong(amountTF.getText()),
                    new Money(currencyCB.getValue(), Double.parseDouble(priceTF.getText()))));
            final CreateTransLogRequest createTransLogRequest = new CreateTransLogRequest(selectedVehicleId, selectedTransLine.getId(), selectedTransLine.getFee(), transferMaterials.getId(), " ");
            final TransLog transLog = transLogService.addItem(createTransLogRequest);
        }else {
            transferMaterials = new TransferMaterials();
            final QueryBuilder queryBuilder = new QueryBuilder();
            if(amountTF.getText().length() > 0) {
                queryBuilder.addQueryParameter("amount", amountTF.getText());
            }
            if(Objects.nonNull(selectedSourceId))
                queryBuilder.addQueryParameter("source", selectedSourceId.toString());
            if(Objects.nonNull(selectedDestinationId))
                queryBuilder.addQueryParameter("destination", selectedDestinationId.toString());
            if(Objects.nonNull(selectedMaterialId))
                queryBuilder.addQueryParameter("material", selectedMaterialId.toString());
            if(priceTF.getText().length()>0)
                queryBuilder.addQueryParameter("priceAmount", priceTF.getText());
            if(Objects.nonNull(currencyCB.getValue()) && currencyCB.getValue().length()>0)
                queryBuilder.addQueryParameter("priceCurrency", currencyCB.getValue());
            queryBuilder.sort();
            controller.setQuery(queryBuilder.getQuery());

        }
        notify(transferMaterials);
    }

    private static void notify(TransferMaterials transferMaterials){
        String message;
        if(Objects.nonNull(transferMaterials.getId())){
            message = "transferMaterials added";
        }else {
            message = "something went wrong";
        }
        controller.addData(transferMaterials);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }

}
