package com.example.desktop.transportation;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.transportation.TransportationService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Transportation;

import java.text.NumberFormat;
import java.util.List;
import java.util.Objects;

public class Transportations implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<Transportation> tableTbl;
    private static ObservableList<Transportation> observableList;
    public static Transportation selectedTransportation;
    private final TransportationService transportationService = TransportationService.getInstance();

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, Transportation, t1) -> selectedTransportation = t1);
    }

    private void setTable() {

        TableColumn<Transportation, String> idCol = new TableColumn<>("id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Transportation, String> vehicleCol = new TableColumn<>("vehicle");
        vehicleCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVehicle().getPlateNumber()+" "+
                data.getValue().getVehicle().getDriver().getName()));

        TableColumn<Transportation, String> refineryCol = new TableColumn<>("refinery");
        refineryCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRefinery().getName()));

        TableColumn<Transportation, String> sizeCol = new TableColumn<>("size");
        sizeCol.setCellValueFactory(data -> new SimpleStringProperty(NumberFormat.getInstance().format(data.getValue().getSize())));

        TableColumn<Transportation, String> partitionsCol = new TableColumn<>("partitions");
        partitionsCol.setCellValueFactory(
                data -> {
                    StringBuilder partitionString = new StringBuilder();
                    if(Objects.nonNull(data.getValue().getPartitions())){
                        data.getValue().getPartitions().forEach(partition -> partitionString.append(partition.getGasStation().getName()).append(" "));
                        return new SimpleStringProperty(partitionString.toString());
                    }else return new SimpleStringProperty("NA");
                }
        );

        tableTbl.getColumns().addAll(idCol, vehicleCol, refineryCol, sizeCol, partitionsCol);
        tableTbl.setItems(observableList);
    }

    @FXML
    void add() {
        AddTransportation.controller = this;
        AddTransportation.isEditingForm = false;
        Modal.start(this.getClass(), "addTransportation.fxml");
    }

    @FXML
    void delete() {

    }

    @FXML
    void edit() {

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

    }

    @Override
    public void addData(Object object) {

    }

    @Override
    public void loadData() {
        final List<Transportation> items = transportationService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        if(Objects.nonNull(items))
            observableList = FXCollections.observableList(items);
        tableTbl.setItems(observableList);
    }
}
