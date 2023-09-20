package com.example.desktop.material;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.material.MaterialService;
import com.example.model.modal.Modal;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.model.Material;

public class Materials implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<Material> tableTbl;
    private static ObservableList<Material> materials;
    private final MaterialService materialService = MaterialService.getInstance();
    public static Material selectedMaterial;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, Material, t1) -> selectedMaterial = t1);
    }

    private void setTable() {
        TableColumn<Material, String> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getId().toString()));

        TableColumn<Material, String> nameCol = new TableColumn<>("name");
        nameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        tableTbl.getColumns().addAll(idColumn, nameCol);
        tableTbl.setItems(materials);
    }

    @FXML
    void add() {
        AddMaterial.controller = this;
        Modal.start(this.getClass(), "AddMaterial.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.deleteUrl = materialService.getEndPoint();
        DeleteConfirmation.selected = selectedMaterial.getId();
        DeleteConfirmation.controller = this;
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddMaterial.controller = this;
        AddMaterial.isEditingForm = true;
        Modal.start(this.getClass(), "AddMaterial.fxml");
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
        materials.remove(selectedMaterial);
        loadData();
    }

    @Override
    public void addData(Object object) {
        materials.add((Material) object);
        loadData();
    }

    @Override
    public void loadData() {
        materials = FXCollections.observableArrayList(materialService.getItems(null, null));
        tableTbl.setItems(materials);
    }
}
