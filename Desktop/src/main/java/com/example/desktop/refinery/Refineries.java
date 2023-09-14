package com.example.desktop.refinery;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.refinery.RefineryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Refinery;
import org.example.model.Region;

public class Refineries implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<Refinery> tableTbl;
    private static ObservableList<Refinery> refineries;
    public static Refinery selectedRefinery;
    private final RefineryService refineryService = RefineryService.getInstance();

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

        TableColumn<Refinery, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(
                new PropertyValueFactory<>("createdAt"));

        TableColumn<Refinery, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(
                new PropertyValueFactory<>("updatedAt"));
        tableTbl.getColumns().addAll(idColumn, nameColumn, createdAtCol, updatedAtCol);
        tableTbl.setItems(refineries);
    }

    @FXML
    void add() {
        AddRefinery.controller = this;
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
        AddRefinery.isEditingForm = true;
        Modal.start(this.getClass(), "addRefinery.fxml");
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
        refineries = FXCollections.observableArrayList(refineryService.getItems(null, null));
        tableTbl.setItems(refineries);
    }
}
