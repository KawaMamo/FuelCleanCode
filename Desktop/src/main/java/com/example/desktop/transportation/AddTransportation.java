package com.example.desktop.transportation;

import com.example.model.TableController;
import com.example.model.category.CategoryService;
import com.example.model.gasStation.GasStationService;
import com.example.model.material.MaterialService;
import com.example.model.modal.Modal;
import com.example.model.partition.PartitionService;
import com.example.model.priceCategory.PriceCategoryService;
import com.example.model.refinery.RefineryService;
import com.example.model.transportation.TransportationService;
import com.example.model.vehicle.VehicleService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import org.example.contract.request.create.CreatePartitionRequest;
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
    private TextField vehicleTF;

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
    private TextField refineryTF;

    @FXML
    private TextField sizeTF;

    @FXML
    private TextField sourceTF;

    @FXML
    private TextField stationTF;

    @FXML
    private Button transBtn;
    @FXML
    private TableView<TransLine> transLineTbl;

    Transportation transportation = null;

    private final RefineryService refineryService = RefineryService.getInstance();
    private final VehicleService vehicleService = VehicleService.getInstance();
    private final TransportationService transportationService = TransportationService.getInstance();
    private final GasStationService gasStationService = GasStationService.getInstance();
    private final CategoryService categoryService = CategoryService.getInstance();
    private final PriceCategoryService priceCategoryService = PriceCategoryService.getInstance();
    private final MaterialService materialService = MaterialService.getInstance();
    private final PartitionService partitionService = PartitionService.getINSTANCE();

    private Long selectedRefineryId;
    private Long selectedVehicleId;
    private Long selectedGasStationId;
    private Long selectedRegionId;
    private Long selectedPriceCatId;
    private Long selectedMaterialId;
    private Transportation addedTransport;
    List<Category> categories = null;


    @FXML
    private void initialize(){

        setPartitionTbl();
        dateDP.setValue(LocalDate.now());

        final List<Refinery> items = refineryService.getItems(null, null);
        final List<String> refineryNames = items.stream().map(Place::getName).toList();
        TextFields.bindAutoCompletion(refineryTF, refineryNames);


        final List<Vehicle> vehicles = vehicleService.getVehicles(null, null);
        final List<String> vehiclesNames = vehicles.stream().map(Vehicle::getPlateNumber).toList();
        TextFields.bindAutoCompletion(vehicleTF, vehiclesNames);

        final List<Material> materials = materialService.getItems(null, null);
        final List<String> materialNames = materials.stream().map(Material::getName).toList();
        TextFields.bindAutoCompletion(materialTF, materialNames);

        final List<PriceCategory> priceCategories = priceCategoryService.getItems(null, null);
        final List<String> priceCatNames = priceCategories.stream().map(PriceCategory::getName).toList();
        TextFields.bindAutoCompletion(priceCateTF, priceCatNames);

        refineryTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(refineryTF.getText().length()>0){
                    final Refinery refinery = items.get(refineryNames.indexOf(refineryTF.getText()));
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
                    selectedMaterialId = gasStation.getMaterial().getId();
                    partAmountTF.setText(sizeTF.getText());
                    categories = categoryService.getItems(0,
                            1,
                            "key=priceCategory&value=" +
                                    gasStation.getPriceCategory().getId() + "&operation=%3A&key=material&value=" +
                                    gasStation.getMaterial().getId() + "&operation=%3A&sort=id,desc");
                    priceLbl.setText(categories.get(0).getPrice().getAmount()+" "+categories.get(0).getPrice().getCurrency());
                }
            }
        });

        priceCateTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(priceCateTF.getText().length()>0){
                    final PriceCategory priceCategory = priceCategories.get(priceCatNames.indexOf(priceCateTF.getText()));
                    if(Objects.nonNull(priceCategory)) {
                        selectedPriceCatId = priceCategory.getId();
                        categories = categoryService.getItems(0,
                                1,
                                "key=priceCategory&value=" +
                                        selectedPriceCatId + "&operation=%3A&key=material&value=" +
                                        selectedMaterialId + "&operation=%3A&sort=id,desc");
                        priceLbl.setText(categories.get(0).getPrice().getAmount()+" "+categories.get(0).getPrice().getCurrency());
                    }

                }
            }
        });

        materialTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(materialTF.getText().length()>0){
                    final Material material = materials.get(materialNames.indexOf(materialTF.getText()));
                    if(Objects.nonNull(material)) {
                        selectedMaterialId = material.getId();
                        categories = categoryService.getItems(0,
                                1,
                                "key=priceCategory&value=" +
                                        selectedPriceCatId + "&operation=%3A&key=material&value=" +
                                        selectedMaterialId + "&operation=%3A&sort=id,desc");
                        priceLbl.setText(categories.get(0).getPrice().getAmount()+" "+categories.get(0).getPrice().getCurrency());
                    }
                }
            }
        });


    }

    @FXML
    void addTrans() {
        if(Objects.nonNull(selectedRefineryId) && Objects.nonNull(selectedVehicleId)){
            transportation = transportationService.addItem(new CreateTransRequest(selectedRefineryId,
                    selectedVehicleId,
                    Long.parseLong(sizeTF.getText().replaceAll(",", "")),
                    TransportationType.NORMAL));
            notify(transportation);
            addedTransport = transportation;
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
        final CreatePartitionRequest createPartitionRequest = new CreatePartitionRequest(selectedMaterialId,
                Integer.parseInt(partAmountTF.getText().replaceAll(",", "")),
                null,
                new Money(categories.get(0).getPrice().getCurrency(), categories.get(0).getPrice().getAmount()),
                selectedGasStationId,
                notesTF.getText(),
                null,
                addedTransport.getId());
        final Partition partition = partitionService.addItem(createPartitionRequest);
        loadData();
        Notifications.create().title("Info").text("Added "+partition.getGasStation().getName()).showInformation();
    }

    private void setPartitionTbl() {

        TableColumn<Partition, String> destinationColumn = new TableColumn<>("Gas Station");
        destinationColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGasStation().getName()));

        TableColumn<Partition, String> materialColumn = new TableColumn<>("material");
        materialColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMaterial().getName()));

        TableColumn<Partition, String> amountColumn = new TableColumn<>("amount");
        amountColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAmount().toString()));

        TableColumn<Partition, String> priceColumn = new TableColumn<>("price");
        priceColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrice().getAmount()+" "+data.getValue().getPrice().getCurrency()));

        TableColumn<Partition, String> notesColumn = new TableColumn<>("notes");
        notesColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNotes()));

        partitionsTbl.getColumns().addAll(destinationColumn, materialColumn, amountColumn, priceColumn, notesColumn);

    }

    void loadData(){
        partitionsTbl.setItems(FXCollections.observableList(partitionService.getItems(
                0,
                10,
                "key=transportationEntity&value="+transportation.getId()+"&operation=%3A&sort=id,desc")));
    }

    @FXML
    void close() {
        controller.addData(transportation);
        Modal.close();
    }

    @FXML
    void addForfeit() {

    }

    @FXML
    void addLineFee() {

    }


}
