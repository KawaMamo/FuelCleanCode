package com.example.desktop.vehicles;

import com.example.desktop.user.Users;
import com.example.model.modal.Modal;
import com.example.model.vehicle.VehicleService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Vehicle;

public class Vehicles {

    @FXML
    private TextField page;

    @FXML
    private TableView<Vehicle> tableTbl;
    private ObservableList<Vehicle> vehicles;
    private final VehicleService vehicleService = VehicleService.getInstance();

    @FXML
    private void initialize(){
        loadData();
        setTable();
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

        TableColumn<Vehicle, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(
                new PropertyValueFactory<>("createdAt"));

        TableColumn<Vehicle, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(
                new PropertyValueFactory<>("updatedAt"));

        tableTbl.getColumns().addAll(idCol, plateNumberCol, trafficCenterCol, sizeCol, officeCol, driverCol, createdAtCol, updatedAtCol);
        tableTbl.setItems(vehicles);
    }

    private void loadData() {
        vehicles = FXCollections.observableArrayList(vehicleService.getVehicles(Integer.parseInt(page.getText())-1));
    }

    @FXML
    void add() {
        Modal.start(Vehicles.class, "addVehicle.fxml");
    }

    @FXML
    void delete() {

    }

    @FXML
    void edit() {

    }

    @FXML
    void pageDown() {
        int newPage = Integer.parseInt(page.getText())-1;
        page.setText(String.valueOf(newPage));
        tableTbl.getItems().clear();
        loadData();
        tableTbl.setItems(vehicles);
    }

    @FXML
    void pageUp() {
        int newPage = Integer.parseInt(page.getText())+1;
        page.setText(String.valueOf(newPage));
        tableTbl.getItems().clear();
        loadData();
        tableTbl.setItems(vehicles);
    }

    @FXML
    void search(){

    }
}
