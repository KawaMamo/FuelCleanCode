package com.example.desktop.region;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.region.RegionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Region;

public class Regions implements TableController {

    @FXML
    private TextField page;
    private static ObservableList<Region> regions;
    public static Region selectedRegion;
    private final RegionService regionService = RegionService.getInstance();

    @FXML
    private TableView<Region> tableTbl;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, Region, t1) -> selectedRegion = t1);
    }

    private void setTable() {
        TableColumn<Region, String> idColumn = new TableColumn<>("التسلسل");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Region, String> nameColumn = new TableColumn<>("الاسم");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Region, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(
                new PropertyValueFactory<>("createdAt"));

        TableColumn<Region, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(
                new PropertyValueFactory<>("updatedAt"));
        tableTbl.getColumns().addAll(idColumn, nameColumn, createdAtCol, updatedAtCol);
        tableTbl.setItems(regions);
    }

    @FXML
    void add() {
        AddRegion.controller = this;
        Modal.start(this.getClass(), "addRegion.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.deleteUrl = regionService.getEndPoint();
        DeleteConfirmation.selected = selectedRegion.getId();
        DeleteConfirmation.controller = this;
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddRegion.controller = this;
        AddRegion.isEditingForm = true;
        Modal.start(this.getClass(), "addRegion.fxml");
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
        regions.remove(selectedRegion);
        loadData();
    }

    @Override
    public void addData(Object object) {
        regions.add((Region) object);
        loadData();
    }

    @Override
    public void loadData() {
        regions = FXCollections.observableArrayList(regionService.getItems(null, null));
        tableTbl.setItems(regions);
    }
}
