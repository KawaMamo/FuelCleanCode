package com.example.desktop.material;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.material.MaterialService;
import com.example.model.modal.Modal;
import com.example.model.tools.FormType;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.model.Material;

import java.util.List;
import java.util.Objects;

public class Materials implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<Material> tableTbl;
    private static ObservableList<Material> materials;
    private final MaterialService materialService = MaterialService.getInstance();
    public static Material selectedMaterial;
    private String query = null;

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
        AddMaterial.formType = FormType.CREATE;
        Modal.start(this.getClass(), "addMaterial.fxml");
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
        AddMaterial.formType = FormType.UPDATE;
        Modal.start(this.getClass(), "addMaterial.fxml");
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
        AddMaterial.controller = this;
        AddMaterial.formType = FormType.GET;
        Modal.start(this.getClass(), "addMaterial.fxml");
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
        final List<Material> items;
        if(Objects.isNull(query)){
            items = materialService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        }else {
            items = materialService.getItems(Integer.parseInt(page.getText()) - 1, 15, query);
        }

        if(Objects.nonNull(items))
            materials = FXCollections.observableArrayList(items);
        else materials = null;
        tableTbl.setItems(materials);
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }
}
