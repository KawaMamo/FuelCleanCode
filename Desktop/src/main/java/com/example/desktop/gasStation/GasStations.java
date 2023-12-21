package com.example.desktop.gasStation;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.gasStation.GasStationService;
import com.example.model.modal.Modal;
import com.example.model.tools.FormType;
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
import java.util.List;
import java.util.Objects;

public class GasStations implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<GasStation> tableTbl;

    private static ObservableList<GasStation> gasStations;
    public static GasStation selectedGasStation;
    private final GasStationService gasStationService = GasStationService.getInstance();
    private String query = null;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, GasStation, t1) -> selectedGasStation = t1);
    }

    @FXML
    void add() {
        AddGasStation.controller = this;
        AddGasStation.formType = FormType.CREATE;
        Modal.start(GasStations.class, "addGasStation.fxml");
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
        AddGasStation.controller = this;
        AddGasStation.formType = FormType.UPDATE;
        Modal.start(GasStations.class, "addGasStation.fxml");
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
    void search() {
        AddGasStation.controller = this;
        AddGasStation.formType = FormType.GET;
        Modal.start(GasStations.class, "addGasStation.fxml");
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

        TableColumn<GasStation, String> translationCol = new TableColumn<>("الترجمة");
        translationCol.setCellValueFactory(new PropertyValueFactory<>("translation"));

        TableColumn<GasStation, String> priceCategoryCol = new TableColumn<>("الفئة الافتراضية");
        priceCategoryCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPriceCategory().getName()));

        TableColumn<GasStation, String> materialCol = new TableColumn<>("المادة الافتراضية");
        materialCol.setCellValueFactory(data -> {
            if(Objects.nonNull(data.getValue().getMaterial()))
                return new SimpleStringProperty(data.getValue().getMaterial().getName());
            else return new SimpleStringProperty("NA");
        });

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

        tableTbl.getColumns().addAll(idCol, nameCol, translationCol, priceCategoryCol, materialCol, ownerCol, debtLimitCol, regionCol, groupCol, createdAtCol, updatedAtCol);
        tableTbl.setItems(gasStations);
    }

    public void loadData() {
        final List<GasStation> items;
        if(Objects.isNull(query))
            items = gasStationService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        else {
            items = gasStationService.getItems(Integer.parseInt(page.getText()) - 1, 15, query);
        }
        if(Objects.nonNull(items))
            gasStations = FXCollections.observableArrayList(items);
        else gasStations = null;
        tableTbl.setItems(gasStations);
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }
}
