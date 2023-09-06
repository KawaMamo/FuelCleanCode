package com.example.desktop.gasStation;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.gasStation.GasStationService;
import com.example.model.modal.Modal;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.GasStation;

import java.text.NumberFormat;

public class GasStations implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<GasStation> tableTbl;

    private static ObservableList<GasStation> gasStations;
    public static GasStation selectedGasStation;
    private final GasStationService gasStationService = GasStationService.getInstance();

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, GasStation, t1) -> selectedGasStation = t1);
    }

    @FXML
    void add() {
        AddGasStation.controller = this;
        Modal.start(GasStations.class, "AddGasStation.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.selected = selectedGasStation.getId();
        DeleteConfirmation.deleteUrl = gasStationService.getEndPoint();
        DeleteConfirmation.controller = this;
        Modal.start(GasStations.class, "/com/example/desktop/delete/deleteConfirmation.fxml");
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
        tableTbl.setItems(gasStations);
    }

    @FXML
    void pageUp() {
        int newPage = Integer.parseInt(page.getText())+1;
        page.setText(String.valueOf(newPage));
        tableTbl.getItems().clear();
        loadData();
        tableTbl.setItems(gasStations);
    }

    @FXML
    void search() {

    }
    @Override
    public void removeData() {
        gasStations.remove(selectedGasStation);
        loadData();
    }

    @Override
    public void addData(Object object) {
        gasStations.add((GasStation) object);
        loadData();
    }

    private void setTable() {
        TableColumn<GasStation, String> idCol = new TableColumn<>("التسلسل");
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        TableColumn<GasStation, String> nameCol = new TableColumn<>("الاسم");
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn<GasStation, String> priceCategoryCol = new TableColumn<>("الفئة الافتراضية");
        priceCategoryCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPriceCategory().getName()));

        TableColumn<GasStation,String> ownerCol = new TableColumn<>("المالك");
        ownerCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOwner().getName()));

        TableColumn<GasStation,String> debtLimitCol = new TableColumn<>("الدين المسموح");
        debtLimitCol.setCellValueFactory(data -> new SimpleStringProperty(NumberFormat.getInstance().format(data.getValue().getDebtLimit())));

        TableColumn<GasStation,String> regionCol = new TableColumn<>("المنطقة");
        regionCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRegion().getName()));

        TableColumn<GasStation,String> groupCol = new TableColumn<>("المجموعة");
        groupCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGroup().getName()));

        TableColumn<GasStation, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(
                new PropertyValueFactory<>("createdAt"));

        TableColumn<GasStation, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(
                new PropertyValueFactory<>("updatedAt"));

        tableTbl.getColumns().addAll(idCol, nameCol, priceCategoryCol, ownerCol, debtLimitCol, regionCol, groupCol, createdAtCol, updatedAtCol);
        tableTbl.setItems(gasStations);
    }

    public void loadData() {
        gasStations = FXCollections.observableArrayList(gasStationService.getItems(null, null));
        tableTbl.setItems(gasStations);
    }
}
