package com.example.desktop.transportation;

import com.example.desktop.HelloApplication;
import com.example.desktop.MyDocumentLocator;
import com.example.model.TableController;
import com.example.model.category.CategoryService;
import com.example.model.gasStation.GasStationService;
import com.example.model.material.MaterialService;
import com.example.model.modal.Modal;
import com.example.model.partition.PartitionService;
import com.example.model.priceCategory.PriceCategoryService;
import com.example.model.refinery.RefineryService;
import com.example.model.tools.FormType;
import com.example.model.tools.QueryBuilder;
import com.example.model.transLine.TransLineService;
import com.example.model.transLog.TransLogService;
import com.example.model.transportation.TransportationService;
import com.example.model.user.LogInData;
import com.example.model.vehicle.VehicleService;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import org.example.contract.request.create.CreatePartitionRequest;
import org.example.contract.request.create.CreateTransLogRequest;
import org.example.contract.request.create.CreateTransRequest;
import org.example.contract.request.update.UpdateTransRequest;
import org.example.model.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;

public class AddTransportation {

    public static TableController controller;
    public static FormType formType = FormType.CREATE;


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
    private ChoiceBox<String> materialTF;

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
    private TableView<TransLog> transLineTbl;
    @FXML
    private TextField lineNotesTF;

    Transportation transportation = null;
    @FXML
    private ToggleButton normalTB;
    @FXML
    private ToggleButton tradeTB;

    private final RefineryService refineryService = RefineryService.getInstance();
    private final VehicleService vehicleService = VehicleService.getInstance();
    private final TransportationService transportationService = TransportationService.getInstance();
    private final GasStationService gasStationService = GasStationService.getInstance();
    private final CategoryService categoryService = CategoryService.getInstance();
    private final PriceCategoryService priceCategoryService = PriceCategoryService.getInstance();
    private final MaterialService materialService = MaterialService.getInstance();
    private final PartitionService partitionService = PartitionService.getINSTANCE();
    private final TransLineService transLineService = TransLineService.getInstance();
    private final TransLogService transLogService = TransLogService.getInstance();

    private Long selectedRefineryId;
    private Long selectedRefineryRegionId;
    private Long selectedVehicleId;
    private Long selectedGasStationId;
    private Long selectedRegionId;
    private Long selectedPriceCatId;
    private Long selectedMaterialId;
    private Transportation addedTransport;
    List<Category> categories = null;

    private Partition selectedPartition;
    private TransLog selectedTransLog;
    ToggleGroup normalTradeGroup = new ToggleGroup();
    TransportationType type = TransportationType.NORMAL;

