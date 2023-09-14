package com.example.desktop.trafficCenter;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.trafficCenter.TrafficCenterService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.TrafficCenter;

public class TrafficCenters implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<TrafficCenter> tableTbl;

    private static ObservableList<TrafficCenter> trafficCenters;
    public static TrafficCenter selectedTrafficCenter;
    private final TrafficCenterService trafficCenterService = TrafficCenterService.getInstance();

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, TrafficCenter, t1) -> selectedTrafficCenter = t1);
    }

    private void setTable() {
        TableColumn<TrafficCenter, String> idColumn = new TableColumn<>("التسلسل");
        idColumn.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getId().toString()));

        TableColumn<TrafficCenter, String> nameCol = new TableColumn<>("الاسم");
        nameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        TableColumn<TrafficCenter, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCreatedAt().toString()));

        TableColumn<TrafficCenter, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(
                new PropertyValueFactory<>("updatedAt"));

        tableTbl.getColumns().addAll(idColumn, nameCol, createdAtCol, updatedAtCol);
        tableTbl.setItems(trafficCenters);

    }

    @FXML
    void add() {
        AddTrafficCenter.controller = this;
        Modal.start(this.getClass(), "addTrafficCenter.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.deleteUrl = trafficCenterService.getEndPoint();
        DeleteConfirmation.selected = selectedTrafficCenter.getId();
        DeleteConfirmation.controller = this;
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddTrafficCenter.controller = this;
        AddTrafficCenter.isEditingForm = true;
        Modal.start(this.getClass(), "addTrafficCenter.fxml");
    }

    @FXML
    void pageDown() {

    }

    @FXML
    void pageUp() {

    }

    @FXML
    void search() {

    }
    @Override
    public void removeData() {
        trafficCenters.remove(selectedTrafficCenter);
        loadData();
    }

    @Override
    public void addData(Object object) {
        trafficCenters.add((TrafficCenter) object);
        loadData();
    }

    @Override
    public void loadData() {
        trafficCenters = FXCollections.observableArrayList(trafficCenterService.getItems(null, null));
        tableTbl.setItems(trafficCenters);
    }
}
