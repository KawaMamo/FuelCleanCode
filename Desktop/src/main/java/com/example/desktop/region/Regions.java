package com.example.desktop.region;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.region.RegionService;
import com.example.model.tools.FormType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Region;

import java.util.List;
import java.util.Objects;

public class Regions implements TableController {

    @FXML
    private TextField page;
    private static ObservableList<Region> regions;
    public static Region selectedRegion;
    private final RegionService regionService = RegionService.getInstance();

    @FXML
    private TableView<Region> tableTbl;
    private String query = null;

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
        TableColumn<Region, String> translationColumn = new TableColumn<>("translation");
        translationColumn.setCellValueFactory(new PropertyValueFactory<>("translation"));

        tableTbl.getColumns().addAll(idColumn, nameColumn, translationColumn, createdAtCol, updatedAtCol);
        tableTbl.setItems(regions);
    }

    @FXML
    void add() {
        AddRegion.controller = this;
        AddRegion.formType = FormType.CREATE;
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
        AddRegion.formType = FormType.UPDATE;
        Modal.start(this.getClass(), "addRegion.fxml");
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
        AddRegion.controller = this;
        AddRegion.formType = FormType.GET;
        Modal.start(this.getClass(), "addRegion.fxml");
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
        final List<Region> items;
        if(Objects.isNull(query))
            items = regionService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        else
            items = regionService.getItems(Integer.parseInt(page.getText()) - 1, 15, query);

        if(Objects.nonNull(items))
            regions = FXCollections.observableArrayList(items);
        else regions = null;
        tableTbl.setItems(regions);
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }
}