    @FXML
    private void initialize(){

        setPartitionTbl();
        setTransLogTable();
        dateDP.setValue(LocalDate.now());

        final List<Refinery> items = refineryService.getItems(null, null);
        final List<String> refineryNames = items.stream().map(Place::getTranslation).toList();
        TextFields.bindAutoCompletion(refineryTF, refineryNames);


        final List<Vehicle> vehicles = vehicleService.getVehicles(null, null);
        final List<String> vehiclesNames = vehicles.stream().map(Vehicle::getPlateNumber).toList();
        TextFields.bindAutoCompletion(vehicleTF, vehiclesNames);

        final List<Material> materials = materialService.getItems(null, null);
        final List<String> materialNames = materials.stream().map(Material::getName).toList();
        materialTF.setItems(FXCollections.observableList(materialNames));

        final List<PriceCategory> priceCategories = priceCategoryService.getItems(null, null);
        final List<String> priceCatNames = priceCategories.stream().map(PriceCategory::getName).toList();
        TextFields.bindAutoCompletion(priceCateTF, priceCatNames);

        refineryTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(refineryTF.getText().length()>0){
                    final Refinery refinery = items.get(refineryNames.indexOf(refineryTF.getText()));
                    selectedRefineryId = refinery.getId();
                    selectedRefineryRegionId = refinery.getRegion().getId();
                    sourceTF.setText(refinery.getRegion().getName());
                }
            }
        });

        vehicleTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(vehicleTF.getText().length()>0){
                    final Vehicle vehicle = vehicles.get(vehiclesNames.indexOf(vehicleTF.getText()));
                    driverTF.setText(vehicle.getDriver().getName());
                    sizeTF.setText(vehicle.getSize().toString());
                    selectedVehicleId = vehicle.getId();
                }
            }
        });

        final List<GasStation> gasStations = gasStationService.getItems(null, null);
        final List<String> stationList = gasStations.stream().map(GasStation::getTranslation).toList();
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
                    materialTF.setValue(gasStation.getMaterial().getName());
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

        priceCateTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                notesTF.setText(t1);
            }
        });

        materialTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(materialTF.getValue().length()>0){
                    final Material material = materials.get(materialNames.indexOf(materialTF.getValue()));
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

        partitionsTbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Partition>() {
            @Override
            public void changed(ObservableValue<? extends Partition> observableValue, Partition partition, Partition t1) {
                selectedPartition = t1;
            }
        });

        transLineTbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TransLog>() {
            @Override
            public void changed(ObservableValue<? extends TransLog> observableValue, TransLog transLog, TransLog t1) {
                selectedTransLog = t1;
            }
        });

        partitionsTbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Partition>() {
            @Override
            public void changed(ObservableValue<? extends Partition> observableValue, Partition partition, Partition t1) {
                if(Objects.nonNull(t1)){
                    selectedPartition = t1;
                    final GasStation gasStation = t1.getGasStation();
                    stationTF.setText(gasStation.getName());
                    notesTF.setText(t1.getNotes());
                    materialTF.setValue(t1.getMaterial().getName());
                    partAmountTF.setText(t1.getAmount().toString());
                    priceLbl.setText(t1.getPrice().getAmount()+" "+t1.getPrice().getCurrency());
                    selectedMaterialId = t1.getMaterial().getId();
                    selectedRegionId = gasStation.getRegion().getId();
                    selectedGasStationId = gasStation.getId();
                    categories = categoryService.getItems(0,
                            1,
                            "key=priceCategory&value=" +
                                    gasStation.getPriceCategory().getId() + "&operation=%3A&key=material&value=" +
                                    gasStation.getMaterial().getId() + "&operation=%3A&sort=id,desc");
                }

            }
        });

        normalTB.setToggleGroup(normalTradeGroup);
        tradeTB.setToggleGroup(normalTradeGroup);
        normalTB.setSelected(true);
        setToggleButtonsColor(type);

        normalTB.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(t1) {
                    type = TransportationType.NORMAL;
                    setToggleButtonsColor(type);
                }
            }
        });

        tradeTB.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                type = TransportationType.COMMERCIAL;
                setToggleButtonsColor(type);
            }
        });


        if(formType.equals(FormType.UPDATE)){
            transportation = transportationService.getItem(Transportations.selectedTransportation.getId());
            refineryTF.setText(transportation.getRefinery().getName());
            vehicleTF.setText(transportation.getVehicle().getPlateNumber());
            Platform.runLater(()-> vehicleTF.requestFocus());
            loadData();
            loadTransData();
            transBtn.setText("تعديل");
        }

    }

    private void setToggleButtonsColor(TransportationType transportationType){
        if(transportationType.equals(TransportationType.NORMAL)){
            normalTB.setStyle("-fx-background-color:orange;");
            tradeTB.setStyle("-fx-background-color:silver;");
        }else if(transportationType.equals(TransportationType.COMMERCIAL)){
            normalTB.setStyle("-fx-background-color:silver;");
            tradeTB.setStyle("-fx-background-color:orange;");
        }
    }

    @FXML
    void addTrans() {
        if(formType.equals(FormType.UPDATE)){
            if(Objects.nonNull(selectedRefineryId) && Objects.nonNull(selectedVehicleId)){
                transportation = transportationService.editItem(new UpdateTransRequest(transportation.getId(),
                        selectedRefineryId,
                        selectedVehicleId,
                        Long.parseLong(sizeTF.getText().replaceAll(",", "")),
                        type));
                controller.loadData();
                notify(transportation);
                addedTransport = transportation;
            }

        }else if(formType.equals(FormType.CREATE)){
            if(Objects.nonNull(selectedRefineryId) && Objects.nonNull(selectedVehicleId)){
                transportation = transportationService.addItem(new CreateTransRequest(selectedRefineryId,
                        selectedVehicleId,
                        Long.parseLong(sizeTF.getText().replaceAll(",", "")),
                        type));
                controller.addData(transportation);
                notify(transportation);
                addedTransport = transportation;
            }
        }else {

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
                transportation.getId());
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

    private void loadData(){
        final List<Partition> partitions = partitionService.getItems(
                0,
                10,
                "key=transportationEntity&value=" + transportation.getId() + "&operation=%3A&sort=id,desc");
        final ObservableList<Partition> partitionObservableList = FXCollections.observableList(partitions);
        partitionsTbl.setItems(partitionObservableList);
    }

    private void loadTransData(){
        transLineTbl.setItems(FXCollections.observableList(transLogService.getItems(0,
                10,
                "key=transportation&value="+transportation.getId()+"&operation=%3A&sort=id,desc")));
    }

    @FXML
    void close() {
        controller.addData(transportation);
        Modal.close();
    }


    @FXML
    void addLineFee() {
        final List<TransLine> transLineList = transLineService.getItems(0,
                10,
                "&key=source&value=" + selectedRefineryRegionId + "&operation=%3A" +
                        "&key=destination&value=" + selectedRegionId + "&operation=%3A&sort=id,desc");
        final TransLine transLine = transLineList.get(0);
        final CreateTransLogRequest createTransLogRequest = new CreateTransLogRequest(transportation.getVehicle().getId(),
                transLine.getId(),
                new Money(transLine.getFee().getCurrency(),
                        transLine.getFee().getAmount()*Double.parseDouble(partAmountTF.getText().replaceAll(",", ""))),
                transportation.getId(),
                lineNotesTF.getText());
        final TransLog transLog = transLogService.addItem(createTransLogRequest);
        loadTransData();
        Notifications.create().title("Info").text("Added "+transLog.getNotes().toString()).showInformation();
    }

    private void setTransLogTable(){

        TableColumn<TransLog, String> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<TransLog, String> feeColumn = new TableColumn<>("fee");
        feeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFees().getAmount()+" "+data.getValue().getFees().getCurrency()));

        TableColumn<TransLog, String> noteColumn = new TableColumn<>("notes");
        noteColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNotes()));

        transLineTbl.getColumns().addAll(idColumn, feeColumn, noteColumn);
    }


    @FXML
    void deletePartition() {
        final Partition delete = partitionService.delete(selectedPartition.getId());
        loadData();
    }

    @FXML
    void deleteTransLog() {
        final TransLog transLog = transLogService.delete(selectedTransLog.getId());
        loadTransData();
    }

    @FXML
    void printPartition(){
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html"));
        fileChooser.setInitialFileName("print.html");
        fileChooser.setInitialDirectory(new File(MyDocumentLocator.locate()));

        final File file = fileChooser.showSaveDialog(HelloApplication.primaryStage);
        final byte[] bytes = partitionService.getPrint(selectedPartition.getId(), LogInData.loggedInUser.getEmail());

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(Base64.getDecoder().decode(bytes));
            Runtime.getRuntime().exec("rundll32.exe shell32.dll ShellExec_RunDLL " +file.getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
