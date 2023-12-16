package com.example.desktop.reports;

import com.example.desktop.HelloApplication;
import com.example.model.transLog.TransLogService;
import com.example.model.vehicle.VehicleService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.example.model.Vehicle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class DriverReport {

    @FXML
    private ToggleButton commercialTB;

    @FXML
    private DatePicker endDP;

    @FXML
    private ToggleButton normalTB;

    @FXML
    private TextField page;

    @FXML
    private DatePicker startDP;

    @FXML
    private TableView<Vehicle> tableTbl;
    private static ObservableList<Vehicle> vehicles;
    private final VehicleService vehicleService = VehicleService.getInstance();
    public static Vehicle selectedVehicle;
    private final TransLogService transLogService = TransLogService.getInstance();

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, vehicle, t1) -> selectedVehicle = t1);
    }

    @FXML
    void pageDown() {

    }

    @FXML
    void pageUp() {

    }

    @FXML
    void report() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"));
        final File file = fileChooser.showSaveDialog(HelloApplication.primaryStage);
        String transType = normalTB.isSelected() ? "NORMAL":"COMMERCIAL";
        final byte[] bytes = transLogService.getReport(file.getName().split("\\.")[1].toUpperCase(),
                transType,
                startDP.getValue(),
                endDP.getValue(),
                selectedVehicle.getId());

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(Base64.getDecoder().decode(bytes));
            Runtime.getRuntime().exec("rundll32.exe shell32.dll ShellExec_RunDLL " +file.getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTable() {
        TableColumn<Vehicle, String> idCol = new TableColumn<>("التسلسل");
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        TableColumn<Vehicle, String> plateNumberCol = new TableColumn<>("رقم اللوحة");
        plateNumberCol.setCellValueFactory(
                new PropertyValueFactory<>("plateNumber"));

        TableColumn<Vehicle, String> trafficCenterCol = new TableColumn<>("مرور");
        trafficCenterCol.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getTrafficCenter().getName())
        );

        TableColumn<Vehicle, String> sizeCol = new TableColumn<>("الحجم");
        sizeCol.setCellValueFactory(
                new PropertyValueFactory<>("size"));

        TableColumn<Vehicle, String> officeCol = new TableColumn<>("مكتب");
        officeCol.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getOffice().getName()));

        TableColumn<Vehicle, String> driverCol = new TableColumn<>("السائق");
        driverCol.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getDriver().getName()));

        TableColumn<Vehicle, String> turnCol = new TableColumn<>("الدور");
        turnCol.setCellValueFactory(new PropertyValueFactory<>("turn"));

        TableColumn<Vehicle, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(
                new PropertyValueFactory<>("createdAt"));

        TableColumn<Vehicle, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(
                new PropertyValueFactory<>("updatedAt"));

        tableTbl.getColumns().addAll(idCol, plateNumberCol, trafficCenterCol, sizeCol, officeCol, driverCol, turnCol, createdAtCol, updatedAtCol);
        tableTbl.setItems(vehicles);
    }

    public void loadData() {
        vehicles = FXCollections.observableArrayList(vehicleService.getVehicles(Integer.parseInt(page.getText())-1, 15));
        tableTbl.setItems(vehicles);
    }
}
