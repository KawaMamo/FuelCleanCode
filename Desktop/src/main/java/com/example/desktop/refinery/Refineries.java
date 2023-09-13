package com.example.desktop.refinery;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.refinery.RefineryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.model.Refinery;

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

    }

    @FXML
    void add() {

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
    }

    @Override
    public void loadData() {
        refineries = FXCollections.observableArrayList(refineryService.getItems(null, null));
        tableTbl.setItems(refineries);
    }
}
