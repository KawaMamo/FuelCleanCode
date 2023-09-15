package com.example.desktop.transLine;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.transLine.TransLineService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.GasStation;
import org.example.model.Place;
import org.example.model.Refinery;
import org.example.model.TransLine;

import java.text.NumberFormat;

public class TransLines implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<TransLine> tableTbl;
    private static ObservableList<TransLine> transLines;
    public static TransLine selectedTransLine;

    private final TransLineService transLineService = TransLineService.getInstance();

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, GasStation, t1) -> selectedTransLine = t1);
    }

    @FXML
    void add() {
        AddTransLine.controller = this;
        AddTransLine.isEditingForm = false;
        Modal.start(this.getClass(), "addTransLine.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.controller = this;
        DeleteConfirmation.selected = selectedTransLine.getId();
        DeleteConfirmation.deleteUrl = transLineService.getEndPoint();
        Modal.start(TransLines.class, "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddTransLine.controller = this;
        AddTransLine.isEditingForm = true;
        Modal.start(this.getClass(), "addTransLine.fxml");
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
        transLines.remove(selectedTransLine);
        loadData();
    }

    @Override
    public void addData(Object object) {
        transLines.add((TransLine) object);
        loadData();
    }

    public void loadData() {
        transLines = FXCollections.observableArrayList(transLineService.getItems(null, null));
        tableTbl.setItems(transLines);
    }

    private void setTable() {
        TableColumn<TransLine, String> idCol = new TableColumn<>("التسلسل");
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        TableColumn<TransLine, String> sourceCol = new TableColumn<>("المصدر");
        sourceCol.setCellValueFactory(
                data-> {
                    final Place source = data.getValue().getSource();
                    if(source instanceof Refinery) {
                        return new SimpleStringProperty(((Refinery) source).getName());
                    }
                    else if (source instanceof GasStation)
                        return new SimpleStringProperty(((GasStation) source).getName());
                    else return null;
                });

        TableColumn<TransLine, String> destinationCol = new TableColumn<>("الوجهة");
        destinationCol.setCellValueFactory(data -> {
            final Place source = data.getValue().getDestination();
            if(source instanceof Refinery) {
                return new SimpleStringProperty(((Refinery) source).getName());
            }
            else if (source instanceof GasStation)
                return new SimpleStringProperty(((GasStation) source).getName());
            else return null;
        });

        TableColumn<TransLine, String> feeCol = new TableColumn<>("الكلفة");
        feeCol.setCellValueFactory(
                data-> new SimpleStringProperty(
                        NumberFormat.getInstance().format(data.getValue().getFee().getAmount())+" "+data.getValue().getFee().getCurrency()));

        TableColumn<TransLine, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(
                new PropertyValueFactory<>("createdAt"));

        TableColumn<TransLine, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(
                new PropertyValueFactory<>("updatedAt"));

        tableTbl.getColumns().addAll(idCol, sourceCol, destinationCol, feeCol, createdAtCol, updatedAtCol);
        tableTbl.setItems(transLines);
    }
}
