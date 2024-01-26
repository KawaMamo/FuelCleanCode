package com.example.desktop.refinery;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.refinery.RefineryService;
import com.example.model.tools.FormType;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Refinery;
import org.example.model.Region;
import org.example.model.Transportation;

import java.util.List;
import java.util.Objects;

public class Refineries implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<Refinery> tableTbl;
    private static ObservableList<Refinery> refineries;
    public static Refinery selectedRefinery;
    private final RefineryService refineryService = RefineryService.getInstance();
    private String query = null;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, Refinery, t1) -> selectedRefinery = t1);
    }

    private void setTable() {
        TableColumn<Refinery, String> idColumn = new TableColumn<>("التسلسل");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Refinery, String> nameColumn = new TableColumn<>("الاسم");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Refinery, String> translationColumn = new TableColumn<>("translation");
        translationColumn.setCellValueFactory(new PropertyValueFactory<>("translation"));

        TableColumn<Refinery, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(
                new PropertyValueFactory<>("createdAt"));

        TableColumn<Refinery, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(
                new PropertyValueFactory<>("updatedAt"));

        TableColumn<Refinery, String> regionCol = new TableColumn<>("region");
        regionCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRegion().getName()));

        tableTbl.getColumns().addAll(idColumn, nameColumn, translationColumn, createdAtCol, regionCol, updatedAtCol);
        tableTbl.setItems(refineries);
    }

    @FXML
    void add() {
        AddRefinery.controller = this;
        AddRefinery.formType = FormType.CREATE;
        Modal.start(this.getClass(), "addRefinery.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.deleteUrl = refineryService.getEndPoint();
        DeleteConfirmation.selected = selectedRefinery.getId();
        DeleteConfirmation.controller = this;
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddRefinery.controller = this;
        AddRefinery.formType = FormType.UPDATE;
        Modal.start(this.getClass(), "addRefinery.fxml");
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
        AddRefinery.controller = this;
        AddRefinery.formType = FormType.GET;
        Modal.start(this.getClass(), "addRefinery.fxml");
    }

    @Override
    public void removeData() {
        refineries.remove(selectedRefinery);
        loadData();
    }

    @Override
    public void addData(Object object) {
        refineries.add((Refinery) object);
        loadData();
    }

    @Override
    public void loadData() {
        final List<Refinery> items;
        if(Objects.isNull(query)){
            items = refineryService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        }else {
            items = refineryService.getItems(Integer.parseInt(page.getText()) - 1, 15, query);
        }

        if(Objects.nonNull(items))
            refineries = FXCollections.observableArrayList(items);
        else refineries = null;
        tableTbl.setItems(refineries);
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }
}
