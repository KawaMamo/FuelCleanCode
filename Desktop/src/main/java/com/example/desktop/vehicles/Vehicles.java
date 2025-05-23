package com.example.desktop.vehicles;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.tools.FormType;
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

import java.util.List;
import java.util.Objects;

public class Vehicles implements TableController {

    @FXML
    private TextField page;
    @FXML
    private TableView<Vehicle> tableTbl;
    private static ObservableList<Vehicle> vehicles;
    private final VehicleService vehicleService = VehicleService.getInstance();
    public static Vehicle selectedVehicle;
    private String query = null;
    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, vehicle, t1) -> selectedVehicle = t1);
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
        final List<Vehicle> vehicleList;
        if(Objects.isNull(query))
            vehicleList = vehicleService.getVehicles(Integer.parseInt(page.getText()) - 1, 15);
        else
            vehicleList = vehicleService.getVehicles(Integer.parseInt(page.getText()) - 1, 15, query);
        if(Objects.nonNull(vehicleList))
            vehicles = FXCollections.observableArrayList(vehicleList);
        tableTbl.setItems(vehicles);
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }

    public void removeData(){
        vehicles.remove(selectedVehicle);
        loadData();
    }

    @Override
    public void addData(Object obj) {
        vehicles.add((Vehicle) obj);
        loadData();
    }

    @FXML
    void add() {
        AddVehicle.controller = this;
        AddVehicle.formType = FormType.CREATE;
        Modal.start(Vehicles.class, "addVehicle.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.selected = selectedVehicle.getId();
        DeleteConfirmation.deleteUrl = "api/v1/vehicle";
        DeleteConfirmation.controller = this;
        Modal.start(Vehicle.class, "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddVehicle.controller = this;
        AddVehicle.formType = FormType.UPDATE;
        Modal.start(Vehicles.class, "addVehicle.fxml");
    }

    @FXML
    void pageDown() {
        page.setText(String.valueOf(Integer.parseInt(page.getText())-1));
        loadData();
    }

    @FXML
    void pageUp() {
        page.setText(String.valueOf(Integer.parseInt(page.getText())+1));
        loadData();
    }

    @FXML
    void search(){
        AddVehicle.controller = this;
        AddVehicle.formType = FormType.GET;
        Modal.start(Vehicles.class, "addVehicle.fxml");
    }


}
